package com.zhiyu.security.service;

import com.zhiyu.core.result.ResponseData;
import com.zhiyu.security.entity.dto.user.AuthUserDto;
import com.zhiyu.security.entity.dto.user.RegisterUserDto;
import com.zhiyu.security.entity.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyu.security.entity.pojo.User;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author Jason
 * @since 2024-12-17
 */
public interface UserService extends IService<User> {

    User getUserByKey(String key);

    ResponseData login(AuthUserDto authUserDto);

    ResponseData register(RegisterUserDto registerUserDto);

    ResponseData getCode();
}
