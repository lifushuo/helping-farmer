package com.sky.entity;



/**
 * (OrderSummary)实体类
 *
 * @author makejava
 * @since 2024-05-19 16:53:48
 */


import java.util.Date;
import java.io.Serializable;
import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderSummary implements Serializable {
    private static final long serialVersionUID = 890410445825777739L;

    private Integer id;

    private Integer number;
    /**
     * 订单状态 1待付款 2待接单 3已接单
     * 4派送中 5已完成 6已取消 7退款 8已收货
     */
    private Integer status;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 地址Id
     */
    private Integer addressBookId;
    /**
     * 支付方式 1微信,2支付宝
     */
    private Integer payMethod;
    /**
     * 支付编号
     */
    private Integer payId;
    /**
     * 支付状态 0未支付 1已支付 2退款'
     */
    private Integer payStatus;
    /**
     * 合计总价
     */
    private Double sumMoney;
    /**
     * 优惠金额
     */
    private Double discountMoney;
    /**
     * 实付款
     */
    private Double payMoney;
    /**
     * 备注
     */
    private String remark;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 地址信息
     */
    private String address;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 订单取消原因
     */
    private String cancelReason;
    /**
     * 商家拒单原因
     */
    private String rejectionReason;
    /**
     * 订单取消时间
     */
    private Date cancelTime;
    /**
     * 送达时间待定
     */
    private Date estimatedDeliveryTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 开始配送时间
     */
    private Date deliveryTime;
    /**
     * 结账时间
     */
    private Date checkoutTime;
    /**
     * 运费
     */
    private Double payAmount;
    /**
     * 是否删除（1删除0未删除）
     */
    private Integer isDeleted;



}

