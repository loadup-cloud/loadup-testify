package com.github.loadup.testify.core.base;

/*-
 * #%L
 * LoadUp Testify - Core
 * %%
 * Copyright (C) 2026 LoadUp Cloud
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.github.loadup.testify.core.engine.TestExecutionEngine;
import com.github.loadup.testify.core.loader.TestCaseLoader;
import com.github.loadup.testify.core.model.PrepareData;
import java.lang.reflect.Field;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Base class for data-driven tests using Testify. Extends
 * AbstractTestNGSpringContextTests for
 * Spring Boot integration with TestNG.
 *
 * <p>
 * Directory structure convention:
 *
 * <pre>
 * |- UserServiceTest.java                 (test class)
 * ├── UserService.createUser/             (method directory: ServiceName.methodName)
 * │   ├── case01/                         (case directory)
 * │   │   ├── test_config.yaml            (test configuration)
 * │   │   ├── PrepareData/                (CSV files for database setup)
 * │   │   └── ExpectedData/               (CSV files for database assertions)
 * │   └── case02/
 * ├── UserService.createUserWithRole/
 * │   └── case01/
 * </pre>
 *
 * <p>
 * Use the {@link TestBean} annotation to mark the service/bean under test:
 *
 * <pre>
 * &#64;TestBean
 * &#64;Autowired
 * private UserService userService;
 * </pre>
 */
@lombok.extern.slf4j.Slf4j
public abstract class TestifyTestBase extends AbstractTestNGSpringContextTests {

  @Autowired
  protected TestExecutionEngine testExecutionEngine;

  @Autowired
  protected TestCaseLoader testCaseLoader;

  private Object cachedTestBean;

  /**
   * Run a test case. This method should be called from within the test method.
   *
   * @param caseId      the case ID
   * @param prepareData the prepared test data
   */
  protected void runTest(String caseId, PrepareData prepareData) {
    String methodName = prepareData.getMethodName();
    String serviceName = prepareData.getServiceName();

    // Pass all information needed for path resolution and method invocation
    testExecutionEngine.runTest(
        getClass(), getTestBean(), serviceName, methodName, caseId, prepareData);
  }

  /**
   * Run a test case with a specific method name.
   *
   * @param caseId      the case ID
   * @param prepareData the prepared test data
   * @param methodName  the target method name to invoke
   */
  protected void runTest(String caseId, PrepareData prepareData, String methodName) {
    String serviceName = prepareData.getServiceName();
    testExecutionEngine.runTest(
        getClass(), getTestBean(), serviceName, methodName, caseId, prepareData);
  }

  /**
   * Get the test bean instance. Uses implicit detection validation or falls back
   * to overridden getTestBean() method.
   *
   * @return the test bean instance
   */
  protected Object getTestBean() {
    if (cachedTestBean != null) {
      return cachedTestBean;
    }

    // 1. Implicit detection: Look for field matching the inferred service name
    String className = getClass().getSimpleName();
    if (className.endsWith("Test")) {
      String expectedServiceName = className.substring(0, className.length() - 4);
      // Check current class fields
      for (Field field : getClass().getDeclaredFields()) {
        if (field.getType().getSimpleName().equals(expectedServiceName)) {
          try {
            field.setAccessible(true);
            cachedTestBean = field.get(this);
            log.debug("implicitly detected test bean from field: {}", field.getName());
            return cachedTestBean;
          } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to access implicitly detected field: " + field.getName(), e);
          }
        }
      }
      // Check parent class (abstract base tests)
      Class<?> superClass = getClass().getSuperclass();
      while (superClass != null && superClass != TestifyTestBase.class) {
        for (Field field : superClass.getDeclaredFields()) {
          if (field.getType().getSimpleName().equals(expectedServiceName)) {
            try {
              field.setAccessible(true);
              cachedTestBean = field.get(this);
              return cachedTestBean;
            } catch (IllegalAccessException e) {
              throw new RuntimeException("Failed to access implicitly detected field in parent: " + field.getName(), e);
            }
          }
        }
        superClass = superClass.getSuperclass();
      }
    }

    // 2. Fallback: Return this (the test class itself)
    cachedTestBean = this;
    return cachedTestBean;
  }

  /** Hook for setup actions before each test method. */
  @BeforeMethod
  protected void beforeTestMethod() {
    // Override in subclass if needed
  }

  /** Hook for cleanup actions after each test method. */
  @AfterMethod
  protected void afterTestMethod() {
    // Override in subclass if needed
  }

}
