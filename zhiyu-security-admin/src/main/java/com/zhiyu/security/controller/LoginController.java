package com.zhiyu.security.controller;

import com.zhiyu.security.entity.dto.AuthUserDto;
import com.zhiyu.security.service.SystemUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zhiyu.core.result.ResponseData;

import javax.annotation.security.PermitAll;

/**
 * @author wengzhiyu
 * @since 2021/12/20 11:46
 */
@RestController
@RequestMapping("/admin")
@Slf4j
@RequiredArgsConstructor
public class LoginController {

    private final SystemUserService systemUserService;

    @PostMapping("/login")
    public ResponseData login(@Validated @RequestBody AuthUserDto authUserDto) {
        return systemUserService.login(authUserDto);
    }


    @PostMapping("/logout")
    @PermitAll
    public ResponseData logout() {
        return ResponseData.success();
    }

}

