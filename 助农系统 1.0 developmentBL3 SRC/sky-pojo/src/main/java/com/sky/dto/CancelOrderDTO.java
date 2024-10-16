package com.sky.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelOrderDTO {
    /**
     * 订单取消原因
     */
    private String cancelReason;
    /**
     * 订单id
     */
    private long id;
}