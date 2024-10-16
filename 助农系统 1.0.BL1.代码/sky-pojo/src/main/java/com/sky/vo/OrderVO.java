package com.sky.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderVO {
    /**
     * 地址信息
     */
    public String address;
    /**
     * 地址id，地址id
     */
    public Integer addressBookId;
    /**
     * 订单取消原因
     */
    public String  cancelReason;
    /**
     * 取消时间
     */
    public Date cancelTime;
    /**
     * 支付时间
     */
    public Date checkoutTime;
    /**
     * 收货人
     */
    public String consignee;
    /**
     * 订单创建时间
     */
    public Date createTime;
    /**
     * 订单开始配送时间
     */
    public Date deliveryTime;
    /**
     * 优惠金额
     */
    public Double discountMoney;
    /**
     * 最终送达时间
     */
    public Date estimatedDeliveryTime;
    /**
     * 订单主键
     */
    public Integer id;
    /**
     * 订单号，订单号
     */
    public Integer number;

    /**
     * 运费
     */
    public Double payAmount;
    /**
     * 支付的编号
     */
    public Integer payId;
    /**
     * 支付方式，支付方法
     */
    public Integer payMethod;
    /**
     * 实付款
     */
    public Double payMoney;
    /**
     * 支付状态，支付状态
     */
    public Integer payStatus;
    /**
     * 电话号码，电话号码
     */
    public String phone;
    /**
     * 商家拒绝原因
     */
    public String rejectionReason;
    /**
     * 备注，备注
     */
    public String remark;
    /**
     * 状态，订单状态
     */
    public Integer status;
    /**
     * 用户id，用户id
     */
    public Integer userId;
    /**
     * 用户名，用户名
     */
    public String userName;
    /**
     * 订单详情数组
     */
    public List<OrderDetailVO> orderDetails;
}

