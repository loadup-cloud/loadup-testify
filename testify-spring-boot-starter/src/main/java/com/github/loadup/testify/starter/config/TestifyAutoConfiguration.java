package com.github.loadup.testify.starter.config;

import com.github.loadup.testify.asserts.engine.DbAssertEngine;
import com.github.loadup.testify.asserts.engine.ResponseAssertEngine;
import com.github.loadup.testify.core.util.SpringContextHolder;
import com.github.loadup.testify.data.engine.db.SqlExecutionEngine;
import com.github.loadup.testify.data.engine.variable.VariableEngine;
import com.github.loadup.testify.starter.container.TestifyContainerManager;
import com.github.loadup.testify.starter.db.DbConnectionProvider;
import com.github.loadup.testify.starter.db.PhysicalDbConnectionProvider;
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
  @ConditionalOnMissingBean
  public VariableEngine testifyVariableEngine() {
    return new VariableEngine();
  }

  @Bean
  @ConditionalOnMissingBean
  public ResponseAssertEngine responseAssertEngine() {
    return new ResponseAssertEngine();
  }

  @Bean
  @ConditionalOnMissingBean
  public DbAssertEngine dbAssertEngine() {
    return new DbAssertEngine();
  }

  @Bean
  @ConditionalOnMissingBean
  public SqlExecutionEngine testifySqlExecutionEngine(
      JdbcTemplate jdbcTemplate, VariableEngine variableEngine) {
    return new SqlExecutionEngine(jdbcTemplate, variableEngine);
  }
}
