package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dto.ShoppingCartAddDTO;
import com.sky.dto.ShoppingCartEditDTO;
import com.sky.entity.ShoppingCart;
import com.sky.dao.ShoppingCartDao;
import com.sky.entity.Sku;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import com.sky.vo.AttriTextVO;
import com.sky.vo.ShoppingCartVO;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (ShoppingCart)表服务实现类
 *
 * @author makejava
 * @since 2024-05-19 17:11:52
 */
@Service("shoppingCartService")
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Resource
    private ShoppingCartDao shoppingCartDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ShoppingCart queryById(Integer id) {
        return this.shoppingCartDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param shoppingCart 筛选条件
     * @param pageRequest  分页对象
     * @return 查询结果
     */
    @Override
    public Page<ShoppingCart> queryByPage(ShoppingCart shoppingCart, PageRequest pageRequest) {
        long total = this.shoppingCartDao.count(shoppingCart);
        return new PageImpl<>(this.shoppingCartDao.queryAllByLimit(shoppingCart, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param shoppingCart 实例对象
     * @return 实例对象
     */
    @Override
    public ShoppingCart insert(ShoppingCart shoppingCart) {
        this.shoppingCartDao.insert(shoppingCart);
        return shoppingCart;
    }

    /**
     * 修改数据
     *
     * @param shoppingCart 实例对象
     * @return 实例对象
     */
    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        this.shoppingCartDao.update(shoppingCart);
        return this.queryById(shoppingCart.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.shoppingCartDao.deleteById(id) > 0;
    }

    @Override
    public Result addShoppingCart(ShoppingCartAddDTO s) {

        // 这是获取到当前用户的id
        Long currentId = BaseContext.getCurrentId();

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setSkuId(Math.toIntExact(s.getSkuId()));
        shoppingCart.setCount(Math.toIntExact(s.getCount()));
        shoppingCart.setUserId(Math.toIntExact(currentId));

        shoppingCart.setIsDeleted("0");
        shoppingCart.setIsSelected("1");

        int insert = shoppingCartDao.insert(shoppingCart);
        if (insert > 0) {
            return Result.success();
        }

        return Result.error("添加失败");
    }

    @Override
    public Result shoppingCartAllSelected(boolean selected) {

        Long currentId = BaseContext.getCurrentId();
        int update = shoppingCartDao.letAllSelected(currentId, selected);
        if (update > 0) {
            return Result.success();
        }
        return Result.error("操作失败");
    }

    @Override
    public Result editShoppingCart(Integer skuId, ShoppingCartEditDTO s) {
        Long currentId = BaseContext.getCurrentId();
        Long selected = s.getSelected();
        Long count = s.getCount();
        int edit = shoppingCartDao.editShoppingCart(currentId, skuId, selected, count);
        if (edit > 0) {
            return Result.success();
        }
        return Result.error("修改失败");
    }

    @Override
    public Result cleanShoppingCart(List<Integer> ids) {
        Long currentId = BaseContext.getCurrentId();
        int sum = 0;
        Integer skuId;
        for (int i = 0; i < ids.size(); i++) {
            skuId = ids.get(i);
            int is_deleted = shoppingCartDao.cleanCart(currentId, skuId);
            sum += is_deleted;
        }
        if (sum == 0) {
            return Result.error("购物车无该商品");
        }
        return Result.success();
    }

    @Override
    public List<ShoppingCartVO> checkShoppingCart() {
        Long currentId = BaseContext.getCurrentId();
        Long skuId;
        List<ShoppingCartVO> lists = shoppingCartDao.check(currentId);
        for (int i = 0; i < lists.size(); i++) {
            lists.get(i).setIs_selected(lists.get(i).getXunzhong() == 1 ? true : false);
            skuId = lists.get(i).getSkuId();
            List<AttriTextVO> attrilist = shoppingCartDao.findattri(skuId);
            String tempStr = "";
            for (int j = 0; j < attrilist.size(); j++) {
                tempStr += attrilist.get(j).getName() + ":" + attrilist.get(j).getValue() + "  ";
            }
            lists.get(i).setAttrsText(tempStr);
        }
        return lists;
    }
}
