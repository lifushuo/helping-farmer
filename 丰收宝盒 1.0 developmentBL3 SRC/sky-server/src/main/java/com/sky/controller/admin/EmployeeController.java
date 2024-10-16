package com.sky.controller.admin;

import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeEditPasswordDto;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * (Employee)表控制层
 *
 * @author makejava
 * @since 2024-05-19 16:51:48
 */
@RestController
@RequestMapping("/admin/employee")

@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result<EmployeeLoginVO> login(@RequestBody Employee employee) {
        log.info("员工登录：{}", employee);

        Employee login = employeeService.login(employee);
        //登陆成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, login.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);
        EmployeeLoginVO build = new EmployeeLoginVO()
                .builder()
                .userName(login.getUsername())
                .token(token)
                .name(login.getName())
                .userName(login.getUsername())
                .build();
        return Result.success(build);


    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 新增员工
     *
     * @param employee
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Employee employee) {
        log.info("新增员工：{}", employee);
        System.out.println(" 当前线程的id：" + Thread.currentThread().getId());
        employeeService.save(employee);
        return Result.success();

    }

    /**
     * 员工分页查询
     *
     * @param employee
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> page(Employee employee, int page, int pageSize) {
        log.info("员工分页查询，参数为：{}", employee);
        PageResult pageResult = employeeService.pageQuery(employee, page, pageSize);
        return Result.success(pageResult);
    }

    /**
     * 启用禁用员工账号
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    public Result startOrStop(@PathVariable Integer status, Integer id) {
        log.info("启用禁用员工账号:{},{}", status, id);
        employeeService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * 根据id查询员工信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Employee> getById(@PathVariable Integer id) {
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }

    /**
     * 编辑员工信息
     *
     * @param employee
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Employee employee) {
        log.info("编辑员工信息：{}", employee);
        employeeService.update(employee);
        return Result.success();
    }


    @PutMapping("/editPassword")
    public Result update(@RequestBody EmployeeEditPasswordDto e) {

        log.info("修改密码 {}", e);

        Result result =employeeService.updatePassword(e);

        return result;
    }


}

