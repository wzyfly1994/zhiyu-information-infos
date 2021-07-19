package com.zhiyu.config.constant;

/**
 * @author wengzhiyu
 * @date 2020/01/07
 */
public enum BCErrorCode {
    /**
     * 异常状态
     */
    SUCCESS("成功", 0),
    ERROR("失败", -1),
    DATA_NOT_NULL("请求参数不能为空", -1),
    TOKEN_ACCOUNT_NULL("用户凭证不存在", -1),
    TOKEN_ACCOUNT_DIFF("用户凭证不匹配", -1),
    USER_TOKEN_NULL("用户凭证为空", -1),
    SEESION_TOKEN_ACCOUNT_NULL("用户缓存凭证不存在", -1),
    LOGIN_TIMEOUT_ERROR("登录超时", 401),
    PARAMS_VALIDATE_ERROR("用户未授权", 403),
    PARAMS_VALIDATE_ONTHER("当前账号在另一地点登录,请重新登录", 405),
    ;


    private String msg;
    private Integer code;

    BCErrorCode(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public Integer getCode() {
        return code;
    }
}
