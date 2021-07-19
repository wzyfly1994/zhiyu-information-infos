package com.zhiyu.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author wengzhiyu
 * @date 2020/9/15
 */
@Data
public class SystemRoleDto implements Serializable {
    private static final long serialVersionUID = -515591619334343783L;

    @ApiModelProperty(value = "角色id(新增不用传)", example = "1")
    private Long id;

    @NotBlank(message = "角色名不能为空")
    @ApiModelProperty(value = "角色名", required = true, example = "测试角色")
    private String roleName;

    @NotEmpty(message = "菜单列表不能为空")
    @ApiModelProperty(value = "菜单列表", required = true, example = "[1,2]")
    private List<Long> menuIdList;

    @NotNull(message = "部门不能为空")
    @ApiModelProperty(value = "部门", required = true, example = "1")
    private Long depId;

    @NotNull(message = "是否可用不能为空")
    @ApiModelProperty(value = "是否可用", required = true, example = "true")
    private Boolean isUse;

    @ApiModelProperty(hidden = true)
    private Date recordDate;
}
