package com.zhiyu.gateway.common;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 路由模型
 *
 * @author wengzhiyu
 * @date 2021/02/04 10:26
 */
@Data
public class RouteDefinitionDTO {

    /**
     * 路由的Id
     */
    private String id;

    /**
     * 路由断言集合配置
     */
    private List<PredicateDefinitionDTO> predicates;

    /**
     * 路由过滤器集合配置
     */
    private List<FilterDefinitionDTO> filters;

    /**
     * 路由规则转发的目标uri
     */
    private String uri;

    /**
     * 路由执行的顺序
     */
    private Integer order;

    @Data
    public static class PredicateDefinitionDTO {

        /**
         * 断言名称
         */
        private String name;

        /**
         * 断言规则
         */
        private LinkedHashMap<String, String> args;
    }

    @Data
    public static class FilterDefinitionDTO {

        /**
         * 过滤器名称
         */
        private String name;

        /**
         * 路由规则
         */
        private LinkedHashMap<String, String> args;
    }
}
