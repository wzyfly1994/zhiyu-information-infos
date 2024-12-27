package com.zhiyu.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyu.security.entity.pojo.Role;
import com.zhiyu.security.entity.pojo.UserRole;
import com.zhiyu.security.mapper.RoleMapper;
import com.zhiyu.security.service.RoleService;
import com.zhiyu.security.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2024-12-17
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final UserRoleService userRoleService;


    @Override
    public List<Role> getUserRolesByUserId(Long userId) {
        if (Objects.isNull(userId)) {
            return Collections.emptyList();
        }

        // 先获取用户关联的所有roleId集合
        List<Long> roleIds = userRoleService.list(new LambdaQueryWrapper<UserRole>()
                        .select(UserRole::getRoleId)
                        .eq(UserRole::getUserId, userId))
                .stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());

        return CollectionUtils.isEmpty(roleIds) ? Collections.emptyList() :
                super.list(new LambdaQueryWrapper<Role>().in(Role::getId, roleIds));
    }
}
