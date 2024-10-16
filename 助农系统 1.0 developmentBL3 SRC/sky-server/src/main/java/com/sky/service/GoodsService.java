package com.sky.service;


import com.sky.result.Result;
import com.sky.vo.GoodsDetailVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


public interface GoodsService {


    Result getGoodsPage(Integer page, Integer pageSize);

    GoodsDetailVO getGoodsDetail(Integer spuId);
}
