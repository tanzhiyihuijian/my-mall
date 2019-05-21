package com.bobo.mall.mvc.controller;

import com.bobo.mall.api.entity.User;
import com.bobo.mall.api.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource(name = "IUserService")
    private IUserService userService;


    @GetMapping("/#{id}")
    public User getUserList(@PathVariable("id") int id) {
        User user = userService.getUserById(id);
        return user;
    }



}
