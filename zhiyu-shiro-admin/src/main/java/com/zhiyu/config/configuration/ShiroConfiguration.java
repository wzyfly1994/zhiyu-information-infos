package com.zhiyu.config.configuration;

import com.zhiyu.common.shiro.credentials.CustomCredentialsMatcher;
import com.zhiyu.common.shiro.filter.JwtFilter;
import com.zhiyu.common.shiro.filter.KickOutFilter;
import com.zhiyu.common.shiro.filter.ResultAdviceFilter;
import com.zhiyu.common.shiro.filter.RoleAutoFilter;
import com.zhiyu.common.shiro.realm.CustomRealm;
import com.zhiyu.service.SystemPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.ServletContainerSessionManager;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wengzhiyu
 * @date 2020/01/08
 */
@Configuration
@Slf4j
public class ShiroConfiguration {
    @Resource
    private SystemPermissionService systemPermissionService;


    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator dapc = new DefaultAdvisorAutoProxyCreator();
        dapc.setProxyTargetClass(true);
        return dapc;
    }


    @Bean("customCredentialsMatcher")
    public CustomCredentialsMatcher customCredentialsMatcher() {
        return new CustomCredentialsMatcher();
    }

    /**
     * 将自己的验证方式加入容器
     *
     * @return CustomRealm
     */
    @Bean
    public CustomRealm myShiroRealm(CustomCredentialsMatcher customCredentialsMatcher) {
        return new CustomRealm(customCredentialsMatcher);
    }


    /**
     * 配置保存sessionId的cookie
     * 注意：这里的cookie 不是上面的记住我 cookie 记住我需要一个cookie session管理 也需要自己的cookie
     *
     * @return simpleCookie
     */
    @Bean
    public SimpleCookie sessionIdCookie() {
        //是cookie的名称
        SimpleCookie simpleCookie = new SimpleCookie("sid");
        simpleCookie.setHttpOnly(true);
        //maxAge=-1表示浏览器关闭时失效此Cookie
        simpleCookie.setMaxAge(-1);
        return simpleCookie;
    }

    @Bean
    public SessionManager sessionManager() {
        return new ServletContainerSessionManager();
    }

    @Bean
    public ExecutorServiceSessionValidationScheduler sessionValidationScheduler() {
        ExecutorServiceSessionValidationScheduler scheduler = new ExecutorServiceSessionValidationScheduler();
        scheduler.setInterval(7200);
        return scheduler;
    }

    /**
     * 权限管理，配置主要是Realm的管理认证
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager(CustomCredentialsMatcher customCredentialsMatcher) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm(customCredentialsMatcher));
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }


    /**
     * Filter工厂，设置对应的过滤条件和跳转条件
     * <p>
     * //拦截器
     * Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
     * // 配置拦截的链接 顺序判断  anon表示不需要拦截
     * filterChainDefinitionMap.put("/user/loginError", "anon");
     * filterChainDefinitionMap.put("/swagger/**", "anon");
     * filterChainDefinitionMap.put("/v2/api-docs/**", "anon");
     * filterChainDefinitionMap.put("/swagger-ui.html", "anon");
     * filterChainDefinitionMap.put("/swagger-resources/**", "anon");
     * filterChainDefinitionMap.put("/webjars/**", "anon");
     * filterChainDefinitionMap.put("/user/login", "anon");
     * filterChainDefinitionMap.put("/user/signIn", "anon");
     * filterChainDefinitionMap.put("/info", "anon");
     * //使用自定义过滤器拦截除上边以外的所有请求
     * // 谁在前就先执行谁   jwtFilter先执行  只有jwtFilter 中的isAccessAllowed方法返回为true 才会执行roleFilter中的方法
     * filterChainDefinitionMap.put("/role/welcome", "jwt,role[abc],authc");
     * filterChainDefinitionMap.put("/**", "jwt,authc");
     * shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager, KickOutFilter kickOutFilter) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 未授权的页面,如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/system/loginError");
        shiroFilterFactoryBean.setLoginUrl("/system/loginError");
        //添加自己的过滤器
        Map<String, Filter> filterMap = new HashMap<>(4);
        filterMap.put("jwt", new JwtFilter());
        filterMap.put("kickout", kickOutFilter);
        filterMap.put("role", new RoleAutoFilter());
        filterMap.put("result", new ResultAdviceFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        shiroFilterFactoryBean.setFilterChainDefinitions(systemPermissionService.intiFilterChain());
        log.info("加载shiro过滤链；[{}]", systemPermissionService.intiFilterChain());
        return shiroFilterFactoryBean;
    }


    /**
     * 加入注解的使用，不加入这个注解不生效
     *
     * @param securityManager
     * @return authorizationAttributeSourceAdvisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 初始化并发控制拦截器
     *
     * @param redissonClient
     * @return
     */
    @Bean
    public KickOutFilter kickOutFilterInit(RedissonClient redissonClient) {
        KickOutFilter kickOutFilter = new KickOutFilter();
        kickOutFilter.setRedissonClient(redissonClient);
        return kickOutFilter;
    }


}
