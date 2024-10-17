package com.sky.service;

import com.sky.dto.RejectOrderDTO;
import com.sky.entity.OrderSearch;
import com.sky.dto.CancelOrderDTO;
import com.sky.entity.OrderSummary;
import com.sky.result.PageResult;
import com.sky.vo.OrderAmountVO;
import com.sky.vo.OrderVO;
import com.sky.result.Result;
import com.sky.vo.OrderPaymentVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (OrderSummary)表服务接口
 *
 * @author makejava
 * @since 2024-05-19 16:53:51
 */
public interface OrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderSummary queryById(Integer id);

    /**
     * 分页查询
     *
     * @param orderSummary 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    Page<OrderSummary> queryByPage(OrderSummary orderSummary, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param orderSummary 实例对象
     * @return 实例对象
     */
    OrderSummary insert(OrderSummary orderSummary);

    /**
     * 修改数据
     *
     * @param orderSummary 实例对象
     * @return 实例对象
     */
    OrderSummary update(OrderSummary orderSummary);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);
    /**
     * 各个状态的订单数量统计
     */
    OrderAmountVO getOrderAmount();

    /**
     * 查询订单详情
     * @return
     */
    OrderVO getOrder(Integer id);

    Result cancelOrder(Integer id);

    Result setOrderPayed(Integer id);

     Result setOrderFahuo(Integer id);

     Result setOrderShouhuo(Integer id);

     Result setOrderCancel(Integer id);

     Result setOrderDeleted(Integer id);


    /**
     * 搜索订单
     * @param orderSearch
     * @param page
     * @param pageSize
     * @return
     */
    public PageResult searchOrders(OrderSearch orderSearch, Integer page, Integer pageSize);



//    Result setOrderPayed(Integer id);
//
//    Result setOrderFahuo(Integer id);
//
//    Result setOrderShouhuo(Integer id);
//
//    Result setOrderCancel(Integer id);
//
//    Result setOrderDeleted(Integer id);

    Result cancelOrder(CancelOrderDTO cancelOrderDTO);

    Result setOrdercomplete(Integer id);

    Result rejectOrder(RejectOrderDTO rejectOrderDTO);

    Result setOrderConfirm(Integer id);

    Result deliverOrder(Integer id);
}
