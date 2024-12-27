package com.zhiyu.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhiyu.security.entity.pojo.Menu;
import com.zhiyu.security.entity.pojo.User;

import java.util.Set;

/**
 * <p>
 * 菜单 服务类
 * </p>
 *
 * @author Jason
 * @since 2024-12-17
 */
public interface MenuService extends IService<Menu> {


    /**
     * 根据用户ID获取其菜单权限集合
     *
     * @param user 用户对象，需包含用户ID信息，用于查询权限
     * @return 返回该用户所拥有的菜单权限标识符集合，如读、写等权限
     */
    Set<String> getMenuPermissionByUserId(User user);

}
