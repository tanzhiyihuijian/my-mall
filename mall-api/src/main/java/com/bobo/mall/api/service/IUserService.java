package com.bobo.mall.api.service;

import com.bobo.mall.api.entity.User;

import java.util.List;

public interface IUserService {

    User getUserById(int id);

    List<User> getUserList();

    User getUserByName(String name);

    int addUser(User user);

    User updateUser(User user);

    int deleteUser(int id);

}
