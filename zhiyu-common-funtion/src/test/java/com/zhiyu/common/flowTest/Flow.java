package com.zhiyu.common.flowTest;


import com.zhiyu.common.flowTest.context.FlowContext;
import com.zhiyu.common.flowTest.rule.RuleHandler;

/**
 * @author wengzhiyu
 * @since 2021/9/14 15:35
 */
public interface Flow<T extends RuleHandler> {


    boolean valid(T context);

    boolean execute(T context);


    /**
     * 重载 execute() 泛型支持
     *
     * @param context
     * @return
     */
    @SuppressWarnings("unchecked")
    default boolean execute(FlowContext context) {
        return execute((T) context);
    }

    /**
     * 重载 isEnable() 泛型支持
     *
     * @param context
     * @return
     */
    @SuppressWarnings("unchecked")
    default boolean valid(FlowContext context) {
        return valid((T) context);
    }


}
