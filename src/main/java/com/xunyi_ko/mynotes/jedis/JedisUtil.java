package com.xunyi_ko.mynotes.jedis;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


public class JedisUtil {
    @Autowired
    private RedisTemplate<String, String> template;

    
}
