package com.sky.dao;

import com.sky.entity.OrderDetail;
import com.sky.vo.OrderDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;
@Mapper
/**
 * (OrderDetail)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-19 16:52:05
 */
public interface OrderDetailDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    OrderDetail queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param orderDetail 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<OrderDetail> queryAllByLimit(OrderDetail orderDetail, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param orderDetail 查询条件
     * @return 总行数
     */
    long count(OrderDetail orderDetail);

    /**
     * 新增数据
     *
     * @param orderDetail 实例对象
     * @return 影响行数
     */
    int insert(OrderDetail orderDetail);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<OrderDetail> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<OrderDetail> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<OrderDetail> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<OrderDetail> entities);

    /**
     * 修改数据
     *
     * @param orderDetail 实例对象
     * @return 影响行数
     */
    int update(OrderDetail orderDetail);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

//    根据orderId获取sku和spu相关信息
    List<OrderDetailVO> getOrderDetailVOList(Integer id);
}

