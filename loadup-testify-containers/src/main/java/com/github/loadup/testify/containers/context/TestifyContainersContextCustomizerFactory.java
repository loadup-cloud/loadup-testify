package com.github.loadup.testify.containers.context;

import com.github.loadup.testify.containers.annotation.EnableContainer;
import com.github.loadup.testify.containers.annotation.EnableContainers;
import java.util.List;
import java.util.Set;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.ContextCustomizerFactory;

/** Factory for creating {@link TestifyContainersContextCustomizer}. */
public class TestifyContainersContextCustomizerFactory implements ContextCustomizerFactory {

  @Override
  public ContextCustomizer createContextCustomizer(
      Class<?> testClass, List<ContextConfigurationAttributes> configAttributes) {
    Set<EnableContainer> annotations =
        AnnotatedElementUtils.getMergedRepeatableAnnotations(
            testClass, EnableContainer.class, EnableContainers.class);

    if (annotations.isEmpty()) {
      return null;
    }

    return new TestifyContainersContextCustomizer(annotations);
  }
}
