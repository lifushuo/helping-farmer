package com.sky.vo;

import com.sky.entity.GoodsDetails;
import com.sky.entity.GoodsSkus;
import com.sky.entity.GoodsSpecs;
import com.sky.entity.GoodsUserAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsDetailVO {
    /**
     * spuId
     */
    private Integer id;/**
     * 商品名称
     */
    private String name;
    /**
     * 商品描述
     */
    private String desc;/**
     * 当前价格
     */
    private Double price; /**
     * 原价
     */
    private Double oldPrice;/**
     * spu的图片
     */
    private List<String> mainPictures;

    private GoodsDetails details;


    private List<GoodsSkus> skus;
    /**
     * 属性数组，这也是属性不是规格！！！！
     */
    private List<GoodsSpecs> specs;
    /**
     * ** 用户地址列表[ 地址信息 ]
     */

    private List<GoodsUserAddress> userAddresses;
}
