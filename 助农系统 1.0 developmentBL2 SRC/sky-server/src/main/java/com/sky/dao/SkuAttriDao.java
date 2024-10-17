package com.sky.dao;

import com.sky.entity.GoodsSkusSpecs;
import com.sky.entity.SkuAttri;
import com.sky.vo.SkuAttrVO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;
@Mapper
/**
 * (SkuAttri)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-19 17:12:14
 */
public interface SkuAttriDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SkuAttri queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param skuAttri 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<SkuAttri> queryAllByLimit(SkuAttri skuAttri, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param skuAttri 查询条件
     * @return 总行数
     */
    long count(SkuAttri skuAttri);

    /**
     * 新增数据
     *
     * @param skuAttri 实例对象
     * @return 影响行数
     */
    int insert(SkuAttri skuAttri);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<SkuAttri> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<SkuAttri> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<SkuAttri> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<SkuAttri> entities);

    /**
     * 修改数据
     *
     * @param skuAttri 实例对象
     * @return 影响行数
     */
    int update(SkuAttri skuAttri);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     *通过skuid删除属性
     * @param skuId
     * @return
     */
    int deleteBySkuId(Integer skuId);

    /**
     *通过skuid获得属性
     * @param skuId
     * @return
     */
    List<Long>  getAttributesBySkuId(Integer skuId);

//    根据skuid获取属性名字
    List<String> getAttriNameBySkuId(Integer skuId);

    List<SkuAttrVO> getSkuAttrVOListBySkuId(Long skuId);


    List<GoodsSkusSpecs> getGoodsSkusSpecsListBySkuId(Integer integer);
}

