package com.sky.controller.admin;

import com.sky.dto.SpuAttrAddDto;
import com.sky.entity.Attribute;
import com.sky.result.Result;
import com.sky.vo.SpuAttrResultVo;
import com.sky.service.AttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/admin/attr")
public class AttrController {
    @Autowired
    private AttributeService attributeService;

    @PostMapping
    public Result addSpuAttr(@RequestBody SpuAttrAddDto spuAttrAddDtou) {
        Result result = attributeService.insertSpuAttr(spuAttrAddDtou);
        return result;
    }
    @DeleteMapping
    public Result deleteSpuAttr(Integer spuId,String name){
        Result result=attributeService.deleteSpuAttr(spuId,name);
        return result;
    }
    @PostMapping("/value")
    public Result addSpecificSpuAttr(Integer spuId,String name,String value){
        Result result=attributeService.addSpecificSpuAttr(spuId,name,value);
        return result;
    }
    @DeleteMapping("/value")
    public Result deleteSpecificAttr(Integer id){
        Result result=attributeService.deleteById(id);
        return result;
    }
    @GetMapping("/page")
    public Result querySpuAttr(@RequestParam("spuId") Integer spuId){
        Result result=attributeService.page(spuId);
        return result;
    }
    @GetMapping
    public Result<SpuAttrResultVo> querySpuBySpuId(Integer spuId, String name) {
        SpuAttrResultVo spuAttrResultVo = attributeService.querySpuBySpuId(spuId, name);
        return Result.success(spuAttrResultVo);
    }
    @PutMapping
    public Result updateSpuAttr(Integer spuId,String oldName,String newName){
        Boolean is_update=attributeService.updateSpuAttr(spuId,oldName,newName);
        if(is_update){
            return Result.success();
        }
        else return Result.error("修改失败");
    }
}
