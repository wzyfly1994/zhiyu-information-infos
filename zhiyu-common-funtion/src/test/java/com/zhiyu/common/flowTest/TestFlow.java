package com.zhiyu.common.flowTest;

import com.zhiyu.common.CommonApplication;
import com.zhiyu.common.flowTest.context.FlowContext;
import com.zhiyu.common.flowTest.context.MessageContext;
import com.zhiyu.common.flowTest.context.RepContext;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @author wengzhiyu
 * @since 2021/9/13 17:32
 */
@SpringBootTest(classes = CommonApplication.class)
@RunWith(value = SpringRunner.class)
@Slf4j
public class TestFlow {

    @Autowired
    private FlowChain<RepContext> flowChain;
    @Autowired
    private Flow<?> applyService;
    @Autowired
    private Flow<?> processService;

    @Test
    public void test() {

        FlowContext flowContext = new FlowContext();
        flowContext.setData("this is data");

        MessageContext messageContext = new MessageContext();
        messageContext.setMessage("this is message");
        messageContext.setData("this is data");

        //boolean start = flowChain.initContext(flowContext).addFlow(applyService).start(success -> true);
        RepContext repContext = flowChain.initContext(messageContext).addFlow(applyService).addFlow(processService).start(
                (callback) -> {
                    log.info("result+++++" + callback);
                    return callback;
                }
        );
        System.out.println(repContext);

        HashMap<String,Object> map=new HashMap<>(16);
        map.put("SS",1);
    }
}
