package com.sky.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class  FcactoryInfo{
    private String createTime;
    private long createUser;
    private String icon;
    /**
     * ID 编号
     */
    private long id;
    private long isEffective;
    private String name;
    private String updateTime;
    private long updateUser;


}
