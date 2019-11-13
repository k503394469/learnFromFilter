package com.liu.test;

import com.liu.learn.utils.JedisUtils;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.*;

public class JedisTest {
    Jedis jedis=new Jedis("127.0.0.1",6379);
    /**
     * String类型
     */
    @Test
    public void t1() throws Exception{
        jedis.set("name","zs");
        String name = jedis.get("name");
        System.out.println(name);
        jedis.setex("age",1,"23");
        Thread.sleep(2000);
        System.out.println(jedis.get("age"));//null
        jedis.close();
    }

    /**
     * hash
     * @throws Exception
     */
    @Test
    public void t2() throws Exception{
        jedis.hset("user","name","zs");
        jedis.hset("user","age","23");
        jedis.hset("user","gender","male");
        Map<String, String> user = jedis.hgetAll("user");
        Set<String> strings = user.keySet();
        for (String key:strings){
            System.out.println(key+":"+user.get(key));
        }
        jedis.close();
    }
    /**
     * list集合
     */
    @Test
    public void t3() throws Exception{
        jedis.del("jlist");
        jedis.lpush("jlist","1","2","3");
        jedis.rpush("jlist","1","2","3");
        List<String> jlist = jedis.lrange("jlist", 0, -1);
        System.out.println(jlist);
        for (int i = 0; i < jlist.size()-3; i++) {
            System.out.println(jedis.lpop("jlist"));;
        }
        List<String> jlist2 = jedis.lrange("jlist", 0, -1);
        System.out.println(jlist2);
        jedis.close();
    }
    /**
     * set&sortedset
     */
    @Test
    public void t4() throws Exception{
        jedis.del("jset");
        jedis.sadd("jset","1","2","3");
        System.out.println(jedis.smembers("jset"));
        jedis.del("jsortset");
        jedis.zadd("jsortset",3.2,"aaa");
        jedis.zadd("jsortset",2.5,"bbb");
        System.out.println(jedis.zrange("jsortset",0,-1));
        jedis.close();
    }
    /**
     * 连接池
     */
    @Test
    public void t5() throws Exception{
        JedisPool pool = new JedisPool();
        jedis=pool.getResource();
        jedis.del("jset");
        jedis.sadd("jset","1","2","3");
        System.out.println(jedis.smembers("jset"));
        jedis.del("jsortset");
        jedis.zadd("jsortset",3.2,"aaa");
        jedis.zadd("jsortset",2.5,"bbb");
        System.out.println(jedis.zrange("jsortset",0,-1));
        jedis.close();
    }
    /**
     * 工具类使用
     */
    @Test
    public void t6() throws Exception{
        Jedis jedis = JedisUtils.getJedis();
        jedis.del("jset");
        jedis.sadd("jset","1","2","3");
        System.out.println(jedis.smembers("jset"));
        jedis.del("jsortset");
        jedis.zadd("jsortset",3.2,"aaa");
        jedis.zadd("jsortset",2.5,"bbb");
        System.out.println(jedis.zrange("jsortset",0,-1));
        jedis.close();
    }
}
