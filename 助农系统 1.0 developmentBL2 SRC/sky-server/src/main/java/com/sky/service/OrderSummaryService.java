package com.sky.service;

import com.sky.dto.OrderSubmitDTO;
import com.sky.entity.OrderSummary;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.vo.OrderWriteVO;
import com.sky.vo.UserOrderDetailVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface OrderSummaryService {
    OrderSummary queryById(Integer id);

    Page<OrderSummary> queryByPage(OrderSummary orderSummary, PageRequest pageRequest);

    OrderSummary insert(OrderSummary orderSummary);

    OrderSummary update(OrderSummary orderSummary);

    boolean deleteById(Integer id);

    Result setOrderPayed(Integer id);

    Result setOrderFahuo(Integer id);

    Result setOrderShouhuo(Integer id);

    Result setOrderCancel(Integer id);

    Result setOrderDeleted(Integer id);

    UserOrderDetailVO getOrderDetail(Integer id);

    PageResult getOrderHistory(Integer page, Integer pageSize, Integer status);


   Integer submitOrder(OrderSubmitDTO orderSubmitDTO);

    OrderWriteVO writeOrder();
}
