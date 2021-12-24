package com.zhiyu.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import result.ResponseData;

/**
 * @author wengzhiyu
 * @since 2021/12/20 11:46
 */
@RestController
@RequestMapping("/admin")
public class LoginController {

    @GetMapping("/login")
    public ResponseData login() {
        return ResponseData.success();
    }

}

