package com.zhiyu.common.pattern.chain.flowchain;

import com.zhiyu.common.pattern.chain.flowchain.service.LoadMessageRule;
import com.zhiyu.common.pattern.chain.flowchain.service.SendMessageRule;
import lombok.Data;

/**
 * 加载上下文参数
 *
 * @author wengzhiyu
 * @since 2021/5/24 15:29
 */
@Data
public class FlowContext implements LoadMessageRule, SendMessageRule {
    private String message;


}
