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
public class Records {
    private Integer id;
    private long isEffective;
    private Double oldPrice;
    private String picture;
    private Double price;
    private String skuName;
    private Long storeCount;
    /**
     * 属性价值的数组，返回文字就可以了
     */
    private List<String> values;
}
