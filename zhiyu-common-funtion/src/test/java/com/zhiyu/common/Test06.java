package com.zhiyu.common;

import com.alibaba.fastjson.JSON;
import com.zhiyu.common.entity.pojo.SystemRole;

/**
 * @author wengzhiyu
 * @since 2021/6/17 16:38
 */
public class Test06 {


    public static void main(String[] args) {
        ClassLoader classLoader=SystemRole.class.getClassLoader();
        System.out.println(JSON.toJSONString(classLoader));

    }

}
