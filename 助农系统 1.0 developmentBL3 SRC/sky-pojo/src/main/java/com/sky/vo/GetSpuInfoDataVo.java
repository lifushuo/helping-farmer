package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class  GetSpuInfoDataVo{
    private List<GetSkuInfoAttribute> attributes;
    private String description;
    private Double discount;
    /**
     * ID 编号
     */
    private long id;
    private List<String> picture;
    private double price;
    private List<GetSpuInfoSpecification> specifications;
    private String spuName;
    private long storeCount;


}

// Attribute.java


// Specification.java



