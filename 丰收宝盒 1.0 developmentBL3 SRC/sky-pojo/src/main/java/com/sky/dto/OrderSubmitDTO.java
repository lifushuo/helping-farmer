package com.sky.dto;


import com.sky.entity.Good;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderSubmitDTO {
    /**
     * 地址的ID，地址簿id
     */
    private Integer addressId;
    /**
     * 备注，备注
     */
    private String buyerMessage;
    /**
     * 配送状态：  1立即送出  0选择具体时间，配送状态：  1立即送出  0选择具体时间
     */
    private Integer deliveryTimeType;
    /**
     * 付款方式，付款方式 默认值是1 1为微信 2为支付宝
     */
    private Integer payMethod;

    private List<Good> goods;
}


