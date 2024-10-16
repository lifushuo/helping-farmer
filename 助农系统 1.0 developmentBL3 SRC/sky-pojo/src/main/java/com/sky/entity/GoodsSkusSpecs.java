package com.sky.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsSkusSpecs {
    /**
     * 属性名
     */
    private String name;
    /**
     * 属性
     */
    private String value;
}
