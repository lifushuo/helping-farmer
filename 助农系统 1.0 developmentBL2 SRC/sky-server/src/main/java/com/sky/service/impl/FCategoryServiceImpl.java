package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dao.FCategoryDao;
import com.sky.dto.CategoryAddDTO;
import com.sky.entity.PrimaryClassification;
import com.sky.result.PageResult;
import com.sky.service.FCategoryService;
import com.sky.vo.FcactoryInfo;
import com.sky.vo.Fcategorylist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FCategoryServiceImpl implements FCategoryService {


    @Autowired
    private FCategoryDao categoryDao;


    @Override
    public PageResult pageQuery(String name, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        Page<PrimaryClassification> newPage=categoryDao.page(name);

        List<PrimaryClassification> result = newPage.getResult();
        long total = newPage.getTotal();


        return new  PageResult(total,result);
    }

    @Override
    public Boolean addCategory(CategoryAddDTO categoryAddDTO) {

        PrimaryClassification primaryClassification = new PrimaryClassification();
        primaryClassification.setIcon(categoryAddDTO.getIcon());
        primaryClassification.setName(categoryAddDTO.getName());


        Boolean is_insert = categoryDao.insert(primaryClassification);
        if (is_insert) {
            return true;
        }
        return false;
    }

    @Override
    public List<Fcategorylist> listName() {

        List<PrimaryClassification> list = categoryDao.getAll();
        ArrayList<Fcategorylist> fcategorylists = new ArrayList<>();

        for (int i=0;i<list.size();i++) {

            Fcategorylist fcategorylist = new Fcategorylist();

            PrimaryClassification primaryClassification = list.get(i);
            fcategorylist.setName(primaryClassification.getName());
            fcategorylist.setId1123(primaryClassification.getId());

            fcategorylists.add(fcategorylist);

        }


        if (fcategorylists.size() == 0) {
            return null;
        } else {
            return fcategorylists;
        }


    }

    @Override
    public FcactoryInfo getInfo(Integer id) {
        PrimaryClassification p=categoryDao.getInfo(id);
        FcactoryInfo factoryInfo=new FcactoryInfo();
        factoryInfo.setId(p.getId());
        factoryInfo.setName(p.getName());
        factoryInfo.setIcon(p.getIcon());
        factoryInfo.setIsEffective(p.getIsEffective().equals("1")?1:0);


        if(factoryInfo == null) {
            return null;
        }
        else return factoryInfo;
    }
}
