package com.zhiyu.common.pattern.chain.flowchain.flow;

import com.zhiyu.common.pattern.chain.flowchain.FlowContext;

/**
 * 流程节点
 *
 * @author wengzhiyu
 * @since 2021/5/24 17:24
 */
public interface Flow<T extends FlowChainRule> {


    /**
     * execute()之前调用
     *
     * @param context
     * @return true 继续调用
     */
    default boolean isEnable(T context) {
        return true;
    }


    /**
     * 执行上下文
     *
     * @param context
     * @return
     */
    boolean execute(T context);

    /**
     * 重载 execute() 泛型支持
     *
     * @param context
     * @return
     */
    @SuppressWarnings("unchecked")
    default boolean execute(FlowContext context) {
        return execute((T)context);
    }

    /**
     * 重载 isEnable() 泛型支持
     *
     * @param context
     * @return
     */
    @SuppressWarnings("unchecked")
    default boolean isEnable(FlowContext context) {
        return isEnable((T)context);
    }

}
