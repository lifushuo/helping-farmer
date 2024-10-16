package com.sky.service;

import com.sky.dto.SpuAttrAddDto;
import com.sky.entity.Attribute;
import com.sky.result.Result;
import com.sky.vo.SpuAttrResultVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Attribute)表服务接口
 *
 * @author makejava
 * @since 2024-05-19 16:50:34
 */
public interface AttributeService {
    Result insertSpuAttr(SpuAttrAddDto spuAttrAddDtou);
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Attribute queryById(Integer id);

    /**
     * 分页查询
     *
     * @param attribute 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Attribute> queryByPage(Attribute attribute, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param attribute 实例对象
     * @return 实例对象
     */
    Attribute insert(Attribute attribute);

    /**
     * 修改数据
     *
     * @param attribute 实例对象
     * @return 实例对象
     */
    Attribute update(Attribute attribute);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Result deleteById(Integer id);

    Result deleteSpuAttr(Integer spuId, String name);

    Result addSpecificSpuAttr(Integer spuId, String name, String value);

    Result querySpuAttr(Attribute attribute);

    SpuAttrResultVo querySpuBySpuId(Integer spuid, String name);

    Boolean updateSpuAttr(Integer spuId, String oldName, String newName);

    Result page(Integer spuId);
}
