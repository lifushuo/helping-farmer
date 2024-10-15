package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * (Attribute)实体类
 *
 * @author makejava
 * @since 2024-05-19 16:50:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attribute implements Serializable {
    private static final long serialVersionUID = 436563353979253119L;
/**
     * 主键

     */
    private Integer id;
/**
     * spuid

     */
    private Integer spuId;
/**
     * 属性名

     */
    private String name;
/**
     * 属性值

     */
    private String value;
/**
     * 是否删除（1删除0未删除）

     */
    private String isDeleted;


}

