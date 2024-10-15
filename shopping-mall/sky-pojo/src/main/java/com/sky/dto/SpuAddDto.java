// Request.java

package com.sky.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SPUDTO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpuAddDto {
    /**
     * 分类2ID
     */
    private long category2Id;
    /**
     * 描述
     */
    private String description;
    /**
     * 折扣（0-1），折扣（比例）
     */
    private Double discount;
    /**
     * 原价
     */
    private double oldPrice;
    /**
     * 图片数组，图片地址数组
     */
    private String[] picture;
    /**
     * 具体价格
     */
    private double price;
    /**
     * 商品规格
     */
    private SPUEditSpecificationDTO[] specifications;
    /**
     * spu名称，spu名称
     */
    private String spuName;
    /**
     * 库存，库存
     */
    private long storeCount;


}


