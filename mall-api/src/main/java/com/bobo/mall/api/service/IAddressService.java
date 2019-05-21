package com.bobo.mall.api.service;

import com.bobo.mall.api.entity.Address;

public interface IAddressService {

    Address getAddressById(int id);

    int addAddress(Address address);

}
