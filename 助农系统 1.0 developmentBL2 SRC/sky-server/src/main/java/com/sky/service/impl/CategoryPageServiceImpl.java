package com.sky.service.impl;

import com.sky.dao.FCategoryDao;
import com.sky.dao.SpuPictureDao;
import com.sky.entity.Spu;
import com.sky.entity.SpuPicture;
import com.sky.service.CategoryPageService;
import com.sky.vo.FirstCategoryPageVO;
import com.sky.vo.GoodsVO;
import com.sky.vo.SecondCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categoryPageService")
public class CategoryPageServiceImpl implements CategoryPageService {
    @Autowired
    FCategoryDao categoryDao;

    @Autowired
    SpuPictureDao spuPictureDao;

    @Override
    public List<FirstCategoryPageVO> getCategoryPage() {
        List<FirstCategoryPageVO> firstlists = categoryDao.getFirstcategory();
        String firstId = null;
        String secondId = null;
        for (int k = 0; k < firstlists.size(); k++) {
            firstId = firstlists.get(k).getId();
            List<SecondCategoryVO> secondlists = categoryDao.getSecondcategory(firstId);
            firstlists.get(k).setChildren(secondlists);
            for (int i = 0; i < secondlists.size(); i++) {
                secondId = secondlists.get(i).getId();
                List<GoodsVO> spulists = categoryDao.getSpuInfo(secondId);
                for (GoodsVO vo : spulists) {
                    List<SpuPicture> l = spuPictureDao.selectBySpuId(vo.getId());
                    if (l.size() != 0) {
                        vo.setPicture(l.get(0).getPictureUrl());
                    }
                }
                secondlists.get(i).setGoods(spulists);
            }
        }
        return firstlists;
    }
}


