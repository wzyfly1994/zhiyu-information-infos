package com.zhiyu.common.flowTest;

import com.zhiyu.common.flowTest.callback.Callback;
import com.zhiyu.common.flowTest.context.FlowContext;

/**
 * @author wengzhiyu
 * @since 2021/9/13 17:35
 */
public interface FlowChain<T> {

    FlowChain<T> initContext(FlowContext flowContext);


    T start(Callback<T> callback);


    FlowChain<T> addFlow(Flow<?> ...flow);

}
