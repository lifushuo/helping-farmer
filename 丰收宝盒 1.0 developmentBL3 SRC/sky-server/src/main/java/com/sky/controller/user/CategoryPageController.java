package com.sky.controller.user;

import com.sky.result.Result;
import com.sky.service.CategoryPageService;
import com.sky.vo.FirstCategoryPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/category/list")
public class CategoryPageController {
    @Autowired
    CategoryPageService categoryPageService;
    @GetMapping
    public Result getCategoryPage(){
        List<FirstCategoryPageVO> lists=categoryPageService.getCategoryPage();
        return Result.success(lists);
    }
}
