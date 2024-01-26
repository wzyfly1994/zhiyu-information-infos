package com.zhiyu.security.config;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import result.ResponseData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        // 处理认证失败的情况，可以自定义返回的HTTP状态码和错误信息
        // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed: " + authException.getMessage());
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
        response.getWriter().print(JSON.toJSONString(ResponseData.error("未授权")));
    }
}