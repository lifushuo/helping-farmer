package com.sky.entity;

//import com.sky.vo.Property;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class GoodsDetails {
    /**
     * spu的图片 数组， 商品详情图片集合[ 图片链接 ]
     *
     */
    private List<String> picture;
    /**
     * 商品规格集合[ 属性信息 ]
     */
    private List<GoodsDetailsProperties> properties;
}
