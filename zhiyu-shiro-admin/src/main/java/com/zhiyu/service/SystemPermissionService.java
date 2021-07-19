package com.zhiyu.service;

import java.util.Map;

/**
 * @author wengzhiyu
 * @date 2020/7/27
 */
public interface SystemPermissionService {

    /**
     * 初始化过滤链
     *
     * @return
     */
    String intiFilterChain();


    /**
     * 过滤链MAP
     *
     * @return
     */
    Map<String, String> initFilterChainMap();

    /**
     * 更新过滤链
     *
     * @return
     */
    Map<String, String> updateFilterChain();
}
