package com.zhiyu.security.service.impl;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.captcha.base.Captcha;
import com.zhiyu.core.exception.BusinessException;
import com.zhiyu.core.result.ResponseData;
import com.zhiyu.core.utils.CloneUtils;
import com.zhiyu.core.utils.PasswordUtils;
import com.zhiyu.core.utils.RedisUtils;
import com.zhiyu.core.utils.RsaUtils;
import com.zhiyu.security.config.properties.LoginProperties;
import com.zhiyu.security.config.properties.SecurityProperties;
import com.zhiyu.security.entity.dto.user.AuthUserDto;
import com.zhiyu.security.entity.dto.user.RegisterUserDto;
import com.zhiyu.security.entity.pojo.SystemUser;
import com.zhiyu.security.enums.LoginCodeEnum;
import com.zhiyu.security.mapper.SystemUserMapper;
import com.zhiyu.security.service.SystemUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

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
@Slf4j
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {

    private final RedisUtils redisUtils;

    private final LoginProperties loginProperties;

    private final SecurityProperties properties;

    @Value("${rsa.private_key}")
    private String privateKey;


    @Override
    public SystemUser getUserByKey(String key) {
        return (SystemUser) redisUtils.get(key);
    }

    @Override
    public ResponseData login(AuthUserDto authUserDto) {
        String password = authUserDto.getPassword();
        try {
            RsaUtils.decryptByPrivateKey(password, privateKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return ResponseData.success();
    }

    @Override
    public ResponseData register(RegisterUserDto registerUserDto) {
        // 校验账户
        String account = registerUserDto.getAccount();
        SystemUser existUser = super.getOne(new LambdaQueryWrapper<SystemUser>()
                .eq(SystemUser::getAccount, account), false);
        if (Objects.nonNull(existUser)) {
            throw new BusinessException("账号已存在");
        }
        SystemUser systemUser = CloneUtils.shallowClone(registerUserDto, SystemUser.class);
        systemUser.setPassWord(PasswordUtils.hashPassword(systemUser.getPassWord()));
        super.save(systemUser);
        return ResponseData.success();
    }

    @Override
    public ResponseData getCode() {
        // 获取运算的结果
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = properties.getCodeKey() + IdUtil.simpleUUID();
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == LoginCodeEnum.ARITHMETIC.ordinal() && captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }
        // 保存
        redisUtils.set(uuid, captchaValue, loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        Object o = redisUtils.get(uuid);
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return ResponseData.success(imgResult);
    }
}
