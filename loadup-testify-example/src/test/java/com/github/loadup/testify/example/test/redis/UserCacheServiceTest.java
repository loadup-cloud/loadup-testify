package com.github.loadup.testify.example.test.redis;

import com.github.loadup.testify.containers.annotation.EnableContainer;
import com.github.loadup.testify.core.annotation.TestifyTest;
import com.github.loadup.testify.example.service.UserCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testcontainers.containers.GenericContainer;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest
@EnableContainer(value = GenericContainer.class, image = "redis:7.0-alpine")
public class UserCacheServiceTest extends AbstractTestNGSpringContextTests {

  @Autowired private UserCacheService userCacheService;

  @TestifyTest
  public void cacheUser(String userId, String userData) {
    userCacheService.cacheUser(userId, userData);
    String cached = userCacheService.getUser(userId);
    Assert.assertEquals(cached, userData);
  }

  @Test
  public void testManual() {
    userCacheService.cacheUser("manual", "data");
    Assert.assertEquals(userCacheService.getUser("manual"), "data");
  }
}
