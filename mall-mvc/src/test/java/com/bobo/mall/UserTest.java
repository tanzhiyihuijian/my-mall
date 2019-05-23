package com.bobo.mall;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bobo.mall.api.entity.Address;
import com.bobo.mall.api.entity.User;
import com.bobo.mall.api.service.IAddressService;
import com.bobo.mall.api.service.IUserService;
import com.bobo.mall.mvc.service.UserServiceWrapper;
import org.junit.Test;

import javax.annotation.Resource;

public class UserTest extends BaseTest {

    @Reference
    private IUserService userService;

    @Reference
    private IAddressService addressService;

    @Resource
    private UserServiceWrapper userServiceWrapper;

    @Test
    public void f1() {

        User user = userServiceWrapper.getUserById(1);
        System.out.println(user);


        Address address = new Address()
                .setProvince("北京市")
                .setCity("昌平区")
                .setRegion("回龙观")
                .setDetail("")
                .setPostcode("000001");

        int i = addressService.addAddress(address);
        if (i == 1) {
            System.out.println("添加成功!");
        }

        Address addr = addressService.getAddressById(1);
        System.out.println(addr);


    }



}
