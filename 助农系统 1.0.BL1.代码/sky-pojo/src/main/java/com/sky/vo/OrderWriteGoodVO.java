package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderWriteGoodVO {
    /**
     * 属性文字，例如“颜色:瓷白色 尺寸：8寸”，属性文字，例如“颜色:瓷白色 尺寸：8寸”
     * 这个要去查 属性表 然后把属性name和value拿出来 拼接成字符串
     */
    private String attrsText;
    /**
     * 数量
     */
    private Integer count;
    /**
     * sku名字
     */
    private String name;
    /**
     * 实付价 就是现价
     */
    private Double payPrice;
    /**
     * sku图片
     */
    private String picture;
    /**
     * 原价
     */
    private Double price;
    /**
     * skuID
     */
    private Integer skuId;
    /**
     * 实付价格小计
     */
    private Double totalPayPrice;
    /**
     * 小计 计算一下多少钱
     */
    private Double totalPrice;
}
