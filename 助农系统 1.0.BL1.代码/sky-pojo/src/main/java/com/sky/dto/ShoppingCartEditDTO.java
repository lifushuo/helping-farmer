package com.sky.dto;

import lombok.Data;

@Data
public class ShoppingCartEditDTO {
    /**
     * 数量
     */
    private Long count;
    /**
     * 1被选中，0未被选
     */
    private Long selected;

}