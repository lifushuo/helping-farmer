package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderPaymentVO {
    /**
     * 随机字符串
     */
    private OffsetDateTime nonceStr;
    /**
     * 统一下单接口返回的 prepay_id 参数值
     */
    private String packageStr;
    /**
     * 签名
     */
    private String paySign;
    /**
     * 签名算法
     */
    private String signType;
    /**
     * 时间戳
     */
    private String timeStamp;
}
