package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * (Collection)实体类
 *
 * @author makejava
 * @since 2024-05-19 16:51:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Star implements Serializable {
    private static final long serialVersionUID = 845816505452039615L;
/**
     * 主键
     */
    private Integer id;
/**
     * 用户ID
     */
    private Integer userId;
/**
     * spu_id
     */
    private Integer spuId;
/**
     * 创建时间
     */
    private Date createTime;
/**
     * 更新时间
     */
    private Date updateTime;
/**
     * 是否删除（1删除0未删除）

     */
    private String isDeleted;

}

