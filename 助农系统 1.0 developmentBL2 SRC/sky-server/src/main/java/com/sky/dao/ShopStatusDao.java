package com.sky.dao;

import com.sky.entity.ShopStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;
@Mapper
/**
 * (ShopStatus)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-19 17:11:41
 */
public interface ShopStatusDao {

    /**
     * 通过ID查询单条数据
     *
     * @param  主键
     * @return 实例对象
     */
    ShopStatus queryById( );

    /**
     * 查询指定行数据
     *
     * @param shopStatus 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<ShopStatus> queryAllByLimit(ShopStatus shopStatus, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param shopStatus 查询条件
     * @return 总行数
     */
    long count(ShopStatus shopStatus);

    /**
     * 新增数据
     *
     * @param shopStatus 实例对象
     * @return 影响行数
     */
    int insert(ShopStatus shopStatus);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ShopStatus> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ShopStatus> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ShopStatus> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ShopStatus> entities);

    /**
     * 修改数据
     *
     * @param shopStatus 实例对象
     * @return 影响行数
     */
    int update(ShopStatus shopStatus);

    /**
     * 通过主键删除数据
     *
     * @param  主键
     * @return 影响行数
     */
    int deleteById( );

}

