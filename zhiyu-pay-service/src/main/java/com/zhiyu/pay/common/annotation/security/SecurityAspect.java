package com.zhiyu.pay.common.annotation.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author wengzhiyu
 * @date 2020/11/19
 */
@Configuration
@Aspect
@Slf4j
public class SecurityAspect {

    /**
     * 切点
     */
    @Pointcut("@annotation(com.zhiyu.pay.common.annotation.security.Encryption)")
    public void encryptionPoint() {

    }


    /**
     * 入参解密
     *
     * @param joinPoint
     */
    @Before("encryptionPoint()")
    @SuppressWarnings("unchecked")
    public void encryptionBefore(JoinPoint joinPoint) {
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        if (servletRequestAttributes == null) {
//            return;
//        }
//        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        // PayDTO payDTO=JSON.parseObject(JSON.toJSONString(joinPoint.getArgs()),PayDTO.class);
//        JSONObject jsonObject=JSON.parseObject(JSON.toJSONString(joinPoint.getArgs()));
//        BeanUtils.copyProperties(joinPoint.getArgs(),payDTO);
        //JSONObject jsonObject=JSON.parseObject(JSON.toJSONString(joinPoint.getArgs()));
        if (joinPoint.getArgs().length ==0) {
            return;
        }
        JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(joinPoint.getArgs()));
        Map<String,Object> reqMap = JSON.parseObject(jsonArray.get(0).toString(), Map.class);
        log.info("请求开始, 参数:{}", reqMap);
    }


    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("encryptionPoint()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result = proceedingJoinPoint.proceed();
        log.info("请求结束，controller的返回值是:{} ", JSON.toJSONString(result));
        return result;
    }

}
