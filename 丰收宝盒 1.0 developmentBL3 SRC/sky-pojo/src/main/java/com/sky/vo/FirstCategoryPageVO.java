package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FirstCategoryPageVO {

    /**
     * 一级分类id
     */
    private String id;


    private String name;



    private String picture;
    /**
     * 级分类图片集[ 一级分类图片项 ] ，这个不用返回  赋值为空就行
     */
    private String[] imageBanners=null;
    /**
     * 一级分类
     */

    /**
     * 一级分类图片
     */
    private List<SecondCategoryVO> children;

}
