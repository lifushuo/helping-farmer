package com.sky.service;

import com.sky.entity.Star;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Collection)表服务接口
 *
 * @author makejava
 * @since 2024-05-19 16:51:38
 */
public interface CollectionService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Star queryById(Integer id);

    /**
     * 分页查询
     *
     * @param star 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Star> queryByPage(Star star, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param star 实例对象
     * @return 实例对象
     */
    Star insert(Star star);

    /**
     * 修改数据
     *
     * @param star 实例对象
     * @return 实例对象
     */
    Star update(Star star);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
