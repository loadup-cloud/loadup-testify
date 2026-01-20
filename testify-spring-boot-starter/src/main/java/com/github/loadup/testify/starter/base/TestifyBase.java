package com.github.loadup.testify.starter.base;

import com.github.loadup.testify.starter.config.TestifyAutoConfiguration;
import com.github.loadup.testify.starter.testng.TestifyListener;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;

/**
 * Base class for Testify integration tests. Provides helper methods for database assertions and
 * common test operations.
 */
@Getter
@Setter
@SpringBootTest
@ContextConfiguration(classes = TestifyAutoConfiguration.class)
@Listeners(TestifyListener.class)
public abstract class TestifyBase extends AbstractTestNGSpringContextTests {}
