package com.sky.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * AddressBook
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressBookPostDto {
    /**
     * 所在城市的编码
     */
    private Long cityCode;
    /**
     * 所在城市的名字
     */
    private String cityName;
    /**
     * 收货人姓名
     */
    private String receiver;
    /**
     * 电话号码，手机号
     */
    private String contact;
    /**
     * 详细地址，详细地址
     */
    private String address;
    /**
     * 所在区的编码
     */
    private Long districtCode;
    /**
     * 所在区的名字
     */
    private String districtName;
    /**
     * 是否为默认地址
     */
    private Long isDefault;
    /**
     * 标签 默认是1
     */
    private Long label;
    /**
     * 所在省份的编码
     */
    private Long provinceCode;
    /**
     * 所在省份的名字
     */
    private String provinceName;


}
