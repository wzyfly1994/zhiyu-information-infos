package com.zhiyu.pay.common.annotation.test;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author wengzhiyu
 * @date 2021/3/11
 */
@Component
@Aspect
@Slf4j
public class TestAop {


    /**
     * execution（）
     * 表达式的主体；
     * 第一个”*“符号   表示返回值的类型任意
     * com.zhiyu.pay.controller AOP所切的服务的包名，即，我们的业务部分
     * 包名后面的”..“ 表示当前包及子包
     * 第二个”*“ 表示类名，*即所有类。此处可以自定义，下文有举例
     * .*(..) 表示任何方法名，括号表示参数，两个点表示任何参数类型
     */
    @Pointcut("execution(* com.zhiyu.pay.common.annotation.test.TestAopService.aopValue(..))")
    public void point() {

    }

    @Before("point()")
    public void aopBefore(JoinPoint joinPoint) {
        log.info("-----execute---aopBefore()------------入参"+ Arrays.toString(joinPoint.getArgs()));
    }


    @Around("point()")
    public Object aopAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object object = proceedingJoinPoint.proceed();
        log.info("-----execute---aopAround()------------返回值" + object);
        return object;
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
