package com.sky.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SPUPageItemVO {
    /**
     * spu描述
     */
    private String description;
    /**
     * spu名称
     */
    private String name;
    /**
     * spuId
     */
    private double spuId;


}
