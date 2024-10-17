package com.sky.controller.admin;

import com.sky.dto.PrimaryCategoryAddDTO;
import com.sky.entity.PrimaryClassification;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.PrimaryClassificationService;
import com.sky.vo.PrimaryCategoryListVo;
import io.swagger.models.auth.In;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (PrimaryClassification)表控制层
 *
 * @author makejava
 * @since 2024-05-19 16:54:07
 */
@RestController
@Slf4j
@RequestMapping("/admin/category1")
public class PrimaryClassificationController {
    /**
     * 服务对象
     */
    @Resource
    private PrimaryClassificationService primaryClassificationService;


    /**
     * 新增数据
     *
     * @param PrimaryCategoryAddDTO 实体
     * @return 新增结果
     */

    @PostMapping
    Result addPrimaryClassification(@RequestBody PrimaryCategoryAddDTO primaryCategoryAddDTO) {
        log.info("add primary classification", primaryCategoryAddDTO);

        Boolean sucess = primaryClassificationService.addPrimaryClassification(primaryCategoryAddDTO);

        if (sucess) {
            return Result.success();
        }
        return Result.error("失败了");

        //return Result.success();

    }


    /**
     * 分页查询
     *
     * @param naeme
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public Result queryByPage(@RequestParam("name") String naeme, @RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {

        log.info("queryByPage" + "naeme = " + naeme + ", page = " + page + ", pageSize = " + pageSize);

        PageResult pages = primaryClassificationService.page(naeme, page, pageSize);

        return Result.success(pages);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/{id}")
    public Result queryById(@PathVariable("id") Integer id) {

        PrimaryClassification primaryClassification = primaryClassificationService.queryById(id);
        return Result.success(primaryClassification);

    }

    /**
     * 编辑数据
     *
     * @param primaryClassification 实体
     * @return 编辑结果
     */
    @PutMapping
    public Result edit(@RequestBody PrimaryClassification primaryClassification) {
        return Result.success(this.primaryClassificationService.update(primaryClassification));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public Result deleteById(Integer id) {
        boolean b = primaryClassificationService.deleteById(id);
        if (b) {
            return Result.success();
        }
        return Result.error("删除失败");
    }

    @PostMapping("/status/{status}")
    public Result UpOrDownStatus(@PathVariable("status") Integer status, Integer id) {

        boolean b = primaryClassificationService.UpOrDownStatus(id, status);
        if (b) {
            return Result.success();
        }
        return Result.error("更新失败");

    }

    @GetMapping("/list")
    public Result listData() {

        List<PrimaryCategoryListVo> lists=primaryClassificationService.listData();
        Map<String,Object> data=new HashMap<String,Object>();
        data.put("fcategorylist",lists);
        return Result.success(data);

    }


}

