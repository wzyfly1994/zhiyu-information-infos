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
import com.zhiyu.security.entity.dto.user.JwtUserDto;
import com.zhiyu.security.entity.dto.user.UserLoginDto;
import com.zhiyu.security.entity.pojo.SystemUser;
import com.zhiyu.security.manager.UserCacheManager;
import com.zhiyu.security.service.SystemUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final SystemUserService systemUserService;
    private final UserCacheManager userCacheManager;

    @Override
    public JwtUserDto loadUserByUsername(String username) {

        JwtUserDto jwtUserDto = userCacheManager.getUserCache(username);
        if (jwtUserDto == null) {
            SystemUser systemUser = systemUserService.getOne(new LambdaQueryWrapper<SystemUser>().eq(SystemUser::getAccount, username),
                    false);
            if (systemUser == null) {
                throw new BusinessException("账户不存在");
            }

            //  添加缓存数据
            userCacheManager.addUserCache(username, jwtUserDto);
        }

        return jwtUserDto;
    }
}
