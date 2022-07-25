package com.zhiyu.common.config;


import com.zhiyu.common.config.rest.RestTemplateProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Data
@Slf4j
@Configuration
public class ApplicationConfig {


    /**
     * 通用的restTemplate
     */
    private RestTemplateProperties restTemplateProperties = new RestTemplateProperties();


    @PostConstruct
    private void init() {
        if (log.isDebugEnabled()) {
            log.debug("工程配置 ApplicationConfig [{}]", this);
        }
        log.info("ApplicationConfig inited");
    }
}
