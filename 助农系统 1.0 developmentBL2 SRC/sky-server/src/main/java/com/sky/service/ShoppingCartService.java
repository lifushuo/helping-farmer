package com.sky.service;

import com.sky.dto.ShoppingCartAddDTO;
import com.sky.dto.ShoppingCartEditDTO;
import com.sky.entity.ShoppingCart;
import com.sky.entity.Sku;
import com.sky.result.Result;
import com.sky.vo.ShoppingCartVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * (ShoppingCart)表服务接口
 *
 * @author makejava
 * @since 2024-05-19 17:11:52
 */
public interface ShoppingCartService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ShoppingCart queryById(Integer id);

    /**
     * 分页查询
     *
     * @param shoppingCart 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<ShoppingCart> queryByPage(ShoppingCart shoppingCart, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param shoppingCart 实例对象
     * @return 实例对象
     */
    ShoppingCart insert(ShoppingCart shoppingCart);

    /**
     * 修改数据
     *
     * @param shoppingCart 实例对象
     * @return 实例对象
     */
    ShoppingCart update(ShoppingCart shoppingCart);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Result addShoppingCart(ShoppingCartAddDTO s);

    Result shoppingCartAllSelected(boolean selected);

    Result editShoppingCart(Integer skuId, ShoppingCartEditDTO s);

    Result cleanShoppingCart(List<Integer> ids);

    List<ShoppingCartVO> checkShoppingCart();
}
