package com.sky.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderAmountVO {
    /**
     * 待派送数量
     */
    private long confirmed;
    /**
     * 派送中数量
     */
    private long deliveryInProgress;
    /**
     * 待接单数量
     */
    private long toBeConfirmed;
}
