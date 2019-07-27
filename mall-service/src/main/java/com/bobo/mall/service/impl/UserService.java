package com.bobo.mall.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bobo.mall.api.dao.UserDao;
import com.bobo.mall.api.entity.User;
import com.bobo.mall.api.service.IUserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Service
@Component
public class UserService implements IUserService {

    @Resource
    private UserDao userDao;

    @Override
    @Cacheable(cacheNames = "user", key = "'user-id:' + #id", unless = "#result == null ")
    public User getUserById(int id) {
        System.out.println(" search db ... userId = " + id);
        return userDao.getUserById(id);
    }

    @Override
    @Cacheable(cacheNames = "user", key = "'userList'")
    public List<User> getUserList() {
        System.out.println(" search db ... ");
        return userDao.getUserList();
    }

    @Override
    @Cacheable(cacheNames = "default", key = "'user-name:' + #name")
    public User getUserByName(String name) {
        System.out.println(" search db ... ");
        return userDao.getUserByName(name);
    }

    @Override
    @CacheEvict(cacheNames = "commonCache", key = "'userList'")
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    /**
     * 注意: @CachePut 会将执行结果(方法返回值)以键值对的形式放入指定缓存中, 所以返回值不能是 int
     */
    @Override
    @CachePut(cacheNames = "commonCache", key = "'user-id:' + #user.id")
    public User updateUser(User user) {
        System.out.println(" search db ... ");
        return userDao.updateUser(user);
    }

    @Override
    @CacheEvict(cacheNames = "commonCache", key = "'user-id:' + #id")
    public int deleteUser(int id) {
        return userDao.deleteUserById(id);
    }


    @Override
    @CacheEvict(cacheNames = "user", allEntries = true)
    public void deleteUserCache() {
        System.out.println("清除缓存成功 ... ");
    }






}
