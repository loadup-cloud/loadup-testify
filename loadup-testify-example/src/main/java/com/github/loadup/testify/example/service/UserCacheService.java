package com.github.loadup.testify.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCacheService {

  private final StringRedisTemplate redisTemplate;

  public void cacheUser(String userId, String userData) {
    redisTemplate.opsForValue().set("user:" + userId, userData);
  }

  public String getUser(String userId) {
    return redisTemplate.opsForValue().get("user:" + userId);
  }
}
