package com.zhiyu.common.pattern.chain.flowchain.service;

import com.zhiyu.common.pattern.chain.flowchain.flow.FlowChainRule;

/**
 * @author wengzhiyu
 * @since 2021/5/24 18:04
 */
public interface LoadMessageRule extends FlowChainRule {

    /**
     * 加载消息
     *
     * @param message
     */
    void setMessage(String message);

}
