package com.sky.vo;

import lombok.Data;

import java.util.List;

@Data
public class SkuQueryForEditVO {
    /**
     * 属性数组
     */
    private List<Long> attributes;
    /**
     * skuid
     */
    private long id;
    /**
     * 原价
     */
    private double oldPrice;
    /**
     * 图片
     */
    private String picture;
    /**
     * 现价
     */
    private double price;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * 库存
     */
    private double storeCount;
}
