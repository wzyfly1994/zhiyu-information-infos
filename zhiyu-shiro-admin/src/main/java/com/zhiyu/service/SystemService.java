package com.zhiyu.service;

import com.zhiyu.entity.dto.SystemUserAddDto;
import com.zhiyu.entity.dto.SystemUserLoginDto;
import com.zhiyu.entity.dto.SystemUserUpdateDto;
import com.zhiyu.entity.pojo.system.SystemUser;
import com.zhiyu.utils.ResponseData;

/**
 * @author wengzhiyu
 * @date 2019/01/07
 */
public interface SystemService {

    /**
     * 登陆
     *
     * @param systemUserLoginDto
     * @return
     */
    ResponseData userLogin(SystemUserLoginDto systemUserLoginDto);


    /**
     * 添加用户
     *
     * @param systemUserAddDto
     * @return
     */
    ResponseData addUser(SystemUserAddDto systemUserAddDto);


    /**
     * 修改用户
     *
     * @param systemUserUpdateDto
     * @return
     */
    ResponseData updateUser(SystemUserUpdateDto systemUserUpdateDto);

    /**
     * 用户保存到redis
     * @param systemUser
     */
    void   userSaveRedis(SystemUser systemUser);
}
