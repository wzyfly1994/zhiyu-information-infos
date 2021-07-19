package com.zhiyu.controller;

import com.zhiyu.repository.SystemDepartmentRepository;
import com.zhiyu.utils.ResponseData;
import com.zhiyu.utils.tree.TreeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wengzhiyu
 * @date 2020/10/14
 */
@RestController
@RequestMapping("/dep")
@Api(tags = "组织架构")
public class DepartmentController {
    @Resource
    private SystemDepartmentRepository systemDepartmentRepository;

    @GetMapping("/tree")
    @ApiOperation("组织架构树")
    public ResponseData depTree() {
        return ResponseData.success(TreeUtil.listToTree(0L, systemDepartmentRepository.findAll()));
    }
}
