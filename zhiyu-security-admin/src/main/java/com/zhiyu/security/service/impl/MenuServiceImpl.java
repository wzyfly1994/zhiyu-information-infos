package com.zhiyu.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiyu.security.entity.pojo.Menu;
import com.zhiyu.security.entity.pojo.User;
import com.zhiyu.security.mapper.MenuMapper;
import com.zhiyu.security.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2024-12-17
 */
@Service
@RequiredArgsConstructor
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    private final MenuMapper menuMapper;

    private final UserServiceImpl userService;


    @Override
    public Set<String> getMenuPermissionByUserId(User user) {
        Set<String> perms = new HashSet<>();

        if (user.getIsAdmin()) {
            perms.add("*:*:*");
        } else {
            List<String> userPerms = menuMapper.selectMenuPermsByUserId(user.getId());
            if (CollectionUtils.isNotEmpty(userPerms)) {
                perms.addAll(userPerms);
            }
        }
        return perms;
    }
}
