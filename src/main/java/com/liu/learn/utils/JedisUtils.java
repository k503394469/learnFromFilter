package com.liu.learn.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.InputStream;
import java.util.Properties;

/**
 * 工具类
 */
public class JedisUtils {
    private static JedisPool pool;

    static {
        InputStream jedisPro = JedisUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
        Properties properties = new Properties();
        try {
            properties.load(jedisPro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(Integer.parseInt(properties.getProperty("maxIdle")));
        config.setMaxTotal(Integer.parseInt(properties.getProperty("maxTotal")));
        pool = new JedisPool(config, properties.getProperty("host"), Integer.parseInt(properties.getProperty("port")));
    }

    public static Jedis getJedis() {
        return pool.getResource();
    }

}
