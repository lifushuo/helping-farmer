package com.sky.dto;

import lombok.Data;

/**
 * SKUDTO
 */
@Data
public class SkuEditDto {
    private long[] attributes;
    /**
     * skuId
     */
    private String id;
    private String oldPrice;
    private String picture;
    private String price;
    private String skuName;
    private String spuId;
    private String storeCount;
}
