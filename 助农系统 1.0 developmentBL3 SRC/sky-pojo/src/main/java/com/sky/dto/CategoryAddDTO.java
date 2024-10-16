package com.sky.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CategoryDTO
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryAddDTO {
    private String icon;
    private Long id;
    /**
     * 分类名称
     */
    private String name;

}
