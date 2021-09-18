package com.zhiyu.common.flowTest.context;

import com.zhiyu.common.flowTest.rule.RuleHandler;
import lombok.Data;

/**
 * @author wengzhiyu
 * @since 2021/9/14 16:04
 */
@Data
public class FlowContext implements RuleHandler {

    private Object data;

}
