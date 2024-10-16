package com.sky.service;

import com.sky.entity.SpuPicture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (SpuPicture)表服务接口
 *
 * @author makejava
 * @since 2024-05-19 17:12:38
 */
public interface SpuPictureService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SpuPicture queryById(Integer id);

    /**
     * 分页查询
     *
     * @param spuPicture 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<SpuPicture> queryByPage(SpuPicture spuPicture, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param spuPicture 实例对象
     * @return 实例对象
     */
    SpuPicture insert(SpuPicture spuPicture);

    /**
     * 修改数据
     *
     * @param spuPicture 实例对象
     * @return 实例对象
     */
    SpuPicture update(SpuPicture spuPicture);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
