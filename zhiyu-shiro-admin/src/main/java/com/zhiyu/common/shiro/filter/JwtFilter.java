package com.zhiyu.common.shiro.filter;

import com.alibaba.fastjson.JSONObject;
import com.zhiyu.common.exception.BusinessException;
import com.zhiyu.config.constant.Constants;
import com.zhiyu.utils.FilterUtil;
import com.zhiyu.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户认证（可以理解为登陆）叫Authentication，用户授权叫Authorization
 *
 * @author wengzhiyu
 * @date 2020/5/28
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {

    /**
     * 如果带有 token，则对 token 进行检查，否则直接通过
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     * @throws UnauthorizedException
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
        boolean isToken = isLoginAttempt(request, response);
        if (isToken) {
            //验证token
            HttpServletRequest req = (HttpServletRequest) request;
            String token = req.getHeader(Constants.TOKEN_HEADER);
            JSONObject result = JwtUtil.validateToken(token);
            boolean isValid = result.getBooleanValue("code");
            String msg = result.getString("msg");
            if (!isValid) {
                throw new BusinessException(msg);
            }
            return true;
        } else {
            FilterUtil.errorResponse(response);
            return false;
        }
    }

    /**
     * 检测 header 里面是否包含 token 字段
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(Constants.TOKEN_HEADER);
        return token != null;
    }


    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }


}
