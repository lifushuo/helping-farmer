package com.sky.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoGetVO {
    /**
     * 账号名称
     */
    private String account;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 省市区的名称：如山东省济南市里历下区，省市区的名称：如山东省济南市里历下区
     * 去 地址表中查 默认地址
     */
    private String fullLocation;
    /**
     * 性别 男 女
     */
    private String gender;
    /**
     * 用户的ID
     */
    private long id;
    /**
     * 用户昵称
     */
    private String nickname;


}