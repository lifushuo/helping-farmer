package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailVO {
    /**
     * 订单详情id
     */
    public Long id;

    /**
     * skuID
     */
    public Long skuId;
    /**
     * sku名字
     */
    public String skuName;
    /**
     * sku原价
     */
    public Double skuOldPrice;
    /**
     * sku图片
     */
    public String skuPicture;
    /**
     * sku价格
     */
    public Double skuPrice;
    /**
     * spu折扣（比例）
     */
    public Double spuDiscount;
    /**
     * spuId
     */
    public Long spuId;
    /**
     * spu名称
     */
    public String spuName;

    /**
     * sku属性数组
     */
    public List<SkuAttrVO> skuAttrs;
}
