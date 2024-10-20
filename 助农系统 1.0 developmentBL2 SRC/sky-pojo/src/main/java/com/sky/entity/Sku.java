package com.sky.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Sku)实体类
 *
 * @author makejava
 * @since 2024-05-19 17:12:07
 */
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sku implements Serializable {
    private static final long serialVersionUID = -24988860170899209L;
/**
     * 主键

     */
    private Integer id;
/**
     * sku名字

     */
    private String skuName;
/**
     * skuid

     */
    private Integer spuId;
/**
     * 具体价格

     */
    private Double price;
/**
     * 原价

     */
    private Double oldPrice;
/**
     * 库存

     */
    private Integer storeCount;
/**
     * 图片

     */
    private String picture;
/**
     * 是否有效（1有效0无效）

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


}

