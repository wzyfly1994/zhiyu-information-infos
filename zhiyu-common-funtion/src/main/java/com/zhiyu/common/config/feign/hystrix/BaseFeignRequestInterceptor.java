package com.zhiyu.common.config.feign.hystrix;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
/**
 * @author wengzhiyu
 * @since 2021/10/9 15:55
 */
@Slf4j
public class BaseFeignRequestInterceptor implements RequestInterceptor {

    private static final String PREFIX_X_HEADER = "x-";

    /**
     * 以 X-/x- 开头的请求头，大小写不敏感
     *
     * (non-Javadoc)
     *
     * @see feign.RequestInterceptor#apply(feign.RequestTemplate)
     */
    @Override
    public void apply(RequestTemplate template) {

        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }

        HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        // @formatter:off
        Optional.ofNullable(request.getHeaderNames())
                .map(headerNames -> {
                    if (log.isDebugEnabled()) {
                        log.debug("X-/x- 开头的请求头将会传递给下游服务，大小写不敏感");
                    }
                    while (headerNames.hasMoreElements()) {
                        String headerName = headerNames.nextElement();
                        log.info("headerName---------------->{}", headerName);
                        // X-/x- 开头的请求头传递给下游，大小写不敏感
                        if (headerName.toLowerCase().startsWith(PREFIX_X_HEADER) ||
                                headerName.equalsIgnoreCase("Accept-Language")) {
                            Enumeration<String> headers = request.getHeaders(headerName);
                            List<String> headerValues = new ArrayList<>();
                            while (headers.hasMoreElements()) {
                                String headerValue = headers.nextElement();
                                headerValues.add(headerValue);
                                if (log.isDebugEnabled()) {
                                    log.debug("headerName: {}, headerValue: {}", headerName, headerValue);
                                }
                            }
                            template.header(headerName, headerValues);
                        }
                    }
                    return null;
                });
        // @formatter:on
    }
}