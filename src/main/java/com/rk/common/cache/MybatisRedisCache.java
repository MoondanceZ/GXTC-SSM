package com.rk.common.cache;

import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.rk.util.JsonUtils;
import org.apache.ibatis.cache.Cache;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import redis.clients.jedis.Jedis;

/**
 * 使用第三方内存数据库Redis作为二级缓存
 * Created by Qin_Yikai on 2018-10-10.
 */
public class MybatisRedisCache implements Cache {
    private static Logger LOGGER = LogManager.getLogger(MybatisRedisCache.class);
    private final String id;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static JedisConnectionFactory jedisConnectionFactory;

    /**
     * 这个地方需要静态注入，这里通过中间类 MybatisRedisCacheTransfer 实现的
     *
     * @param jedisConnectionFactory
     */
    static void setJedisConnectionFactory(JedisConnectionFactory jedisConnectionFactory) {
        MybatisRedisCache.jedisConnectionFactory = jedisConnectionFactory;
    }

    public MybatisRedisCache(final String id) {
        if (null == id || "".equals(id)) {
            throw new IllegalArgumentException("mybatis redis cache need an id.");
        }
        this.id = id;
    }


    @Override
    public String getId() {
        return this.id;
    }

    /**
     * 存值
     *
     * @param key
     * @param value
     */
    @Override
    public void putObject(Object key, Object value) {
        if (null == key) {
            return;
        }
        JedisConnection redisConnection = null;
        try {
            redisConnection = jedisConnectionFactory.getConnection();
            JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();
            Jedis jedis = getJedis(redisConnection);
            jedis.set(serializer.serialize(key), serializer.serialize(value));
            // 将key保存到redis.list中
            jedis.lpush(serializer.serialize(id), serializer.serialize(key));
            //redisConnection.lPush(serializer.serialize(id), serializer.serialize(key));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("mybatis redis cache put exception. K=" + key + " V=" + value + "", e);
        } finally {
            if (null != redisConnection) {
                redisConnection.close();
            }
        }
    }

    /**
     * 取值
     *
     * @param key
     * @return
     */
    @Override
    public Object getObject(Object key) {
        if (null == key) {
            return null;
        }
        JedisConnection redisConnection = null;
        Object result = null;
        try {
            redisConnection = jedisConnectionFactory.getConnection();
            JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();
            result = serializer.deserialize(redisConnection.get(serializer.serialize(key)));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("mybatis redis cache get exception. K=" + key + " V=" + result + "", e);
        } finally {
            if (null != redisConnection) {
                redisConnection.close();
            }
        }
        return result;
    }

    /**
     * 删值
     *
     * @param key
     * @return
     */
    @Override
    public Object removeObject(Object key) {
        if (null == key) {
            return null;
        }
        RedisConnection redisConnection = null;
        Object result = null;
        try {
            redisConnection = jedisConnectionFactory.getConnection();
            JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();

            // 讲key设置为立即过期
            result = redisConnection.expireAt(serializer.serialize(key), 0);

            // 将key从redis.list中删除
            redisConnection.lRem(serializer.serialize(id), 0, serializer.serialize(key));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("mybatis redis cache remove exception. " + key + " V=" + result + "", e);
        } finally {
            if (null != redisConnection) {
                redisConnection.close();
            }
        }
        return result;
    }

    /**
     * 清空缓存
     * flushCache="true" 的时候会调用这个地方
     */
    @Override
    public void clear() {
        JedisConnection redisConnection = null;
        try {
            redisConnection = jedisConnectionFactory.getConnection();

            /**
             * 千万不要直接 redisConnection.flushDb()，会把整个redis的东西都清除掉，我不相信你的redis里没有其他东西
             * 获取redis.list中的保存的key值，遍历删除
             */

            JdkSerializationRedisSerializer serializer = new JdkSerializationRedisSerializer();
            Long length = redisConnection.lLen(serializer.serialize(id));
            if (0 == length) {
                return;
            }
            List<byte[]> keyList = redisConnection.lRange(serializer.serialize(id), 0, length - 1);
            for (byte[] key : keyList) {
                redisConnection.expireAt(key, 0);
            }
            redisConnection.expireAt(serializer.serialize(id), 0);
            keyList.clear();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("mybatis redis cache clear exception. ", e);
        } finally {
            if (null != redisConnection) {
                redisConnection.close();
            }
        }
    }

    @Override
    public int getSize() {
        int result = 0;
        try {
            RedisConnection redisConnection = jedisConnectionFactory.getConnection();
            result = Math.toIntExact(redisConnection.dbSize());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("mybatis redis cache getSize exception. V=" + result + "", e);
        }
        return result;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return this.readWriteLock;
    }

    private Jedis getJedis(JedisConnection jedisConnection) {
        return jedisConnection.getNativeConnection();
    }
}