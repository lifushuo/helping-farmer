package com.sky.service;

import com.sky.dto.CategoryAddDTO;
import com.sky.entity.PrimaryClassification;
import com.sky.result.PageResult;
import com.sky.vo.FcactoryInfo;
import com.sky.vo.Fcategorylist;

import java.util.List;

public interface FCategoryService {

    PageResult pageQuery(String name, Integer page, Integer pageSize);

    /**
     * 添加一个 第一分类
     * @param categoryAddDTO
     * @return
     */
    Boolean addCategory(CategoryAddDTO categoryAddDTO);

    List<Fcategorylist> listName();

    FcactoryInfo getInfo(Integer id);
}
