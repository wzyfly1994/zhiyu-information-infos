
package com.zhiyu.common.shiro.credentials;

import com.zhiyu.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Sha384Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * shiro加密方法
 *
 * @author wengzhiyu
 * @date 20120/01/12
 */
@Slf4j
public class CustomCredentialsMatcher extends SimpleCredentialsMatcher {

    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authToken, AuthenticationInfo info) {
        log.info("==========CustomCredentialsMatcher=================doCredentialsMatch=======================");
        UsernamePasswordToken token = (UsernamePasswordToken) authToken;
        String account = (String) authToken.getPrincipal();
        Object tokenCredentials = String.valueOf(token.getPassword());
        Object accountCredentials = getCredentials(info);
        String redisKey = "userErrorLogin:" + account;
        Object loginErrorCount = redisUtil.get(redisKey);
        AtomicInteger errorLoginCount = new AtomicInteger(loginErrorCount == null ? 0 : (int) loginErrorCount);
        int number = 8;
        if (errorLoginCount.get() > number) {
            throw new LockedAccountException("账号已被锁定,请半小时再试");
        }
        if (!equals(tokenCredentials, accountCredentials)) {
            AtomicInteger errCount = new AtomicInteger(1);
            if (errorLoginCount.get() != 0) {
                errCount = errorLoginCount;
                errCount.incrementAndGet();
            }
            redisUtil.set(redisKey, errCount.get(), TimeUnit.MINUTES.toSeconds(30));
            throw new UnknownAccountException("账号或密码错误");
        }
        return true;
    }


    public static String encrypt(String password, String account) {
        return new Sha384Hash(password, ByteSource.Util.bytes(account), 1000).toBase64();
    }
}