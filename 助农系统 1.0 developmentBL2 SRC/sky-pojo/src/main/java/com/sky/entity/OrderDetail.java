package com.sky.entity;

import lombok.*;

import java.io.Serializable;

/**
 * (OrderDetail)实体类
 *
 * @author makejava
 * @since 2024-05-19 16:52:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class OrderDetail implements Serializable {
    private static final long serialVersionUID = 328593062729157100L;
/**
     * 主键

     */
    private Integer id;
/**
     * spuid

     */
    private Integer spuid;
/**
     * skuid

     */
    private Integer skuid;
/**
     * 订单概况表id

     */
    private Integer orderId;

    private Integer count;

}

