package io.github.loadup.testify.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface TestifyParam {
    /**
     * 对应 YAML 中 input 节点下的 Key 名字
     */
    String value();
}