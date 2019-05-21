package com.bobo.mall.api.dao.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bobo.mall.api.entity.Address;

public interface AddressMapper extends BaseMapper<Address> {

    Address getAddressById(int id);

}
