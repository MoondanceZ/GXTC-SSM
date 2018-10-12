package com.rk.common.cache;

import com.rk.common.cache.Impl.RedisCacheImpl;
import com.rk.util.JsonUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-10-10.
 */
public class RedisCache implements Cache {
    private static final Logger logger = LogManager.getLogger(RedisCacheImpl.class);
    /*
        @Autowired
        private JedisPool jedisPool;*/
    private Jedis jedis;
/*
    public Jedis getJedis() {
        if (jedis == null) {
            jedis = jedisPool.getResource();
        }
        return jedis;
    }*/

    public RedisCache(JedisPool jedisPool) {
        jedis = jedisPool.getResource();
    }

    /**
     * 存入List集合中
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public long lpush(String key, String value) {
        try {
            long result = jedis.lpush(key, value);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Jedis lpush 异常 ：" + e.getMessage());
            return 0;
        }
    }

    /**
     * 获取指定值
     *
     * @param timeout
     * @param key
     * @return
     */
    @Override
    public List<String> brpop(int timeout, String key) {
        try {
            return jedis.brpop(timeout, key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Jedis brpop 异常 ：" + e.getMessage());
            return null;
        }
    }

    /**
     * 给Redis中Set集合中某个key值设值
     *
     * @param key
     * @param value
     */
    @Override
    public void set(String key, String value) {
        try {
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Jedis set 异常" + e.getMessage());
        }
    }

    /**
     * 给Redis中Set集合中某个key值设值 过期时间
     *
     * @param key
     * @param seconds
     * @param value
     */
    @Override
    public void setex(String key, int seconds, String value) {
        try {
            jedis.setex(key, seconds, value);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Jedis set 异常" + e.getMessage());
        }
    }

    /**
     * 从Redis中Set集合中获取key对应value值
     *
     * @param key
     */
    @Override
    public String get(String key) {
        try {
            return jedis.get(key);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Jedis get 异常" + e.getMessage());
            return null;
        }
    }

    /**
     * 序列化
     *
     * @param key
     * @param t
     */
    @Override
    public <T> void setObject(String key, T t) {
        set(key, JsonUtils.Serialize(t));
    }

    @Override
    public <T> void setexObject(String key, int seconds, T t) {
        setex(key, seconds, JsonUtils.Serialize(t));
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz) {

        String value = get(key);
        if (value != null) {
            return JsonUtils.Deserialize(value, clazz);
        }
        return null;
    }

}
