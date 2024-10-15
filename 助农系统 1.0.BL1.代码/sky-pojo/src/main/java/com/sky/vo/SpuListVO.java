package com.sky.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpuListVO {
    /**
     * spu的ID
     */
    private long spuId;
    /**
     * spu名字
     */
    private String spuName;
}
