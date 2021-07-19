package com.zhiyu.pay.utils;


import com.zhiyu.pay.config.constants.BCErrorCode;

import java.io.IOException;

/**
 * @author wzy
 */
public class ResponseData {

    private Object data;
    private String msg;
    private Integer code;

    public Boolean isSuccess() {
        return this.code.equals(BCErrorCode.SUCCESS.getCode());
    }

    public static ResponseData success() {
        ResponseData res = new ResponseData();
        res.setCode(BCErrorCode.SUCCESS.getCode());
        res.setMsg(BCErrorCode.SUCCESS.getMsg());
        return res;
    }

    public static ResponseData success(Object data) {
        ResponseData res = new ResponseData();
        res.setCode(BCErrorCode.SUCCESS.getCode());
        res.setMsg(BCErrorCode.SUCCESS.getMsg());
        res.setData(data);
        return res;
    }

    public static ResponseData error(IOException e) {
        ResponseData res = new ResponseData();
        res.setCode(BCErrorCode.ERROR.getCode());
        res.setMsg(BCErrorCode.ERROR.getMsg());
        return res;
    }

    public static ResponseData error(String msg) {
        ResponseData res = new ResponseData();
        res.setCode(BCErrorCode.ERROR.getCode());
        res.setMsg(msg);
        return res;
    }

    public static ResponseData error(String msg, Object data) {
        ResponseData res = new ResponseData();
        res.setCode(BCErrorCode.ERROR.getCode());
        res.setMsg(msg);
        res.setData(data);
        return res;
    }

    public static ResponseData error(Integer code, String msg, Object data) {
        ResponseData res = new ResponseData();
        res.setCode(code);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    private void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    private void setCode(Integer code) {
        this.code = code;
    }
}
