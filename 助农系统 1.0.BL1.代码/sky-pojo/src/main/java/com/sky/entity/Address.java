package com.sky.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Address)实体类
 *
 * @author makejava
 * @since 2024-05-21 10:18:08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName
public class Address implements Serializable {
    private static final long serialVersionUID = -96590206818378917L;
/**
     * 主键

     */
    private Integer id;
/**
     * 用户id

     */
    private Integer userId;
/**
     * 收货人

     */
    private String consignee;
/**
     * 性别

     */
    private String sex;
/**
     * 手机号

     */
    private String phone;
/**
     * 省级区划编号

     */
    private String provinceCode;
/**
     * 省级名称

     */
    private String provinceName;
/**
     * 市级区划编号

     */
    private String cityCode;
/**
     * 市级名称

     */
    private String cityName;
/**
     * 区级区划编号

     */
    private String districtCode;
/**
     * 区级名称

     */
    private String districtName;
/**
     * 详细地址

     */
    private String detail;
/**
     * 标签

     */
    private String label;
/**
     * 默认 0 否 1是

     */
    private String isDefault;
/**
     * 是否删除（1删除0未删除）

     */
    private String isDeleted;


}

