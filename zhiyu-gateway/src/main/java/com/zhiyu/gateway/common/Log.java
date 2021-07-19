package com.zhiyu.gateway.common;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wengzhiyu
 * @date 2021/01/30 10:01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * IP
     */
    private String ip;

    /**
     * path
     */
    private String path;

    /**
     * 请求报文
     */
    private JSONObject requestMessage;

    /**
     * 响应报文
     */
    private JSONObject responseMessage;

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 请求时间时间
     */
    private String requestTime;

    /**
     * 响应时间时间
     */
    private String responseTime;

    /**
     * 处理时间毫秒
     */
    private Long handleTime;
}
