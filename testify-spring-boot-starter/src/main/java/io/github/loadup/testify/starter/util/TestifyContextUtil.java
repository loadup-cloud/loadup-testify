package io.github.loadup.testify.starter.util;

import java.lang.reflect.Method;
import org.testng.ITestResult;

public class TestifyContextUtil {
  public static String getContextKey(Method method) {
    return method.getDeclaringClass().getName() + "." + method.getName();
  }

  // 在 Listener 中对应的获取方式
  public static String getContextKey(ITestResult result) {
    return result.getMethod().getQualifiedName();
    // 注意：TestNG 的 getQualifiedName() 默认就是 "package.class.method"
  }
}
