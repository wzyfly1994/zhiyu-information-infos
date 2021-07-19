package com.zhiyu.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author wengzhiyu
 * @date 2020/10/30
 */
@Data
public class SystemUserLoginDto implements Serializable {
    private static final long serialVersionUID = -8062342505980230179L;

    @ApiModelProperty(value = "账号", example = "wzy999")
    @NotBlank(message = "账号不能为空")
    private String account;

    @ApiModelProperty(value = "密码", example = "asc123")
    @NotBlank(message = "密码不能为空")
    private String password;
}
