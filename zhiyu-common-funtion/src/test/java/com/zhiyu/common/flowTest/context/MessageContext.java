package com.zhiyu.common.flowTest.context;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author wengzhiyu
 * @since 2021/9/16 16:07
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageContext extends FlowContext {

    private String message;



}
