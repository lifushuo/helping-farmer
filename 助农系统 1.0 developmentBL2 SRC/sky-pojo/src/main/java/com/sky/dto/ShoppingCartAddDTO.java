package com.sky.dto;

import lombok.Data;

/**
 * ShoppingCartDTO
 */
@Data
public class ShoppingCartAddDTO {
    /**
     * 数量
     */
    private long count;
    /**
     * skuId
     */
    private long skuId;

}