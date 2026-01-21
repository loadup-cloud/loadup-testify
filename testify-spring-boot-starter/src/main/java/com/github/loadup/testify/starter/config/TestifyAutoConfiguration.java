package com.github.loadup.testify.starter.config;

import com.github.loadup.testify.asserts.engine.DbAssertEngine;
import com.github.loadup.testify.asserts.engine.ExceptionAssertEngine;
import com.github.loadup.testify.asserts.engine.ResponseAssertEngine;
import com.github.loadup.testify.asserts.engine.TestifyAssertEngine;
import com.github.loadup.testify.asserts.facade.AssertionFacade;
import com.github.loadup.testify.starter.util.SpringContextHolder;
import com.github.loadup.testify.data.engine.db.SqlExecutionEngine;
import com.github.loadup.testify.data.engine.function.CommonFunction;
import com.github.loadup.testify.data.engine.function.TestifyFunction;
import com.github.loadup.testify.data.engine.function.TimeFunction;
import com.github.loadup.testify.data.engine.variable.VariableEngine;
import com.github.loadup.testify.starter.container.TestifyContainerManager;
import com.github.loadup.testify.starter.db.DbConnectionProvider;
import com.github.loadup.testify.starter.db.PhysicalDbConnectionProvider;
import java.util.List;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Auto-configuration for Testify framework. Configures Testcontainers or physical database based on
 * properties.
 */
@Configuration
@EnableConfigurationProperties(TestifyProperties.class)
public class TestifyAutoConfiguration {

  @Bean
  @ConditionalOnProperty(prefix = "testify.containers", name = "enabled", havingValue = "true")
  public TestifyContainerManager containerManager() {
    // Initialize Testcontainers
    return new TestifyContainerManager();
  }

  @Bean
  @ConditionalOnMissingBean
  public DbConnectionProvider physicalDbProvider() {
    // Default: use physical database from properties
    return new PhysicalDbConnectionProvider();
  }

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
  public DbAssertEngine dbAssertEngine(JdbcTemplate jdbcTemplate,VariableEngine variableEngine) {
    return new DbAssertEngine(jdbcTemplate,variableEngine);
  }

  @Bean
  @ConditionalOnMissingBean
  public ExceptionAssertEngine exceptionAssertEngine() {
    return new ExceptionAssertEngine();
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
}
