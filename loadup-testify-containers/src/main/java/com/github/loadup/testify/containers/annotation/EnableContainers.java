package com.github.loadup.testify.containers.annotation;

import java.lang.annotation.*;

/** Container annotation for @EnableContainer. */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnableContainers {
  EnableContainer[] value();
}
