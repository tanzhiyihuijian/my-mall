package com.bobo.mall.service.redis;

import lombok.Data;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.*;
import java.util.concurrent.Callable;


@Data
public class RedisCache implements Cache {


    private RedisTemplate<String, Object> redisTemplate;
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getNativeCache() {
        return redisTemplate;
    }

    @Override
    public ValueWrapper get(Object key) {
        final String keyf = String.valueOf(key);
        Object object = redisTemplate.execute((RedisCallback<Object>) connection -> {
            byte[] k = keyf.getBytes();
            byte[] value = connection.get(k);
            if (value == null || value.length == 0) {
                return null;
            }
            return toObject(value);
        });
        return (object != null ? new SimpleValueWrapper(object) : null);
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        final String keyf = String.valueOf(key);
        final Object valuef = value;
        final long liveTime = 86400;

        redisTemplate.execute((RedisCallback<Long>) connection -> {
            byte[] k = keyf.getBytes();
            byte[] v = toByteArray(valuef);
            connection.set(k, v);
            if (liveTime > 0) {
                connection.expire(k, liveTime);
            }
            return 1L;
        });
    }

    private byte[] toByteArray(Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    private Object toObject(byte[] bytes) {
        Object obj = null;
        try {
            ByteArrayInputStream  bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            ois.close();
            bis.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return obj;
    }


    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        return null;
    }

    @Override
    public void evict(Object key) {
        final String k = (String) key;
        redisTemplate.execute((RedisCallback<Long>) connection -> connection.del(k.getBytes()));
    }

    @Override
    public void clear() {
        redisTemplate.execute((RedisCallback<String>) connection -> {
            connection.flushDb();
            return "ok";
        });
    }
}
