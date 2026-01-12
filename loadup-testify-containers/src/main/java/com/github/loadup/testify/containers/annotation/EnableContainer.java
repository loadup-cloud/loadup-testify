package com.github.loadup.testify.containers.annotation;

import java.lang.annotation.*;
import org.testcontainers.containers.GenericContainer;

/**
 * Annotation to enable and configure a Testcontainer for the test class.
 *
 * <p>Example:
 *
 * <pre>
 * @EnableContainer(MySQLContainer.class)
 * public class UserServiceTest { ... }
 * </pre>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(EnableContainers.class)
@Documented
public @interface EnableContainer {

  /** The container class to instantiate (must have a no-arg constructor or be a supported type). */
  Class<? extends GenericContainer> value();

  /**
   * Whether to reuse the container across test classes (Singleton pattern). Defaults to true for
   * performance.
   */
  boolean shared() default true;

  /** Custom image name to use (overrides the container class default). */
  String image() default "";
}
