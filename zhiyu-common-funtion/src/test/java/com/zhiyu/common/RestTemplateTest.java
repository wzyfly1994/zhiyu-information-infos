package com.zhiyu.common;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author wengzhiyu
 * @since 2022/7/5 20:12
 */
@SpringBootTest(classes = CommonApplication.class)
@RunWith(value = SpringRunner.class)
@Slf4j
public class RestTemplateTest {


    @Autowired
    RestTemplate restTemplate;

    @Test
    public void test(){
        String url="";
        String jason="";
//        MultiValueMap<String, String> headerMap=new HttpHeaders();
//        headerMap.put("Content-Type","application/json");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");
        HttpEntity<String> httpEntity =new HttpEntity<>(jason,headers);
        System.out.println("请求url: "+url);
        System.out.println("请求jason: "+jason);
        ResponseEntity<Map> exchange = restTemplate.exchange(url, HttpMethod.POST, httpEntity, Map.class);
        System.out.println("返回体Body: "+ JSON.toJSONString(exchange.getBody()));


    }
}
