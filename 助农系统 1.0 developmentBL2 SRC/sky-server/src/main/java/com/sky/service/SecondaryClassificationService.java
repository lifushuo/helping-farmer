package com.sky.service;

import com.sky.dto.Category2AddDto;
import com.sky.dto.Category2EditDto;
import com.sky.entity.SecondaryClassification;
import com.sky.result.PageResult;
import com.sky.result.Result;

import java.util.List;

/**
 *
 * (SecondaryClassification)表服务接口
 *
 * @author makejava
 * @since 2024-05-19 17:11:26
 */
public interface SecondaryClassificationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SecondaryClassification queryById(Integer id);



    /**
     * 新增数据
     *
     * @param secondaryClassification 实例对象
     * @return 实例对象
     */
    boolean insert(Category2AddDto secondaryClassification);

    /**
     * 修改数据
     *
     * @param secondaryClassification 实例对象
     * @return 实例对象
     */
    SecondaryClassification update(SecondaryClassification secondaryClassification);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Result deleteById(Integer id);

    PageResult queryPage(Integer page, Integer pageSize, String name, Integer category1Id);


    /**
     * 查询所有数据
     * @return
     */
    List<SecondaryClassification> list(Integer id);

    /**
     * 更新数据
     * @param category2EditDto
     * @return
     */
    boolean edit(Category2EditDto category2EditDto);



    Result upOrDown(Integer status, Integer id);
}
