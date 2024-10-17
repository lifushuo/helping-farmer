package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsSpecs {
    /**
     * 属性名，属性名
     */
    private String name;
    private List<GoodsSpecsValues> specValues;
}
