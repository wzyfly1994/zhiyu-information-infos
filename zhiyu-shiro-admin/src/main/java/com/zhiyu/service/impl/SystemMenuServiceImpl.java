package com.zhiyu.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhiyu.config.constant.BCErrorCode;
import com.zhiyu.entity.dto.MenuDto;
import com.zhiyu.entity.pojo.system.SystemMenu;
import com.zhiyu.entity.pojo.system.SystemPermission;
import com.zhiyu.entity.vo.SystemMenuVo;
import com.zhiyu.repository.SystemMenuRepository;
import com.zhiyu.repository.SystemPermissionRepository;
import com.zhiyu.service.SystemMenuService;
import com.zhiyu.utils.ResponseData;
import com.zhiyu.utils.tree.TreeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wengzhiyu
 * @date 2020/10/27
 */
@Service
@Slf4j
public class SystemMenuServiceImpl implements SystemMenuService {

    @Autowired
    private SystemMenuRepository systemMenuRepository;
    @Autowired
    private SystemPermissionRepository systemPermissionRepository;

    @Override
    public ResponseData menuTree() {
        List<Map<String, Object>> treeMap = systemMenuRepository.tree();
        List<SystemMenuVo> treeList = treeMap.stream().map(x -> JSON.parseObject(JSON.toJSONString(x), SystemMenuVo.class)).collect(Collectors.toList());
        return ResponseData.success(TreeUtil.listToTree(0L, treeList));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData saveUpdate(MenuDto menuDto) {
        if (menuDto == null) {
            return ResponseData.error(BCErrorCode.DATA_NOT_NULL.getMsg());
        }
        Long menuId = menuDto.getId();
        String permissionValue = RandomStringUtils.randomAlphanumeric(32);
        SystemMenu systemMenu = new SystemMenu();
        if (menuId != null) {
            Optional<SystemMenu> menuOptional = systemMenuRepository.findById(menuId);
            if (menuOptional.isPresent()) {
                systemMenu = menuOptional.get();
            } else {
                return ResponseData.error("不存在此菜单,修改失败");
            }
            List<SystemPermission> systemPermissionList = systemPermissionRepository.findAllByMenuId(menuId);
            if (CollectionUtils.isEmpty(systemPermissionList)) {
                return ResponseData.error("修改时不存在此菜单权限");
            }
            BeanUtils.copyProperties(menuDto, systemMenu);
            systemMenu.setUpdateTime(new Date());
            SystemPermission systemPermissionUpdate = systemPermissionList.get(0);
            systemPermissionUpdate.setDescription(systemMenu.getDescription());
            systemPermissionUpdate.setPermissionName(systemMenu.getMenuName());
        } else {
            BeanUtils.copyProperties(menuDto, systemMenu);
            systemMenu.setCreateTime(new Date());
            systemMenu.setPermissionValue(permissionValue);
            SystemMenu systemMenu1Add = systemMenuRepository.save(systemMenu);
            SystemPermission systemPermission = new SystemPermission();
            systemPermission.setPermissionName(menuDto.getMenuName());
            systemPermission.setDescription(menuDto.getDescription());
            systemPermission.setMenuId(systemMenu1Add.getId());
            systemPermission.setPermissionValue(permissionValue);
            systemPermissionRepository.save(systemPermission);
        }
        return ResponseData.success();
    }
}
