package com.sky.dao;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dto.CancelOrderDTO;
import com.sky.entity.OrderSearch;
import com.sky.entity.OrderSummary;
import com.sky.entity.ShoppingCart;
import com.sky.vo.OrderWriteGoodVO;
import com.sky.vo.OrderWriteVO;
import com.sky.vo.UserOrderDetailSkuVO;
import com.sky.vo.UserOrderDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
@Mapper
/**
 * (OrderSummary)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-19 16:53:48
 */
public interface OrderSummaryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderSummary queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param orderSummary 查询条件
     * @param pageable     分页对象
     * @return 对象列表
     */
    List<OrderSummary> queryAllByLimit(OrderSummary orderSummary, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param orderSummary 查询条件
     * @return 总行数
     */
    long count(OrderSummary orderSummary);

    /**
     * 新增数据
     *
     * @param orderSummary 实例对象
     * @return 影响行数
     */
    int insert(OrderSummary orderSummary);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<OrderSummary> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<OrderSummary> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<OrderSummary> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<OrderSummary> entities);

    /**
     * 修改数据
     *
     * @param orderSummary 实例对象
     * @return 影响行数
     */
    int update(OrderSummary orderSummary);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

//    获取订单某种状态的数量
    Integer getOrderAmount(Integer status);

    int setPayed(Integer id);

    int setFaHuo(Integer id);

    int setShouHuo(Integer id);

    int setCancel(Integer id);

    int setDelete(Integer id);


    Page<OrderSummary> searchOrders(@Param("orderSearch")OrderSearch orderSearch, Integer offset, Integer pageSize);

    long countOrders(@Param("orderSearch")OrderSearch orderSearch);

    UserOrderDetailVO getOrderDetail(Integer id);

    List<UserOrderDetailSkuVO>  getOrderDetailSkuList(Integer id);


    Page<UserOrderDetailVO> getOrderHistory(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("status") int status);

    int getOrderHistoryCount(@Param("status") int status);



    List<OrderWriteGoodVO> writeOrder(Long userId);



//TODO
// 查询购物车中选中的商品
//List<ShoppingCart> getSelectedItemsBySkuIds(@Param("userId") Integer userId, @Param("skuIds") List<Integer> skuIds);

    // 创建订单
//    int insertOrder(Order order);
//
//    // 创建订单详细
//    int insertOrderDetail(OrderDetail orderDetail);
//
//    // 清空已提交的购物车项
//    int deleteSelectedCartItems(@Param("userId") Integer userId, @Param("skuIds") List<Integer> skuIds);

    int cancelOrder(Long orderId,String cancelReason);

    int setOrderComplete(Integer orderId);

    int rejectOrder(Long orderId, String rejectReason);

    int setOrderConfirm(Integer orderId);

    int setOrderDeliver(Integer orderId);

}

