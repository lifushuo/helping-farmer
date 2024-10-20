package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.sky.context.BaseContext;
import com.sky.dao.AddressDao;
import com.sky.dao.OrderDetailDao;
import com.sky.dao.SkuDao;
import com.sky.dto.OrderSubmitDTO;
import com.sky.entity.Address;
import com.sky.entity.Good;
import com.sky.entity.OrderDetail;
import com.sky.entity.OrderSummary;
import com.sky.dao.OrderSummaryDao;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.OrderSummaryService;
import com.sky.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (OrderSummary)表服务实现类
 *
 * @author makejava
 * @since 2024-05-19 16:53:51
 */
@Service
public class OrderSummaryServiceImpl implements OrderSummaryService {
    @Resource
    private OrderSummaryDao orderSummaryDao;
    @Autowired
    SkuDao skuDao;
    @Autowired
    OrderDetailDao orderDetailDao;
    @Autowired
    AddressDao addressDao;

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

    @Override
    public Result setOrderPayed(Integer id) {
        int update = this.orderSummaryDao.setPayed(id);
        if (update > 0)
            return Result.success();
        else
            return Result.error("设置待派送状态失败");
    }

    @Override
    public Result setOrderFahuo(Integer id) {
        int update = this.orderSummaryDao.setFaHuo(id);
        if (update > 0)
            return Result.success();
        else
            return Result.error("订单发货失败");
    }

    @Override
    public Result setOrderShouhuo(Integer id) {
        int update = this.orderSummaryDao.setShouHuo(id);
        if (update > 0)
            return Result.success();
        else
            return Result.error("收货失败");
    }

    @Override
    public Result setOrderCancel(Integer id) {
        int update = this.orderSummaryDao.setCancel(id);
        if (update > 0)
            return Result.success();
        else
            return Result.error("订单取消失败");


    }

    @Override
    public Result setOrderDeleted(Integer id) {
        int update = this.orderSummaryDao.setDelete(id);
        if (update > 0)
            return Result.success();
        else
            return Result.error("订单删除失败");
    }

    @Override
    public UserOrderDetailVO getOrderDetail(Integer id) {
        UserOrderDetailVO u = orderSummaryDao.getOrderDetail(id);
        List<UserOrderDetailSkuVO> skuList = orderSummaryDao.getOrderDetailSkuList(id);
        u.setSkus(skuList);
        return u;
    }


    public PageResult getOrderHistory(Integer page, Integer pageSize, Integer status) {
        // 计算分页的起始位置
        int offset = (page - 1) * pageSize;
        // 查询订单列表

        PageHelper.startPage(page, pageSize);

        com.github.pagehelper.Page<UserOrderDetailVO> list = orderSummaryDao.getOrderHistory(offset, pageSize, status);


        List<UserOrderDetailVO> orderList = list.getResult();
        // 查询总记录数
        //int totalCount = orderSummaryDao.getOrderHistoryCount(status);

        int totalCount = Math.toIntExact(list.getTotal());
        // 构建分页结果
        PageResult pageResult = new PageResult();
        pageResult.setTotal(totalCount);
        pageResult.setRecords(orderList);

        return pageResult;
    }


    @Override
    public Integer submitOrder(OrderSubmitDTO orderSubmitDTO) {
        Long userId = BaseContext.getCurrentId();
        OrderSummary orderSummary = new OrderSummary();

        OrderWriteVO orderWriteVO = writeOrder();
        OrderWriteSummaryVO summary = orderWriteVO.getSummary();
        OrderWriteUserVO orderWriteUserVO = orderWriteVO.getUserAddresses().get(0);


        orderSummary.setPayMoney(summary.getTotalPayPrice());
        orderSummary.setSumMoney(summary.getTotalPayPrice());
        orderSummary.setPayAmount(summary.getPostFee());
        orderSummary.setAddress(orderWriteUserVO.getFullLocation() + orderWriteUserVO.getAddress());

        orderSummary.setStatus(1);
        orderSummary.setUserId(Math.toIntExact(userId));
        orderSummary.setPayMethod(orderSubmitDTO.getPayMethod());
        orderSummary.setDiscountMoney(orderSummary.getDiscountMoney());
        orderSummary.setRemark(orderSubmitDTO.getBuyerMessage());

        orderSummary.setPhone(orderWriteUserVO.getContact());
        orderSummary.setConsignee(orderWriteUserVO.getReceiver());


        orderSummary.setAddressBookId(orderSubmitDTO.getAddressId());
        orderSummary.setRemark(orderSubmitDTO.getBuyerMessage());
        orderSummary.setPayMethod(orderSubmitDTO.getPayMethod());

        orderSummaryDao.insert(orderSummary);

        int orderId = orderSummary.getId();

        List<Good> goods = orderSubmitDTO.getGoods();
        for (int i = 0; i < goods.size(); i++) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(orderId);
            orderDetail.setSkuid((int) goods.get(i).getSkuId());
            orderDetail.setCount((int) goods.get(i).getCount());
            orderDetail.setSpuid(skuDao.getSpuIdBySkuId((int) goods.get(i).getSkuId()));

            orderDetailDao.insert(orderDetail);
        }

        return orderId;
    }

    @Override
    public OrderWriteVO writeOrder() {
        Long userId = BaseContext.getCurrentId();
        OrderWriteVO orderWrite = new OrderWriteVO();

        List<OrderWriteGoodVO> list = orderSummaryDao.writeOrder(userId);


        orderWrite.setGoods(list);

        List<Address> addressesList = addressDao.selectByUserId(userId);
        List<OrderWriteUserVO> userList = new ArrayList<>();

        for (Address address : addressesList) {

            OrderWriteUserVO o = new OrderWriteUserVO();
            o.setId(address.getId());
            o.setProvinceCode(Integer.valueOf(address.getProvinceCode()));
            o.setCityCode(Integer.valueOf(address.getCityCode()));
            o.setCountyCode(Integer.valueOf(address.getDistrictCode()));
            String location = address.getProvinceName() + address.getCityName() + address.getDistrictName();
            o.setFullLocation(location);
            o.setIsDefault(Integer.valueOf(address.getIsDefault()));
            o.setReceiver(address.getConsignee());
            o.setContact(address.getPhone());

            userList.add(o);
        }

        orderWrite.setUserAddresses(userList);
        OrderWriteSummaryVO summaryVO = new OrderWriteSummaryVO();
        Double totalPayPrice = (double) 0;
        Double totalPrice = (double) 0;
        Long total = 0L;
        summaryVO.setPostFee(Double.valueOf(0));
        for (OrderWriteGoodVO goodVO : list) {
            totalPrice += goodVO.getTotalPrice();
            totalPayPrice += goodVO.getTotalPayPrice();
            total += goodVO.getCount();
        }
        summaryVO.setGoodsCount(total);
        summaryVO.setTotalPrice(totalPrice);
        summaryVO.setTotalPayPrice(totalPayPrice);
        summaryVO.setPostFee(Double.valueOf(6));
        orderWrite.setSummary(summaryVO);


        return orderWrite;
    }
}
