package com.zhiyu.controller;

import com.zhiyu.entity.dto.MenuDto;
import com.zhiyu.service.SystemMenuService;
import com.zhiyu.utils.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wengzhiyu
 * @date 2020/10/21
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单模块")
public class MenuController {

    @Autowired
    private SystemMenuService systemMenuService;

    @GetMapping("/tree")
    @ApiOperation("菜单树")
    public ResponseData menuTree() {
        return systemMenuService.menuTree();
    }

    @PostMapping("/saveUpdate")
    @ApiOperation("添加菜单")
    public ResponseData saveUpdate(@RequestBody MenuDto menuDto) {
        return systemMenuService.saveUpdate(menuDto);
    }


}
