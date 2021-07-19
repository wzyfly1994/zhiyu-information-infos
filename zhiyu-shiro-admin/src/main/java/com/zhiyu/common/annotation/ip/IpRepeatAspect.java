package com.zhiyu.common.annotation.ip;


import com.zhiyu.utils.IpUtil;
import com.zhiyu.utils.RedisUtil;
import com.zhiyu.utils.ResponseData;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @author wengzhiyu
 * @date 2020/6/3
 */
@Aspect
@Configuration
public class IpRepeatAspect {
    @Autowired
    private RedisUtil redisUtil;

    @Around("@annotation(com.zhiyu.common.annotation.ip.IpRepeat)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        if (servletRequestAttributes == null) {
            return proceedingJoinPoint.proceed();
        }
        HttpServletRequest request = servletRequestAttributes.getRequest();
        // 获取出方法上的AntiRepeat注解
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        IpRepeat annotation = method.getAnnotation(IpRepeat.class);
        String ip = IpUtil.getIpAddr(request);
        if (annotation != null && StringUtils.isNotBlank(ip)) {
            String key = "com:zhiyu:admin:" + ip;
            if (redisUtil.hasKey(key)) {
                return ResponseData.error(annotation.msg());
            }
            redisUtil.set(key, ip, annotation.time());
        }
        return proceedingJoinPoint.proceed();
    }
}
