package com.zhiyu.common;

import com.alibaba.fastjson.JSON;
import com.zhiyu.common.entity.pojo.SystemRole;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author wengzhiyu
 * @since 2021/6/17 16:38
 */
public class Test06 {


    public static void main(String[] args) {
//        ClassLoader classLoader=SystemRole.class.getClassLoader();
//        System.out.println(JSON.toJSONString(classLoader));
//       String s="{ \"properties\": { \"id\": { \"type\": \"integer\" }, \"name\": { \"type\": \"keyword\" }, \"price\": { \"type\": \"double\" }, \"detail\": { \"type\": \"text\" } } }";
//        System.out.println(s);
        SystemRole systemRole1=new SystemRole().setId(1L).setRoleName("名字1");
        SystemRole systemRole2=new SystemRole().setId(1L).setRoleName("名字2");
        List<SystemRole> list=new ArrayList<>(16);
        list.add(systemRole1);
        list.add(systemRole2);
        Map<Long,SystemRole> map=list.stream().collect(Collectors.toMap(SystemRole::getId, Function.identity(),(v1,v2)->v2));
        System.out.println(map);

    }


//    public static int getOffsetNum(int current, int size) {
//        int offset = 0;
//        if (current <= 1) {
//            return offset;
//        }
//        offset = (current - 1) * size;
//        return offset;
//    }

    public static int getOffsetNum(int current, int size) {
        if (current <= 1) {
            return 0;
        }
        return (current - 1) * size;
    }

}
