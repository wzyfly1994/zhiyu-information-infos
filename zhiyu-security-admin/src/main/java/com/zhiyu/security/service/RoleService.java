package com.zhiyu.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyu.security.entity.pojo.Role;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author Jason
 * @since 2024-12-17
 */
public interface RoleService extends IService<Role> {

    List<Role> getUserRolesByUserId(Long userId);
}
