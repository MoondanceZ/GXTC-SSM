package com.rk.common.cache;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-10-11.
 */
public interface Cache {
    long lpush(String key, String value);
    List<String> brpop(int timeout, String key);
    void set(String key, String value);
    void setex(String key, int seconds, String value);
    String get(String key);
    <T> void setObject(String key, T t);
    <T> void setexObject(String key, int seconds, T t);
    <T> T getObject(String key, Class<T> clazz);

}
