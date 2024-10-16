package com.sky.controller.user;

import com.sky.result.Result;
import com.sky.service.GoodsService;
import com.sky.vo.GoodsDetailVO;
import com.sky.vo.GoodsPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;


    @GetMapping("/page")
    public Result<GoodsPageVO> goodsPage(@RequestParam Integer page, @RequestParam Integer pageSize) {
        return goodsService.getGoodsPage(page, pageSize);
    }

    //@GetMapping("/detail")
    //public Result goodsDetail(@RequestParam Integer skuId){
    //    System.out.println(123);
    //    return null;
    //}

    @GetMapping("/detail")
    public Result goodsDetail(@RequestParam Integer id) {
        GoodsDetailVO g = goodsService.getGoodsDetail(id);
        return Result.success(g);
    }

}
