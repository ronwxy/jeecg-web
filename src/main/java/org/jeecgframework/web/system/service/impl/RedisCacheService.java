package org.jeecgframework.web.system.service.impl;

import freemarker.template.Template;
import org.jeecgframework.web.system.service.CacheServiceI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.Set;

@Service
public class RedisCacheService implements CacheServiceI {
    private static final Logger log = LoggerFactory.getLogger(RedisCacheService.class);

    @Resource
    private RedisTemplate redisTemplate;

    //序列化
    private static byte[] serialize(Object obj) {
        ObjectOutputStream obi = null;
        ByteArrayOutputStream bai = null;
        try {
            bai = new ByteArrayOutputStream();
            obi = new ObjectOutputStream(bai);
            obi.writeObject(obj);
            byte[] byt = bai.toByteArray();
            return byt;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //反序列化
    private static Object unserizlize(byte[] byt) {
        ObjectInputStream oii = null;
        ByteArrayInputStream bis = null;
        bis = new ByteArrayInputStream(byt);
        try {
            oii = new ObjectInputStream(bis);
            Object obj = oii.readObject();
            return obj;
        } catch (Exception e) {

            e.printStackTrace();
        }


        return null;
    }

    @Override
    public Object get(String cacheName, Object key) {
        Object obj = redisTemplate.boundValueOps(cacheName + "_" + key).get();
        log.debug("  RedisCacheService  get cacheName: [{}] , key: [{}]", cacheName, key);
        if (obj != null && obj instanceof byte[]) {
            obj = unserizlize((byte[]) obj);
            return obj;
        }
        return obj;
    }

    @Override
    public void put(String cacheName, Object key, Object value) {
        log.debug("  RedisCacheService  put cacheName: [{}] , key: [{}]", cacheName, key);
        if (value instanceof Template) {
            redisTemplate.boundValueOps(cacheName + "_" + key).set(serialize(value));
        } else {
            redisTemplate.boundValueOps(cacheName + "_" + key).set(value);
        }
    }

    @Override
    public boolean remove(String cacheName, Object key) {
        log.debug("  RedisCacheService  remove cacheName: [{}] , key: [{}]", cacheName, key);
        if (redisTemplate.hasKey(cacheName + "_" + key)) {
            redisTemplate.delete(cacheName + "_" + key);
            return true;
        }
        return false;
    }

    @Override
    public void clean() {
        log.debug("  RedisCacheService  clean  all  ");
        Set eternalKeys = redisTemplate.keys(SYSTEM_BASE_CACHE + "*");
        Set tagKeys = redisTemplate.keys(TAG_CACHE + "*");
        if (eternalKeys != null && !eternalKeys.isEmpty()) {
            redisTemplate.delete(eternalKeys);
        }
        if (tagKeys != null && !tagKeys.isEmpty()) {
            redisTemplate.delete(tagKeys);
        }
    }

    @Override
    public void clean(String cacheName) {
        log.info("  RedisCacheService  clean cacheName：[{}] ", cacheName);
        Set dictKeys = redisTemplate.keys(cacheName + "*");
        if (dictKeys != null && !dictKeys.isEmpty()) {
            redisTemplate.delete(dictKeys);
        }
    }

}
