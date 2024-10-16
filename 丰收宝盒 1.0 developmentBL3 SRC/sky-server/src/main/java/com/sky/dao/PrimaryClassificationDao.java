package com.sky.dao;

import com.github.pagehelper.Page;
import com.sky.entity.PrimaryClassification;
import com.sky.vo.PrimaryCategoryListVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Pageable;
import java.util.List;
@Mapper
/**
 * (PrimaryClassification)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-19 16:54:07
 */
public interface PrimaryClassificationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PrimaryClassification queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param primaryClassification 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<PrimaryClassification> queryAllByLimit(PrimaryClassification primaryClassification, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param primaryClassification 查询条件
     * @return 总行数
     */
    long count(PrimaryClassification primaryClassification);

    /**
     * 新增数据
     *
     * @param primaryClassification 实例对象
     * @return 影响行数
     */
    int insert(PrimaryClassification primaryClassification);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<PrimaryClassification> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<PrimaryClassification> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<PrimaryClassification> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<PrimaryClassification> entities);

    /**
     * 修改数据
     *
     * @param primaryClassification 实例对象
     * @return 影响行数
     */
    int update(PrimaryClassification primaryClassification);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    Page<PrimaryClassification> page(String name);


    @Select("select name,id from WZY.primary_classification where is_deleted=0")
    List<PrimaryCategoryListVo> listData();
}

