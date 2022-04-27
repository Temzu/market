package com.temzu.market.corelib.services.impl;

import com.temzu.market.corelib.services.RedisService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.cache.CacheProperties.Redis;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl<T> implements RedisService<T> {

  private final RedisTemplate<String, T> redisTemplate;

  @Override
  public Optional<T> getOptional(String key) {
    return Optional.ofNullable(redisTemplate.opsForValue().get(key));
  }

  @Override
  public T get(String key) {
    return getOptional(key).orElseThrow(() -> ResourceNotFoundException.byUuid(key, Redis.class));
  }

  @Override
  public boolean exists(String key) {
    return Boolean.TRUE.equals(redisTemplate.hasKey(key));
  }

  @Override
  public void set(String key, T o) {
    redisTemplate.opsForValue().set(key, o);
  }
}
