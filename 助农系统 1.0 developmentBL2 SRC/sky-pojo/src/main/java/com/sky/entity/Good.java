package com.sky.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Good {
    /**
     * 数量
     */
    private long count;
    /**
     * sku的ID
     */
    private long skuId;
}
