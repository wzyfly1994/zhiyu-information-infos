package com.zhiyu.common.entity.dto.elasticsearch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wengzhiyu
 * @since 2021/8/27 17:52
 */
@Data
public class IndexDocDto<T> {

    @ApiModelProperty(value = "索引名称",example = "goods")
    private String index;

    @ApiModelProperty(value = "索引类型",example = "b2b")
    private String type;

    @ApiModelProperty(value = "索引ID",example = "1")
    private String id;

    @ApiModelProperty(value = "数据")
    private T source;
}
