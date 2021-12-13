package com.zhiyu.common;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author wengzhiyu
 * @since 2021/6/17 16:38
 */
public class Test06 {


    public static void main(String[] args) {
//        ClassLoader classLoader=SystemRole.class.getClassLoader();
//        System.out.println(JSON.toJSONString(classLoader));
//        String s="{ \"properties\": { \"id\": { \"type\": \"integer\" }, \"name\": { \"type\": \"keyword\" }, \"price\": { \"type\": \"double\" }, \"detail\": { \"type\": \"text\" } } }";
//        System.out.println(s);
        //people();
//        String projectPath = System.getProperty("user.dir");
//        System.out.println(projectPath);
      List<Integer> list=new ArrayList<>(16);
        System.out.println(CollectionUtils.isEmpty(list));
    }


    public static void people() {
        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println("methodName----------------"+methodName);
    }
}
