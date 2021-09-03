package com.zhiyu.common.entity.dto.elasticsearch;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wengzhiyu
 * @since 2021/8/27 17:52
 */
@Data
@ApiModel(value = "搜索DTO")
public class SearchDocDto {
    @ApiModelProperty(value = "索引名称", example = "goods")
    private String index;

    @ApiModelProperty(value = "索引类型", example = "b2b")
    private String type;

    @ApiModelProperty(value = "搜索内容", example = "huawei")
    private String keywords;

    @ApiModelProperty(value = "第几页", example = "1")
    private Integer pageNo;

    @ApiModelProperty(value = "每页多少条", example = "10")
    private Integer pageSize;

    @ApiModelProperty(value = "关键字是否高亮", example = "false")
    private Boolean isHighlight = false;
}
