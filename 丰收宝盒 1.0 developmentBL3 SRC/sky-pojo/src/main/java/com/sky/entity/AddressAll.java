package com.sky.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName
public class AddressAll {
    /**
     * 收获地址id
     */
    private long id;
    /**
     * 收货人姓名
     */
    private String receiver;
    /**
     * 电话号码，手机号
     */
    private String contact;
    /**
     * 所在省份的编码
     */
    private Long provinceCode;
    /**
     * 所在城市的编码
     */
    private Long cityCode;
    /**
     * 所在区的编码
     */
    private Long countyCode;
    /**
     * 完整的地址，将前面的省市区的名字进行拼接
     */
    private String fullLocation;
    /**
     * 是否为默认地址
     */
    private Integer isDefault;
    /**
     * 详细地址，详细地址
     */
    private String address;

    /**
     * 标签 默认是1
     */
    private Integer label;




}
