package com.zhiyu.security.controller;

import com.zhiyu.core.result.ResponseData;
import com.zhiyu.security.annotation.rest.AnonymousGetMapping;
import com.zhiyu.security.annotation.rest.AnonymousPostMapping;
import com.zhiyu.security.entity.dto.user.AuthUserDto;
import com.zhiyu.security.entity.dto.user.RegisterUserDto;
import com.zhiyu.security.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author wengzhiyu
 * @since 2021/12/20 11:46
 */
@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
@Api(tags = "授权模块")
public class LoginController {


    private final UserService userService;

    @AnonymousPostMapping("/login")
    // @PermitAll
    @ApiOperation("登录")
    public ResponseData login(@Valid @RequestBody AuthUserDto authUserDto) {
        return userService.login(authUserDto);
    }


    @PostMapping("/logout")
    @PermitAll
    @ApiOperation("登出")
    public ResponseData logout(@Valid @RequestBody AuthUserDto authUserDto) {

        return ResponseData.success();
    }

    @PostMapping("/register")
    @PermitAll
    @ApiOperation("注册")
    public ResponseData register(@Valid @RequestBody RegisterUserDto registerUserDto) {
        return userService.register(registerUserDto);
    }


    @ApiOperation("获取验证码")
    @AnonymousGetMapping("getCode")
    public ResponseData getCode() {
        return userService.getCode();
    }


    @AnonymousGetMapping("/test")
    @PreAuthorize("@el.check()")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        log.info("1111");

    }

}

