package com.zhiyu.security.controller;

import com.zhiyu.core.result.ResponseData;
import com.zhiyu.security.entity.form.user.UserQueryForm;
import com.zhiyu.security.service.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
@RequiredArgsConstructor
@Api(tags = "用户管理")
public class UserController {

    private final UserService userService;

    /**
     * 获取用户列表
     *
     * @param userQueryForm 查询条件表单，包含账号、用户名等信息
     * @return 包含用户列表的响应数据
     */
    @GetMapping("/list")
    public ResponseData getUserList(@ModelAttribute UserQueryForm userQueryForm) {
        return userService.getUserList(userQueryForm);
    }

    /**
     * 获取用户信息
     *
     * @return 包含用户信息的响应数据
     */
    @GetMapping("/userInfo")
    public ResponseData getUserInfo() {
        return userService.getUserInfo();
    }


    @GetMapping("/getOnlineUser")
    public ResponseData getOnlineUser(String username, Pageable pageable) {
        return userService.getOnlineUser(username, pageable);
    }


    @PostMapping("/addUser")
    public ResponseData addUser() {


        return ResponseData.success();
    }

    @PutMapping("/updateUser")
    public ResponseData updateUser() {


        return ResponseData.success();
    }


    @DeleteMapping("/deleteUser")
    public ResponseData deleteUser() {


        return ResponseData.success();
    }


}
