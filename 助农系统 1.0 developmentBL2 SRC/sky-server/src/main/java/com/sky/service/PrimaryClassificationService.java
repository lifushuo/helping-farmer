package com.sky.service;

import com.sky.dto.PrimaryCategoryAddDTO;
import com.sky.entity.PrimaryClassification;
import com.sky.result.PageResult;
import com.sky.vo.PrimaryCategoryListVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (PrimaryClassification)表服务接口
 *
 * @author makejava
 * @since 2024-05-19 16:54:07
 */
public interface PrimaryClassificationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PrimaryClassification queryById(Integer id);

    /**
     * 分页查询
     *
     * @param primaryClassification 筛选条件
     * @param pageRequest           分页对象
     * @return 查询结果
     */
    Page<PrimaryClassification> queryByPage(PrimaryClassification primaryClassification, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param primaryClassification 实例对象
     * @return 实例对象
     */
    PrimaryClassification insert(PrimaryClassification primaryClassification);

    /**
     * 修改数据
     *
     * @param primaryClassification 实例对象
     * @return 实例对象
     */
    PrimaryClassification update(PrimaryClassification primaryClassification);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Boolean addPrimaryClassification(PrimaryCategoryAddDTO primaryCategoryAddDTO);

    PageResult page(String naeme, Integer page, Integer pageSize);

    boolean UpOrDownStatus(Integer id, Integer status);


    List<PrimaryCategoryListVo> listData();

}
