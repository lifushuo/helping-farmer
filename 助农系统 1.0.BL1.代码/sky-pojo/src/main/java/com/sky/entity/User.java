package com.sky.entity;

import java.io.Serializable;
import lombok.*;
/**
 * (User)实体类
 *
 * @author makejava
 * @since 2024-05-19 17:12:45
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = 748456622476342546L;
/**
     * 主键

     */
    private Integer id;
/**
     * 微信用户唯一标识

     */
    private String openid;
/**
     * 姓名

     */
    private String name;
/**
     * 手机号

     */
    private String phone;
/**
     * 性别

     */
    private Integer sex;
/**
     * 身份证号

     */
    private String idNumber;
/**
     * 头像

     */
    private String headImage;
/**
     * 是否删除（1删除0未删除）

     */
    private Integer isDeleted;

}

