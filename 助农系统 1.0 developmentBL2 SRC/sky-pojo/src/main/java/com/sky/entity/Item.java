package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    /**
     * 商品折扣，没有也没关系
     */
    private Double discount;
    /**
     * spuId
     */
    private Long id;
    /**
     * spu名字
     */
    private String name;
    /**
     * 下单数量
     */
    private Long orderNum;
    /**
     * spu图片，拿出来一张
     */
    private String picture;
    /**
     * 商品价格
     */
    private Double price;
}
