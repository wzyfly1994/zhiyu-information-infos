package com.zhiyu.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyu.common.entity.pojo.SystemRole;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author wengzhiyu
 * @since 2021-06-17
 */
public interface SystemRoleService extends IService<SystemRole> {

    void testTransactionalC();
}
