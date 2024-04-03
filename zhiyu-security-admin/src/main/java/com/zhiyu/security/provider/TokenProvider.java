package com.zhiyu.security.provider;

import cn.hutool.core.date.DateField;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.core.date.DateUtil;
import com.zhiyu.core.utils.RedisUtils;
import com.zhiyu.security.config.properties.SecurityProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class TokenProvider implements InitializingBean {
    private final SecurityProperties properties;
    private final RedisUtils redisUtils;
    public static final String AUTHORITIES_KEY = "user";
    private JwtParser jwtParser;
    private JwtBuilder jwtBuilder;
    /**
     * 加密算法
     */
    private final static SecureDigestAlgorithm<SecretKey, SecretKey> ALGORITHM = Jwts.SIG.HS256;

    public TokenProvider(SecurityProperties properties, RedisUtils redisUtils) {
        this.properties = properties;
        this.redisUtils = redisUtils;
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(properties.getBase64Secret());
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);
        jwtParser = Jwts.parser().decryptWith(key).build();
        jwtBuilder = Jwts.builder().signWith(key, ALGORITHM);
    }

    /**
     * 创建Token 设置永不过期，
     * Token 的时间有效性转到Redis 维护
     *
     * @param authentication /
     * @return /
     */
    public String createToken(Authentication authentication) {
        return jwtBuilder
                // 加入ID确保生成的 Token 都不一致
                .id(IdUtil.simpleUUID())
                .claim(AUTHORITIES_KEY, authentication.getName())
                .subject(authentication.getName())
                .compact();
    }

    /**
     * 依据Token 获取鉴权信息
     *
     * @param token /
     * @return /
     */
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        User principal = new User(claims.getSubject(), "******", new ArrayList<>());
        return new UsernamePasswordAuthenticationToken(principal, token, new ArrayList<>());
    }

    public Claims getClaims(String token) {
        return jwtParser.parseSignedClaims(token).getPayload();
    }

    /**
     * @param token 需要检查的token
     */
    public void checkRenewal(String token) {
        // 判断是否续期token,计算token的过期时间
        String loginKey = loginKey(token);
        long time = redisUtils.getExpire(loginKey) * 1000;
        Date expireDate = DateUtil.offset(new Date(), DateField.MILLISECOND, (int) time);
        // 判断当前时间与过期时间的时间差
        long differ = expireDate.getTime() - System.currentTimeMillis();
        // 如果在续期检查的范围内，则续期
        if (differ <= properties.getDetect()) {
            long renew = time + properties.getRenew();
            redisUtils.expire(loginKey, renew, TimeUnit.MILLISECONDS);
        }
    }

    public String getToken(HttpServletRequest request) {
        final String requestHeader = request.getHeader(properties.getHeader());
        if (requestHeader != null && requestHeader.startsWith(properties.getTokenStartWith())) {
            return requestHeader.substring(7);
        }
        return null;
    }

    /**
     * 获取登录用户RedisKey
     *
     * @param token /
     * @return key
     */
    public String loginKey(String token) {
        Claims claims = getClaims(token);
        String md5Token = DigestUtil.md5Hex(token);
        return properties.getOnlineKey() + claims.getSubject() + "-" + md5Token;
    }
}
