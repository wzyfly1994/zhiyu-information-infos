package com.zhiyu.common.shiro.filter;

import com.zhiyu.utils.FilterUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @author wengzhiyu
 * @date 2020/01/09
 */
public class RoleAutoFilter extends AuthorizationFilter {

    /**
     * 校验权限
     * 返回为false才会执行onAccessDenied
     * 判断是否拥有权限 触发realm
     * subject.isPermitted(peri)
     * 是否拥有角色,触发realm
     * subject.hasRole("abc");
     *
     * @param servletRequest
     * @param servletResponse
     * @param o
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) {
        Set<String> permSet = CollectionUtils.asSet((String[]) o);
        return !CollectionUtils.isEmpty(permSet) && SecurityUtils.getSubject().isPermitted(permSet.iterator().next());
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        FilterUtil.errorResponse(response);
        return false;
    }


}
