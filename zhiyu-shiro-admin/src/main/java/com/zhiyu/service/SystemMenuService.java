package com.zhiyu.service;

import com.zhiyu.entity.dto.MenuDto;
import com.zhiyu.utils.ResponseData;

/**
 * @author wengzhiyu
 * @date 2020/10/27
 */
public interface SystemMenuService {

    /**
     * 菜单树
     *
     * @return
     */
    ResponseData menuTree();


    /**
     * 添加菜单
     *
     * @param menuDto
     * @return
     */
    ResponseData saveUpdate(MenuDto menuDto);
}
