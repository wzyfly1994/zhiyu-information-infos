package com.zhiyu.common.flowTest.callback;

import com.zhiyu.common.flowTest.context.RepContext;

/**
 * @author wengzhiyu
 * @since 2021/9/13 17:31
 */
@FunctionalInterface
public interface Callback<T> {

    T apply(RepContext repContext);

}
