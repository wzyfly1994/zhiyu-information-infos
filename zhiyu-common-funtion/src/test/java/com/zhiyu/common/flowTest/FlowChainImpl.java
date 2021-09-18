package com.zhiyu.common.flowTest;

import com.google.common.collect.Lists;
import com.zhiyu.common.flowTest.callback.Callback;
import com.zhiyu.common.flowTest.context.FlowContext;
import com.zhiyu.common.flowTest.context.RepContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wengzhiyu
 * @since 2021/9/13 17:37
 */
@Service
public class FlowChainImpl<T> implements FlowChain<T> {

    private List<Flow<?>> flowList = new ArrayList<>(16);

    private FlowContext flowContext;


    @Override
    public FlowChain<T> initContext(FlowContext flowContext) {
        this.flowContext = flowContext;
        return this;
    }

    @Override
    public T start(Callback<T> callback) {
        boolean result = true;
        if (!CollectionUtils.isEmpty(flowList)) {
            for (Flow<?> flow : flowList) {
                result = flow.valid(flowContext);
                if (result) {
                    result = flow.execute(flowContext);
                }
            }
        }
        RepContext repContext=new RepContext();
        repContext.setFinishReason("1111");
        repContext.setSuccess(result);
        return callback.apply(repContext);
    }

    @Override
    public FlowChain<T> addFlow(Flow<?>... flow) {
        flowList.addAll(Lists.newArrayList(flow));
        return this;
    }
}
