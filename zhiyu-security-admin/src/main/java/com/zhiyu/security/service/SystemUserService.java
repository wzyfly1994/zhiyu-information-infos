package com.zhiyu.security.service;

import com.zhiyu.core.result.ResponseData;
import com.zhiyu.security.entity.dto.user.AuthUserDto;
import com.zhiyu.security.entity.dto.user.RegisterUserDto;
import com.zhiyu.security.entity.pojo.SystemUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wengzhiyu
 * @since 2024-04-02
 */
public interface SystemUserService extends IService<SystemUser> {


    SystemUser getUserByKey(String key);

    ResponseData login(AuthUserDto authUserDto);

    ResponseData register(RegisterUserDto registerUserDto);

    ResponseData getCode();

}
