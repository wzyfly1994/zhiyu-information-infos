package com.zhiyu.entity.dto;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author wengzhiyu
 * @date 2020/5/29
 */
public class JwtTokenDto implements AuthenticationToken {
    private static final long serialVersionUID = -7638855084653225753L;

    private String token;

    public JwtTokenDto(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
