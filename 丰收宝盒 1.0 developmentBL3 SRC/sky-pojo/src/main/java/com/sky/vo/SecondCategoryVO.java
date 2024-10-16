package com.sky.vo;

import com.sky.entity.Spu;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SecondCategoryVO {

    /**
     * 二级分类id
     */
    private String id;
    /**
     * 二级分类名字
     */
    private String name;
    /**
     * 二级分类图片，这个 直接为null即可
     */
    private String picture=null;


    private List<GoodsVO> goods;
}
