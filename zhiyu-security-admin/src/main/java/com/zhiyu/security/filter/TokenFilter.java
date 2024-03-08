package com.zhiyu.security.filter;

import com.zhiyu.security.config.properties.SecurityProperties;
import com.zhiyu.security.manager.UserCacheManager;
import com.zhiyu.security.provider.TokenProvider;
import com.zhiyu.security.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

import io.jsonwebtoken.ExpiredJwtException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author /
 */
public class TokenFilter extends GenericFilterBean {
    private static final Logger log = LoggerFactory.getLogger(TokenFilter.class);


    private final TokenProvider tokenProvider;
    private final SecurityProperties properties;
    private final UserService userService;
    private final UserCacheManager userCacheManager;

    /**
     * @param tokenProvider    Token
     * @param properties       JWT
     * @param userService      用户
     * @param userCacheManager 用户缓存工具
     */
    public TokenFilter(TokenProvider tokenProvider, SecurityProperties properties, UserService userService, UserCacheManager userCacheManager) {
        this.properties = properties;
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.userCacheManager = userCacheManager;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        String token = resolveToken(httpServletRequest);
//        // 对于 Token 为空的不需要去查 Redis
//        if (StringUtils.isNotBlank(token)) {
//            OnlineUserDto onlineUserDto = null;
//            boolean cleanUserCache = false;
//            try {
//                String loginKey = tokenProvider.loginKey(token);
//                onlineUserDto = onlineUserService.getOne(loginKey);
//            } catch (ExpiredJwtException e) {
//                log.error(e.getMessage());
//                cleanUserCache = true;
//            } finally {
//                if (cleanUserCache || Objects.isNull(onlineUserDto)) {
//                    userCacheManager.cleanUserCache(String.valueOf(tokenProvider.getClaims(token).get(TokenProvider.AUTHORITIES_KEY)));
//                }
//            }
//            if (onlineUserDto != null && StringUtils.hasText(token)) {
//                Authentication authentication = tokenProvider.getAuthentication(token);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//                // Token 续期
//                tokenProvider.checkRenewal(token);
//            }
//        }
//        filterChain.doFilter(servletRequest, servletResponse);

    }

    /**
     * 初步检测Token
     *
     * @param request /
     * @return /
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(properties.getHeader());
        if (StringUtils.isNotBlank(bearerToken) && bearerToken.startsWith(properties.getTokenStartWith())) {
            // 去掉令牌前缀
            return bearerToken.replace(properties.getTokenStartWith(), "");
        } else {
            log.debug("非法Token：{}", bearerToken);
        }
        return null;
    }

    public static void main(String[] args) {
   

    }
}
