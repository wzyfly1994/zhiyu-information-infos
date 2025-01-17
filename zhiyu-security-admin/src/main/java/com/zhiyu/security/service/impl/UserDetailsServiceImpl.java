/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.zhiyu.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhiyu.core.exception.BusinessException;
import com.zhiyu.core.utils.CloneUtils;
import com.zhiyu.security.entity.dto.user.AuthorityDto;
import com.zhiyu.security.entity.dto.user.JwtUserDto;
import com.zhiyu.security.entity.dto.user.UserLoginDto;
import com.zhiyu.security.entity.dto.user.UserRoleDto;
import com.zhiyu.security.entity.pojo.Role;
import com.zhiyu.security.entity.pojo.User;
import com.zhiyu.security.manager.UserCacheManager;
import com.zhiyu.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final UserCacheManager userCacheManager;
    private final MenuServiceImpl menuService;
    private final RoleServiceImpl roleService;


    @Override
    public JwtUserDto loadUserByUsername(String username) {

        JwtUserDto jwtUserDto = userCacheManager.getUserCache(username);
        if (jwtUserDto == null) {
            User user = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getAccount, username),
                    false);
            if (user == null) {
                throw new BusinessException("账户不存在");
            }

            // 用户权限
            Set<String> userPermissions = menuService.getMenuPermissionByUserId(user);
            List<AuthorityDto> authorities = userPermissions.stream().map(item -> {
                AuthorityDto dto = new AuthorityDto();
                dto.setAuthority(item);
                return dto;
            }).collect(Collectors.toList());

            UserLoginDto userLoginDto = UserLoginDto.toMo(user);

            //用户角色
            List<Role> userRoles = roleService.getUserRolesByUserId(user.getId());
            if (CollectionUtils.isNotEmpty(userRoles)) {
                List<UserRoleDto> userRolesDto = userRoles.stream()
                        .map(UserRoleDto::toMo)
                        .collect(Collectors.toList());
                userLoginDto.setRoles(userRolesDto);
            }

            jwtUserDto = new JwtUserDto(userLoginDto, userLoginDto.getId(),
                    userPermissions, userLoginDto.getDeptId(),authorities);

            //  添加用户缓存数据
            userCacheManager.addUserCache(username, jwtUserDto);
        }

        return jwtUserDto;
    }
}
