package com.zhiyu.common.config.elasticsearch;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author wengzhiyu
 * @since 2021/12/31 10:55
 */
@Component
@Data
public class DemoConfig {

    @Value("${user}")
    private String userId;

}
