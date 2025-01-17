package com.zhiyu.security.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
import com.zhiyu.security.entity.form.user.UserQueryForm;
import com.zhiyu.security.entity.pojo.User;
import com.zhiyu.security.entity.vo.UserQueryVo;
import com.zhiyu.security.enums.LoginCodeEnum;
import com.zhiyu.security.mapper.UserMapper;
import com.zhiyu.security.provider.TokenProvider;
import com.zhiyu.security.service.UserService;
import com.zhiyu.security.utils.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author Jason
 * @since 2024-12-17
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    private final RedisUtils redisUtils;

    private final LoginProperties loginProperties;

    private final SecurityProperties properties;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final TokenProvider tokenProvider;


    @Value("${rsa.private_key}")
    private String privateKey;


    @Override
    public OnlineUserDto getOnlineUserByKey(String key) {
        return (OnlineUserDto) redisUtils.get(key);
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
            // 此处会调用 com.zhiyu.security.config.CustomAuthenticationProvider.authenticate
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = tokenProvider.createToken(authentication);

            final JwtUserDto jwtUserDto = (JwtUserDto) authentication.getPrincipal();
            // 返回 token 与 用户信息
            Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
                put("token", properties.getTokenStartWith() + token);
                put("userInfo", jwtUserDto);
            }};
            if (loginProperties.isSingleLogin()) {
                // 踢掉之前已经登录的token
                kickOutForUsername(account);
            }
            // 保存在线信息
            save(jwtUserDto, token, request);
            return ResponseData.success(authInfo);
        } catch (Exception e) {
            log.error("登录异常: - {}", e.getMessage());
            return ResponseData.error(e.getMessage());
        }
    }

    @Async
    public void kickOutForUsername(String username) {
        String loginKey = properties.getOnlineKey() + username + "*";
        redisUtils.scanDel(loginKey);
    }

    public void save(JwtUserDto jwtUserDto, String token, HttpServletRequest request) {
        String ip = CustomUtils.getIp(request);
        String browser = CustomUtils.getBrowser(request);
        String address = CustomUtils.getCityInfo(ip);
        OnlineUserDto onlineUserDto = null;
        try {
            onlineUserDto = new OnlineUserDto(jwtUserDto.getUsername(),
                    jwtUserDto.getUser().getUserName(), browser, ip, address, EncryptUtils.desEncrypt(token), new Date());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        String loginKey = tokenProvider.loginKey(token);
        redisUtils.set(loginKey, onlineUserDto, properties.getTokenValidityInSeconds(), TimeUnit.MILLISECONDS);
    }

    @Override
    public ResponseData register(RegisterUserDto registerUserDto) {
        // 校验账户
        String account = registerUserDto.getAccount();
        User existUser = super.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getAccount, account), false);
        if (Objects.nonNull(existUser)) {
            throw new BusinessException("账号已存在");
        }
        User user = CloneUtils.shallowClone(registerUserDto, User.class);
        // 使用自定义密码加密
        // user.setPassWord(PasswordUtils.hashPassword(User.getPassWord()));
        // 使用security加密
        user.setPassWord(SpringBeanUtil.getBean(PasswordEncoder.class).encode(user.getPassWord()));
        super.save(user);
        return ResponseData.success("注册成功");
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
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return ResponseData.success(imgResult);
    }

    @Override
    public void logout(String token) {
        String loginKey = tokenProvider.loginKey(token);
        redisUtils.del(loginKey);
    }

    @Override
    public ResponseData getUserList(UserQueryForm userQueryForm) {
        int pageIndex = userQueryForm.getPageIndex();
        int pageSize = userQueryForm.getPageSize();

        Page<User> page = new Page<>(pageIndex, pageSize);

        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(userQueryForm.getUserName()), User::getUserName,
                userQueryForm.getUserName());
        queryWrapper.like(StringUtils.isNotBlank(userQueryForm.getAccount()), User::getAccount,
                userQueryForm.getAccount());

        Page<User> pageInfo = super.page(page, queryWrapper);
        List<UserQueryVo> vos = pageInfo.getRecords().stream().map(UserQueryVo::build).collect(Collectors.toList());

        Page<UserQueryVo> userQueryVoPage = new Page<>(pageIndex, pageSize);
        userQueryVoPage.setRecords(vos);
        userQueryVoPage.setTotal(pageInfo.getTotal());

        return ResponseData.success(userQueryVoPage);
    }

    @Override
    public ResponseData getUserInfo() {
        UserDetails currentUser = SecurityUtils.getCurrentUser();
        return ResponseData.success(currentUser);
    }

    @Override
    public ResponseData getOnlineUser(String username, Pageable pageable) {
        List<OnlineUserDto> onlineUserDtos = getAllOnlineUser(username);
        PageResult<OnlineUserDto> pages = PageUtil.toPage(
                PageUtil.paging(pageable.getPageNumber(), pageable.getPageSize(), onlineUserDtos),
                onlineUserDtos.size()
        );
        return ResponseData.success(pages);
    }

    public List<OnlineUserDto> getAllOnlineUser(String username){
        String loginKey = properties.getOnlineKey() +
                (StringUtils.isBlank(username) ? "" : "*" + username);
        List<String> keys = redisUtils.scan(loginKey + "*");
        Collections.reverse(keys);
        List<OnlineUserDto> onlineUserDtos = new ArrayList<>();
        for (String key : keys) {
            onlineUserDtos.add((OnlineUserDto) redisUtils.get(key));
        }
        onlineUserDtos.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
        return onlineUserDtos;
    }
}
