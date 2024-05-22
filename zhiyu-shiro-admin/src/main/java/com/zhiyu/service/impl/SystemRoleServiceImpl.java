package com.zhiyu.service.impl;

import com.zhiyu.common.exception.BusinessException;
import com.zhiyu.config.constant.BCErrorCode;
import com.zhiyu.entity.dto.SystemRoleDto;
import com.zhiyu.entity.pojo.system.SystemPermission;
import com.zhiyu.entity.pojo.system.SystemRole;
import com.zhiyu.entity.pojo.system.SystemRolePermission;
import com.zhiyu.repository.SystemPermissionRepository;
import com.zhiyu.repository.SystemRolePermissionRepository;
import com.zhiyu.repository.SystemRoleRepository;
import com.zhiyu.service.SystemRoleService;
import com.zhiyu.utils.ResponseData;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wengzhiyu
 * @date 2020/9/15
 */
@Service
public class SystemRoleServiceImpl implements SystemRoleService {

    @Autowired
    private SystemRoleRepository systemRoleRepository;
    @Autowired
    private SystemRolePermissionRepository systemRolePermissionRepository;
    @Autowired
    private SystemPermissionRepository systemPermissionRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData addRole(SystemRoleDto systemRoleDto) {
        if (systemRoleDto == null) {
            return ResponseData.error(BCErrorCode.DATA_NOT_NULL.getMsg());
        }
        SystemRole systemRole = new SystemRole();
        BeanUtils.copyProperties(systemRoleDto, systemRole);
        systemRole.setRoleValue(RandomStringUtils.randomAlphanumeric(32));
        SystemRole roleMode = systemRoleRepository.save(systemRole);
        //菜单列表
        List<Long> menuIdList = systemRoleDto.getMenuIdList();
        saveRolePermission(menuIdList, roleMode.getId());
        return ResponseData.success("角色新增成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData updateRole(SystemRoleDto systemRoleDto) {
        if (systemRoleDto == null) {
            return ResponseData.error(BCErrorCode.DATA_NOT_NULL.getMsg());
        }
        Long roleId = systemRoleDto.getId();
        Optional<SystemRole> optionalSystemRole = systemRoleRepository.findById(roleId);
        if (optionalSystemRole.isPresent()) {
            SystemRole systemRole = optionalSystemRole.get();
            BeanUtils.copyProperties(systemRoleDto, systemRole);
            List<Long> menuIdList = systemRoleDto.getMenuIdList();
            if (!CollectionUtils.isEmpty(menuIdList)) {
                systemRolePermissionRepository.deleteByRoleIdIn(menuIdList);
                saveRolePermission(menuIdList, roleId);
            }
        } else {
            return ResponseData.error("不存在此角色");
        }
        return ResponseData.success("角色修改成功");
    }


    private void saveRolePermission(List<Long> menuIdList, Long roleId) {
        if (!CollectionUtils.isEmpty(menuIdList)) {
            for (Long menuId : menuIdList) {
                List<Long> permissionList = systemPermissionRepository.findAllByMenuId(menuId).stream().map(SystemPermission::getId).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(permissionList)) {
                    Long permissionId = permissionList.get(0);
                    SystemRolePermission systemRolePermission = new SystemRolePermission();
                    systemRolePermission.setRoleId(roleId);
                    systemRolePermission.setPermissionId(permissionId);
                    systemRolePermissionRepository.save(systemRolePermission);
                } else {
                    throw new BusinessException("至少有一条菜单权限不存在");
                }
            }
        }
    }


}




