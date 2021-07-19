package com.zhiyu.service;

import com.zhiyu.entity.dto.SystemRoleDto;
import com.zhiyu.utils.ResponseData;

/**
 * @author wengzhiyu
 * @date 2020/9/15
 */
public interface SystemRoleService {


    /**
     * 添加角色
     *
     * @param systemRoleDto
     * @return
     */
    ResponseData addRole(SystemRoleDto systemRoleDto);


    /**
     * 修改角色
     *
     * @param systemRoleDto
     * @return
     */
    ResponseData updateRole(SystemRoleDto systemRoleDto);
}
