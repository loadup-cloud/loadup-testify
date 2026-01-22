package com.github.loadup.testify.mock.engine;

import com.github.loadup.testify.core.util.JsonUtil;
import com.github.loadup.testify.data.engine.variable.VariableEngine;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.AopUtils;
import org.springframework.util.StringUtils;

/** 核心拦截器：实现 AOP 方法拦截、多规则参数匹配及动态变量解析 */
@Slf4j
public class MockInterceptor implements MethodInterceptor {

  private final VariableEngine variableEngine;

  /** 存储结构：Map<BeanName, Map<MethodName, List<MockRule>>> 一个方法可以对应多个 Rule（不同参数对应不同返回值） */
  private final Map<String, Map<String, List<MockRule>>> mockRules = new ConcurrentHashMap<>();

  public MockInterceptor(VariableEngine variableEngine) {
    this.variableEngine = variableEngine;
  }

  /** 注册 Mock 规则 */
  public void registerMock(
      String beanName,
      String methodName,
      List<Object> expectedArgs,
      Object returnValue,
      String throwEx,
      Class<?> returnType,
      Map<String, Object> context) {

    mockRules
        .computeIfAbsent(beanName, k -> new ConcurrentHashMap<>())
        .computeIfAbsent(methodName, k -> new ArrayList<>())
        .add(new MockRule(expectedArgs, returnValue, throwEx, returnType, context));

    log.info(
        ">>> [TESTIFY-REGISTRY] Registered Mock: {}.{} (Args: {})",
        beanName,
        methodName,
        expectedArgs == null ? "ANY" : expectedArgs);
  }

  public void clear() {
    mockRules.clear();
  }

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    // 快速路径：无规则直接放行
    if (mockRules.isEmpty()) {
      return invocation.proceed();
    }

    String beanName = resolveBeanName(invocation.getThis());
    String methodName = invocation.getMethod().getName();
    Object[] actualArgs = invocation.getArguments();

    Map<String, List<MockRule>> beanRules = mockRules.get(beanName);
    if (beanRules != null) {
      List<MockRule> rules = beanRules.get(methodName);
      if (rules != null) {
        // 遍历规则，寻找匹配当前入参的 Rule
        for (MockRule rule : rules) {
          if (isArgsMatch(rule, actualArgs)) {
            return executeMockAction(rule);
          }
        }
      }
    }

    // 未命中任何 Mock 规则，执行原始逻辑（或交给开发人员手动 Mockito 处理）
    return invocation.proceed();
  }

  /** 参数匹配核心算法 */

  // 修改后的匹配方法
  private boolean isArgsMatch(MockRule rule, Object[] actualArgs) {
    List<Object> rawExpectedArgs = rule.getExpectedArgs();

    // 1. YAML 没配 args，代表匹配任意参数
    if (rawExpectedArgs == null) {
      return true;
    }

    // 2. 参数数量不等
    if (rawExpectedArgs.size() != actualArgs.length) {
      return false;
    }

    // 3. 【核心修复】解析预期参数中的变量
    // 例如将 ["${userId}"] 解析为 [123]
    List<Object> resolvedExpectedArgs =
        (List<Object>) variableEngine.resolveValue(rawExpectedArgs, rule.getContext());

    // 4. 逐个比对
    for (int i = 0; i < resolvedExpectedArgs.size(); i++) {
      Object expected = resolvedExpectedArgs.get(i);
      Object actual = actualArgs[i];

      if ("any".equals(expected)) continue;
      if (expected == null && actual == null) continue;

      if (expected != null) {
        // 强化比对：处理类型不一致（Integer vs Long）
        if (!expected.equals(actual)) {
          String expStr = JsonUtil.toJson(expected);
          String actStr = JsonUtil.toJson(actual);
          if (!expStr.equals(actStr)) return false;
        }
      } else {
        return false;
      }
    }
    return true;
  }

  /** 执行 Mock 结果：解析变量并处理类型转换 */
  private Object executeMockAction(MockRule rule) throws Throwable {
    // A. 异常 Mock
    if (StringUtils.hasText(rule.getThrowEx())) {
      log.info(">>> [TESTIFY-HIT] Mock Throwing: {}", rule.getThrowEx());
      throw (Throwable)
          Class.forName(rule.getThrowEx())
              .getConstructor(String.class)
              .newInstance("Testify Mock Exception");
    }

    // B. 返回值 Mock
    Object rawReturn = rule.getReturnValue();
    // 关键：调用 VariableEngine 执行动态解析（支持 now() 等函数）
    Object resolvedValue = variableEngine.resolveValue(rawReturn, rule.getContext());

    // 类型强制转换：确保从 Map/String 转换为方法要求的真实类型
    if (resolvedValue != null
        && rule.getReturnType() != null
        && !rule.getReturnType().isAssignableFrom(resolvedValue.getClass())) {

      log.debug(">>> [TESTIFY-AOP] Converting result to type: {}", rule.getReturnType().getName());
      resolvedValue = JsonUtil.convertValue(resolvedValue, rule.getReturnType());
    }

    log.info(">>> [TESTIFY-HIT] Mock Returning: {}", resolvedValue);
    return resolvedValue;
  }

  private String resolveBeanName(Object instance) {
    // 穿透代理获取原始类名并转为首字母小写
    Class<?> targetClass = AopUtils.getTargetClass(instance);
    String simpleName = targetClass.getSimpleName();
    return Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
  }

  @Data
  @AllArgsConstructor
  private static class MockRule {
    private List<Object> expectedArgs; // 预期参数条件
    private Object returnValue; // 原始返回配置
    private String throwEx; // 异常配置
    private Class<?> returnType; // 方法返回类型
    private Map<String, Object> context; // 变量上下文
  }
}
