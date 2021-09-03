package com.zhiyu.common.exception;


import com.zhiyu.common.utils.response.BCErrorCode;

/**
 * 自定义异常类
 *
 * @author wengzhiyu
 * @date 2020/04/23 16:43
 */
public class BusinessException extends RuntimeException {


    private static final long serialVersionUID = -4040684854010235554L;

    @Override
    public String toString() {
        return "BusinessException{" +
                "code=" + code +
                ", error='" + error + '\'' +
                '}';
    }

    private Integer code;
    private String error;

    public String getError() {
        return error;
    }

    public Integer getCode() {
        return code;
    }

    public BusinessException(BCErrorCode bcErrorCode) {
        super(bcErrorCode.getMsg());
        this.code = bcErrorCode.getCode();
        this.error = bcErrorCode.getMsg();
    }

    public BusinessException(String msg) {
        super(msg);
        this.code = BCErrorCode.ERROR.getCode();
        this.error = msg;
    }

    public BusinessException(String msg, Integer code) {
        super(msg);
        this.code = code;
        this.error = msg;
    }


}
