package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.sky.dao.AttributeDao;
import com.sky.dao.SpecificationDao;
import com.sky.dao.SpuPictureDao;

import com.sky.dto.SPUEditSpecificationDTO;
import com.sky.dto.SpuAddDto;
import com.sky.dto.SpuEditDto;
import com.sky.entity.*;
import com.sky.dao.SpuDao;
import com.sky.result.Result;
import com.sky.service.SpuService;
import com.sky.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.*;

import java.util.Arrays;


/**
 * (Spu)表服务实现类
 *
 * @author makejava
 * @since 2024-05-19 17:12:31
 */
@Service("spuService")
public class SpuServiceImpl implements SpuService {
    @Resource
    private SpuDao spuDao;

    @Autowired
    private SpuPictureDao spuPictureDao;
    @Autowired
    private SpecificationDao specificationDao;
    @Autowired
    private AttributeDao attributeDao;

//    @Override
//    public Result insertSpuAttr(SpuAttrAddDto spuAttrAddDtou) {
//        return null;
//    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Spu queryById(Integer id) {
        return this.spuDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param spu         筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<Spu> queryByPage(Spu spu, PageRequest pageRequest) {
        long total = this.spuDao.count(spu);
        return new PageImpl<>(this.spuDao.queryAllByLimit(spu, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param spu 实例对象
     * @return 实例对象
     */
    @Override
    public Spu insert(Spu spu) {
        this.spuDao.insert(spu);
        return spu;
    }

    /**
     * 修改数据
     *
     * @param spu 实例对象
     * @return 实例对象
     */
    @Override
    public Spu update(Spu spu) {
        this.spuDao.update(spu);
        return this.queryById(spu.getId());
    }
/*

    *
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
    @Override
    public boolean deleteById(Integer id) {
        return this.spuDao.deleteById(id) > 0;
    }
*/

    @Override
    public Result addSpu(SpuAddDto spuAddDto) {
        Spu spu = Spu.builder().spuName(spuAddDto.getSpuName())
                .description(spuAddDto.getDescription())
                .price(Double.valueOf(spuAddDto.getPrice()))
                .discount(Double.valueOf(spuAddDto.getDiscount()))
                .storeCount(Math.toIntExact(spuAddDto.getStoreCount()))
                .category2Id(Math.toIntExact(spuAddDto.getCategory2Id()))
                .isDeleted("0")
                .isEffective("1")
                .salesCount(0)
                .picture("")
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        int insert = spuDao.insert(spu);
        if (insert == 0) {
            return Result.error("fault");
        }
        String[] pictures = spuAddDto.getPicture();
        Integer spuId = spu.getId();

        for (int i = 0; i < pictures.length; i++) {
            spuPictureDao.insertSpuIDandPictureUrl(spuId, pictures[i]);
        }


//<<<<<<< HEAD
//        Specification[] specifications = spuAddDto.getSpecifications();
//        for (int i = 0; i < specifications.length; i++) {
//            specificationDao.insertSpecification(spuId, specifications[i]);
//=======
        SPUEditSpecificationDTO[] SPUEditSpecificationDTOS = spuAddDto.getSpecifications();
        for (int i = 0; i < SPUEditSpecificationDTOS.length; i++) {
            specificationDao.insertSpecification(spuId, SPUEditSpecificationDTOS[i]);
//>>>>>>> yhc

        }
        return Result.success();
    }

//    @Override
//    public Result setStatus(Integer id, Integer status) {
//        return null;
//    }


    @Override
    public Result setStatus(Integer id, Integer status) {
        spuDao.setStatus(id, status);
        return Result.success();
    }

    @Override

    public Result getList(Integer category1Id, Integer category2Id) {

        List<SpuListVO> list;


        if (category2Id == null) {
            list = spuDao.getListByCateory1Id(category1Id);
        } else {
            list = spuDao.getListByCateory2Id(category2Id);
        }
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("spuList", list);
        return Result.success(map);


    }

    public Result editSpu(SpuEditDto spuEditDto) {
        Spu spu = Spu.builder().id(Math.toIntExact(spuEditDto.getId()))
                .spuName(spuEditDto.getSpuName())
                .description(String.valueOf(spuEditDto.getDescription()))
                .price(Double.valueOf(spuEditDto.getPrice()))
                .discount(Double.valueOf(spuEditDto.getDiscount()))
                .storeCount(Math.toIntExact(spuEditDto.getStoreCount()))
                .picture(Arrays.toString(spuEditDto.getPicture()))
                .isDeleted("0")
                .isEffective("1")
                .salesCount(0)
                .build();
        int update = spuDao.update(spu);
        if (update == 0) {
            return Result.error("error");
        }

        Integer id = spu.getId();

        specificationDao.deleteBySpuId(id);

        SPUEditSpecificationDTO[] SPUEditSpecificationDTOS = spuEditDto.getSpecifications();
        for (int i = 0; i < SPUEditSpecificationDTOS.length; i++) {
            SPUEditSpecificationDTO s = SPUEditSpecificationDTOS[i];
            specificationDao.insertSpecification(id, new SPUEditSpecificationDTO(s.getName(), s.getValue()));

        }


//        System.out.println("specifications = " + SPUEditSpecificationDTOS.length);
//        for(int i = 0; i< SPUEditSpecificationDTOS.length; i++){
////            System.out.println(specifications[i]);
//            specificationDao.updateSpecification(id, SPUEditSpecificationDTOS[i]);
////            return null;
//        }

        String picture = spu.getPicture();
//        spuPictureDao.deleteById(id);
        SpuPicture spuPicture = new SpuPicture(id, id, picture);
        spuPictureDao.updateSpuPicture(spuPicture);

        return Result.success();
    }

    @Override
    public Result deleteById(Integer id) {
        int cnt = spuDao.deleteById(id);
        if (cnt > 0) {
            return Result.success();
        }
        return Result.error("error");
    }

    @Override
    public Result page(Integer pageNumber, Integer pageSize, Integer category1Id, Integer category2Id) {
        PageHelper.startPage(pageNumber, pageSize);
        List<SPUPageItemVO> list = null;
        com.github.pagehelper.Page<SPUPageItemVO> pageList = null;

        if (category2Id != null) {
            pageList = spuDao.selectByCategory2ID(category2Id);
        } else if (category1Id != null) {
            pageList = spuDao.selectByCategory1ID(category1Id);
        } else {
            pageList = spuDao.selectAll();
        }

        Map<String, Object> map = new HashMap<>();

        map.put("total", pageList.getTotal());
        map.put("records", pageList.getResult());
        return Result.success(map);

//        spuDao.selectBy()


    }

    @Override
    public Result getSpuInfo(Integer spuId) {

        GetSpuInfoDataVo data = new GetSpuInfoDataVo();

        Spu spu = spuDao.queryById(spuId);
        data.setId(spuId);
        data.setSpuName(spu.getSpuName());
        data.setDescription(spu.getDescription());
        data.setPrice(spu.getPrice());
        data.setDiscount(spu.getDiscount());
        data.setStoreCount(spu.getStoreCount());

        List<SpuPicture> spuPictures = spuPictureDao.selectBySpuId(spuId);
        List<String> pictureList = new ArrayList<>();
        for (SpuPicture spuPicture : spuPictures) {
            pictureList.add(spuPicture.getPictureUrl());
        }

        data.setPicture(pictureList);

        List<Specification> spuSpecifications = specificationDao.SelectBySpuId(spuId);
        List<GetSpuInfoSpecification> spuInfoSpecifications = new ArrayList<>();

        for (Specification s : spuSpecifications) {
            GetSpuInfoSpecification build = GetSpuInfoSpecification.builder()
                    .name(s.getName())
                    .value(s.getValue())
                    .spuId(Long.valueOf(s.getSpuId()))
                    .id(s.getId())
                    .build();

            spuInfoSpecifications.add(build);
        }

        data.setSpecifications(spuInfoSpecifications);

        List<Attribute> attributeList = attributeDao.selectBySpuId(spuId);
        Map<String, String> map = new HashMap<>();
        List<GetSkuInfoAttribute> spuInfoValues = new ArrayList<>();
        for (Attribute a : attributeList) {


            if (map.containsKey(a.getName())) {
                continue;
            }
            map.put(a.getName(), a.getName());

            String name = a.getName();
            GetSkuInfoAttribute arr = new GetSkuInfoAttribute();
            arr.setName(name);
            List<String> valueList = new ArrayList<>();

            for (Attribute b : attributeList) {
                if (b.getName().equals(name)) {
                    valueList.add(b.getValue());
                }
            }
            arr.setValues(valueList);
            spuInfoValues.add(arr);
        }
        data.setAttributes(spuInfoValues);
        return Result.success(data);
    }

}
