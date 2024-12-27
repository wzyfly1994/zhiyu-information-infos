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
package com.zhiyu.security.entity.dto.user;


import com.alibaba.fastjson2.annotation.JSONField;
import com.zhiyu.core.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserLoginDto extends BaseDTO {

    private Long id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    @JSONField(serialize = false)
    private String passWord;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 性别 1:男，2:女，3:未知
     */
    private Integer sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 组织id
     */
    private Long deptId;

    /**
     * 是否超级管理员
     */
    private Boolean isAdmin;

    /**
     * 状态(0:正常 1:禁用)
     */
    private Boolean status;

    /**
     * 角色信息
     */
    private List<UserRoleDto> roles;


}
