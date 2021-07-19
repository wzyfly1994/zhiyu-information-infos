package com.zhiyu.service.impl;

import com.zhiyu.common.exception.BusinessException;
import com.zhiyu.entity.pojo.system.SystemFilterChain;
import com.zhiyu.repository.SystemFilterChainRepository;
import com.zhiyu.service.SystemPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wengzhiyu
 * @date 2020/7/27
 */
@Service
@Slf4j
public class SystemPermissionServiceImpl implements SystemPermissionService {
    @Resource
    private SystemFilterChainRepository systemFilterChainRepository;
    @Autowired
    @Lazy
    private ShiroFilterFactoryBean shiroFilterFactoryBean;

    @Override
    public String intiFilterChain() {
        Map<String, String> filterChainMap = initFilterChainMap();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : filterChainMap.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("\r\n");
        }
        return sb.toString();
    }

    @Override
    public Map<String, String> initFilterChainMap() {
        try {
            List<SystemFilterChain> shiroFilterChains = systemFilterChainRepository.findAll();
            Map<String, String> shiroFilterChainMap = new LinkedHashMap<>(shiroFilterChains.size());
            for (SystemFilterChain shiroFilterChain : shiroFilterChains) {
                shiroFilterChainMap.put(shiroFilterChain.getKey(), shiroFilterChain.getValue());
            }
            shiroFilterChainMap.put("/**", "authc");
            return shiroFilterChainMap;
        } catch (Exception e) {
            log.error("初始化shiro过滤链错误:", e);
            return new HashMap<>(0);
        }

    }

    @Override
    @SuppressWarnings("all")
    public Map<String, String> updateFilterChain() {
        AbstractShiroFilter abstractShiroFilter;
        try {
            abstractShiroFilter = (AbstractShiroFilter) shiroFilterFactoryBean.getObject();
            // 获取过滤管理器
            PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) abstractShiroFilter.getFilterChainResolver();
            DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
            // 清空初始权限配置
            manager.getFilterChains().clear();
            shiroFilterFactoryBean.getFilterChainDefinitionMap().clear();
            // 生产新的过滤链
            shiroFilterFactoryBean.setFilterChainDefinitionMap(initFilterChainMap());
            Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue().trim().replace(" ", "");
                manager.createChain(url, chainDefinition);
            }
            return shiroFilterFactoryBean.getFilterChainDefinitionMap();
        } catch (Exception e) {
            log.error("更新过滤链失败：",e);
            throw new BusinessException("更新过滤链失败：" + e.getMessage());
        }
    }
}
