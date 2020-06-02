package com.chenhao.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 连接池工具类
 * 加载配置文件，配置连接吃参数
 * 提供获取链接的方法
 *
 */
public class PoolUtil {

    private static JedisPool jedisPool;

    static {
        //读取配置文件
        InputStream is = PoolUtil.class.getClassLoader().getResourceAsStream("jedis.properties");
        //创建Properti对象
        Properties pro = new Properties();
        //关联文件
        try {
            pro.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取数据，设置到JedisPoolConfig中
        JedisPoolConfig config = new JedisPoolConfig();
        //获取最大连接数
        config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
        //初始化JedisPool
        jedisPool = new JedisPool(config,pro.getProperty("host"),Integer.parseInt("port"));

    }
    public static Jedis getJedis() {

        return jedisPool.getResource();
    }
}
