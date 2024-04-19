package com.zhiyu.security.entity.form.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthUserForm {

    @NotBlank(message = "账号不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    private String code;

    private String uuid = "";


}
