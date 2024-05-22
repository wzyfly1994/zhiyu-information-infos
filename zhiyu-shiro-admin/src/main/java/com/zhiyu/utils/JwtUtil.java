package com.zhiyu.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhiyu.common.exception.BusinessException;
import com.zhiyu.config.constant.Constants;
import com.zhiyu.entity.pojo.system.SystemUser;
import com.zhiyu.entity.vo.CommonDataVo;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

import static cn.hutool.crypto.asymmetric.KeyType.SecretKey;


/**
 * JWT工具
 *
 * @author wzy
 * @date 2019/11/1
 */
@Slf4j
@Component
public class JwtUtil {
    private static RedisUtil redisUtil;

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        JwtUtil.redisUtil = redisUtil;
    }

    private static Date getExpireTime() {
        return new Date(System.currentTimeMillis() + 7200000L);
    }

    public static String getToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims)
                //签发时间
                .setIssuedAt(new Date())
                //过期时间
                .setExpiration(getExpireTime())
                .signWith(SignatureAlgorithm.HS512, Constants.JWT_SECRET_KEY)
                .compact();
    }

    public static JSONObject validateToken(String token) {
        token = token.replace(Constants.TOKEN_PREFIX, "");
        JSONObject jsonObject = new JSONObject();
        boolean code = true;
        String msg = "";
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(Constants.JWT_SECRET_KEY.getBytes())).build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            // token已过期
            code = false;
            msg = "token已过期，请重新登录";
        } catch (UnsupportedJwtException e) {
            // token不支持
            code = false;
            msg = "token信息不能被解析，请重新登录";
        } catch (MalformedJwtException e) {
            // token格式不对
            code = false;
            msg = "token格式错误，请重新登录";
        } catch (SignatureException e) {
            // token签名不对
            code = false;
            msg = "token签名错误，请重新登录";
        } catch (IllegalArgumentException e) {
            // token格式转换错误
            code = false;
            msg = "token为空，请重新登录";
        }
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        return jsonObject;
    }

    public static Claims getClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().verifyWith(Keys.hmacShaKeyFor(Constants.JWT_SECRET_KEY.getBytes())).build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            // token错误
            log.info("token解析失败:原因:[{}]", e.getMessage());
            throw new BusinessException("token解析失败");
        }
        return claims;
    }

    /**
     * 全局获取用户基本信息
     *
     * @return
     */
    public static CommonDataVo getCommonData() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            throw new BusinessException("请求参数获取失败");
        }
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        String token = httpServletRequest.getHeader(Constants.TOKEN_HEADER);
        JSONObject result = validateToken(token);
        boolean isValid = result.getBooleanValue("code");
        String msg = result.getString("msg");
        if (!isValid) {
            throw new BusinessException(msg);
        }
        token = token.replace(Constants.TOKEN_PREFIX, "");
        //解析传过来的token
        Claims claims = getClaims(token);
        String account = claims.get("account", String.class);
        SystemUser systemUser = JSON.parseObject(String.valueOf(redisUtil.get(Constants.LOGIN_USER + account)), SystemUser.class);
        CommonDataVo commonDataVo = new CommonDataVo();
        commonDataVo.setAccount(account);
        commonDataVo.setDeptId(systemUser.getDeptId());
        commonDataVo.setUserName(systemUser.getUserName());
        commonDataVo.setId(systemUser.getId());
        return commonDataVo;
    }


    public static Long getUserId() {
        return getCommonData().getId();
    }

    public static String getAccount() {
        return getCommonData().getAccount();
    }

    public static Long getUserDepId() {
        return getCommonData().getDeptId();
    }

    public static String getUserName() {
        return getCommonData().getUserName();
    }

}
