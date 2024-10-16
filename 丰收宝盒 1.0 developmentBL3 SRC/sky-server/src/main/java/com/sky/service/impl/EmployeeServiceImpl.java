package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeEditPasswordDto;
import com.sky.entity.Employee;
import com.sky.dao.EmployeeDao;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.PasswordErrorException;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * (Employee)表服务实现类
 *
 * @author makejava
 * @since 2024-05-19 16:51:48
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    /**
     * 员工登录
     *
     * @param employee
     * @return
     */

    public Employee login(Employee employee) {
        String username = employee.getUsername();
        String password = employee.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee newEmployee = employeeDao.getByUsername(username);
        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (newEmployee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        //密码比对
        //对前端传过来的明文密码进行md5加密，然后再进行比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(newEmployee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (newEmployee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象

        return newEmployee;
    }


    /**
     * 新增员工
     *
     * @param employee
     */
    @Override
    public void save(Employee employee) {
        System.out.println("当前线程的id：" + Thread.currentThread().getId());

        //设置账号的状态，默认正常状态，1表示状态，0表示锁定
        employee.setStatus((StatusConstant.ENABLE));
        //设置密码,默认123456
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        //设置当前记录的创建时间和修改时间


        //employee.setCreateTime(new Date());
        //employee.setUpdateTime(new Date());
        //设置当前记录人id和修改人id
        employee.setCreateUser(Math.toIntExact(BaseContext.getCurrentId()));
        employee.setUpdateUser(Math.toIntExact(BaseContext.getCurrentId()));
        employee.setUpdateUser(Math.toIntExact(BaseContext.getCurrentId()));
        employeeDao.insert(employee);
    }


    /**
     * 分页查询
     *
     * @param employee
     * @return
     */

    public PageResult pageQuery(Employee employee, int page, int pageSize) {
        //select * from employee limit 0,10
        PageHelper.startPage(page, pageSize);

        Page<Employee> newPage = employeeDao.pageQuery(employee);

        long total = newPage.getTotal();
        List<Employee> records = newPage.getResult();

        return new PageResult(total, records);
    }

    /**
     * 启用禁用员工账号
     *
     * @param status
     * @param id
     */

    public void startOrStop(Integer status, Integer id) {

        Employee employee = new Employee();
        employee.setStatus(status);
        employee.setId(id);

        employeeDao.update(employee);
    }


    /**
     * 根据id查询员工信息
     *
     * @param id
     * @return
     */

    public Employee getById(Integer id) {
        Employee employee = employeeDao.getById(id);
        employee.setPassword("******");
        return employee;


    }


    /**
     * 编辑员工信息
     *
     * @param employee
     */
    @Override
    public void update(Employee employee) {
        employeeDao.update(employee);
    }

    @Override
    public Result updatePassword(EmployeeEditPasswordDto e) {

        // 先判断 密码对不对
        Employee employee = employeeDao.getById(Math.toIntExact(e.getEmpId()));
        if (employee == null) {
            return Result.error("没有该员工");
        }

        // 对原密码加密 比较
        String s = DigestUtils.md5DigestAsHex(e.getOldPassword().getBytes());

        if (!s.equals(employee.getPassword())) {
            //旧密码错误
            return Result.error("密码错误");
        }

        // 对新密码加密 插入数据库
        String s2 = DigestUtils.md5DigestAsHex(e.getNewPassword().getBytes());
        Employee employee1 = new Employee();

        employee1.setId(Math.toIntExact(e.getEmpId()));
        employee1.setPassword(s2);
        // 更新
        int update = employeeDao.update(employee1);
        if (update == 0) {
            return Result.error("更新失败");
        }
        return Result.success();

    }
}
