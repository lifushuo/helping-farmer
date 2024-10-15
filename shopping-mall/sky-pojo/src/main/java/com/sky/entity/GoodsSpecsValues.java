package com.sky.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsSpecsValues {
    /**
     * 是否可以销售 是否有效，都是 true
     */
    private boolean avaliable;
    /**
     * 属性备注 ，不用传
     */
    private String desc;
    /**
     * 属性值，属性值 不是属性名
     */
    private String name;
    /**
     * 图片，为null没有
     */
    private String picture;
}
