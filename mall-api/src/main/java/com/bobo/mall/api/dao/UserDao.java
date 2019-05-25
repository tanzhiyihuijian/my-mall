package com.bobo.mall.api.dao;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bobo.mall.api.dao.mapper.UserMapper;
import com.bobo.mall.api.entity.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class UserDao {

    @Resource
    private UserMapper userMapper;

    public User getUserById(int id) {
        return userMapper.selectById(id);
    }

    public List<User> getUserList() {
        return userMapper.selectList(new EntityWrapper<>());
    }

    public User getUserByName(String name) {
        Wrapper<User> wrapper = new EntityWrapper<User>().eq("name", name);
        List<User> userList = userMapper.selectList(wrapper);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    public int addUser(User user) {
        return userMapper.insert(user);
    }

    public User updateUser(User user) {
        int res = userMapper.updateById(user);
        if (res == 1) {
            return user;
        }
        return userMapper.selectById(user.getId());
    }

    public int deleteUserById(int id) {
        return userMapper.deleteById(id);
    }
}
