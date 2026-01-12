package com.github.loadup.testify.core.provider;

import com.github.loadup.testify.core.loader.TestCaseLoader;
import com.github.loadup.testify.core.model.PrepareData;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestContextManager;
import org.testng.annotations.DataProvider;

@Slf4j
public class TestifyDataProvider {

  @DataProvider(name = "TestifyProvider")
  public static Iterator<Object[]> provideData(Method method) {
    Class<?> testClass = method.getDeclaringClass();
    String normalizedMethodName = stripTestPrefix(method.getName());
    String serviceName = getServiceName(testClass);

    try {
      // Use TestContextManager to get the ApplicationContext associated with the test
      TestContextManager testContextManager = new TestContextManager(testClass);
      // We don't need to prepare the instance here, just access the context.
      testContextManager.prepareTestInstance(testClass.getDeclaredConstructor().newInstance());
      ApplicationContext context = testContextManager.getTestContext().getApplicationContext();

      TestCaseLoader loader = context.getBean(TestCaseLoader.class);

      List<PrepareData> testCases = loader.loadTestCasesForMethod(testClass, serviceName, normalizedMethodName);

      return testCases.stream()
          .filter(PrepareData::isLoaded)
          .filter(pd -> pd.getConfig() == null || pd.getConfig().isEnabled())
          .map(pd -> loader.convertArgs(pd, method, testClass))
          .iterator();

    } catch (Exception e) {
      log.error("Failed to provide data for method {}", method.getName(), e);
      throw new RuntimeException("Failed to provide test data", e);
    }
  }

  /**
   * Get the service name from the test class.
   *
   * @param testClass the test class
   * @return the service name
   */
  public static String getServiceName(Class<?> testClass) {
    // Implicit detection: infer from class name
    String className = testClass.getSimpleName();
    if (className.endsWith("Test")) {
      return className.substring(0, className.length() - 4);
    }
    return className;
  }

  /**
   * Strip "test" prefix from method name if present.
   *
   * @param methodName the original method name
   * @return the method name with "test" prefix removed
   */
  public static String stripTestPrefix(String methodName) {
    if (methodName != null
        && methodName.length() > 4
        && methodName.toLowerCase().startsWith("test")) {
      String withoutPrefix = methodName.substring(4);
      if (!withoutPrefix.isEmpty()) {
        return Character.toLowerCase(withoutPrefix.charAt(0))
            + (withoutPrefix.length() > 1 ? withoutPrefix.substring(1) : "");
      }
    }
    return methodName;
  }
}
