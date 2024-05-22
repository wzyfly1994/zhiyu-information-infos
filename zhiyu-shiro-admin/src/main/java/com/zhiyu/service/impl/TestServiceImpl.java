package com.zhiyu.service.impl;

import com.zhiyu.entity.pojo.system.SystemPermission;
import com.zhiyu.entity.pojo.system.SystemRole;
import com.zhiyu.repository.SystemPermissionRepository;
import com.zhiyu.repository.SystemRoleRepository;
import com.zhiyu.service.DictionaryService;
import com.zhiyu.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wengzhiyu
 * @date 2021/2/26
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private SystemRoleRepository systemRoleRepository;

    @Autowired
    private SystemPermissionRepository systemPermissionRepository;
    @Autowired
    private DictionaryService dictionaryService;

    @Override
    //@Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public void roleSave() {
        SystemRole systemRole = new SystemRole();
        systemRole.setRoleName("角色1");
        systemRoleRepository.save(systemRole);
        dictionaryService.permissionSave();
       // int a = 1 / 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void permissionSave() {
        SystemPermission systemPermission = new SystemPermission();
        systemPermission.setMenuId(55L);
        systemPermission.setDescription("权限");
        systemPermissionRepository.save(systemPermission);
        //throw new BusinessException("1");
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public void add() {
        roleSave();
        permissionSave();
        int a = 1 / 0;
    }
}
