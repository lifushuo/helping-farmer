package com.sky.service;

import com.sky.dto.EmployeeEditPasswordDto;
import com.sky.entity.Employee;
import com.sky.result.PageResult;
import com.sky.result.Result;

/**
 * (Employee)表服务接口
 *
 * @author makejava
 * @since 2024-05-19 16:51:48
 */
public interface EmployeeService {

    /**
     * 员工登录
     * @param employee
     * @return
     */
    Employee login(Employee employee);

    /**
     * 新增员工
     * @param employee
     */
    void save(Employee employee);

    /**
     * 分页查询
     * @param employee
     * @return
     */
    PageResult pageQuery(Employee employee,int page,int pageSize);

    /**
     * 启用禁用员工账号
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Integer id);

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    Employee getById(Integer id);

    /**
     * 编辑员工信息
     * @param employee
     */
    void update(Employee employee);

    /**
     * 更新密码
     * @param e
     * @return
     */
    Result updatePassword(EmployeeEditPasswordDto e);
}
