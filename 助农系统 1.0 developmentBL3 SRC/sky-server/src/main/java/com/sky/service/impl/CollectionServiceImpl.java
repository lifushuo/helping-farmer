package com.sky.service.impl;

import com.sky.entity.Star;
import com.sky.dao.CollectionDao;
import com.sky.service.CollectionService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (Collection)表服务实现类
 *
 * @author makejava
 * @since 2024-05-19 16:51:38
 */
@Service("collectionService")
public class CollectionServiceImpl implements CollectionService {
    @Resource
    private CollectionDao collectionDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Star queryById(Integer id) {
        return this.collectionDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param star 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Star> queryByPage(Star star, PageRequest pageRequest) {
        long total = this.collectionDao.count(star);
        return new PageImpl<>(this.collectionDao.queryAllByLimit(star, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param star 实例对象
     * @return 实例对象
     */
    @Override
    public Star insert(Star star) {
        this.collectionDao.insert(star);
        return star;
    }

    /**
     * 修改数据
     *
     * @param star 实例对象
     * @return 实例对象
     */
    @Override
    public Star update(Star star) {
        this.collectionDao.update(star);
        return this.queryById(star.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.collectionDao.deleteById(id) > 0;
    }
}
