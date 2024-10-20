package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsSkus {
    /**
     * skuID
     */
    private Integer id;
    /**
     * 库存
     */
    private Integer inventory;
    /**
     * 原价
     */
    private Double oldPrice;
    /**
     * 图片
     */
    private String picture;
    /**
     * 当前价格
     */
    private Double price;
    /**
     * 属性值Attr数组 不是规格！！！，属性值
     */
    private List<GoodsSkusSpecs> specs;
}
