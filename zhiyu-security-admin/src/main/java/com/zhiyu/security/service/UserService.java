package com.zhiyu.security.service;

import com.zhiyu.core.result.ResponseData;
import com.zhiyu.security.entity.dto.user.AuthUserDto;
import com.zhiyu.security.entity.dto.user.OnlineUserDto;
import com.zhiyu.security.entity.dto.user.RegisterUserDto;
import com.zhiyu.security.entity.form.user.UserQueryForm;
import com.zhiyu.security.entity.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 * 用户 服务类
 * </p>
 *
 * @author Jason
 * @since 2024-12-17
 */
public interface UserService extends IService<User> {

    OnlineUserDto getOnlineUserByKey(String key);

    ResponseData login(AuthUserDto authUserDto);

    ResponseData register(RegisterUserDto registerUserDto);

    ResponseData getCode();

    void logout(String token);

    ResponseData getUserList(UserQueryForm userQueryForm);

    ResponseData getUserInfo();

    ResponseData getOnlineUser(String username, Pageable pageable);
}
