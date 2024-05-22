package com.zhiyu.security.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wf.captcha.base.Captcha;
import com.zhiyu.core.exception.BusinessException;
import com.zhiyu.core.result.ResponseData;
import com.zhiyu.core.utils.*;
import com.zhiyu.security.config.properties.LoginProperties;
import com.zhiyu.security.config.properties.SecurityProperties;
import com.zhiyu.security.entity.dto.user.AuthUserDto;
import com.zhiyu.security.entity.dto.user.JwtUserDto;
import com.zhiyu.security.entity.dto.user.OnlineUserDto;
import com.zhiyu.security.entity.dto.user.RegisterUserDto;
import com.zhiyu.security.entity.pojo.SystemUser;
import com.zhiyu.security.enums.LoginCodeEnum;
import com.zhiyu.security.mapper.SystemUserMapper;
import com.zhiyu.security.provider.TokenProvider;
import com.zhiyu.security.service.SystemUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
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
//@RequiredArgsConstructor(onConstructor_ = {@Lazy})
@RequiredArgsConstructor
@Slf4j
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {

    private final RedisUtils redisUtils;

    private final LoginProperties loginProperties;

    private final SecurityProperties properties;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final TokenProvider tokenProvider;


    @Value("${rsa.private_key}")
    private String privateKey;


    @Override
    public SystemUser getUserByKey(String key) {
        return (SystemUser) redisUtils.get(key);
    }

    @Override
    public ResponseData login(AuthUserDto authUserDto) {
        try {
            HttpServletRequest request = ((ServletRequestAttributes)
                    Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

            String account = authUserDto.getAccount();

            // 查询验证码
//            String uuid = authUserDto.getUuid();
//            String code = (String) redisUtils.get(uuid);
//            // 清除验证码
//            redisUtils.del(uuid);
//            if (StringUtils.isBlank(code)) {
//                return ResponseData.error("验证码不存在或已过期");
//            }
//            if (StringUtils.isBlank(authUserDto.getCode()) || !authUserDto.getCode().equalsIgnoreCase(code)) {
//                return ResponseData.error("验证码错误");
//            }


            String password = authUserDto.getPassword();
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(authUserDto.getAccount(), password);
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = tokenProvider.createToken(authentication);

            final JwtUserDto jwtUserDto = (JwtUserDto) authentication.getPrincipal();
            // 返回 token 与 用户信息
            Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
                put("token", properties.getTokenStartWith() + token);
                put("user", jwtUserDto);
            }};
            if (loginProperties.isSingleLogin()) {
                // 踢掉之前已经登录的token
                kickOutForUsername(account);
            }
            // 保存在线信息
            save(jwtUserDto, token, request);
            return ResponseData.success(authInfo);
        } catch (Exception e) {
            return ResponseData.error(e.getMessage());
        }
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
        // 使用自定义密码加密
        // systemUser.setPassWord(PasswordUtils.hashPassword(systemUser.getPassWord()));
        // 使用security加密
        systemUser.setPassWord(SpringBeanUtil.getBean(PasswordEncoder.class).encode(systemUser.getPassWord()));
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

    @Async
    public void kickOutForUsername(String username) {
        String loginKey = properties.getOnlineKey() + username + "*";
        redisUtils.scanDel(loginKey);
    }

    public void save(JwtUserDto jwtUserDto, String token, HttpServletRequest request) {
        String dept = jwtUserDto.getUser().getDept().getName();
        String ip = CustomUtils.getIp(request);
        String browser = CustomUtils.getBrowser(request);
        String address = CustomUtils.getCityInfo(ip);
        OnlineUserDto onlineUserDto = null;
        try {
            onlineUserDto = new OnlineUserDto(jwtUserDto.getUsername(),
                    jwtUserDto.getUser().getNickName(), dept, browser, ip, address, EncryptUtils.desEncrypt(token), new Date());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        String loginKey = tokenProvider.loginKey(token);
        redisUtils.set(loginKey, onlineUserDto, properties.getTokenValidityInSeconds(), TimeUnit.MILLISECONDS);
    }
}
