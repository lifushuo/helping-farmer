package com.sky.service;

import com.sky.entity.ShoppingCartView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (ShoppingCartView)表服务接口
 *
 * @author makejava
 * @since 2024-05-19 17:11:58
 */
public interface ShoppingCartViewService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ShoppingCartView queryById(Integer id);

    /**
     * 分页查询
     *
     * @param shoppingCartView 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<ShoppingCartView> queryByPage(ShoppingCartView shoppingCartView, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param shoppingCartView 实例对象
     * @return 实例对象
     */
    ShoppingCartView insert(ShoppingCartView shoppingCartView);

    /**
     * 修改数据
     *
     * @param shoppingCartView 实例对象
     * @return 实例对象
     */
    ShoppingCartView update(ShoppingCartView shoppingCartView);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
