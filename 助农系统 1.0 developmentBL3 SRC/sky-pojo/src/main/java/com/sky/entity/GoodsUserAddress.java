package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class GoodsUserAddress {
    /**
     * 地址的id
     */
    private Integer id;
    /**
     * 收货人姓名
     */
    private String receiver;
    /**
     * 联系方式
     */
    private String contact;
    /**
     * 省市区
     */
    private String fullLocation;
    /**
     * 详细地址
     */
    private String address;
    /**
     * 是否为默认地址
     */
    private String isDefault;

}
