package com.zhiyu.common.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyu.common.entity.pojo.SystemRole;
import com.zhiyu.common.mapper.SystemRoleMapper;
import com.zhiyu.common.service.SystemRoleService;
import org.springframework.stereotype.Service;

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

}
