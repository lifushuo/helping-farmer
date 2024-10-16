package com.sky.service;

import com.sky.dto.SkuAddDto;
import com.sky.dto.SkuEditDto;
import com.sky.entity.Sku;
import com.sky.result.Result;
import com.sky.vo.SkuQueryForEditVO;
import com.sky.vo.SkuQueryForSpuIdVO;
import com.sky.vo.SkuQueryVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Sku)表服务接口
 *
 * @author makejava
 * @since 2024-05-19 17:12:07
 */
public interface SkuService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SkuQueryVO queryById(Integer id);

    /**
     * 分页查询
     *
     * @param sku 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Sku> queryByPage(Sku sku, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param sku 实例对象
     * @return 实例对象
     */
    Sku insert(Sku sku);

    /**
     * 修改数据
     *
     * @param sku 实例对象
     * @return 实例对象
     */
//    Sku update(Sku sku);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Result deleteById(Integer id);

    /**
     * 新增Sku
     * @param skuAddDto
     * @return
     */
    Result addSku(SkuAddDto skuAddDto);

    /**
     * 修改Sku
     * @param skuEditDto
     * @return
     */
    Result editSku(SkuEditDto skuEditDto);

    /**
     * 设置起售停售
     * @param id
     * @return
     */
    Result setStatus(Integer id,Integer status);

    /**
     * 根据id查询信息供编辑使用
     *
     * @param id
     * @return
     */
    SkuQueryForEditVO queryByIdForEdit(Integer id);

    /**
     *  根据spuId获取对应的全部sku
     * @param spuId
     * @return
     */
    SkuQueryForSpuIdVO queryListBySpuId(Integer spuId);





}
