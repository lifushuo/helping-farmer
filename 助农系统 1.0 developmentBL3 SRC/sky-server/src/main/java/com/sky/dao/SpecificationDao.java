package com.sky.dao;


import com.sky.dto.SPUEditSpecificationDTO;
import com.sky.entity.Specification;
import com.sky.entity.SpuSpecification;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;
@Mapper
/**
 * (Specification)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-19 17:12:23
 */
public interface SpecificationDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Specification queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param specification 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Specification> queryAllByLimit(Specification specification, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param specification 查询条件
     * @return 总行数
     */
    long count(Specification specification);

    /**
     * 新增数据
     *
     * @param specification 实例对象
     * @return 影响行数
     */
    int insert(Specification specification);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Specification> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Specification> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Specification> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Specification> entities);

    /**
     * 修改数据
     *
     * @param specification 实例对象
     * @return 影响行数
     */
    int update(Specification specification);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);



    List<SpuSpecification> queryBySpuId(Integer spuId);

    void insertSpecification(Integer spuId, SPUEditSpecificationDTO SPUEditSpecificationDTO);

    void updateSpecification(@Param("id") Integer id, @Param("specification") SPUEditSpecificationDTO SPUEditSpecificationDTO);

    void deleteBySpuId(Integer id);

    List<Specification> SelectBySpuId(@Param("spuId") Integer spuId);
}

