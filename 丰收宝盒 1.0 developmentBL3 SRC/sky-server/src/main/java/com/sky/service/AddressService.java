package com.sky.service;

import com.sky.dto.AddressBookPostDto;
import com.sky.dto.AddressBookPutDto;
import com.sky.entity.Address;
import com.sky.result.Result;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (Address)表服务接口
 *
 * @author makejava
 * @since 2024-05-21 10:18:08
 */
public interface AddressService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Result queryById(Integer id);

    /**
     * 分页查询
     *
     * @param address 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<Address> queryByPage(Address address, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param address 实例对象
     * @return 实例对象
     */
    Address insert(Address address);

    /**
     * 修改数据
     *
     * @param address 实例对象
     * @return 实例对象
     */
//    Address update(Address address);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    Result deleteById(Integer id);

    Result addAddress(AddressBookPostDto addressBookPostDto);


    Result updateAddressBook(Integer id, AddressBookPutDto addressBookPutDto);

    Result queryAll();

}
