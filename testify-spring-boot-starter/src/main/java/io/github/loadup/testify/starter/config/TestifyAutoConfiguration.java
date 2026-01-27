package io.github.loadup.testify.starter.config;

import io.github.loadup.testify.asserts.engine.DbAssertEngine;
import io.github.loadup.testify.asserts.engine.ExceptionAssertEngine;
import io.github.loadup.testify.asserts.engine.ResponseAssertEngine;
import io.github.loadup.testify.asserts.engine.TestifyAssertEngine;
import io.github.loadup.testify.asserts.facade.AssertionFacade;
import io.github.loadup.testify.data.engine.db.SqlExecutionEngine;
import io.github.loadup.testify.data.engine.function.CommonFunction;
import io.github.loadup.testify.data.engine.function.TestifyFunction;
import io.github.loadup.testify.data.engine.function.TimeFunction;
import io.github.loadup.testify.data.engine.variable.VariableEngine;
import io.github.loadup.testify.mock.MockProxyPostProcessor;
import io.github.loadup.testify.mock.engine.MockEngine;
import io.github.loadup.testify.mock.engine.MockInterceptor;
import io.github.loadup.testify.starter.util.SpringContextHolder;
import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Auto-configuration for Testify framework. Configures Testcontainers or physical database based on
 * properties.
 */
@Configuration
@EnableConfigurationProperties(TestifyProperties.class)
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class TestifyAutoConfiguration {

  @Bean
  @ConditionalOnMissingBean
  public SpringContextHolder springContextHolder() {
    return new SpringContextHolder();
  }

  @Bean
  public TimeFunction timeFunction() {
    return new TimeFunction();
  }

  @Bean
  public CommonFunction commonFunction() {
    return new CommonFunction();
  }

  /** 1. 自动收集容器中所有的 TestifyFunction 实现 */
  @Bean
  @ConditionalOnMissingBean
  public VariableEngine variableEngine(List<TestifyFunction> functions) {
    return new VariableEngine(functions);
  }

  @Bean
  @ConditionalOnMissingBean
  public ResponseAssertEngine responseAssertEngine(VariableEngine variableEngine) {
    return new ResponseAssertEngine(variableEngine);
  }

  @Bean
  @ConditionalOnMissingBean
  public DbAssertEngine dbAssertEngine(JdbcTemplate jdbcTemplate, VariableEngine variableEngine) {
    return new DbAssertEngine(jdbcTemplate, variableEngine);
  }

  @Bean
  @ConditionalOnMissingBean
  public ExceptionAssertEngine exceptionAssertEngine( VariableEngine variableEngine) {
    return new ExceptionAssertEngine(variableEngine);
  }

  @Bean
  @ConditionalOnMissingBean
  public AssertionFacade assertionFacade(List<TestifyAssertEngine> engines) {
    return new AssertionFacade(engines);
  }

  @Bean
  @ConditionalOnMissingBean
  public SqlExecutionEngine sqlExecutionEngine(
      JdbcTemplate jdbcTemplate, VariableEngine variableEngine) {
    return new SqlExecutionEngine(jdbcTemplate, variableEngine);
  }

  @Bean
  @ConditionalOnMissingBean
  public MockInterceptor mockInterceptor(VariableEngine variableEngine) {
    return new MockInterceptor(variableEngine);
  }

  @Bean
  @ConditionalOnMissingBean
  public MockEngine mockEngine(
      ApplicationContext applicationContext,
      VariableEngine variableEngine,
      MockInterceptor mockInterceptor) {
    return new MockEngine(applicationContext, variableEngine, mockInterceptor);
  }

  /** 定义一个切面，只拦截符合条件的业务类 */
  @Bean
  public MockProxyPostProcessor testifyMockAdvisor(MockInterceptor interceptor) {
    return new MockProxyPostProcessor(interceptor);
  }
}
