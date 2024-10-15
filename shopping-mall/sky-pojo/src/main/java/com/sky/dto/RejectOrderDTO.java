package com.sky.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RejectOrderDTO {
    /**
     * 订单id
     */
    private long id;
    /**
     * 拒单原因
     */
    private String rejectionReason;
}
