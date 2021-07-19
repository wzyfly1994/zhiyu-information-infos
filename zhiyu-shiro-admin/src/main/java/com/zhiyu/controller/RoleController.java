package com.zhiyu.controller;

import com.zhiyu.entity.dto.SystemRoleDto;
import com.zhiyu.service.SystemRoleService;
import com.zhiyu.utils.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author wengzhiyu
 * @date 2020/5/26
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色模块")
public class RoleController {

    @Autowired
    private SystemRoleService systemRoleService;




    @PostMapping("/addRole")
    @ApiOperation("添加角色")
    public ResponseData addRole(@RequestBody @Valid SystemRoleDto systemRoleDto) {
        return systemRoleService.addRole(systemRoleDto);
    }


    @PostMapping("/updateRole")
    @ApiOperation("修改角色")
    public ResponseData updateRole(@RequestBody @Valid SystemRoleDto systemRoleDto) {
        if (systemRoleDto.getId() == null) {
            return ResponseData.error("角色id不能为空");
        }
        return systemRoleService.updateRole(systemRoleDto);
    }

}
