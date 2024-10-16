package com.sky.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.BaseContext;
import com.sky.dao.*;
import com.sky.entity.*;
import com.sky.result.Result;
import com.sky.service.GoodsService;
import com.sky.vo.GoodsDetailVO;
import com.sky.vo.GoodsPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;


    @Autowired
    private SpuPictureDao spuPictureDao;

    @Autowired
    private SpuDao spuDao;
    @Autowired
    private SkuDao skuDao;
    @Autowired
    private AttributeDao attributeDao;
    @Autowired
    private SkuAttriDao skuAttriDao;
    @Autowired
    private AddressDao addressDao;


    @Override
    public Result getGoodsPage(Integer page, Integer pageSize) {
        GoodsPageVO g = new GoodsPageVO();
        g.setPage(page);
        g.setPageSize(pageSize);
        PageHelper.startPage(page, pageSize);

        Page<Item> pages = goodsDao.getGoodsItems();
        List<Item> result = pages.getResult();
        for (Item item : result) {
            List<SpuPicture> spuPictures = spuPictureDao.selectBySpuId(item.getId());
            if (spuPictures.size() > 0) {
                item.setPicture(spuPictures.get(0).getPictureUrl());
            }

        }

        g.setCounts(Math.toIntExact(pages.getTotal()));
        g.setItems(result);
        return Result.success(g);
    }


    public GoodsDetailVO getGoodsDetail(Integer spuId) {
//
        GoodsDetailVO goodsDetailVO = new GoodsDetailVO();
        Spu spu = spuDao.queryById(spuId);
        goodsDetailVO.setId(spu.getId());
        goodsDetailVO.setName(spu.getSpuName());
        goodsDetailVO.setDesc(spu.getDescription());
        goodsDetailVO.setOldPrice(spu.getPrice());
        goodsDetailVO.setPrice(spu.getPrice() * spu.getDiscount());

//
        List<String> mainPicturesList = spuDao.getSpuPicturesList(spuId);
        goodsDetailVO.setMainPictures(mainPicturesList);


//
        GoodsDetails goodsDetails = new GoodsDetails();
        goodsDetails.setPicture(mainPicturesList);
        List<GoodsDetailsProperties> goodsDetailsPropertiesList = attributeDao.getGoodsDetailsProperties(spuId);
        goodsDetails.setProperties(goodsDetailsPropertiesList);
        goodsDetailVO.setDetails(goodsDetails);

//
        List<GoodsSkus> goodsSkusList = new ArrayList<GoodsSkus>();
        List<Integer> skuIdList = skuDao.getSkuIdListBySpuId(spuId);
        for (int i = 0; i < skuIdList.size(); i++) {
            GoodsSkus tmp = new GoodsSkus();
            Sku sku = skuDao.queryById(skuIdList.get(i));
            tmp.setId(sku.getId());
            tmp.setInventory(sku.getStoreCount());
            tmp.setOldPrice(sku.getOldPrice());
            tmp.setPrice(sku.getPrice());
            tmp.setPicture(sku.getPicture());
            tmp.setSpecs(skuAttriDao.getGoodsSkusSpecsListBySkuId(skuIdList.get(i)));
            goodsSkusList.add(tmp);
        }
        goodsDetailVO.setSkus(goodsSkusList);

//
        List<GoodsSpecs> goodsSpecsList = new ArrayList<>();
        List<String> nameList = attributeDao.getAttributeNameListBySpuId(spuId);
        for (int i = 0; i < nameList.size(); i++) {
            GoodsSpecs tmp = new GoodsSpecs();
            tmp.setName(nameList.get(i));
            List<GoodsSpecsValues> goodsSpecsValuesList = new ArrayList<>();
            List<String> valueList = attributeDao.getAttributeValueListByName(nameList.get(i));
            for (int j = 0; j < valueList.size(); j++) {
                GoodsSpecsValues tmp2 = new GoodsSpecsValues();

                tmp2.setName(valueList.get(j));
                tmp2.setAvaliable(true);
                tmp2.setDesc(null);
                tmp2.setPicture(null);
                goodsSpecsValuesList.add(tmp2);

            }
            tmp.setSpecValues(goodsSpecsValuesList);
            goodsSpecsList.add(tmp);
        }
        goodsDetailVO.setSpecs(goodsSpecsList);


//
        List<GoodsUserAddress> goodsUserAddressList = new ArrayList<GoodsUserAddress>();
        Integer userId = Math.toIntExact(BaseContext.getCurrentId());

        List<Integer> addressId = addressDao.getAddressIdListByUserId(userId);
        for (int i = 0; i < addressId.size(); i++) {
            GoodsUserAddress tmp = new GoodsUserAddress();
            Address address = addressDao.queryById(addressId.get(i));
            tmp.setId(address.getId());
            tmp.setReceiver(address.getConsignee());
            tmp.setContact(address.getPhone());
            tmp.setFullLocation(address.getProvinceName() + address.getCityName() + address.getDistrictName());
            tmp.setAddress(address.getDetail());
            tmp.setIsDefault(address.getIsDefault());
            goodsUserAddressList.add(tmp);

        }
        goodsDetailVO.setUserAddresses(goodsUserAddressList);


        return goodsDetailVO;
    }
}
