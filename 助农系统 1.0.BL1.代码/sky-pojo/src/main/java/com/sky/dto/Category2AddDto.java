package com.sky.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category2AddDto {
    /**
     * 一级分类id
     */
    private long category1Id;
    /**
     * 二级分类名字，分类名称
     */
    private String name;

}
