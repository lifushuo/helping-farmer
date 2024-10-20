package com.sky.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SKUDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class  SkuAddDto{
    private long[] attributes;
    /**
     * 原价
     */
    private String oldPrice;
    /**
     * 图片
     */
    private String picture;
    /**
     * sku价格
     */
    private String price;
    /**
     * 自己定义的sku名字
     */
    private String skuName;
    /**
     * spuId
     */
    private String spuId;
    /**
     * 库存
     */
    private String storeCount;


}