package com.zhiyu.security.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhiyu.core.exception.BusinessException;
import com.zhiyu.core.utils.RsaUtils;
import com.zhiyu.security.entity.pojo.User;
import com.zhiyu.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {


    private final UserService userService;

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;


    @Value("${rsa.private_key}")
    private String privateKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.getOne(new LambdaQueryWrapper<User>()
                .eq(User::getAccount, username), false);
        if (Objects.isNull(user)) {
            throw new BusinessException("用户不存在");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String pwdSource;
        try {
            pwdSource = RsaUtils.decryptByPrivateKey(privateKey, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 使用自定义密码解密
        // boolean matches = PasswordUtils.verifyPassword(pwdSource, systemUser.getPassWord());
        // 使用security加密
        boolean matches = passwordEncoder.matches(pwdSource, user.getPassWord());

        if (matches) {
            // 如果认证成功，返回一个经过认证的Authentication对象
            return new UsernamePasswordAuthenticationToken(userDetails, password);
        } else {
            // 如果认证失败，抛出AuthenticationException异常
            throw new BusinessException("Authentication failed.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // 这里指定支持的Authentication类型，通常是UsernamePasswordAuthenticationToken的子类
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
