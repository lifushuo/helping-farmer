package com.sky.service;

import com.sky.entity.SkuAttri;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (SkuAttri)表服务接口
 *
 * @author makejava
 * @since 2024-05-19 17:12:14
 */
public interface SkuAttriService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SkuAttri queryById(Integer id);

    /**
     * 分页查询
     *
     * @param skuAttri 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<SkuAttri> queryByPage(SkuAttri skuAttri, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param skuAttri 实例对象
     * @return 实例对象
     */
    SkuAttri insert(SkuAttri skuAttri);

    /**
     * 修改数据
     *
     * @param skuAttri 实例对象
     * @return 实例对象
     */
    SkuAttri update(SkuAttri skuAttri);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
