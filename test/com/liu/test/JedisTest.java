package com.liu.test;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    @Test
    public void t1(){
        Jedis jedis=new Jedis("127.0.0.1",6379);
        jedis.set("name","zs");
        String name = jedis.get("name");
        System.out.println(name);
        jedis.close();
    }
}
