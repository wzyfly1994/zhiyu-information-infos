package com.zhiyu.common;

import com.sun.org.glassfish.external.amx.MBeanListener;
import com.zhiyu.common.pattern.chain.flowchain.flow.Flow;
import com.zhiyu.common.pattern.chain.flowchain.FlowContext;
import com.zhiyu.common.pattern.chain.flowchain.flow.FunctionFlowChain;
import com.zhiyu.common.pattern.chain.flowchain.service.LoadMessageRule;
import com.zhiyu.common.pattern.chain.flowchain.service.SendMessageRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wengzhiyu
 * @date 2020/12/31
 */
@SpringBootTest(classes = CommonApplication.class)
@RunWith(value = SpringRunner.class)
public class RuleHandler {

    @Autowired
    private Flow<LoadMessageRule> loadMessageFlow;
    @Autowired
    private Flow<SendMessageRule> sendMessageRule;

    @Test
    public void test() {
        FlowContext flowContext = new FlowContext();
//        flowContext.setMessage("KO");
//        loadMessageFlow.execute(flowContext);
//        sendMessageRule.execute(flowContext);

        new FunctionFlowChain<>().initContext(flowContext).addFlow(loadMessageFlow).addFlow(sendMessageRule).start((success,flowContexts) ->{return null;} );
    }


}
