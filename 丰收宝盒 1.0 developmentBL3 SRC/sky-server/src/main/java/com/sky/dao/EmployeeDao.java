package com.sky.dao;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * (Employee)表数据库访问层
 *
 * @author makejava
 * @since 2024-05-19 16:51:48
 */
@Mapper
public interface EmployeeDao {


    @Select("select * from employee where username=#{username}")
    Employee getByUsername(String username);

    @Insert(" insert into employee ( name,username,password,phone,sex,id_number,create_user,update_user,status)"+
            "values"+
            "(#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{createUser},#{updateUser},#{status})")
    @AutoFill(value = OperationType.INSERT)
    void insert(Employee employee);

    Page<Employee> pageQuery(Employee employee);

    @AutoFill(value =OperationType.UPDATE)
    int update(Employee employee);

    @Select("select * from employee where id=#{id} ")
    Employee getById(Integer id);
}

