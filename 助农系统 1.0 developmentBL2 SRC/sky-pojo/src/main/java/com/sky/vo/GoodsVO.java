package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoodsVO {
    /**
     * 描述
     */

    /**
     * spuId
     */
    private long id;
    /**
     * spu名称
     */
    private String name;


    private String description;
    /**
     * 已下单的数量
     */

    private String picture;

    private double price;

    private double orderNum;
    /**
     * spu图片，很多张 要一张就可
     */

}
