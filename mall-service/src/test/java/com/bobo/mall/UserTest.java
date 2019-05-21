package com.bobo.mall;

import com.bobo.mall.api.entity.User;
import com.bobo.mall.service.impl.UserService;
import org.junit.Test;

import javax.annotation.Resource;

public class UserTest extends BaseTest {

    @Resource
    private UserService userService;

    @Test
    public void getById() {
        User user = userService.getUserById(1);
        System.out.println(user);
    }


}
