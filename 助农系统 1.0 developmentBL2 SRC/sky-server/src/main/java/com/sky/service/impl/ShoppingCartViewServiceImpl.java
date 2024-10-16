package com.sky.service.impl;

import com.sky.entity.ShoppingCartView;
import com.sky.dao.ShoppingCartViewDao;
import com.sky.service.ShoppingCartViewService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;

/**
 * (ShoppingCartView)表服务实现类
 *
 * @author makejava
 * @since 2024-05-19 17:11:58
 */
@Service("shoppingCartViewService")
public class ShoppingCartViewServiceImpl implements ShoppingCartViewService {
    @Resource
    private ShoppingCartViewDao shoppingCartViewDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ShoppingCartView queryById(Integer id) {
        return this.shoppingCartViewDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param shoppingCartView 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<ShoppingCartView> queryByPage(ShoppingCartView shoppingCartView, PageRequest pageRequest) {
        long total = this.shoppingCartViewDao.count(shoppingCartView);
        return new PageImpl<>(this.shoppingCartViewDao.queryAllByLimit(shoppingCartView, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param shoppingCartView 实例对象
     * @return 实例对象
     */
    @Override
    public ShoppingCartView insert(ShoppingCartView shoppingCartView) {
        this.shoppingCartViewDao.insert(shoppingCartView);
        return shoppingCartView;
    }

    /**
     * 修改数据
     *
     * @param shoppingCartView 实例对象
     * @return 实例对象
     */
    @Override
    public ShoppingCartView update(ShoppingCartView shoppingCartView) {
        this.shoppingCartViewDao.update(shoppingCartView);
        return this.queryById(shoppingCartView.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.shoppingCartViewDao.deleteById(id) > 0;
    }
}
