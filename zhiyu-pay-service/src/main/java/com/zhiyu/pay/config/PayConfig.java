package com.zhiyu.pay.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wengzhiyu
 * @date 2020/11/19
 */
@Data
@Component
@ConfigurationProperties(prefix = "pay.config")
public class PayConfig {

    private String signType;

    private String privateKey;

    private String publicKey;
}
