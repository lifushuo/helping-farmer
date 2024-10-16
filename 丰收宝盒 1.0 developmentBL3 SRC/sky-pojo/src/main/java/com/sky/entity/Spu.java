package com.sky.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Spu)实体类
 *
 * @author makejava
 * @since 2024-05-19 17:12:31
 */
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Spu implements Serializable {
    private static final long serialVersionUID = -39159175664474956L;
/**
     * 主键

     */
    private Integer id;
/**
     * spu名称

     */
    private String spuName;
/**
     * 原价

     */
    private Double price;
/**
     * 折扣（比例）

     */
    private Double discount;
/**
     * 库存

     */
    private Integer storeCount;
/**
     * 销量 默认为0

     */
    private Integer salesCount;
/**
     * 图片集合待定？

     */
    private String picture;
/**
     * 商品详情描述

     */
    private String description;
/**
     * 是否有效（1有效0无效）默认是有效

     */
    private String isEffective;
/**
     * 创建时间

     */
    private Date createTime;
/**
     * 更新时间

     */
    private Date updateTime;
/**
     * 是否删除（1删除0未删除）

     */
    private String isDeleted;
    /**
     * 二级分类id
     */
    private Integer category2Id;

}

