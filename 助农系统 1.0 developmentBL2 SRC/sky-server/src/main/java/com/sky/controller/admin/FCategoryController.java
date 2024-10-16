package com.sky.controller.admin;

import com.sky.dto.CategoryAddDTO;
import com.sky.entity.PrimaryClassification;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.FCategoryService;
import com.sky.service.impl.FCategoryServiceImpl;
import com.sky.vo.FcactoryInfo;
import com.sky.vo.Fcategorylist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category1")
public class FCategoryController {


    @Autowired
    private FCategoryService categoryService;

    @GetMapping("/list")
    Result getCategoryList(){

       List<Fcategorylist> list= categoryService.listName();
        return Result.success(list);
    }
    @GetMapping("/{id}")
    Result getCategoryInfo(@PathVariable("id") Integer id){
         FcactoryInfo fcactoryInfo= categoryService.getInfo(id);
         if(fcactoryInfo!=null)
         return Result.success(fcactoryInfo);
         else return Result.error("无该id对应信息");

        //System.out.println("id = " + id);
        //return null;
    }


    @PostMapping
    Result addCategory(@RequestBody CategoryAddDTO categoryAddDTO) {


        Boolean is_insert = categoryService.addCategory(categoryAddDTO);


        if (is_insert == true) {
            Result<String> result = Result.success(new String("插入啦"));

            return result;
        }
        Result<String> result = Result.error("错误啦");
        return result;
    }
    @GetMapping("/page")
     public Result<PageResult> pageQuery(String name, Integer page, Integer pageSize){
         PageResult pageResult=categoryService.pageQuery(name, page, pageSize);
         if(pageResult!=null) {
             return Result.success(pageResult);
         }
         else return Result.error("分页查询失败");
     }
}
