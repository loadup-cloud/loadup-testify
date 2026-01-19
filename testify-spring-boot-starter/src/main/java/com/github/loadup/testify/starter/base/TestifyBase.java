package com.github.loadup.testify.starter.base;

import com.github.loadup.testify.asserts.engine.DbAssertEngine;
import com.github.loadup.testify.starter.config.TestifyProperties;
import com.github.loadup.testify.starter.sql.SqlExecutor;
import com.github.loadup.testify.starter.testng.TestifyListener;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;

import java.util.List;
import java.util.Map;

/**
 * Base class for Testify integration tests. Provides helper methods for database assertions and
 * common test operations.
 */
@Getter
@Setter
@Listeners(TestifyListener.class)
public abstract class TestifyBase extends AbstractTestNGSpringContextTests {

}
