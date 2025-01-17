package com.zhiyu.security.handler;

import com.zhiyu.core.exception.GlobalExceptionHandler;
import com.zhiyu.core.result.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.AccessDeniedException;

@Slf4j
@RestControllerAdvice
@Order(1) // 指定执行顺序，优先级高于父类
public class GlobalExceptionExtHandler extends GlobalExceptionHandler {


    @ExceptionHandler(AccessDeniedException.class)
    public Object handler(HttpServletRequest request,  AccessDeniedException exception) {
        log.error("异常", exception);
        sendMessage(request, exception);
        return ResponseData.error("无权限访问，请联系系统管理员！");
    }


}
