package com.zhiyu.security.config;

import com.zhiyu.security.config.properties.SecurityProperties;
import com.zhiyu.security.manager.UserCacheManager;
import com.zhiyu.security.provider.TokenProvider;
import com.zhiyu.security.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.security.PermitAll;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author wengzhiyu
 * @since 2021/12/16 11:17
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationEntryPoint authenticationErrorHandler;

    private final ApplicationContext applicationContext;

    private final SecurityProperties securityProperties;

    private final TokenProvider tokenProvider;

    private final UserService userService;

    private final UserCacheManager userCacheManager;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 用于配置认证管理器，指定用户存储的方式和密码加密方式。
     *
     * @param auth
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        log.info("AuthenticationManagerBuilder------------");
    }

    /**
     * 用于配置如何通过拦截器保护请求，哪些请求需要认证，哪些请求不需要认证等。
     *
     * @param http
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.cors() // 跨域
                .and()
                .csrf().disable() // 禁用 CSRF
                .exceptionHandling()
                .authenticationEntryPoint(authenticationErrorHandler) // 授权异常处理
                .and()
                .authorizeRequests()
                .antMatchers("/admin/login").permitAll()
                .antMatchers(getPermitAllUrlsFromAnnotations().toArray(new String[0])).permitAll()
                // 放行OPTIONS请求
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new TokenConfigurerAdapter(securityProperties, tokenProvider, userService, userCacheManager));
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return source;
    }


    private List<String> getPermitAllUrlsFromAnnotations() {
        List<String> permitAllUrlList = new ArrayList<>();
        RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping)
                applicationContext.getBean("requestMappingHandlerMapping");
        Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = requestMappingHandlerMapping.getHandlerMethods();
        // 获得有 @PermitAll 注解的接口
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethodMap.entrySet()) {
            HandlerMethod handlerMethod = entry.getValue();
            if (!handlerMethod.hasMethodAnnotation(PermitAll.class)) {
                continue;
            }
            Set<String> urls = entry.getKey().getPatternsCondition().getPatterns();
            permitAllUrlList.addAll(urls);
        }
        return permitAllUrlList;
    }
}
