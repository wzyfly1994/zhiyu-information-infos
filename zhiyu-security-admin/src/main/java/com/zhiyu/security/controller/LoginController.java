package com.zhiyu.security.controller;

import com.zhiyu.core.result.ResponseData;
import com.zhiyu.security.annotation.rest.AnonymousGetMapping;
import com.zhiyu.security.annotation.rest.AnonymousPostMapping;
import com.zhiyu.security.entity.dto.user.AuthUserDto;
import com.zhiyu.security.entity.dto.user.RegisterUserDto;
import com.zhiyu.security.service.SystemUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

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

    private final SystemUserService systemUserService;

    @AnonymousPostMapping("/login")
    // @PermitAll
    @ApiOperation("登录")
    public ResponseData login(@Valid @RequestBody AuthUserDto authUserDto) {
        return systemUserService.login(authUserDto);
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
        return systemUserService.register(registerUserDto);
    }


    @ApiOperation("获取验证码")
    @AnonymousGetMapping("getCode")
    public ResponseData getCode() {
        return systemUserService.getCode();
    }


    @AnonymousGetMapping("/test")
    @PreAuthorize("@el.check()")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        log.info("1111");

    }

}

