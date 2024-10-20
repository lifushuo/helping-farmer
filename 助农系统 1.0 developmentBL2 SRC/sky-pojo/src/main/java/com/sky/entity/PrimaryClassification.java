package com.sky.entity;

import java.io.Serializable;
import lombok.*;
/**
 * (PrimaryClassification)实体类
 *
 * @author makejava
 * @since 2024-05-19 16:54:07
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrimaryClassification implements Serializable {
    private static final long serialVersionUID = 755427692726747782L;
/**
     * 主键

     */
    private Integer id;
/**
     * 一级分类名

     */
    private String name;
/**
     * 图标地址

     */
    private String icon;
/**
     * 是否有效（1有效0无效）

     */
    private String isEffective;
/**
     * 是否删除（1删除0未删除）

     */
    private String isDeleted;



}

