package com.weixin.Utils;

import com.weixin.commons.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;


@Component
public class TokenBlack {
    @Autowired
    StringRedisTemplate redisTemplate;

    public void addBlacklist(String token) {
        redisTemplate.opsForValue().set(RedisKey.TokenBlack + token,"token");
    }

    public Boolean checkBlacklist(String token) {
        return redisTemplate.hasKey(RedisKey.TokenBlack + token);
    }

}
