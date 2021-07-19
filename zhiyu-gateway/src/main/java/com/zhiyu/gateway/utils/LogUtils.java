package com.zhiyu.gateway.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhiyu.gateway.common.Constant;
import com.zhiyu.gateway.common.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

/**
 * @author 向振华
 * @date 2021/01/30 11:51
 */
@Slf4j
public class LogUtils {

    /**
     * 发送
     *
     * @param exchange
     * @param responseMessage
     */
    public static void send(ServerWebExchange exchange, String responseMessage) {
        long requestTime = getRequestTime(exchange);
        long responseTime = System.currentTimeMillis();
        long handleTime = responseTime - requestTime;
        Log l = Log.builder()
                .userId(getUserId(exchange))
                .ip(IpUtils.getIp(exchange))
                .path(exchange.getRequest().getURI().getPath())
                .requestMessage(getRequestBody(exchange))
                .responseMessage(JSON.parseObject(responseMessage))
                .code(getCode(responseMessage))
                .requestTime(new SimpleDateFormat(Constant.DATE_TIME).format(requestTime))
                .responseTime(new SimpleDateFormat(Constant.DATE_TIME).format(responseTime))
                .handleTime(handleTime)
                .build();
        log.info("网关请求日志---> " + JSONObject.toJSONString(l));
    }

    /**
     * 获取状态码
     *
     * @param responseData
     * @return
     */
    private static Integer getCode(String responseData) {
        if (StringUtils.isBlank(responseData)) {
            return Constant.CODE_GATEWAY_NO_CODE;
        }
        try {
            JSONObject data = JSONObject.parseObject(responseData);
            if (data != null && data.containsKey(Constant.CODE)) {
                return (Integer) data.get(Constant.CODE);
            }
        } catch (Exception ignored) {
        }
        return Constant.CODE_GATEWAY_NO_CODE;
    }

    /**
     * 获取用户ID
     *
     * @param exchange
     * @return
     */
    private static Long getUserId(ServerWebExchange exchange) {
        try {
            JSONObject jsonObject = TokenUtils.verifyGet(exchange);
            return Long.parseLong(jsonObject.get("userId").toString());
        } catch (Exception e) {
            return 0L;
        }
    }

    /**
     * 获取请求体
     *
     * @param exchange
     * @return
     */
    private static JSONObject getRequestBody(ServerWebExchange exchange) {
        Object requestBodyCache = exchange.getAttribute(Constant.REQUEST_BODY_CACHE);
        if (requestBodyCache == null) {
            return null;
        }
        byte[] body = (byte[]) requestBodyCache;
        String requestBody = new String(body, StandardCharsets.UTF_8);
        if (StringUtils.isBlank(requestBody)) {
            return null;
        }
        return JSON.parseObject(requestBody);
    }

    /**
     * 获取请求时间
     *
     * @param exchange
     * @return
     */
    private static Long getRequestTime(ServerWebExchange exchange) {
        Object requestTimeCache = exchange.getAttribute(Constant.REQUEST_TIME_CACHE);
        if (requestTimeCache == null) {
            return System.currentTimeMillis();
        }
        return (Long) requestTimeCache;
    }
}
