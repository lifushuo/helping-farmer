package com.sky.service.impl;

import com.sky.context.BaseContext;
import com.sky.dao.AddressDao;
import com.sky.dto.UserPutDto;
import com.sky.entity.Address;
import com.sky.entity.User;
import com.sky.dao.UserDao;
import com.sky.result.Result;
import com.sky.service.UserService;
import com.sky.vo.UserInfoGetVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author makejava
 * @since 2024-05-19 17:12:45
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;


    @Autowired
    private AddressDao addressDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param user        筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @Override
    public Page<User> queryByPage(User user, PageRequest pageRequest) {
        long total = this.userDao.count(user);
        return new PageImpl<>(this.userDao.queryAllByLimit(user, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public Result updateUser(UserPutDto userPutDto) {
        Long currentId = BaseContext.getCurrentId();

        User user = User.builder()
                .headImage(userPutDto.getAvatar())
                .name(userPutDto.getNickname())
                .sex(Integer.valueOf(userPutDto.getGender()))
                .id(Math.toIntExact(currentId))
                .build();

        int update = userDao.update(user);
        if (update > 0) {
            return Result.success();
        } else {
            return Result.error("插入失败");
        }

//        return Result.success();
    }

    @Override
    public Result getInfo() {

        Long currentId = BaseContext.getCurrentId();

        User user = userDao.selectById(currentId);


        UserInfoGetVO userInfoGetVO = new UserInfoGetVO();

        userInfoGetVO.setId(user.getId());
        userInfoGetVO.setNickname(user.getName());
        userInfoGetVO.setAvatar(user.getHeadImage());
        userInfoGetVO.setGender(String.valueOf(user.getSex()));

        List<Address> address = addressDao.selectByUserId(currentId);

        if (address != null && address.size() > 0) {
            //TODO: 应该是 默认地址的 地址
            Address address1 = address.get(0);
            String s = address1.getProvinceName() + ","
                    + address1.getCityName() + "," + address1.getDistrictName()
                    + "," + address1.getDetail();
            userInfoGetVO.setFullLocation(s);

        }

        userInfoGetVO.setId(user.getId());
        userInfoGetVO.setAccount(user.getIdNumber());


        return Result.success(userInfoGetVO);


    }
}
