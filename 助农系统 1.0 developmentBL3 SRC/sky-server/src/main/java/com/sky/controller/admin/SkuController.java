package com.sky.controller.admin;

import com.sky.dto.Category2AddDto;
import com.sky.dto.Category2EditDto;
import com.sky.dto.SkuAddDto;
import com.sky.dto.SkuEditDto;
import com.sky.entity.SecondaryClassification;
import com.sky.entity.Sku;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SecondaryClassificationService;
import com.sky.service.SkuService;
import com.sky.vo.SkuQueryForEditVO;
import com.sky.vo.SkuQueryForSpuIdVO;
import com.sky.vo.SkuQueryVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (SecondaryClassification)表控制层
 *
 * @author makejava
 * @since 2024-05-19 17:11:26
 */
@RestController
@RequestMapping("/admin/sku")
public class SkuController {
    /**
     * 服务对象
     */
    @Autowired
    SkuService skuService;


    /**
     * 新增Sku
     * @param skuAddDto
     * @return
     */
    @PostMapping
    Result addSku(@RequestBody SkuAddDto skuAddDto){


        Result result= skuService.addSku(skuAddDto);

        return result;
    }

    /**
     * 修改Sku
     */
    @PutMapping
    Result editSku(@RequestBody SkuEditDto skuEditDto){
        Result result=skuService.editSku(skuEditDto);

        return result;
    }


    /**
     * 删除sku
     */
    @DeleteMapping
    Result deleteSku(@RequestParam("id") Integer id){

        Result result= skuService.deleteById(id);


        return result;

    }
    /**
     * 设置起售停售
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    Result setStatus(@RequestParam("id")Integer id ,@PathVariable Integer status){

        Result result= skuService.setStatus(id,status);

        return result;
    }

    /**
     * 根据id查询sku详细信息
     */
    @GetMapping("/detail")
    Result queryById(@RequestParam("id")Integer id){

        SkuQueryVO s=skuService.queryById(id);

        return Result.success(s);

    }

    /**
     * 根据id查询sku信息供编辑使用
     */

    @GetMapping("/edit/detail")
    Result queryByIdForEdit(@RequestParam("id")Integer id){

        SkuQueryForEditVO s= skuService.queryByIdForEdit(id);

        return Result.success(s);

    }


    /**
     * 根据spuId获取对应的全部sku
     */
    @GetMapping("list")
    Result queryListBySpuId(@RequestParam("spuId")Integer spuId){

        SkuQueryForSpuIdVO s =skuService.queryListBySpuId(spuId);
        return Result.success(s);
    }
}

