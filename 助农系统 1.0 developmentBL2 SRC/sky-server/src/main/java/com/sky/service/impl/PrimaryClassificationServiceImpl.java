package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.sky.dto.PrimaryCategoryAddDTO;
import com.sky.entity.PrimaryClassification;
import com.sky.dao.PrimaryClassificationDao;
import com.sky.result.PageResult;
import com.sky.service.PrimaryClassificationService;
import com.sky.vo.PrimaryCategoryListVo;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (PrimaryClassification)表服务实现类
 *
 * @author makejava
 * @since 2024-05-19 16:54:07
 */
@Service("primaryClassificationService")
public class PrimaryClassificationServiceImpl implements PrimaryClassificationService {
    @Resource
    private PrimaryClassificationDao primaryClassificationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PrimaryClassification queryById(Integer id) {
        return this.primaryClassificationDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param primaryClassification 筛选条件
     * @param pageRequest           分页对象
     * @return 查询结果
     */
    @Override
    public Page<PrimaryClassification> queryByPage(PrimaryClassification primaryClassification, PageRequest pageRequest) {
        long total = this.primaryClassificationDao.count(primaryClassification);
        List<PrimaryClassification> primaryClassifications = this.primaryClassificationDao.queryAllByLimit(primaryClassification, pageRequest);
        return new PageImpl<>(primaryClassifications, pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param primaryClassification 实例对象
     * @return 实例对象
     */
    @Override
    public PrimaryClassification insert(PrimaryClassification primaryClassification) {
        this.primaryClassificationDao.insert(primaryClassification);
        return primaryClassification;
    }

    /**
     * 修改数据
     *
     * @param primaryClassification 实例对象
     * @return 实例对象
     */
    @Override
    @Transactional
    public PrimaryClassification update(PrimaryClassification primaryClassification) {
        this.primaryClassificationDao.update(primaryClassification);
        return this.queryById(primaryClassification.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {

        PrimaryClassification build = PrimaryClassification.builder().id(id).isDeleted("1").build();
        int update = this.primaryClassificationDao.update(build);

        if (update >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean addPrimaryClassification(PrimaryCategoryAddDTO p) {

        PrimaryClassification primaryClassification = new PrimaryClassification();
        primaryClassification.setName(p.getName());
        primaryClassification.setIcon(p.getIcon());
        primaryClassification.setIsDeleted("0");
        primaryClassification.setIsEffective("1");

        int insert = primaryClassificationDao.insert(primaryClassification);
        if (insert > 0) {
            return true;
        }
        return false;


    }

    @Override
    public PageResult page(String name, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        com.github.pagehelper.Page<PrimaryClassification> p = primaryClassificationDao.page(name);

        long total = p.getTotal();
        List<PrimaryClassification> result = p.getResult();


        return new PageResult(total, result);


    }

    @Override
    public boolean UpOrDownStatus(Integer id, Integer status) {
        PrimaryClassification build = PrimaryClassification.builder().id(id).isEffective(String.valueOf(status)).build();

        int update = primaryClassificationDao.update(build);
        if (update >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public List<PrimaryCategoryListVo> listData() {
        List<PrimaryCategoryListVo> list = primaryClassificationDao.listData();
        return list;
    }

}
