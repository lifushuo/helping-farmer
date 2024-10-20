package com.sky.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SPUEditSpecificationDTO {
    /**
     * 规格名称
     */
    private String name;
    /**
     * 参数值
     */
    private String value;


}