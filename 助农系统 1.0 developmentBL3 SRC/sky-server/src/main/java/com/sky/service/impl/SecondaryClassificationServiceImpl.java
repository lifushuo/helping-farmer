package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.dao.PrimaryClassificationDao;
import com.sky.dto.Category2AddDto;
import com.sky.dto.Category2EditDto;
import com.sky.entity.PrimaryClassification;
import com.sky.entity.SecondaryClassification;
import com.sky.dao.SecondaryClassificationDao;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SecondaryClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * (SecondaryClassification)表服务实现类
 *
 * @author makejava
 * @since 2024-05-19 17:11:26
 */
@Service("secondaryClassificationService")
public class SecondaryClassificationServiceImpl implements SecondaryClassificationService {
    @Autowired
    private SecondaryClassificationDao secondaryClassificationDao;
    @Autowired
    private PrimaryClassificationDao primaryClassificationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SecondaryClassification queryById(Integer id) {
        return this.secondaryClassificationDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param c2 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(Category2AddDto c2) {
        // 先判断是否已经存在一级分类
        long category1Id = c2.getCategory1Id();
        PrimaryClassification p1 = primaryClassificationDao.queryById((int) category1Id);
        // 一级分类不存在
        if (p1 == null) {
            return false;
        }

        // 准备数据
        SecondaryClassification s2 = new SecondaryClassification();

        s2.setName(c2.getName());
        s2.setIsDeleted("0");
        s2.setParentId(String.valueOf(p1.getId()));
        s2.setParentName(p1.getName());
        s2.setIsEffective("1");

        // 二级分类插入
        int insert = secondaryClassificationDao.insert(s2);
        if (insert >= 1) {
            return true;
        }
        return false;


    }

    /**
     * 修改数据
     *
     * @param secondaryClassification 实例对象
     * @return 实例对象
     */
    @Override
    public SecondaryClassification update(SecondaryClassification secondaryClassification) {
        this.secondaryClassificationDao.update(secondaryClassification);
        return this.queryById(secondaryClassification.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Result deleteById(Integer id) {


        int delete = secondaryClassificationDao.deleteById(id);
        if (delete == 0) {
            return Result.error("没有这个值");
        }
        return Result.success();


    }

    @Override
    public PageResult queryPage(Integer page, Integer pageSize, String name, Integer category1Id) {

        PageHelper.startPage(page, pageSize);


        // 这里的 page 必须是 import com.github.pagehelper.Page;
        Page<SecondaryClassification> pageData = secondaryClassificationDao.pageByName(name,category1Id);

        long total = pageData.getTotal();
        List<SecondaryClassification> result = pageData.getResult();

        return new PageResult(total, result);


    }

    @Override
    public List<SecondaryClassification> list(Integer id) {


        List<SecondaryClassification> data = secondaryClassificationDao.selectList(id);

        return data;
    }

    @Override
    public boolean edit(Category2EditDto category2EditDto) {
        SecondaryClassification s = secondaryClassificationDao.queryById(Math.toIntExact(category2EditDto.getId()));
        if (s == null) {
            System.out.println("不存在二级分类 无法修改");
            return false;
        }

        // 准备参数 进行 数据库插入
        SecondaryClassification s2 = new SecondaryClassification();
        s2.setId(Math.toIntExact(category2EditDto.getId()));
        s2.setName(category2EditDto.getName());
        s2.setParentId(String.valueOf(category2EditDto.getCategory1Id()));


        // 数据库插入
        int update = secondaryClassificationDao.update(s2);
        if (update > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Result upOrDown(Integer status, Integer id) {

        SecondaryClassification s = new SecondaryClassification();
        s.setId(id);
        s.setIsEffective(String.valueOf(status));
        int update = secondaryClassificationDao.update(s);
        if (update > 0) {
            return Result.success();
        } else {
            return Result.error("更新失败");
        }


    }
}
