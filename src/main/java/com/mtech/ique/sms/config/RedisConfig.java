package com.mtech.ique.sms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

  @Bean
  public RedisTemplate<Long, Long> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<Long, Long> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory);
    // Add some specific configuration here. Key serializers, etc.
    return template;
  }
}
