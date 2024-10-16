package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderWriteSummaryVO {
    /**
     * 折扣总计，应付减去价格
     */
    private Double discountPrice;
    /**
     * 商品件数
     */
    private Long goodsCount;
    /**
     * 邮费，默认是6
     */
    private Double postFee;
    /**
     * 应付总计
     */
    private Double totalPayPrice;
    /**
     * 价格总计
     */
    private Double totalPrice;
}
