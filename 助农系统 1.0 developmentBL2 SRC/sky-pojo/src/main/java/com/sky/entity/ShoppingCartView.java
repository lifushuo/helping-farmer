package com.sky.entity;

import java.io.Serializable;
import lombok.*;
/**
 * (ShoppingCartView)实体类
 *
 * @author makejava
 * @since 2024-05-19 17:11:58
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartView implements Serializable {
    private static final long serialVersionUID = 285711988071281282L;
/**
     * 主键

     */
    private Integer id;
/**
     * skuid

     */
    private Integer skuId;
/**
     * spuid

     */
    private Integer spuId;
/**
     * 商品名称

     */
    private String skuName;
/**
     * 用户id

     */
    private Integer userId;
/**
     * 是否选中

     */
    private String isSelected;
/**
     * 数量

     */
    private Integer count;
/**
     * 折扣(比例)

     */
    private Double discount;
/**
     * 原价

     */
    private Double oldPrice;
/**
     * 具体价格

     */
    private Double price;
/**
     * 具体属性值（字符数组）

     */
    private String skuValue;
/**
     * 是否有效（1有效0无效）

     */
    private String isEffective;
/**
     * sku库存

     */
    private String skuStoreCount;
/**
     * 是否收藏

     */
    private String isCollected;


}

