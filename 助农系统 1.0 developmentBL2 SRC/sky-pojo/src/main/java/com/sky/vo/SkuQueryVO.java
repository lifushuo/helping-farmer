package com.sky.vo;

import com.sky.entity.SkuValue;
import com.sky.entity.SpuSpecification;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkuQueryVO {
    /**
     * skuid
     */
    private long id;
    /**
     * sku名字
     */
    private String skuName;
    /**
     * sku原价
     */
    private double skuOldPrice;
    /**
     * sku图片
     */
    private String skuPicture;
    /**
     * sku价格
     */
    private double skuPrice;
    /**
     * sku库存
     */
    private long skuStoreCount;
   
    /**
     * spu描述
     */
    private String spuDescription;
    /**
     * spu折扣
     */
    private double spuDiscount;
    /**
     * spuId
     */
    private long spuId;
    /**
     * spu名字
     */
    private String spuName;
    /**
     * sku属性
     */
    private List<SkuValue> skuValues;
    /**
     * spu规格
     */
    private List<SpuSpecification> spuSpecification;
}


