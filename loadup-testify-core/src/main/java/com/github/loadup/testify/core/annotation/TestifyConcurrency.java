package com.github.loadup.testify.core.annotation;

import java.lang.annotation.*;

/**
 * Annotation to execute a test method concurrently.
 *
 * <p>This converts a standard data-driven test into a mini load test. Each thread will have its own
 * isolated SharedVariablePool and database preparation (if applicable).
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TestifyConcurrency {

  /** Number of concurrent threads to use. */
  int threads() default 1;

  /** Number of iterations per thread. */
  int iterations() default 1;

  /** Ramp-up period in milliseconds. */
  long rampUp() default 0;
}
