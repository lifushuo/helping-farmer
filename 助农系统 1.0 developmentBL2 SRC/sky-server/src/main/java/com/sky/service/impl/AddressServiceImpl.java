package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dao.UserDao;
import com.sky.dto.AddressBookPostDto;
import com.sky.dto.AddressBookPutDto;
import com.sky.entity.Address;
import com.sky.dao.AddressDao;
import com.sky.entity.AddressAll;
import com.sky.entity.AddressQueryById;
import com.sky.result.Result;
import com.sky.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Address)表服务实现类
 *
 * @author makejava
 * @since 2024-05-21 10:18:08
 */
@Service("addressService")
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressDao addressDao;
    @Autowired
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Result queryById(Integer id) {
//        return this.addressDao.queryById(id);
        Address addressSQl=addressDao.queryById(id);
        Integer userId=addressSQl.getUserId();
//        User user=userDao.queryById(userId);
        Integer addressId=addressSQl.getId();
        String receiver=addressSQl.getConsignee();
        String contact=addressSQl.getPhone();
        Integer provinceCode= Integer.valueOf(addressSQl.getProvinceCode());
        Integer cityCode=Integer.valueOf(addressSQl.getCityCode());
        Integer districtCode=Integer.valueOf(addressSQl.getDistrictCode());
        String fullLocation=addressSQl.getProvinceName()+addressSQl.getCityName()+addressSQl.getDistrictName();
        String address=addressSQl.getDetail();
        AddressQueryById addressQueryById=new AddressQueryById(userId,addressId,receiver,contact,provinceCode,cityCode,districtCode,fullLocation,address);

        return Result.success(addressQueryById);
    }

    /**
     * 分页查询
     *
     * @param address 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Address> queryByPage(Address address, PageRequest pageRequest) {
        long total = this.addressDao.count(address);
        return new PageImpl<>(this.addressDao.queryAllByLimit(address, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param address 实例对象
     * @return 实例对象
     */
    @Override
    public Address insert(Address address) {
        this.addressDao.insert(address);
        return address;
    }

    /**
     * 修改数据
     *
     * @param address 实例对象
     * @return 实例对象
     */
//    @Override
//    public Address update(Address address) {
//        this.addressDao.update(address);
//        return this.queryById(address.getId());
//    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public Result deleteById(Integer id) {

//        return this.addressDao.deleteById(id) > 0;
        int cnt = addressDao.deleteById(id);
        if(cnt>0){
            return Result.success();
        }
        return Result.error("error");

    }

    @Override
    public Result addAddress(AddressBookPostDto addressBookPostDto) {
        Long currentId= BaseContext.getCurrentId();

        Address address= Address.builder()
                .userId(Math.toIntExact(currentId))
                .consignee(addressBookPostDto.getReceiver())
                .sex("1")
                .phone(addressBookPostDto.getContact())
                .cityCode(String.valueOf(addressBookPostDto.getCityCode()))
                .cityName(addressBookPostDto.getCityName())

                .detail(addressBookPostDto.getAddress())
                .districtCode(String.valueOf(addressBookPostDto.getDistrictCode()))
                .districtName(addressBookPostDto.getDistrictName())
                .label(String.valueOf(addressBookPostDto.getLabel()))
                .provinceCode(String.valueOf(addressBookPostDto.getProvinceCode()))
                .provinceName(addressBookPostDto.getProvinceName())
                .isDefault("1")
                .isDeleted("0")
                .build();
        int insert = addressDao.insert(address);
        if(insert==0)
            return Result.error("error");

        return Result.success();
    }

    @Override
    public Result updateAddressBook(Integer id, AddressBookPutDto addressBookPutDto) {
        Long currentId= BaseContext.getCurrentId();
        Address address= Address.builder()
                .userId(Math.toIntExact(currentId))
                .consignee(addressBookPutDto.getConsignee())
                .sex("1")
                .phone(addressBookPutDto.getContact())
                .cityCode(String.valueOf(addressBookPutDto.getCityCode()))
                .cityName(addressBookPutDto.getCityName())
                .detail(addressBookPutDto.getDetail())
                .districtCode(addressBookPutDto.getDistrictName())
                .districtName(addressBookPutDto.getDistrictName())
                .label(String.valueOf(addressBookPutDto.getLabel()))
                .provinceCode(String.valueOf(addressBookPutDto.getProvinceCode()))
                .provinceName(addressBookPutDto.getProvinceName())
                .isDefault("1")
                .isDeleted("0")
                .build();

        addressDao.deleteById(id);
        int insert = addressDao.insert(address);
        if(insert==0)
            return Result.error("error");

        return Result.success();

    }

    @Override
    public Result queryAll() {
        List<AddressAll> listall = new ArrayList<>();
        List<Address> listSQL =addressDao.queryAll();

        for(int i =0 ; i<listSQL.size();i++){
            Integer id=listSQL.get(i).getId();
            String receiver=listSQL.get(i).getConsignee();
            String contact=listSQL.get(i).getPhone();
            Long provinceCode= Long.valueOf(Integer.valueOf(listSQL.get(i).getProvinceCode()));
            Long cityCode= Long.valueOf(Integer.valueOf(listSQL.get(i).getCityCode()));
            Long countyCode= Long.valueOf(Integer.valueOf(listSQL.get(i).getCityCode()));
            String fullLocation=listSQL.get(i).getProvinceName()+listSQL.get(i).getCityName()+listSQL.get(i).getDistrictName();
            String address=listSQL.get(i).getDetail();

            AddressAll addressAll=new AddressAll(id,receiver,contact,provinceCode,cityCode,countyCode,fullLocation,0,address,1);
            listall.add(addressAll);
        }

        return Result.success(listall);
    }



}

