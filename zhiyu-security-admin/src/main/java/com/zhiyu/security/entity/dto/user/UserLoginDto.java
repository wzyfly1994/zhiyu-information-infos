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
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhiyu.core.base.BaseDTO;
import com.zhiyu.security.entity.pojo.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserLoginDto extends BaseDTO {

    private String id;

    /**
     * 账号
     */
    private String account;

    /**
     * 密码
     */
    @JSONField(serialize = false)
    @JsonIgnore
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
    private String deptId;

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


    public static UserLoginDto toMo(User user) {
        if (user == null) {
            return null;
        }
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setId(user.getId() != null ? user.getId().toString() : null);
        userLoginDto.setAccount(user.getAccount());
        userLoginDto.setPassWord(user.getPassWord());
        userLoginDto.setUserName(user.getUserName());
        userLoginDto.setSex(user.getSex());
        userLoginDto.setEmail(user.getEmail());
        userLoginDto.setPhone(user.getPhone());
        userLoginDto.setDeptId(user.getDeptId() != null ? user.getDeptId().toString() : null);
        userLoginDto.setIsAdmin(user.getIsAdmin());
        userLoginDto.setStatus(user.getStatus());
        userLoginDto.setCreateTime(user.getCreateTime());
        userLoginDto.setUpdateTime(user.getUpdateTime());
        return userLoginDto;
    }

}
