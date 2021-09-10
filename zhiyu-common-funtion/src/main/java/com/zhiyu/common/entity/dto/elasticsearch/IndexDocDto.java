package com.zhiyu.common.entity.dto.elasticsearch;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wengzhiyu
 * @since 2021/8/27 17:52
 */
@Data
public class IndexDocDto {

    @ApiModelProperty(value = "索引名称",example = "employee_index")
    private String index;

    @ApiModelProperty(value = "索引ID",example = "1")
    private String id;

    @ApiModelProperty(value = "数据体 JSON格式")
    private String source;
}
