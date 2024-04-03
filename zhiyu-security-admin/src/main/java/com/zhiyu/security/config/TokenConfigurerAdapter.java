package com.zhiyu.security.config;

import com.zhiyu.security.config.properties.SecurityProperties;
import com.zhiyu.security.filter.TokenFilter;
import com.zhiyu.security.manager.UserCacheManager;
import com.zhiyu.security.provider.TokenProvider;
import com.zhiyu.security.service.SystemUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class TokenConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final SecurityProperties securityProperties;
    private final TokenProvider tokenProvider;
    private final SystemUserService systemUserService;
    private final UserCacheManager userCacheManager;

    @Override
    public void configure(HttpSecurity http) {
        TokenFilter customFilter = new TokenFilter(tokenProvider, securityProperties, systemUserService, userCacheManager);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
