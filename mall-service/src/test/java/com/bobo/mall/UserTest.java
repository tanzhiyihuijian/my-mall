package com.bobo.mall;

import com.bobo.mall.api.entity.User;
import com.bobo.mall.api.service.IUserService;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UserTest extends BaseTest {

    @Resource
    private IUserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    public void getById() {
        User user = userService.getUserById(1);
        System.out.println(user);
    }

    @Test
    public void testJedis() {

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("123456");

        String name = jedis.get("name");
        System.out.println(name);


        // list
        jedis.rpush("list1", "aaa");
        jedis.rpush("list1", "bb");
        jedis.rpush("list1", "b");

        List<String> list = jedis.lrange("list1", 0, -1);
        System.out.println("list: " + list);


        // set
        jedis.sadd("s1", "AA");
        jedis.sadd("s1", "B");
        jedis.sadd("s1", "ZX");

        Set<String> set = jedis.smembers("s1");
        System.out.println("set: " + set);

        // zset
        jedis.zadd("zs", 100, "张三3");
        jedis.zadd("zs", 95, "张三1");
        jedis.zadd("zs", 102, "张三2");

        Set<String> zs = jedis.zrange("zs", 0, -1);
        System.out.println("zset: " + zs);

        // hash
        Map<String, String> map = new HashMap<>();
        map.put("name", "tom");
        map.put("age", "11");
        map.put("phone", "021-12345678");

        jedis.hmset("myHash", map);
        List<String> v = jedis.hmget("myHash", "name", "age");
        System.out.println(v);

        List<String> v2 = jedis.hmget("myHash", "phone");
        System.out.println(v2);

        jedis.hdel("myHash", "phone");

    }


    @Test
    public void testRedisTx() {

        // 事务控制


        // 事务方式: 主要的目的是保障, 一个client发起的事务中的命令可以连续的执行, 而中间不会插入其他client的命令

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.auth("123456");

        Transaction tx = jedis.multi();
        for (int i = 0; i < 9999; i++) {
            tx.set("keyttt" + i, "valttt" + i);
        }

        List<Object> res = tx.exec();

        jedis.disconnect();

    }


    @Test
    public void testCache() {

//        User user = userService.getUserByName("bobo");
//        User user = userService.getUserById(1);

       /* User user = new User().setName("xiaohong").setAge(26)
                .setEmail("tom@163.com").setPassword("123456")
                .setPhone("188-8888-8888")
                .setCreateTime(new Date()).setUpdateTime(new Date());
        userService.addUser(user);

        System.out.println("user: " + user);*/


//        List<User> userList = userService.getUserList();
//        System.out.println(userList);

        userService.getUserById(2);
        userService.getUserById(11);

        User oldUser = userService.getUserById(1);
        System.out.println("oldUser: " + oldUser);

        userService.deleteUser(1);
        User user = userService.getUserById(1);
        System.out.println("user: " + user);

    }











}
