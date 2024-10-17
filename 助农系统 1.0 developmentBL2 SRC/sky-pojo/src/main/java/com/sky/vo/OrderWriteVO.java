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
public class OrderWriteVO {

    private List<OrderWriteUserVO> userAddresses;
    /**
     * 商品的集合
     */
    private List<OrderWriteGoodVO> goods;
    /**
     * 结算信息
     */
    private OrderWriteSummaryVO summary;
}
