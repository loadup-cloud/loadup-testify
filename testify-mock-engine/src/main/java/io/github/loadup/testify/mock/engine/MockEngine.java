package io.github.loadup.testify.mock.engine;

import io.github.loadup.testify.core.model.MockConfig;
import io.github.loadup.testify.data.engine.variable.VariableEngine;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;

/** Mock 引擎：负责解析配置并向 AOP 拦截器注册 Mock 规则 */
@Slf4j
public class MockEngine {
  private final ApplicationContext applicationContext;
  private final VariableEngine variableEngine;
  private final MockInterceptor mockInterceptor;

  public MockEngine(
      ApplicationContext applicationContext,
      VariableEngine variableEngine,
      MockInterceptor mockInterceptor) {
    this.applicationContext = applicationContext;
    this.variableEngine = variableEngine;
    this.mockInterceptor = mockInterceptor;
  }

  /** 应用 Mock 配置 */
  public void applyMocks(List<MockConfig> configs, Map<String, Object> resolvedVars) {
    if (configs == null || configs.isEmpty()) return;

    for (MockConfig config : configs) {
      try {
        applySingleMock(config, resolvedVars);
      } catch (Exception e) {
        log.error(">>> [ENGINE] Failed to apply mock for bean: {}", config.bean(), e);
        throw new RuntimeException("Mock setup failed for bean: " + config.bean(), e);
      }
    }
  }

  private void applySingleMock(MockConfig config, Map<String, Object> context) throws Exception {
    String beanName = config.bean();

    // 1. 从容器获取 Bean（此时该 Bean 已被 TestifyMockProxyCreator 代理）
    Object bean = applicationContext.getBean(beanName);

    // 2. 自动探测方法的返回类型（修复了 Optional 泛型问题）
    Class<?> returnType = getMethodReturnType(bean, config);

    // 3. 解析 Mock 匹配条件中的参数变量
    List<Object> expectedArgs = null;
    if (config.args() != null) {
      // 解析 YAML 中的 args，如 [ "${userId}", "any" ]
      Object resolvedArgs = variableEngine.resolveValue(config.args(), context);
      if (resolvedArgs instanceof List) {
        expectedArgs = (List<Object>) resolvedArgs;
      }
    }

    // 4. 将元数据注册到 AOP 拦截器
    // 核心逻辑：不在这里做返回值转换，只存原始配置，确保拦截时动态解析变量
    mockInterceptor.registerMock(
        beanName,
        config.method(),
        expectedArgs,
        config.thenReturn(), // 可能是对象、Map 或包含表达式的 String
        config.thenThrow(), // 异常类名
        returnType, // 运行时转换的目标类型
        context, // 保持当前的变量上下文
        config.delay());

    log.info(
        ">>> [ENGINE] Mock registered: {}.{} (TargetType: {})",
        beanName,
        config.method(),
        returnType.getSimpleName());
  }

  /** 获取目标方法的返回类型，支持重载识别 */
  private Class<?> getMethodReturnType(Object bean, MockConfig config) {
    // 穿透所有代理，获取真实的业务类
    Class<?> targetClass = AopUtils.getTargetClass(bean);

    // 获取所有同名方法
    Method[] methods =
        Arrays.stream(targetClass.getMethods())
            .filter(m -> m.getName().equals(config.method()))
            .toArray(Method[]::new);

    if (methods.length == 0) {
      throw new NoSuchMethodError(
          "No method named '" + config.method() + "' in " + targetClass.getName());
    }

    // 策略 1：如果指定了 args，按参数数量精准匹配（最常见场景）
    if (config.args() != null) {
      int argCount = ((List<?>) config.args()).size();
      for (Method m : methods) {
        if (m.getParameterCount() == argCount) {
          return m.getReturnType();
        }
      }
    }

    // 策略 2：如果没有 args 或者没搜到匹配数量的方法，检查是否有重载
    if (methods.length > 1) {
      long distinctTypes = Arrays.stream(methods).map(Method::getReturnType).distinct().count();
      if (distinctTypes > 1) {
        log.warn(
            ">>> [ENGINE] Ambiguous return type for method '{}'. Using first found: {}",
            config.method(),
            methods[0].getReturnType().getSimpleName());
      }
    }

    // 默认返回第一个同名方法的返回类型
    return methods[0].getReturnType();
  }

  /** 测试结束后的清理工作 */
  public void resetAllMocks() {
    // 遍历所有规则，找出 hit == false 的
    mockInterceptor
        .getMockRules()
        .forEach(
            (bean, methodMap) -> {
              methodMap.forEach(
                  (method, rules) -> {
                    for (int i = 0; i < rules.size(); i++) {
                      if (!rules.get(i).isHit()) {
                        log.warn(
                            ">>> [TESTIFY-AUDIT] 警告: Mock 规则未触发! Bean: {}, Method: {}, Index: {}, Args: {}",
                            bean,
                            method,
                            i,
                            rules.get(i).getExpectedArgs());
                      }
                    }
                  });
            });
    mockInterceptor.clear();
  }
}
