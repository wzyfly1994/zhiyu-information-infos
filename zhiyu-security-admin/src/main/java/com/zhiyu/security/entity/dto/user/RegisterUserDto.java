package com.zhiyu.security.entity.dto.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterUserDto {


    @ApiModelProperty(value = "账号", example = "jason001")
    @NotBlank(message = "账号不能为空")
    private String account;

    @ApiModelProperty(value = "密码", example = "123456")
    @NotBlank(message = "密码不能为空")
    private String passWord;


    @ApiModelProperty(value = "用户名", example = "jason")
    @NotBlank(message = "用户名不能为空")
    private String userName;


    @ApiModelProperty(value = "头像图片url", example = "11111")
    private String headImg;


    @ApiModelProperty(value = "性别 1:男，2:女，3:未知", example = "1")
    private Integer sex;


    @ApiModelProperty(value = "邮箱", example = "123@gmail.com")
    private String email;


    @ApiModelProperty(value = "手机号", example = "12311")
    private String phone;


    @ApiModelProperty(value = "组织id", example = "1")
    private Long deptId;


    @ApiModelProperty(value = "是否启用，1：启用，0：禁用", example = "1", hidden = true)
    private Boolean isActivated;


    @ApiModelProperty(value = "是否锁定，1：锁定，0：正常", example = "1", hidden = true)
    private Boolean isLocked;
}
