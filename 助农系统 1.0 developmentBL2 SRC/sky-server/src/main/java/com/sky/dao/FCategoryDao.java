package com.sky.dao;


import com.github.pagehelper.Page;
import com.sky.entity.PrimaryClassification;
import com.sky.entity.Spu;
import com.sky.vo.Fcategorylist;
import com.sky.vo.FirstCategoryPageVO;
import com.sky.vo.GoodsVO;
import com.sky.vo.SecondCategoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FCategoryDao {


    Boolean insert(PrimaryClassification primaryClassification);

    List<PrimaryClassification> getAll();

    PrimaryClassification getInfo(Integer id);

    Page<PrimaryClassification> page(String name);

    List<FirstCategoryPageVO> getFirstcategory();

    List<SecondCategoryVO> getSecondcategory(String firstId);

    List<GoodsVO> getSpuInfo(String secondId);
}
