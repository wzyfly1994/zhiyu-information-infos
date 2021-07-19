package com.zhiyu.common.pattern.chain.flowchain.flow;

import com.baomidou.mybatisplus.extension.api.R;
import com.google.common.collect.Lists;
import com.zhiyu.common.pattern.chain.flowchain.FlowContext;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wengzhiyu
 * @since 2021/5/24 21:34
 */
@Slf4j
public class FunctionFlowChain<T> implements FlowChain<T> {

    /**
     * 流程链容器，用于各个流程节点的存储
     */
    private final List<Flow<?>> flowChain = new ArrayList<>();

    /**
     * 走整个流程链的上下文, 上下文需要实现各种流程的规则
     */
    private FlowContext context;


    @Override
    public FlowChain<T> initContext(FlowContext flowContext) {
        this.context = flowContext;
        return this;
    }

    @Override
    public T start(Callback4Context<T> callback) {
        return start(success -> callback.apply(success, context));
    }

    @Override
    public T start(Callback<T> callback) {
        int pos = 0;
        Flow<?> flow = next(pos++);
        boolean result = true;
        long startTime = System.currentTimeMillis();

        // 报错则视为失败
        try {
            while (flow != null && result) {

                startTime = System.currentTimeMillis();

                if (result && flow.isEnable(context)) {

                    result = flow.execute(context);

                    if (!result) {
                        log.warn("[流程控制] => flow:[{}], context:[{}], 流程终止, 耗时:{}ms", flow.getClass().getSimpleName(),
                                context.getClass().getSimpleName(), System.currentTimeMillis() - startTime);
                    } else {
                        log.info("[流程控制] => flow:[{}], context:[{}], 耗时:{}ms", flow.getClass().getSimpleName(),
                                context.getClass().getSimpleName(), System.currentTimeMillis() - startTime);
                    }
                }
                flow = next(pos++);
            }
        } catch (ClassCastException e) {
            result = false;
            log.error("[流程控制] => flow:[{}], context:[{} 未实现 {} 规则], 执行异常, 耗时:{}ms", flow.getClass().getSimpleName(),
                    context.getClass().getSimpleName(), e.getMessage().substring(e.getMessage().lastIndexOf(".") + 1),
                    System.currentTimeMillis() - startTime, e);
        } catch (Exception e) {
            result = false;
            log.error("[流程控制] => flow:[{}], context:[{}], 执行异常, 耗时:{}ms", flow.getClass().getSimpleName(),
                    context.getClass().getSimpleName(), System.currentTimeMillis() - startTime, e);
        }

        return callback.apply(result);
    }

    @Override
    public FlowChain<T> addFlow(Flow<?>... flows) {
        flowChain.addAll(Lists.newArrayList(flows));
        return this;
    }

    @Override
    public boolean removeFlow(Flow<?> flow) {
        return flowChain.remove(flow);
    }

    @Override
    public Flow<?> getFlow(int index) {
        return flowChain.get(index);
    }

    @Override
    public int size() {
        return flowChain.size();
    }

    /**
     * 获取下一个流程节点
     *
     * @param pos
     *            下一个流程节点位置下标
     * @return 流程
     */
    private Flow<?> next(int pos) {
        if (pos < size()) {
            return getFlow(pos);
        }
        return null;
    }
}
