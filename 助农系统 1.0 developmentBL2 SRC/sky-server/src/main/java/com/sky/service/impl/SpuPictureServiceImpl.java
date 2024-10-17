package com.sky.service.impl;

import com.sky.entity.SpuPicture;
import com.sky.dao.SpuPictureDao;
import com.sky.service.SpuPictureService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (SpuPicture)表服务实现类
 *
 * @author makejava
 * @since 2024-05-19 17:12:38
 */
@Service("spuPictureService")
public class SpuPictureServiceImpl implements SpuPictureService {
    @Resource
    private SpuPictureDao spuPictureDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SpuPicture queryById(Integer id) {
        return this.spuPictureDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param spuPicture 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<SpuPicture> queryByPage(SpuPicture spuPicture, PageRequest pageRequest) {
        long total = this.spuPictureDao.count(spuPicture);
        return new PageImpl<>(this.spuPictureDao.queryAllByLimit(spuPicture, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param spuPicture 实例对象
     * @return 实例对象
     */
    @Override
    public SpuPicture insert(SpuPicture spuPicture) {
        this.spuPictureDao.insert(spuPicture);
        return spuPicture;
    }

    /**
     * 修改数据
     *
     * @param spuPicture 实例对象
     * @return 实例对象
     */
    @Override
    public SpuPicture update(SpuPicture spuPicture) {
        this.spuPictureDao.update(spuPicture);
        return this.queryById(spuPicture.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.spuPictureDao.deleteById(id) > 0;
    }
}
