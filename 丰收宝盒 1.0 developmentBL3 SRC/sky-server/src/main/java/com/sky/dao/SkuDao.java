package com.sky.dao;

import com.sky.annotation.AutoFill;
import com.sky.entity.Sku;
import com.sky.enumeration.OperationType;
import com.sky.vo.SkuQueryForEditVO;
import com.sky.vo.SkuQueryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;
@Mapper
/**
 * (Sku)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-19 17:12:07
 */
public interface SkuDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Sku queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param sku 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Sku> queryAllByLimit(Sku sku, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param sku 查询条件
     * @return 总行数
     */
    long count(Sku sku);

    /**
     * 新增数据
     *
     * @param sku 实例对象
     * @return 影响行数
     */
    @AutoFill(OperationType.INSERT)
    int insert(Sku sku);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Sku> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Sku> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Sku> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Sku> entities);

    /**
     * 修改数据
     *
     * @param sku 实例对象
     * @return 影响行数
     */
    int update(Sku sku);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    SkuQueryForEditVO queryByIdForEdit(Integer id);

    boolean setStatus(Integer id, Integer status);

    int countBySpuId(Integer spuId);

    List<Integer> getSkuIdListBySpuId(Integer spuId);

    int getSpuIdBySkuId(Integer skuId);
}

