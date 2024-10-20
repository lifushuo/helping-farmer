package com.sky.service.impl;

import com.sky.entity.SkuAttri;
import com.sky.dao.SkuAttriDao;
import com.sky.service.SkuAttriService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SkuAttri)表服务实现类
 *
 * @author makejava
 * @since 2024-05-19 17:12:14
 */
@Service("skuAttriService")
public class SkuAttriServiceImpl implements SkuAttriService {
    @Resource
    private SkuAttriDao skuAttriDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SkuAttri queryById(Integer id) {
        return this.skuAttriDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param skuAttri 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<SkuAttri> queryByPage(SkuAttri skuAttri, PageRequest pageRequest) {
        long total = this.skuAttriDao.count(skuAttri);
        return new PageImpl<>(this.skuAttriDao.queryAllByLimit(skuAttri, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param skuAttri 实例对象
     * @return 实例对象
     */
    @Override
    public SkuAttri insert(SkuAttri skuAttri) {
        this.skuAttriDao.insert(skuAttri);
        return skuAttri;
    }

    /**
     * 修改数据
     *
     * @param skuAttri 实例对象
     * @return 实例对象
     */
    @Override
    public SkuAttri update(SkuAttri skuAttri) {
        this.skuAttriDao.update(skuAttri);
        return this.queryById(skuAttri.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.skuAttriDao.deleteById(id) > 0;
    }
}
