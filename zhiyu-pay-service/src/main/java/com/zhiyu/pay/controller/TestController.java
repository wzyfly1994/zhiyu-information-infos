package com.zhiyu.pay.controller;

import cn.shuibo.annotation.Decrypt;
import cn.shuibo.annotation.Encrypt;
import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.zhiyu.pay.common.annotation.ratelimiter.RateLimiters;
import com.zhiyu.pay.entity.dto.PayDTO;
import com.zhiyu.pay.utils.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author wengzhiyu
 * @date 2020/11/16
 */
@RestController
@RequestMapping("/test")
@Api(tags = "测试模块")
@Slf4j
public class TestController {


    @Value("${server.port}")
    public Integer port;

    @GetMapping("/encrypt")
    @ApiOperation("测试返回值加密")
    @Encrypt
    public PayDTO testEncrypt() {
        PayDTO payDTO = new PayDTO();
        payDTO.setSubject("我是subject");
        payDTO.setBody("我是body");
        return payDTO;
    }

    @Decrypt
    @PostMapping("/decryption")
    public String decryption(@RequestBody PayDTO payDTO) {
        return JSON.toJSONString(payDTO);
    }

    @GetMapping("/var")
    @RateLimiters(value = 1.0, timeout = 100, timeUnit = TimeUnit.MILLISECONDS)
    public ResponseData test(String data) {

        return ResponseData.success(data);
    }

    @PostMapping("testhttp")
    public String testhttp(@RequestHeader(value = "client",required = false) String client,String name) {
        log.info("pay--header---client-->{}",client);
        log.info("pay--name---client-->{}",name);
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert servletRequestAttributes != null;
        HttpServletResponse httpServletResponse = servletRequestAttributes.getResponse();
        assert httpServletResponse != null;
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        return "服务端口号：" + port;
    }


    @GetMapping("/sleep")
    public String testSleep() {

        for (int i = 0; i < 5; i++) {
            try {
                TimeUnit.SECONDS.sleep(2);
                log.info("ThreadName----,[{}]", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "suucess";
    }

    @GetMapping("/testSentinel")
    //@SentinelResource("testSentinel")
    public Object testSentinel() {
        initFlowRules();
        // 1.5.0 版本开始可以直接利用 try-with-resources 特性，自动 exit entry
        try (Entry entry = SphU.entry("HelloWorld")) {
            // 被保护的逻辑
            System.out.println("hello world");
        } catch (BlockException ex) {
            // 处理被流控的逻辑
            System.out.println("blocked!");
        }
        return "success";
    }

    private  void initFlowRules(){
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        rule.setResource("testSentinel");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        // Set limit QPS to 20.
        rule.setCount(20);
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
