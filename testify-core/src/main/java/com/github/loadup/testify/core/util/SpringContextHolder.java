package com.github.loadup.testify.core.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class SpringContextHolder implements ApplicationContextAware {
  private static ApplicationContext context;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) {
    context = applicationContext;
  }

  public static <T> T getBean(Class<T> clazz) {
    Assert.notNull(context, "SpringContextHolder.context is null. Is Spring Context started?");
    return context.getBean(clazz);
  }
}
