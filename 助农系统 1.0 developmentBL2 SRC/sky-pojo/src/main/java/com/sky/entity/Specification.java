package com.sky.entity;

import java.io.Serializable;
import lombok.*;
/**
 * (Specification)实体类
 *
 * @author makejava
 * @since 2024-05-19 17:12:23
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Specification implements Serializable {
    private static final long serialVersionUID = -45708588798790995L;
/**
     * 主键

     */
    private Integer id;
/**
     * spuid

     */
    private Integer spuId;
/**
     * 规格名称

     */
    private String name;
/**
     * 参数值

     */
    private String value;
/**
     * 是否删除（1删除0未删除）默认是0

     */
    private String isDeleted;


}

