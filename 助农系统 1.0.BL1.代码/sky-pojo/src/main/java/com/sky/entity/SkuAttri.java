package com.sky.entity;

import java.io.Serializable;
import lombok.*;
/**
 * (SkuAttri)实体类
 *
 * @author makejava
 * @since 2024-05-19 17:12:14
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkuAttri implements Serializable {
    private static final long serialVersionUID = 463488111000402524L;
/**
     * 主键

     */
    private Integer id;
/**
     * skuid

     */
    private Integer skuId;
/**
     * 属性id

     */
    private Integer arriId;


}

