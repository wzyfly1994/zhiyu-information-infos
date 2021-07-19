package com.zhiyu.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author wengzhiyu
 * @date 2020/10/30
 */
@Data
public class SystemUserUpdateDto implements Serializable {
    private static final long serialVersionUID = 1769645168893287543L;
    @ApiModelProperty(value = "用户id", example = "1")
    private Long id;

    @ApiModelProperty(value = "用户名称", example = "小Y")
    private String userName;

    @ApiModelProperty("头像图片")
    private String headImg;

    @ApiModelProperty(value = "性别 1:男，2:女，3:未知", example = "1")
    @NotNull(message = "性别不能为空")
    private Integer sex;

    @ApiModelProperty(value = "邮箱", example = "159558@qq.com")
    private String email;

    @ApiModelProperty(value = "手机号", example = "18995359956")
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty(value = "角色列表", example = "[1]")
    @NotEmpty(message = "角色列表不能为空")
    private List<Long> roleList;
}
