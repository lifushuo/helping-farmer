package com.sky.entity;

import java.util.Date;
import java.io.Serializable;
import lombok.*;
/**
 * (ShoppingCart)实体类
 *
 * @author makejava
 * @since 2024-05-19 17:11:52
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCart implements Serializable {
    private static final long serialVersionUID = -56155804936875642L;
/**
     * 主键
     */
    private Integer id;
/**
     * sku_id
     */
    private Integer skuId;
/**
     * 用户id
     */
    private Integer userId;
/**
     * 是否选中
     */
    private String isSelected;
/**
     * 数量
     */
    private Integer count;
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

