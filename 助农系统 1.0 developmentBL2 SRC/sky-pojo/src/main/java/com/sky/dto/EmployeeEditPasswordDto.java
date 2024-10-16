package com.sky.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEditPasswordDto {
    /**
     * 员工id
     */
    private long empId;
    /**
     * 新密码
     */
    private String newPassword;
    /**
     * 旧密码
     */
    private String oldPassword;
}
