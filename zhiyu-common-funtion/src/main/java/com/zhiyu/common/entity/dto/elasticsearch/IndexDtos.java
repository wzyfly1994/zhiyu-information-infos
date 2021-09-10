package com.zhiyu.common.entity.dto.elasticsearch;

import lombok.Data;

/**
 * @author wengzhiyu
 * @since 2021/9/8 10:48
 */
@Data
public class IndexDtos {

    private String index;

    private Mapping mappings;

    private Setting settings;

    @Data
    static class Mapping {

        private Properties properties;

        @Data
        static class Properties {


        }

    }


    @Data
    static class Setting {


    }

}
