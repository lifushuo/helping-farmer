package com.sky.dao;

import com.sky.entity.Attribute;
import com.sky.entity.GoodsDetailsProperties;
import com.sky.entity.SkuValue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;
@Mapper
/**
 * (Attribute)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-19 16:50:34
 */
public interface AttributeDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Attribute queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param attribute 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Attribute> queryAllByLimit(Attribute attribute, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param attribute 查询条件
     * @return 总行数
     */
    long count(Attribute attribute);

    /**
     * 新增数据
     *
     * @param attribute 实例对象
     * @return 影响行数
     */
    int insert(Attribute attribute);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Attribute> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Attribute> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Attribute> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Attribute> entities);

    /**
     * 修改数据
     *
     * @param attribute 实例对象
     * @return 影响行数
     */
    //int update(Attribute attribute);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);


    int insertSpuAttr(Attribute attribute);

    int deleteSpuAttr(Integer spuId, String name);

    int querySpuAttr(Attribute attribute);

    List<Attribute> querySpuBySpuId(Integer spuid, String name);

    //int update(Integer spuId, String oldName, String newName);

    int updateSpuAttr(Integer spuId, String oldName, String newName);

    /**
     * 通过attri_id获取属性
     * @param id
     * @return
     */
    SkuValue getAttributesByAttriId(Long id);

    List<Attribute> selectAll(@Param("spuId") Integer spuId);

    List<Attribute> selectBySpuId(Integer spuId);


    List<GoodsDetailsProperties> getGoodsDetailsProperties(Integer spuId);

    List<String> getAttributeNameListBySpuId(Integer spuId);

    List<String> getAttributeValueListByName(String name);
}

