package com.zhiyu.common.annotation.aop;

import com.zhiyu.common.annotation.ActionLog;
import com.zhiyu.common.entity.dto.log.ActionLogDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author: wengzhiyu
 * @create: 2022-09-05 21:44
 **/
@Configuration
@Aspect
@Slf4j
public class ActionLogAspect {

    /**
     * execution（）
     * 表达式的主体；
     * 第一个”*“符号   表示返回值的类型任意
     * com.zhiyu.pay.controller AOP所切的服务的包名，即，我们的业务部分
     * 包名后面的”..“ 表示当前包及子包
     * 第二个”*“ 表示类名，*即所有类。此处可以自定义，下文有举例
     * .*(..) 表示任何方法名，括号表示参数，两个点表示任何参数类型
     */
    @Pointcut("execution(* com.zhiyu.common.annotation.ActionLog(..))")
    public void point() {

    }

    @Before("point()")
    public void aopBefore(JoinPoint joinPoint) {
        log.info("-----execute---aopBefore()------------入参"+ Arrays.toString(joinPoint.getArgs()));

    }


    @Around("point()")
    public Object aopAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        if (servletRequestAttributes == null) {
            return proceedingJoinPoint.proceed();
        }
        HttpServletRequest request = servletRequestAttributes.getRequest();
        // 获取出方法上的AntiRepeat注解
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();
        ActionLog annotation = method.getAnnotation(ActionLog.class);
        Class<? extends ActionLogDto>[] actionLogDto = annotation.value();

        log.info("-----execute---aopAround()------------返回值" );
        return null;
    }


    @After("point()")
    public void aopAfter() {
        log.info("-----execute---aopAfter()------------");
    }


    @AfterReturning("point()")
    public void aopReturning() {
        log.info("-----execute---aopReturning()------------");
    }

    @AfterThrowing("point()")
    public void aopThrowing() {
        log.info("-----execute---aopThrowing()------------");
    }

}
