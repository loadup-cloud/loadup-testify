package com.github.loadup.testify.core.annotation;

import com.github.loadup.testify.core.provider.TestifyDataProvider;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.testng.annotations.Test;

/**
 * Annotation for Testify data-driven tests. Automatically wires up the
 * TestifyDataProvider.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Test(dataProvider = "TestifyProvider", dataProviderClass = TestifyDataProvider.class)
public @interface TestifyTest {
}
