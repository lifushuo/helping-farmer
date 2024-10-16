package com.sky.dao;

import com.github.pagehelper.Page;
import com.sky.entity.Spu;
import com.sky.result.Result;
import com.sky.vo.SPUPageItemVO;
import com.sky.vo.SpuListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;
@Mapper
/**
 * (Spu)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-19 17:12:31
 */
public interface SpuDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Spu queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param spu 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<Spu> queryAllByLimit(Spu spu, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param spu 查询条件
     * @return 总行数
     */
    long count(Spu spu);

    /**
     * 新增数据
     *
     * @param spu 实例对象
     * @return 影响行数
     */
    int insert(Spu spu);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Spu> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Spu> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Spu> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Spu> entities);

    /**
     * 修改数据
     *
     * @param spu 实例对象
     * @return 影响行数
     */
    int update(Spu spu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    Boolean setStatus(Integer id, Integer status);

    List<SpuListVO> getListByCateory1Id(@Param("id") Integer category1Id);

    List<SpuListVO> getListByCateory2Id(@Param("id") Integer category2Id);

    Page<SPUPageItemVO> selectByCategory2ID(@Param("category2Id") Integer category2Id);

    Page<SPUPageItemVO> selectByCategory1ID(@Param("id1") Integer category1Id);

    //Result getSpuInfo(Integer spuId);

    Page<SPUPageItemVO> selectAll();

    List<String> getSpuPicturesList(Integer spuId);
}

