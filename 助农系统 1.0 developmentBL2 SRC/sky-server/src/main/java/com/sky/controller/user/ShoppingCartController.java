package com.sky.controller.user;

import com.sky.dto.ShoppingCartAddDTO;
import com.sky.dto.ShoppingCartEditDTO;
import com.sky.entity.Sku;
import com.sky.result.Result;
import com.sky.service.ShoppingCartService;
import com.sky.vo.ShoppingCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/shoppingCart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @PostMapping("add")
    public Result addShoppingCart(@RequestBody ShoppingCartAddDTO s){

       Result result= shoppingCartService.addShoppingCart(s);


        return result;
    }
    @PutMapping("selected")
    public Result shoppingCartAllSelected(boolean selected){

        Result result = shoppingCartService.shoppingCartAllSelected(selected);


        return result;
    }

    @PutMapping("/{skuId}")
    public Result editShoppingCart(@PathVariable("skuId") Integer skuId, @RequestBody ShoppingCartEditDTO s){
        Result result=shoppingCartService.editShoppingCart(skuId,s);
        return result;
    }
    @DeleteMapping("clean")
    public Result cleanShoppingCart(@RequestParam("ids")List<Integer> ids){
        Result result=shoppingCartService.cleanShoppingCart(ids);
        return result;
    }
    //查看购物车
    @GetMapping("/list")
    public Result checkShoppingCart(){
        List<ShoppingCartVO> lists=shoppingCartService.checkShoppingCart();
        return Result.success(lists);
    }

}
