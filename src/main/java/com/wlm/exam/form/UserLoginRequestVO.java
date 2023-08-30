package com.wlm.exam.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author wlm
 * @date 2023/8/14 - 15:33
 */
@Data
public class UserLoginRequestVO {

    @NotEmpty(message = "用户名不能为空")
    private String name;

    @NotEmpty(message = "密码不能为空")
    private String password;

    /**
     * 用户类型，其中1为学生，2为管理员
     */
    private Integer userType;
}
