package com.sky.vo;



import lombok.*;
/**
 * EmployeeLoginVO，员工登录返回的数据格式
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder

public class EmployeeLoginVO {
    /**
     * 主键值
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * jwt令牌
     */
    private String token;
    /**
     * 用户名
     */
    private String userName;

}
