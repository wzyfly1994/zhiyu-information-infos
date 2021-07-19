package com.zhiyu.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author wengzhiyu
 * @date 2020/10/19
 */
@RestController
@RequestMapping("/open/api")
@Api(tags = "开放接口")
@Slf4j
public class OpenApiController {


    @PostMapping("/data")
    public void welcome(@RequestBody JSONObject jsonObject) {
        log.info("接收到数据：[{}]", JSON.toJSONString(jsonObject));
    }
}
