package com.sky.dao;


import com.github.pagehelper.Page;
import com.sky.entity.Item;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;



@Mapper
public interface GoodsDao {


    Integer getGoodsCount();

    Page<Item> getGoodsItems();
}

