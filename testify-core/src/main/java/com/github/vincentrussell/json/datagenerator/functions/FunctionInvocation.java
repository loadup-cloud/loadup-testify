package com.github.vincentrussell.json.datagenerator.functions;

import java.lang.annotation.*;

/**
 * helper annotation to register methods on a function class
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface FunctionInvocation {
}
