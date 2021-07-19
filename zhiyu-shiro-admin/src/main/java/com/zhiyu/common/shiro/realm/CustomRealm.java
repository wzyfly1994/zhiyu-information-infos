package com.zhiyu.common.shiro.realm;

import com.zhiyu.config.constant.Constants;
import com.zhiyu.entity.pojo.system.*;
import com.zhiyu.repository.*;
import com.zhiyu.service.SystemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.data.domain.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author wengzhiyu
 * @date 20120/01/12
 */
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Resource
    private SystemUserRepository systemUserRepository;

    @Resource
    private SystemUserRoleRepository systemUserRoleRepository;

    @Resource
    private SystemRoleRepository systemRoleRepository;

    @Resource
    private SystemRolePermissionRepository systemRolePermissionRepository;

    @Resource
    private SystemPermissionRepository systemPermissionRepository;

    @Resource
    private SystemService systemService;

    public CustomRealm(CredentialsMatcher credentialsMatcher) {
        super(credentialsMatcher);
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("==========CustomRealm=================doGetAuthorizationInfo=======================");
        //获取登录用户名
        String account = (String) principalCollection.getPrimaryPrincipal();
        List<String> roleList = new ArrayList<>();
        List<String> permissionList = new ArrayList<>();
        SystemUser systemUser = new SystemUser();
        systemUser.setAccount(account);
        //获取拥有的角色
        List<Long> userList = new ArrayList<>();
        //根据用户名去数据库查询用户信息
        Optional<SystemUser> userOptional = systemUserRepository.findOne(Example.of(systemUser));
        userList.add(userOptional.map(SystemUser::getId).orElse(-1L));
        List<SystemUserRole> userRoleList = systemUserRoleRepository.findAllByUserIdIn(userList);
        if (!CollectionUtils.isEmpty(userRoleList)) {
            List<Long> listRoleId = userRoleList.stream().map(SystemUserRole::getRoleId).distinct().collect(Collectors.toList());
            roleList.addAll(systemRoleRepository.findAllByIdIn(listRoleId).stream().map(SystemRole::getRoleValue).collect(Collectors.toList()));
            List<Long> userPermission = systemRolePermissionRepository.findAllByRoleIdIn(listRoleId).stream().map(SystemRolePermission::getPermissionId).collect(Collectors.toList());
            permissionList.addAll(systemPermissionRepository.findAllByIdIn(userPermission).stream().map(SystemPermission::getPermissionValue).collect(Collectors.toList()));
        }
        log.info("当前角色拥有的角色列表：[{}]", roleList);
        log.info("当前角色拥有的权限列表：[{}]", permissionList);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(roleList);
        simpleAuthorizationInfo.addStringPermissions(permissionList);
        return simpleAuthorizationInfo;
    }

    /**
     * subject.login 会调用此方法
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("==========CustomRealm=================cationInfo=======================");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        SystemUser systemUser = new SystemUser();
        systemUser.setAccount(token.getUsername());
        systemUser.setIsDelete(Boolean.FALSE);
        Optional<SystemUser> userOptional = systemUserRepository.findOne(Example.of(systemUser));
        SystemUser user = userOptional.orElse(null);
        if (user == null) {
            throw new UnknownAccountException("用户不存在");
        }
        if (user.getIsLocked()) {
            throw new LockedAccountException("用户已被锁定");
        }
        if (!user.getIsActivated()) {
            throw new UnknownAccountException("用户未启用");
        }
        String account = user.getAccount();
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute(Constants.LOGIN_USER + account, user);
        //缓存入redis
        userOptional.ifPresent(userInfo -> systemService.userSaveRedis(userInfo));
        //ByteSource.Util.bytes(account) 为加盐值
        return new SimpleAuthenticationInfo(account, user.getPassWord(), ByteSource.Util.bytes(account), getName());
    }

}
