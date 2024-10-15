package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkuValue {
    /**
     * 属性名
     */
    private String name;
    /**
     * 属性价值
     */
    private String value;
}