package com.sky.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkuAttrVO {
    /**
     * 属性名字
     */
    public String attrName;
    /**
     * attr属性值
     */
    public String attrValue;
}