package com.bobo.mall.mvc.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bobo.mall.api.entity.User;
import com.bobo.mall.api.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private IUserService userService;

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        return user;
    }

    @GetMapping("/list")
    public User getUserList() {
        User user = userService.getUserById(1);
        return user;
    }



}
