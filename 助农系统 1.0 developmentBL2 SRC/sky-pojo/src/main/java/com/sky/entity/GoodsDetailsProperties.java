package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsDetailsProperties {
    /**
     * 规格名
     */
    private String name;
    /**
     * 属性值
     */
    private String value;
}
