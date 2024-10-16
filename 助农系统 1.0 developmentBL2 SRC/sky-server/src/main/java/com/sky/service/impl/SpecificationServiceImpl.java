package com.sky.service.impl;

import com.sky.entity.Specification;
import com.sky.dao.SpecificationDao;
import com.sky.service.SpecificationService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Specification)表服务实现类
 *
 * @author makejava
 * @since 2024-05-19 17:12:23
 */
@Service("specificationService")
public class SpecificationServiceImpl implements SpecificationService {
    @Resource
    private SpecificationDao specificationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Specification queryById(Integer id) {
        return this.specificationDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param specification 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Specification> queryByPage(Specification specification, PageRequest pageRequest) {
        long total = this.specificationDao.count(specification);
        return new PageImpl<>(this.specificationDao.queryAllByLimit(specification, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param specification 实例对象
     * @return 实例对象
     */
    @Override
    public Specification insert(Specification specification) {
        this.specificationDao.insert(specification);
        return specification;
    }

    /**
     * 修改数据
     *
     * @param specification 实例对象
     * @return 实例对象
     */
    @Override
    public Specification update(Specification specification) {
        this.specificationDao.update(specification);
        return this.queryById(specification.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.specificationDao.deleteById(id) > 0;
    }
}
