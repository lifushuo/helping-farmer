package com.sky.service;

import com.sky.dto.SpuAddDto;
import com.sky.dto.SpuEditDto;
import com.sky.entity.Spu;
import com.sky.result.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Spu)表服务接口
 *
 * @author makejava
 * @since 2024-05-19 17:12:31
 */
public interface SpuService {
//    Result insertSpuAttr(SpuAttrAddDto spuAttrAddDtou);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Spu queryById(Integer id);

    /**
     * 分页查询
     *
     * @param spu 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Spu> queryByPage(Spu spu, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param spu 实例对象
     * @return 实例对象
     */
    Spu insert(Spu spu);

    /**
     * 修改数据
     *
     * @param spu 实例对象
     * @return 实例对象
     */
    Spu update(Spu spu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    /*boolean deleteById(Integer id);*/

    Result addSpu(SpuAddDto spuAddDto);

    Result setStatus(Integer id, Integer status);

    Result getList(Integer category1Id, Integer category2Id);

    Result editSpu(SpuEditDto spuEditDto);

    Result deleteById(Integer id);

    Result page(Integer pageNumber, Integer pageSize, Integer category1Id, Integer category2Id);

    Result getSpuInfo(Integer spuId);
}
