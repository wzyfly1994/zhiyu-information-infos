package com.zhiyu.controller;

import com.zhiyu.config.constant.BCErrorCode;
import com.zhiyu.entity.dto.SystemUserAddDto;
import com.zhiyu.entity.dto.SystemUserLoginDto;
import com.zhiyu.entity.dto.SystemUserUpdateDto;
import com.zhiyu.service.DictionaryService;
import com.zhiyu.service.SystemPermissionService;
import com.zhiyu.service.SystemService;
import com.zhiyu.utils.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


/**
 * @author wengzhiyu
 * @date 2020/1/06
 */
@RestController
@RequestMapping("/system")
@Api(tags = "用户系统模块")
public class SystemController {

    @Resource
    private SystemService systemService;
    @Resource
    private SystemPermissionService systemPermissionService;
    @Resource
    private DictionaryService dictionaryService;

    @PostMapping("/login")
    @ApiOperation("登陆")
    public ResponseData login(@RequestBody @Valid SystemUserLoginDto systemUserLoginDto) {
        return systemService.userLogin(systemUserLoginDto);
    }

    @PostMapping("/logout")
    @ApiOperation("退出登陆")
    public ResponseData logout() {
        SecurityUtils.getSubject().logout();
        return ResponseData.success("退出登陆成功");
    }


    @PostMapping("/addUser")
    @ApiOperation("添加用户")
    public ResponseData addUser(@RequestBody @Valid SystemUserAddDto systemUserAddDto) {
        return systemService.addUser(systemUserAddDto);
    }

    @PostMapping("/updateUser")
    @ApiOperation("添加用户")
    public ResponseData updateUser(@RequestBody @Valid SystemUserUpdateDto systemUserUpdateDto) {
        return systemService.updateUser(systemUserUpdateDto);
    }

    @ApiOperation("更新过滤链")
    @GetMapping("/permission/update")
    public ResponseData updatePermission() {
        return ResponseData.success(systemPermissionService.updateFilterChain());
    }


    @GetMapping("queryDictionary")
    @ApiOperation(value = "下拉字典查询")
    public ResponseData queryDictionary(String selectName) {
        return ResponseData.success(dictionaryService.loadKevValueManage(selectName));
    }

    @GetMapping("/loginError")
    public ResponseData error() {
        return ResponseData.error(BCErrorCode.PARAMS_VALIDATE_ERROR.getCode(),
                BCErrorCode.PARAMS_VALIDATE_ERROR.getMsg(),
                null);
    }

}
