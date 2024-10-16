package com.sky.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttrListDataVO {
    /**
     * 属性名
     */
    private String name;
    /**
     * 属性归属的spu
     */
    private long spuId;
    /**
     * 属性价值数组
     */
    private List<AttrValueListDataItemVO> values;

}
