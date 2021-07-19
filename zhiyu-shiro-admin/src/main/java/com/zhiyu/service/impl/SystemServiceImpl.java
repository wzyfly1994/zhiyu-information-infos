package com.zhiyu.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhiyu.common.exception.BusinessException;
import com.zhiyu.common.shiro.credentials.CustomCredentialsMatcher;
import com.zhiyu.config.constant.BCErrorCode;
import com.zhiyu.config.constant.Constants;
import com.zhiyu.entity.dto.SystemUserAddDto;
import com.zhiyu.entity.dto.SystemUserLoginDto;
import com.zhiyu.entity.dto.SystemUserUpdateDto;
import com.zhiyu.entity.pojo.system.SystemRole;
import com.zhiyu.entity.pojo.system.SystemUser;
import com.zhiyu.entity.pojo.system.SystemUserRole;
import com.zhiyu.entity.vo.SystemUserVo;
import com.zhiyu.repository.SystemRoleRepository;
import com.zhiyu.repository.SystemUserRepository;
import com.zhiyu.repository.SystemUserRoleRepository;
import com.zhiyu.service.SystemService;
import com.zhiyu.utils.JwtUtil;
import com.zhiyu.utils.RedisUtil;
import com.zhiyu.utils.ResponseData;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author wengzhiyu
 * @date 2020/01/08
 */
@Service
public class SystemServiceImpl implements SystemService {


    @Resource
    private SystemUserRepository systemUserRepository;
    @Resource
    private SystemRoleRepository systemRoleRepository;
    @Resource
    private SystemUserRoleRepository systemUserRoleRepository;
    @Resource
    private RedisUtil redisUtil;


    @Override
    public ResponseData userLogin(SystemUserLoginDto systemUserLoginDto) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(systemUserLoginDto.getAccount(),
                    CustomCredentialsMatcher.encrypt(systemUserLoginDto.getPassword(), systemUserLoginDto.getAccount()));
            //运行这个才会执行CustomRealm
            subject.login(token);
            //生成tokens
            Map<String, Object> objectMap = new HashMap<>(2);
            objectMap.put("account", systemUserLoginDto.getAccount());
            objectMap.put("timestamp", System.currentTimeMillis());
            String jwtToken = JwtUtil.getToken(objectMap);
            //session会话
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute(Constants.LOGIN_TOKEN + systemUserLoginDto.getAccount(), jwtToken);
            SystemUserVo vo = new SystemUserVo();
            vo.setToken(jwtToken);
            return ResponseData.success(vo);
        } catch (Exception e) {
            return ResponseData.error("登录失败：" + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData addUser(SystemUserAddDto systemUserAddDto) {
        if (systemUserAddDto == null) {
            return ResponseData.error(BCErrorCode.DATA_NOT_NULL.getMsg());
        }
        String account = systemUserAddDto.getAccount();
        String phone = systemUserAddDto.getPhone();
        if (StringUtils.isBlank(phone)) {
            return ResponseData.error("手机号不能为空");
        }
        Optional<SystemUser> optionalSystemUser = systemUserRepository.findByAccountOrPhone(account, phone);
        if (optionalSystemUser.isPresent()) {
            return ResponseData.error("账号或手机号已存在");
        }
        String password = systemUserAddDto.getPassword();
        SystemUser systemUser = new SystemUser();
        BeanUtils.copyProperties(systemUserAddDto, systemUser);
        systemUser.setPassWord(CustomCredentialsMatcher.encrypt(password, account));
        systemUserRepository.save(systemUser);
        updateUserRole(systemUser.getId(), systemUserAddDto.getRoleList());
        return ResponseData.success("用户新增成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseData updateUser(SystemUserUpdateDto systemUserUpdateDto) {
        if (systemUserUpdateDto == null) {
            return ResponseData.error(BCErrorCode.DATA_NOT_NULL.getMsg());
        }
        Long userId = systemUserUpdateDto.getId();
        if (userId == null) {
            return ResponseData.error("请选择要修改的人员");
        }
        Optional<SystemUser> systemUserOptional = systemUserRepository.findById(userId);
        if (!systemUserOptional.isPresent()) {
            return ResponseData.error("不存在此用户");
        }
        SystemUser systemUser = systemUserOptional.get();
        List<SystemUser> systemUserList = systemUserRepository.findAllByIdNot(userId);
        int phoneExit = (int) systemUserList.stream().filter(e -> e.getPhone().equals(systemUserUpdateDto.getPhone())).count();
        if (phoneExit > 0) {
            return ResponseData.error("已存此手机号");
        }
        int emailExit = (int) systemUserList.stream().filter(e -> e.getEmail().equals(systemUserUpdateDto.getEmail())).count();
        if (emailExit > 0) {
            return ResponseData.error("已存此邮箱");
        }
        BeanUtils.copyProperties(systemUserUpdateDto, systemUser);
        updateUserRole(userId, systemUserUpdateDto.getRoleList());
        return ResponseData.success("用户修改成功");
    }

    @Override
    public void userSaveRedis(SystemUser systemUser) {
        if (systemUser == null) {
            throw new BusinessException("用户存入缓存时，数据为空");
        }
        String account = systemUser.getAccount();
        String userString = JSON.toJSONString(systemUser);
        redisUtil.set(Constants.LOGIN_USER + account, userString, TimeUnit.HOURS.toSeconds(3));
    }


    private void updateUserRole(Long userId, List<Long> roleIdList) {
        Optional<SystemUser> systemUserOptional = systemUserRepository.findById(userId);
        if (!systemUserOptional.isPresent()) {
            throw new BusinessException("不存在此用户");
        }
        List<SystemRole> roleList = systemRoleRepository.findAllByIdIn(roleIdList);
        if (CollectionUtils.isEmpty(roleList)) {
            throw new BusinessException("至少有一条角色不存在");
        }
        List<SystemUserRole> userRoleDelList = systemUserRoleRepository.findAllByUserId(userId);
        if (CollectionUtils.isNotEmpty(userRoleDelList)) {
            systemUserRoleRepository.deleteInBatch(userRoleDelList);
        }
        List<SystemUserRole> userRoleAddList = new ArrayList<>(10);
        for (Long roleId : roleIdList) {
            SystemUserRole systemUserRole = new SystemUserRole();
            systemUserRole.setRoleId(roleId);
            systemUserRole.setUserId(userId);
            userRoleAddList.add(systemUserRole);
        }
        systemUserRoleRepository.saveAll(userRoleAddList);
    }

}
