package com.sky.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserOrderDetailVO {
    /**
     * 下单时间
     */
    private String createTime;
    /**
     * 订单的id
     */
    private Integer id;
    /**
     * 订单状态
     */
    private Integer orderState;
    /**
     * 支付的金额
     */
    private Double payMoney;
    /**
     * 运费
     */
    private Double postFee;
    /**
     * 收获的完整地址，地址信息中获取
     */
    private String receiverAddress;
    /**
     * 收货人，地址信息里面获取
     */
    private String receiverContact;
    /**
     * 收货人手机号，地址信息中获取
     */
    private String receiverMobile;
    /**
     * 商品总价
     */
    private Double totalMoney;

    private List<UserOrderDetailSkuVO> skus;
}


