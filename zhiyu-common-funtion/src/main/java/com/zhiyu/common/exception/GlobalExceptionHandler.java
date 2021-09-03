package com.zhiyu.common.exception;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.zhiyu.common.utils.response.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 全局异常捕获处理
 *
 * @author wengzhiyu
 * @date 2020/04/23 16:43
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler implements ApplicationListener<ApplicationEvent> {

    @Value("${spring.profiles.active}")
    String profilesActive;

    /**
     * 参数验证异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Object handler(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.error("异常", exception);
        return ResponseData.error("参数验证异常 :" + message);
    }

    /**
     * 参数格式异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Object handler(HttpMessageNotReadableException exception) {
        log.error("异常", exception);
        return ResponseData.error("参数格式错误");
    }

    /**
     * 数字转换异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = NumberFormatException.class)
    public Object handler(NumberFormatException exception) {
        log.error("异常", exception);
        return ResponseData.error("数字转换异常");
    }

    /**
     * 请求方式异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Object handler(HttpRequestMethodNotSupportedException exception) {
        log.error("异常", exception);
        return ResponseData.error("请求方式错误");
    }

    /**
     * 媒体类型异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public Object handler(HttpMediaTypeNotSupportedException exception) {
        log.error("异常", exception);
        return ResponseData.error("媒体类型错误");
    }

    /**
     * JSON解析异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = JSONException.class)
    public Object handler(JSONException exception) {
        log.error("异常", exception);
        return ResponseData.error("JSON解析异常");
    }

    /**
     * 业务异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public Object handler(BusinessException exception) {
        log.error("异常", exception);
        return ResponseData.error(exception.getCode(), exception.getError(), exception.getMessage());
    }

    /**
     * 数据库异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public Object handler(HttpServletRequest request, DataIntegrityViolationException exception) {
        log.error("异常", exception);
        String message = exception.getMessage();
        if (StringUtils.isNotBlank(message)) {
            String info="Data too long";
            if (message.contains(info)) {
                return ResponseData.error("数据太长");
            }
            String infos="doesn't have a default value";
            if (message.contains(infos)) {
                return ResponseData.error("数据库缺少默认参数");
            }
        }
        sendMessage(request, exception);
        return ResponseData.error("数据库异常");
    }

    @ExceptionHandler(value = NullPointerException.class)
    public Object handler(HttpServletRequest request, NullPointerException exception) {
        sendMessage(request, exception);
        log.error("异常", exception);
        return ResponseData.error("空指针异常");
    }

    /**
     * 未知异常
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Object handler(HttpServletRequest request, Exception exception) {
        log.error("异常", exception);
        sendMessage(request, exception);
        return ResponseData.error("系统繁忙,请稍后重试");
    }

    private void sendMessage(HttpServletRequest request, Exception exception) {
        logError(request, exception);
    }

    private String logError(HttpServletRequest request, Exception exception) {
        log.error("发生异常:", exception);
        StringWriter sw = new StringWriter();
        sw.append(String.format("url:%s;\n", request.getRequestURI()));
        sw.append(String.format("参数:%s;\n", JSONObject.toJSONString(request.getParameterMap())));
        sw.append("-----------------------------------------------------\n");
        exception.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }


    @Override
    public void onApplicationEvent(ApplicationEvent event) {
//        ContextClosedEvent：应用关闭事件
//        ContextRefreshedEvent：应用刷新事件
//        ContextStartedEvent：应用开启事件
//        ContextStoppedEvent：应用停止事件
        //如果是容器刷新事件
//        if (event instanceof ContextClosedEvent) {
//            System.out.println(event.getClass().getSimpleName() + " 事件已发生！");
//        } else if (event instanceof ContextRefreshedEvent) {
//            System.out.println(event.getClass().getSimpleName() + " 事件已发生！");
//        } else if (event instanceof ContextStartedEvent) {
//            System.out.println(event.getClass().getSimpleName() + " 事件已发生！");
//        } else if (event instanceof ContextStoppedEvent) {
//            System.out.println(event.getClass().getSimpleName() + " 事件已发生！");
//        } else {
//            System.out.println("有其它事件发生:" + event.getClass().getName());
//        }
    }
}