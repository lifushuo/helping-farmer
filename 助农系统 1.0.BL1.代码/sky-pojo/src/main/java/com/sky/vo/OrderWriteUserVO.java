package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderWriteUserVO {
    /**
     * 详细地址
     */
    private String address;
    /**
     * 城市编码
     */
    private Integer cityCode;
    /**
     * 联系方式 手机号
     */
    private String contact;
    /**
     * 所在区/县编码
     */
    private Integer countyCode;
    /**
     * 省市区的字符组合
     */
    private String fullLocation;
    /**
     * 地址的id
     */
    private Integer id;
    /**
     * 是否为默认地址
     */
    private Integer isDefault;
    /**
     * 省份编码
     */
    private Integer provinceCode;
    /**
     * 收件人
     */
    private String receiver;
}
