package com.bobo.mall.mvc.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bobo.mall.api.entity.User;
import com.bobo.mall.api.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceWrapper {

    @Reference
    private IUserService userService;

    public User getUserById(int id) {
        return userService.getUserById(id);
    }

}
