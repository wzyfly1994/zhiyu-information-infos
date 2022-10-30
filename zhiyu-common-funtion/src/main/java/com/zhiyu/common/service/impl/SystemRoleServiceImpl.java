package com.zhiyu.common.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyu.common.entity.pojo.SystemRole;
import com.zhiyu.common.mapper.SystemRoleMapper;
import com.zhiyu.common.service.SystemRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author wengzhiyu
 * @since 2021-06-17
 */
@Service
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements SystemRoleService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void testTransactionalC() {
        saveData("C");
        int a = 1 / 0;
    }

    private void saveData(String data) {
        SystemRole systemRole = new SystemRole();
        systemRole.setRoleName(data);
        this.save(systemRole);
    }
}
