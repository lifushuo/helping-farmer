package com.sky.controller.admin;

import com.sky.dto.Category2AddDto;
import com.sky.dto.Category2EditDto;
import com.sky.entity.SecondaryClassification;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SecondaryClassificationService;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (SecondaryClassification)表控制层
 *
 * @author makejava
 * @since 2024-05-19 17:11:26
 */
@RestController
@RequestMapping("/admin/category2")
public class SecondaryClassificationController {
    /**
     * 服务对象
     */
    @Autowired
    private SecondaryClassificationService secondaryClassificationService;


    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @param category1Id
     * @return
     */
    @GetMapping("/page")
    public Result queryByPage(Integer page, Integer pageSize, String name, Integer category1Id) {

        System.out.println("page = " + page + ", pageSize = " + pageSize + ", name = " + name + ", category1Id = " + category1Id);

        PageResult pages = secondaryClassificationService.queryPage(page, pageSize, name, category1Id);

        return Result.success(pages);


    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Result<SecondaryClassification> queryById(@PathVariable("id") Integer id) {
        SecondaryClassification secondaryClassification = secondaryClassificationService.queryById(id);
        if (secondaryClassification != null) {
            return Result.success(secondaryClassification);
        } else {
            return Result.error("没有和这个信息");
        }
    }

    /**
     * 新增数据
     *
     * @param category2AddDto 实体
     * @return 新增结果
     */
    @PostMapping
    public Result add(@RequestBody Category2AddDto category2AddDto) {
        boolean is_add = secondaryClassificationService.insert(category2AddDto);
        if (is_add) {
            return Result.success();
        } else {
            return Result.error("添加失败");
        }
    }


    /**
     * 获取全部的二级分类
     *
     * @return
     */
    @GetMapping("/list")
    public Result list( Integer id) {

        List<SecondaryClassification> lists = secondaryClassificationService.list(id);

        Map<String, Object> map=new HashMap<String, Object>();
        map.put("total",  (Integer)lists.size());
        map.put("records",lists);
        return Result.success(map);

    }

    @Transactional
    @PutMapping()
    public Result edit(@RequestBody Category2EditDto category2EditDto) {

        System.out.println("Edit category2EditDto " + category2EditDto);

        boolean is_update = secondaryClassificationService.edit(category2EditDto);
        if (is_update) {
            return Result.success();
        } else {
            return Result.error("Edit category2EditDto 更新失败");
        }

    }

    /**
     * 根据 id 删除
     *
     * @param id
     * @return
     */

    @DeleteMapping()
    public Result delete(@RequestParam("id") Integer id) {

        Result result = secondaryClassificationService.deleteById(id);
        return result;
    }


    @PostMapping("/status/{status}")
    public Result upOrDown(@PathVariable("status") Integer status, @RequestParam("id") Integer id) {

        Result result = secondaryClassificationService.upOrDown(status, id);

        return result;

    }


}

