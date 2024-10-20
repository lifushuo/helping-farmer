package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShoppingCartVO {
    private Long spuId;

    private String skuName;

    private double price;


    private String picture;

    private Long skuId;

    private Double count;


    private double storeCount;

    private Boolean is_selected;
    private Integer xunzhong;

    private String attrsText;


}
