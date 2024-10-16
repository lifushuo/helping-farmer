package com.sky.dao;

import com.sky.entity.SpuPicture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;
@Mapper
/**
 * (SpuPicture)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-19 17:12:38
 */
public interface SpuPictureDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SpuPicture queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param spuPicture 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SpuPicture> queryAllByLimit(SpuPicture spuPicture, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param spuPicture 查询条件
     * @return 总行数
     */
    long count(SpuPicture spuPicture);

    /**
     * 新增数据
     *
     * @param spuPicture 实例对象
     * @return 影响行数
     */
    int insert(SpuPicture spuPicture);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SpuPicture> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SpuPicture> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SpuPicture> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SpuPicture> entities);

    /**
     * 修改数据
     *
     * @param spuPicture 实例对象
     * @return 影响行数
     */
    int update(SpuPicture spuPicture);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    void insertSpuIDandPictureUrl(Integer spuId, String picture);


    List<SpuPicture> selectBySpuId(@Param("id") long id);

    void updateSpuPicture(SpuPicture spuPicture);

}

