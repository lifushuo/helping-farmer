package com.sky.dao;

import com.github.pagehelper.Page;
import com.sky.entity.SecondaryClassification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
/**
 * (SecondaryClassification)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-19 17:11:26
 */
public interface SecondaryClassificationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SecondaryClassification queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param secondaryClassification 查询条件
     * @return 对象列表
     */
    List<SecondaryClassification> queryAllByLimit(SecondaryClassification secondaryClassification);


    /**
     * 统计总行数
     *
     * @param secondaryClassification 查询条件
     * @return 总行数
     */
    long count(SecondaryClassification secondaryClassification);


    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SecondaryClassification> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SecondaryClassification> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SecondaryClassification> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SecondaryClassification> entities);

    /**
     * 修改数据
     *
     * @param secondaryClassification 实例对象
     * @return 影响行数
     */
    int update(SecondaryClassification secondaryClassification);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    Page<SecondaryClassification> pageByName(String name, Integer category1Id);


    int deleteByPrimaryKey(Long id);

    int insert(SecondaryClassification record);

    int insertSelective(SecondaryClassification record);

    SecondaryClassification selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecondaryClassification record);

    int updateByPrimaryKey(SecondaryClassification record);

    List<SecondaryClassification> selectList(Integer id);
}

