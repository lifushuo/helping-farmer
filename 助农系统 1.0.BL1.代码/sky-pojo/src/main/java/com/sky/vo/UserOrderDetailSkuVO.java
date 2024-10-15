package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderDetailSkuVO {
    /**
     * 订单详细的ID
     */
    private long id;

    /**
     * spuID
     */
    private Integer spuId;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品属性文字，这个茶sku表的信息
     */
    private String attrsText;

    /**
     * sku的商品价格
     */
    private Double curPrice;

    /**
     * sku图片 一张
     */
    private String image;


    /**
     * 购买的商品数量
     */
    private Integer quantity;


}