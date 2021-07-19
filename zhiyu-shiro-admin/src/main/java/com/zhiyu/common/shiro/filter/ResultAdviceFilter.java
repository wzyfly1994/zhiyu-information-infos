package com.zhiyu.common.shiro.filter;

import com.zhiyu.config.constant.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.servlet.AdviceFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * 返回结果处理拦截器
 * @author wengzhiyu
 * @date 2020/01/13
 */
public class ResultAdviceFilter extends AdviceFilter {

    @Override
    public boolean preHandle(ServletRequest request, ServletResponse response) {
        Session session = SecurityUtils.getSubject().getSession();
        String token = String.valueOf(session.getAttribute(Constants.LOGIN_TOKEN));
        // 在response请求头中设置 token
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        httpServletResponse.setHeader(Constants.TOKEN,token);
        return true;
    }

    @Override
    public void postHandle(ServletRequest request, ServletResponse response) {

    }

    @Override
    public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) {
    }

}