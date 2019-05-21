package com.bobo.mall.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.bobo.mall.api.dao.AddressDao;
import com.bobo.mall.api.entity.Address;
import com.bobo.mall.api.service.IAddressService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Service
@Component
public class AddressService implements IAddressService {

    @Resource
    private AddressDao addressDao;

    @Override
    public Address getAddressById(int id) {
        return addressDao.getAddressById(id);
    }

    @Override
    public int addAddress(Address address) {
        return addressDao.addAddress(address);
    }
}
