package com.zhiyu.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wengzhiyu
 * @date 2020/11/2
 */
@Component
public class ResponseUtil {

    public static void setResponse(ServletResponse response, int code, String message, Object data) throws IOException {
        // 在response请求头中设置 Access-Control-Expose-Headers
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        ServletOutputStream out = httpServletResponse.getOutputStream();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", message);
        jsonObject.put("data", data);
        out.write(jsonObject.toJSONString().getBytes());
        out.flush();
        out.close();
    }
}
