package com.zhiyu.gateway.common;

import lombok.Data;

/**
 * 业务异常
 *
 * @author wengzhiyu
 * @date 2020/06/05 11:34
 */
@Data
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 2311099737347491924L;
    /**
     * 错误码
     */
    private int code;

    /**
     * 提示信息
     */
    private String msg;

    public BusinessException(String msg) {
        super(msg);
        this.code = Constant.ERROR_CODE;
        this.msg = msg;
    }

    public BusinessException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
