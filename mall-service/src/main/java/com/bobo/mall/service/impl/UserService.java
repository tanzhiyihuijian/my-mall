package com.bobo.mall.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bobo.mall.api.dao.UserDao;
import com.bobo.mall.api.entity.User;
import com.bobo.mall.api.service.IUserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Service
@Component
public class UserService implements IUserService {

    @Resource
    private UserDao userDao;

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }




}
