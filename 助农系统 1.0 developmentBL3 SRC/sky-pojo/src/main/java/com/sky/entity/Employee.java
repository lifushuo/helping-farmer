package com.sky.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.Date;

/**
 * (Employee)实体类
 *
 * @author makejava
 * @since 2024-05-19 16:51:48
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee implements Serializable {
    private static final long serialVersionUID = 552939975442421810L;
/**
     * 主键

     */
    private Integer id;
/**
     * 姓名

     */
    private String name;
/**
     * 用户名称

     */
    private String username;
/**
     * 密码

     */
    private String password;
/**
     * 手机号码

     */
    private String phone;
/**
     * 性别

     */
    private String sex;
/**
     * 身份证号码

     */
    private Integer idNumber;
/**
     * 状态（0禁用1启用）

     */
    private Integer status;
/**
     * 创建时间

     */
    private Date createTime;
/**
     * 更新时间

     */
    private Date updateTime;
/**
     * 是否删除（1删除0未删除）

     */
    private String isDeleted;
/**
     * 创建人id

     */
    private Integer createUser;
/**
     * 修改人id

     */
    private Integer updateUser;



}

