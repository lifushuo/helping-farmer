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
public class SpuEditDto {
    /**
     * 描述
     */
    private long description;
    /**
     * 折扣
     */
    private long discount;
    /**
     * 主键
     */
    private long id;
    /**
     * 原价
     */
    private String oldPrice;
    /**
     * 图片地址数组
     */
    private String[] picture;
    /**
     * 具体价格
     */
    private String price;
    /**
     * 规格
     */
    private SPUEditSpecificationDTO[] specifications;
    /**
     * spu名称
     */
    private String spuName;
    /**
     * 库存
     */
    private long storeCount;


}
