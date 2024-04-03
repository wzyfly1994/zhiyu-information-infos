package com.zhiyu.security.service.impl;

import com.zhiyu.core.result.ResponseData;
import com.zhiyu.core.utils.RedisUtils;
import com.zhiyu.security.entity.dto.AuthUserDto;
import com.zhiyu.security.entity.pojo.SystemUser;
import com.zhiyu.security.mapper.SystemUserMapper;
import com.zhiyu.security.service.SystemUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wengzhiyu
 * @since 2024-04-02
 */
@Service
@RequiredArgsConstructor
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {

    private final RedisUtils redisUtils;


    @Override
    public SystemUser getUserByKey(String key) {
        return (SystemUser) redisUtils.get(key);
    }

    @Override
    public ResponseData login(AuthUserDto authUserDto) {
        return ResponseData.success();
    }
}
