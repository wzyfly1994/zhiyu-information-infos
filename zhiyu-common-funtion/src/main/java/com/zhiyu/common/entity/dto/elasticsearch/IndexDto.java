package com.zhiyu.common.entity.dto.elasticsearch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wengzhiyu
 * @since 2021/8/27 17:52
 */
@Data
public class IndexDto {
    @ApiModelProperty(value = "索引名称",example = "goods")
    private String index;

    @ApiModelProperty(value = "索引类型",example = "b2b")
    private String type;

    @ApiModelProperty(value = "索引ID",example = "1")
    private String id;

    @ApiModelProperty(value = "分片设置信息",example = "json格式")
    private String settings;

    @ApiModelProperty(value = "mapping设置信息",example = "json格式")
    private String mappings;
}
