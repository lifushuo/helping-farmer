package com.sky.service.impl;

import com.sky.dao.*;
import com.sky.dto.SkuAddDto;
import com.sky.dto.SkuEditDto;
import com.sky.entity.*;
import com.sky.result.Result;
import com.sky.service.SkuService;
import com.sky.vo.SkuQueryForEditVO;
import com.sky.vo.SkuQueryForSpuIdVO;
import com.sky.vo.SkuQueryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * (Sku)表服务实现类
 *
 * @author makejava
 * @since 2024-05-19 17:12:07
 */
@Service("skuService")
public class SkuServiceImpl implements SkuService {
    @Resource
    private SkuDao skuDao;

    @Autowired
    SkuAttriDao skuAttriDao;

    @Autowired
    private SpuDao spuDao;

    @Autowired
    private AttributeDao attributeDao;

    @Autowired
    private SpecificationDao specificationDao;


    /**
     * 分页查询
     *
     * @param sku         筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Sku> queryByPage(Sku sku, PageRequest pageRequest) {
        long total = this.skuDao.count(sku);
        return new PageImpl<>(this.skuDao.queryAllByLimit(sku, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param sku 实例对象
     * @return 实例对象
     */
    @Override
    public Sku insert(Sku sku) {
        this.skuDao.insert(sku);
        return sku;
    }

    /**
     * 修改数据
     *
     * @param sku 实例对象
     * @return 实例对象
     */
//    @Override
//    public Sku update(Sku sku) {
//        this.skuDao.update(sku);
//        return this.queryById(sku.getId());
//    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Result deleteById(Integer id) {
        int cnt = skuDao.deleteById(id);
        if (cnt > 0) {
            return Result.success();
        }

        return Result.error("budui");
    }

    /**
     * 新增sku
     *
     * @param s
     * @return
     */
    @Override
    public Result addSku(SkuAddDto s) {


        Sku sss = Sku.builder().skuName(s.getSkuName())
                .spuId(Integer.valueOf(s.getSpuId()))
                .price(Double.valueOf(s.getPrice()))
                .oldPrice(Double.valueOf(s.getOldPrice()))
                .picture(s.getPicture())
                .storeCount(Integer.valueOf(s.getStoreCount()))
                .isDeleted("0")
                .isEffective("1")
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        int insert = skuDao.insert(sss);
        if (insert == 0) {
            return Result.error("budui");
        }


        long[] attributes = s.getAttributes();
        List<SkuAttri> list = new ArrayList<>();
        Integer id = sss.getId();

        for (int i = 0; i < attributes.length; i++) {
            SkuAttri skuAttri = new SkuAttri();
            skuAttri.setArriId(Math.toIntExact(attributes[i]));
            skuAttri.setSkuId(id);
            skuAttriDao.insert(skuAttri);
            list.add(skuAttri);
        }


        //int insertBatch = skuAttriDao.insertBatch(list);

        //if (insertBatch == 0) {
        //    return Result.error("budui");
        //}


        return Result.success();
    }

    /**
     * 修改sku
     *
     * @param s
     * @return
     */
    @Override
    public Result editSku(SkuEditDto s) {
        Sku sku = Sku.builder().id(Integer.valueOf(s.getId())).oldPrice(Double.valueOf(s.getOldPrice()))
                .picture(s.getPicture()).price(Double.valueOf(s.getPrice()))
                .skuName(s.getSkuName()).spuId(Integer.valueOf(s.getSpuId()))
                .storeCount(Integer.valueOf(s.getStoreCount())).build();

        int update = skuDao.update(sku);
        if (update == 0) {
            return Result.error("budui");
        }


        long[] attributes = s.getAttributes();
        List<SkuAttri> list = new ArrayList<>();

        Integer id = sku.getId();
        skuAttriDao.deleteBySkuId(id);

        for (int i = 0; i < attributes.length; i++) {
            SkuAttri skuAttri = new SkuAttri();
            skuAttri.setArriId(Math.toIntExact(attributes[i]));
            skuAttri.setSkuId(id);
            list.add(skuAttri);
        }

        int insertBatch = skuAttriDao.insertBatch(list);
        if (insertBatch == 0) {
            return Result.error("budui");
        }


        return Result.success();
    }

    /**
     * 设置起售停售
     *
     * @param id
     * @return
     */
    @Override
    public Result setStatus(Integer id, Integer status) {


        skuDao.setStatus(id, status);
        return Result.success();

    }


    /**
     * 通过skuID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SkuQueryVO queryById(Integer id) {
        //sku相关信息
        Sku sku = skuDao.queryById(id);

        SkuQueryVO s = SkuQueryVO.builder().id(sku.getId()).skuName(sku.getSkuName())
                .skuOldPrice(sku.getOldPrice()).skuPrice(sku.getPrice()).skuPicture(sku.getPicture())
                .skuStoreCount(sku.getStoreCount()).build();

//        spu相关信息
        Integer spuId = sku.getSpuId();
        Spu spu = spuDao.queryById(spuId);
        s.setSpuId(spu.getId());
        s.setSpuName(spu.getSpuName());
        s.setSpuDescription(spu.getDescription());
        s.setSpuDiscount(spu.getDiscount());

//        sku属性
        List<Long> attributes = skuAttriDao.getAttributesBySkuId(id);
        List<SkuValue> skuValues = new ArrayList<>();
        for (int i = 0; i < attributes.size(); i++) {
            SkuValue tmp = attributeDao.getAttributesByAttriId(attributes.get(i));
            skuValues.add(tmp);
        }
        s.setSkuValues(skuValues);

//        spu规格
        List<SpuSpecification> spuSpecifications = specificationDao.queryBySpuId(spuId);
        s.setSpuSpecification(spuSpecifications);

        return s;
    }

    /**
     * 根据id查询信息供编辑使用
     *
     * @param id
     * @return
     */
    @Override
    public SkuQueryForEditVO queryByIdForEdit(Integer id) {

        SkuQueryForEditVO s = skuDao.queryByIdForEdit(id);
        List<Long> attributes = skuAttriDao.getAttributesBySkuId(id);
        s.setAttributes(attributes);
        return s;
    }

    /**
     * 根据spuId获取对应的全部sku
     *
     * @param spuId
     * @return
     */
    @Override
    public SkuQueryForSpuIdVO queryListBySpuId(Integer spuId) {
        SkuQueryForSpuIdVO s = new SkuQueryForSpuIdVO();

        int total = skuDao.countBySpuId(spuId);
        s.setTotal(total);

        List<Records> listRecords = new ArrayList<>();

        List<Integer> skuIdList = skuDao.getSkuIdListBySpuId(spuId);
        for (int i = 0; i < skuIdList.size(); i++) {
            Sku sku = skuDao.queryById(skuIdList.get(i));
            Records record = Records.builder().id(sku.getId()).price(sku.getPrice())
                    .oldPrice(sku.getOldPrice()).picture(sku.getPicture())
                    .isEffective(Long.parseLong(sku.getIsEffective())).skuName(sku.getSkuName())
                    .storeCount(Long.valueOf(sku.getStoreCount())).build();
            List<String> values = skuAttriDao.getAttriNameBySkuId(skuIdList.get(i));
            record.setValues(values);
            listRecords.add(record);
        }

        s.setRecords(listRecords);

        return s;
    }


}
