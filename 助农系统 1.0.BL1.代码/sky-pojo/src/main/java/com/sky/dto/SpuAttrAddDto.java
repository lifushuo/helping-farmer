package com.sky.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpuAttrAddDto {
    /**
     * 属性名
     */
    private String name;
    /**
     * spuId
     */
    private long spuId;
    /**
     * 属性值数组
     */
    private String[] values;


}