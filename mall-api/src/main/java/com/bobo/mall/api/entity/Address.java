package com.bobo.mall.api.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@TableName("address")
@Accessors(chain = true)
public class Address implements Serializable {

    private static final long serialVersionUID = 2L;

    @TableId(value = "id", type = IdType.AUTO)
    private int id;
    private String province;
    private String city;
    private String region;
    private String postcode;
    private String detail;

}
