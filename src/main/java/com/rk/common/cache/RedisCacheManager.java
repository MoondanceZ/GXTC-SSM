package com.rk.common.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Qin_Yikai on 2018-10-10.
 */
@Component
public class RedisCacheManager {
    @Autowired
    private JedisPool jedisPool;
    private Jedis jedis;

    public Jedis getJedis() {
        if (jedis == null) {
            jedis = jedisPool.getResource();
        }
        return jedis;
    }

}
