package com.zhiyu.common.entity.dto.elasticsearch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wengzhiyu
 * @since 2021/8/27 17:52
 */
@Data
public class IndexDto {
    @ApiModelProperty(value = "索引名称",example = "employee_index")
    private String index;

    @ApiModelProperty(value = "分片设置信息",example = "json格式")
    private String settings;

    @ApiModelProperty(value = "mapping设置信息",example = "json格式")
    private String mappings;
}
