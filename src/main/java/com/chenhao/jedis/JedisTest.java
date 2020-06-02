package com.chenhao.jedis;

import org.testng.annotations.Test;
import redis.clients.jedis.Jedis;


import java.util.List;
import java.util.Map;
import java.util.Set;


public class JedisTest {

    /**
     * String 数据结构操作
     */
    @Test
    public void test1() {
        //获取连接
        Jedis jedis = new Jedis("localhost",6379);//如果使用空参构造器，默认值“localhost”
                                                                              // ，端口：6379
        //操作，存储
        jedis.set("username","chenaho");
        //获取
        String username = jedis.get("username");
        System.out.println(username);
        //可以使用setex()方法存储，可以指定过期时间的key-value
        jedis.setex("activecode",20,"hehe");//将activecode：hehe键值存入redis，并且
                                                                //20秒后自动删除该键值对
        //关闭连接
        jedis.close();
    }

    /**
     * hash 数据结构操作
     */
    @Test
    public  void  test2() {
        //获取连接
        Jedis jedis = new Jedis();
        //操作
        jedis.hset("user","name","lisi");
        jedis.hset("user","age","23");
        jedis.hset("user","gender","male");

        //获取hash
        String name = jedis.hget("user","name");
        System.out.println(name);

        //获取hash的所有map的数据
        Map<String,String> user=jedis.hgetAll("user");
        System.out.println(user);
    }

    /**
     * list 操作
     */
    @Test
    public void test3() {
        //1.获取连接
        Jedis jedis = new Jedis();
        //2.操作
        //存储：lpush :从左边添加到列表；rpush：从右边添加到列表
        jedis.lpush("mylist","a","b","c");
        jedis.rpush("mylist","A","B","C");

        //范围获取
        List<String> mylist = jedis.lrange("mylist",0,-1);//获取所有
        System.out.println(mylist);

        //弹出 lpop :从左边弹出；rpop：从右边弹出
        String lpop = jedis.lpop("mylist");
        String roop = jedis.rpop("mylist");
        System.out.println(lpop + roop);

        //关闭
        jedis.close();
    }


    /**
     * set 不允许重复操作
     */
    @Test
    public  void  test4(){
        Jedis jedis = new Jedis();
        // 操作
        //添加
        jedis.sadd("set","java","php");
        //获取
        Set<String> set = jedis.smembers("set");
        System.out.println(set);

        jedis.close();

    }

    /***
     * sotedset :不重复，有序
     */
    @Test
    public  void test5() {
        Jedis jedis = new Jedis();
        //操作
        //存储
        jedis.zadd("sortset",3,"monkey");
        jedis.zadd("sortset",2,"tiger");
        jedis.zadd("sortset",5,"lion");
        //获取
        Set<String> set = jedis.zrange("sortset",0,-1);
        System.out.println(set);

        jedis.close();
    }
}