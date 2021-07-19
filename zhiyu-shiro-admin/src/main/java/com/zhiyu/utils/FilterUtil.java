package com.zhiyu.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wengzhiyu
 * @date 2020/7/23
 */
@Slf4j
public class FilterUtil {

    /**
     * 未授权的跳转
     */
    public static void errorResponse(ServletResponse response) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.sendRedirect("/shiro-admin/system/loginError");
        } catch (IOException e) {
            log.error("未授权的跳转失败:", e);
        }
    }
}
