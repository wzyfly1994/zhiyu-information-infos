package com.zhiyu.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author wengzhiyu
 * @date 2020/10/26
 */
@Data
public class MenuDto {

    @ApiModelProperty(value = "菜单ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "上级菜单", example = "0")
    @NotNull(message = "上级菜单不能为空")
    private Long parentId;

    @ApiModelProperty(value = "菜单名称", example = "菜单名称")
    @NotBlank(message = "菜单名称不能为空")
    private String menuName;

    @ApiModelProperty(value = "菜单级别", example = "1")
    @NotNull(message = "菜单级别不能为空")
    private Integer level;

    @ApiModelProperty(value = "菜单排序号", example = "1")
    @NotNull(message = "菜单排序号不能为空")
    private Integer serialNum;

    @ApiModelProperty(value = "是否有效", example = "true")
    @NotNull(message = "是否有效不能为空")
    private Boolean isUse;

    @ApiModelProperty(value = "菜单描述", example = "菜单")
    @NotBlank(message = "菜单描述不能为空")
    private String description;

    @ApiModelProperty(value = "菜单图标", example = " ''")
    private String imageUrl;
}
