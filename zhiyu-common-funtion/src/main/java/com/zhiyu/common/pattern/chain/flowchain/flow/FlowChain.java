package com.zhiyu.common.pattern.chain.flowchain.flow;

import com.zhiyu.common.pattern.chain.flowchain.FlowContext;

/**
 * 流程链
 *
 * @author wengzhiyu
 * @since 2021/5/24 21:13
 */
public interface FlowChain<T> {


    /**
     * 初始化 上下文
     *
     * @param flowContext
     * @return
     */
    FlowChain<T> initContext(FlowContext flowContext);


    /**
     * 添加链条
     *
     * @param flows
     * @return
     */
    FlowChain<T> addFlow(Flow<?>... flows);

    /**
     * 删除指定流程节点
     *
     * @param flow
     *            流程节点
     * @return 是否删除成功
     */
    boolean removeFlow(Flow<?> flow);


    /**
     * 启动整个链式流程
     *
     * @param callback(success)
     *            回调函数
     * @return 链式流程返回结果
     */
    T start(Callback<T> callback);

    /**
     * 启动整个链式流程
     *
     * @param callback(success,
     *            context)
     *            回调函数
     * @return 链式流程返回结果
     */
    T start(Callback4Context<T> callback);

    /**
     * 根据顺序位置获取Flow, 为遍历提供支持
     *
     * @param index
     *            流程节点位置下标
     * @return 流程节点
     */
    Flow<?> getFlow(int index);

    /**
     * 获取流程的数量, 为遍历提供支持
     *
     * @return 流程数量
     */
    int size();


    @FunctionalInterface
    interface Callback<T> {
        /**
         * 回调函数
         *
         * @param success 流程调用链最终返回 true or false
         * @return
         */
        T apply(boolean success);
    }

    @FunctionalInterface
    interface Callback4Context<T> {
        /**
         * 回调函数
         *
         * @param success 流程调用链最终返回 true or false
         * @param context 流程调用链的上下文
         * @return
         */
        T apply(boolean success, FlowContext context);
    }
}
