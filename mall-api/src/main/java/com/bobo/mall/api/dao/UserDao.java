package com.bobo.mall.api.dao;

import com.bobo.mall.api.dao.mapper.UserMapper;
import com.bobo.mall.api.entity.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class UserDao {

    @Resource
    private UserMapper userMapper;

    public User getUserById(int id) {
        return userMapper.selectById(id);
    }


}
