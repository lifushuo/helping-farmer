package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.sky.dao.OrderDetailDao;
import com.sky.dao.SkuAttriDao;
import com.sky.dto.RejectOrderDTO;
import com.sky.entity.OrderSearch;
import com.sky.entity.OrderSummary;
import com.sky.dao.OrderSummaryDao;
import com.sky.result.PageResult;
import com.sky.dto.CancelOrderDTO;
import com.sky.result.Result;
import com.sky.service.OrderService;
import com.sky.vo.OrderAmountVO;
import com.sky.vo.OrderDetailVO;
import com.sky.vo.OrderVO;
import com.sky.vo.SkuAttrVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * (OrderSummary)表服务实现类
 *
 * @author makejava
 * @since 2024-05-19 16:53:51
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDetailDao orderDetailDao;
    @Autowired
    OrderSummaryDao orderSummaryDao;
    @Autowired
    SkuAttriDao skuAttriDao;


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public OrderSummary queryById(Integer id) {
        return this.orderSummaryDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param orderSummary 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @Override
    public Page<OrderSummary> queryByPage(OrderSummary orderSummary, PageRequest pageRequest) {
        long total = this.orderSummaryDao.count(orderSummary);
        return new PageImpl<>(this.orderSummaryDao.queryAllByLimit(orderSummary, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param orderSummary 实例对象
     * @return 实例对象
     */
    @Override
    public OrderSummary insert(OrderSummary orderSummary) {
        this.orderSummaryDao.insert(orderSummary);
        return orderSummary;
    }

    /**
     * 修改数据
     *
     * @param orderSummary 实例对象
     * @return 实例对象
     */
    @Override
    public OrderSummary update(OrderSummary orderSummary) {
        this.orderSummaryDao.update(orderSummary);
        return this.queryById(orderSummary.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.orderSummaryDao.deleteById(id) > 0;
    }

    /**
     * 各个状态的订单数量统计
     */
    @Override
    public OrderAmountVO getOrderAmount() {
//        待派送数量（已接单） 数字 3
        Integer confirmed = orderSummaryDao.getOrderAmount(3);
//        派送中数量  数字 4
        Integer deliveryInProgress = orderSummaryDao.getOrderAmount(4);
//        待接单数量  数字2
        Integer toBeConfirmed = orderSummaryDao.getOrderAmount(2);

        OrderAmountVO o = OrderAmountVO.builder().confirmed(confirmed)
                .deliveryInProgress(deliveryInProgress)
                .toBeConfirmed(toBeConfirmed).build();

        return o;
    }


    /**
     * 查询订单详情
     */
    @Override
    public OrderVO getOrder(Integer id) {
        OrderVO orderVO = new OrderVO();

        OrderSummary orderSummary = orderSummaryDao.queryById(id);
        BeanUtils.copyProperties(orderSummary, orderVO);

        //      我现在有orderId，需要在order_detail表中，根据order_id查询所有skuid，
        //      再根据skuid，在sku表中，查询sku所有属性，其中sku表中有一个字段为spuId，
        //      再根据spuId在spu表中查询spu的信息。

        List<OrderDetailVO> orderDetails = orderDetailDao.getOrderDetailVOList(id);
        for (int i = 0; i < orderDetails.size(); i++) {
            List<SkuAttrVO> skuAttrList = skuAttriDao.getSkuAttrVOListBySkuId(orderDetails.get(i).getSkuId());
            orderDetails.get(i).setSkuAttrs(skuAttrList);
        }
        orderVO.setOrderDetails(orderDetails);
        return orderVO;
    }

    @Override
    public Result cancelOrder(CancelOrderDTO cancelOrderDTO) {
        Long orderId = cancelOrderDTO.getId();
        String cancelReason = cancelOrderDTO.getCancelReason();
        int update = orderSummaryDao.cancelOrder(orderId, cancelReason);
        if (update > 0) return Result.success();
        else return Result.error("取消订单失败");
    }

    @Override
    public Result setOrdercomplete(Integer id) {
        int update = this.orderSummaryDao.setOrderComplete(id);
        if (update > 0)
            return Result.success();
        else
            return Result.error("完成订单失败");

    }

    @Override
    public Result rejectOrder(RejectOrderDTO rejectOrderDTO) {
        Long orderId = rejectOrderDTO.getId();
        String rejectReason = rejectOrderDTO.getRejectionReason();
        int update = orderSummaryDao.rejectOrder(orderId, rejectReason);
        if (update > 0)
            return Result.success();
        else return Result.error("拒单失败");
    }

    @Override
    public Result setOrderConfirm(Integer id) {
        int update = this.orderSummaryDao.setOrderConfirm(id);
        if (update > 0)
            return Result.success();
        else
            return Result.error("接单失败");
    }

    @Override
    public Result deliverOrder(Integer id) {
        int update = this.orderSummaryDao.setOrderDeliver(id);
        if (update > 0)
            return Result.success();
        else
            return Result.error("设置派送失败");
    }

    @Override
    public PageResult searchOrders(OrderSearch orderSearch, Integer page, Integer pageSize) {
        Integer offset = (page - 1) * pageSize;

        PageHelper.startPage(page, pageSize);

        com.github.pagehelper.Page<OrderSummary> s =  orderSummaryDao.searchOrders(orderSearch, offset, pageSize);
        //long total = orderSummaryDao.countOrders(orderSearch);
        long total = s.getTotal();
        List<OrderSummary> orderSummaries = s.getResult();

        List<OrderVO> orderVOList = new ArrayList<>();
        for (OrderSummary orderSummary : orderSummaries) {
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(orderSummary, orderVO);

            List<OrderDetailVO> orderDetails = orderDetailDao.getOrderDetailVOList(orderSummary.getId());
            for (OrderDetailVO orderDetail : orderDetails) {
                List<SkuAttrVO> skuAttrList = skuAttriDao.getSkuAttrVOListBySkuId(orderDetail.getSkuId());
                orderDetail.setSkuAttrs(skuAttrList);
            }
            orderVO.setOrderDetails(orderDetails);
            orderVOList.add(orderVO);
        }

        PageResult pageResult = new PageResult();
        pageResult.setTotal(total);
        pageResult.setRecords(orderVOList);
        return pageResult;
    }


    @Override
    public Result cancelOrder(Integer id) {
        int update = this.orderSummaryDao.setCancel(id);
        if (update > 0)
            return Result.success();
        else
            return Result.error("订单取消失败");
    }

    @Override
    public Result setOrderPayed(Integer id) {
        return null;
    }

    @Override
    public Result setOrderFahuo(Integer id) {
        return null;
    }

    @Override
    public Result setOrderShouhuo(Integer id) {
        return null;
    }

    @Override
    public Result setOrderCancel(Integer id) {
        return null;
    }

    @Override
    public Result setOrderDeleted(Integer id) {
        return null;
    }

}
