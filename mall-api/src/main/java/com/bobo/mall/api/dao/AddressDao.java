package com.bobo.mall.api.dao;

import com.bobo.mall.api.dao.mapper.AddressMapper;
import com.bobo.mall.api.entity.Address;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class AddressDao {

    @Resource
    private AddressMapper addressMapper;

    public Address getAddressById(int id) {
        return addressMapper.getAddressById(id);
    }

    public int addAddress(Address address) {
        return addressMapper.insert(address);
    }


}
