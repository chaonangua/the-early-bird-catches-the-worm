package com.chenhao.jedis;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.*;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 连接池
 * Created by 陈浩 on 2020/3/11.
 */
public class jedisPool {
    @Test
    public void pool(){
        //创建一个配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大数量
        config.setMaxTotal(50);
        //设置最大连接数量
        config.setMaxIdle(10);

        //1.创建Jedis连接池对象
        JedisPool jedisPool = new JedisPool(config,"localhost",6379);
        //2.获取连接对象
        Jedis jedis = jedisPool.getResource();
        //3.使用
        jedis.set("hehhe","hheh");

        jedis.close();//关闭，归还到连接池
    }
}
