package com.zhiyu.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.ResponseData;

import javax.annotation.security.PermitAll;

/**
 * @author wengzhiyu
 * @since 2021/12/20 11:46
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class LoginController {

    @Value("${admin.en}")
    private String userName1;

    @PostMapping("/login")
    public ResponseData login() {
        log.info("userName:{}", userName1);
        return ResponseData.success(userName1);
    }


    @PostMapping("/logout")
    @PermitAll
    public ResponseData logout() {
        log.info("userName:{}", userName1);
        return ResponseData.success(userName1);
    }

}

