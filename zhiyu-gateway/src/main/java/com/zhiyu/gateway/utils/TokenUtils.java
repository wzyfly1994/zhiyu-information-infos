package com.zhiyu.gateway.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhiyu.gateway.common.BusinessException;
import com.zhiyu.gateway.common.Constant;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;



/**
 * Token工具
 *
 * @author 向振华
 * @date 2021/01/15 15:52
 */
@Slf4j
@Component
public class TokenUtils {

    public static String jwtSigningKey;

    @Value("${jwt.signing.key}")
    public void setJwtSigning(String jwtSigningKey) {
        TokenUtils.jwtSigningKey = jwtSigningKey;
    }

    /**
     * 验证并获取
     *
     * @param exchange
     * @return
     */
    public static JSONObject verifyGet(ServerWebExchange exchange) {
        String authorization = exchange.getRequest().getHeaders().getFirst(Constant.AUTHORIZATION);
        if (authorization == null) {
            throw new BusinessException(Constant.NOT_LOGIN, "未登录");
        }
        if (!StringUtils.startsWith(authorization, Constant.BEARER)) {
            throw new BusinessException(Constant.NOT_LOGIN, "Authorization格式错误");
        }
        String token = authorization.substring(Constant.BEARER.length());
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtSigningKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new BusinessException(Constant.NOT_LOGIN, "token过期");
        } catch (UnsupportedJwtException e) {
            throw new BusinessException(Constant.NOT_LOGIN, "不支持的token");
        } catch (MalformedJwtException e) {
            throw new BusinessException(Constant.NOT_LOGIN, "token格式错误");
        } catch (SignatureException e) {
            throw new BusinessException(Constant.NOT_LOGIN, "token签名异常");
        } catch (IllegalArgumentException e) {
            throw new BusinessException(Constant.NOT_LOGIN, "token非法请求");
        } catch (Exception e) {
            log.error("解析异常：", e);
            throw new BusinessException(Constant.NOT_LOGIN, "token解析异常");
        }
        JSONObject json = JSON.parseObject(claims.getSubject());
        if (json == null) {
            throw new BusinessException(Constant.NOT_LOGIN, "token数据不存在");
        }
        return json;
    }
}