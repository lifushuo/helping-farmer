package com.sky.controller.admin;

import com.sky.dao.SpuDao;
import com.sky.dto.SpuAddDto;
import com.sky.dto.SpuEditDto;
import com.sky.entity.Spu;
import com.sky.result.Result;
import com.sky.service.SpuService;
import com.sky.vo.GetSpuInfoDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/spu")
public class SpuController {
    @Autowired
    private SpuService spuService;
    @Autowired
    private SpuDao spuDao;


    /**
     * 根据 分类1 或者分类2 的Id 去获取 spu 的ID和名字
     */
    @GetMapping
    Result getList(@RequestParam("category1Id") Integer category1Id, @RequestParam("category2Id") Integer category2Id) {
        Result result = spuService.getList(category1Id, category2Id);
        return result;

    }


    @PostMapping
    Result addSpu(@RequestBody SpuAddDto spuAddDto) {
        Result result = spuService.addSpu(spuAddDto);
        return result;
    }

    @PostMapping("/status/{status}")
    Result setStatus(@RequestParam("id") Integer id, @PathVariable Integer status) {
      /*  System.out.println("id = " + id + ", status = " + status);
        return null;*/
        Result result = spuService.setStatus(id, status);

        return result;
    }

    @PutMapping
    Result editSpu(@RequestBody SpuEditDto spuEditDto) {
        /*System.out.println("spuEditDto = " + spuEditDto);
        return null;*/
        Result result = spuService.editSpu(spuEditDto);
        return result;
    }

    @DeleteMapping
    Result deleteSpu(Integer id) {
        Result result = spuService.deleteById(id);
        return result;
    }
//    @GetMapping
//    Result getSpuIdandName(Integer category1Id,Integer category2Id){
////        System.out.println("category1Id = " + category1Id);
////        return null;
//        return null;
//
//    }


    @GetMapping("/page")
    Result page(@RequestParam("pageSize") Integer pageSize,
                @RequestParam("pageNumber") Integer pageNumber,
                @RequestParam(value = "category1Id", required = false) Integer category1Id,
                @RequestParam(value = "category2Id", required = false) Integer category2Id) {


        System.out.println("pageSize = " + pageSize + ", pageNumber = " + pageNumber + ", category1Id = " + category1Id + ", category2Id = " + category2Id);

        Result result = spuService.page(pageNumber, pageSize, category1Id, category2Id);


        return result;

    }



    @GetMapping("{spuId}")
    Result getSpuInfo(@PathVariable Integer spuId) {

       Result result= spuService.getSpuInfo(spuId);

       return result;


    }


}
