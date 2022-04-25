package com.zhiyu.common;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zhiyu.common.entity.pojo.SystemRole;
import com.zhiyu.common.utils.ListUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
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
//        SystemRole systemRole1=new SystemRole().setId(1L).setRoleName("名字1");
//        SystemRole systemRole2=new SystemRole().setId(1L).setRoleName("名字2");
//        List<SystemRole> list=new ArrayList<>(16);
//        list.add(systemRole1);
//        list.add(systemRole2);
//        Map<Long,SystemRole> map=list.stream().collect(Collectors.toMap(SystemRole::getId, Function.identity(),(v1,v2)->v2));
//        System.out.println(map);

//        final String nullStr = "null";
//        System.out.println(nullStr.equalsIgnoreCase("NULL"));

//        Integer categoryId = conversionCategoryId(1048588);
//        System.out.println(categoryId);

//        List<Integer> numList = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8);
//
//        List<List<Integer>> lists= Lists.partition(numList,3);
//        System.out.println(lists);

//        List<SystemRole> list=new ArrayList<>(6);
//        SystemRole role1=null;
//        SystemRole role2=new SystemRole();
//        list.add(role1);
//        list.add(role2);
//        forEach(list,s->{
//            System.out.println(s);
//        });

//        Map<String, String> pushShopMap1 = new HashMap<>(6);
//        pushShopMap1.put("A","1");
//        pushShopMap1.put("B","2");
//        pushShopMap1.put("C","3");
//
//
//        Map<String, String> pushShopMap2= new HashMap<>(6);
//        pushShopMap2.put("A","1");
//        pushShopMap2.put("B","2");
//        Set<String> set1 = pushShopMap1.keySet();
//        Set<String> set2 = pushShopMap2.keySet();
//        Set<String> result = new HashSet<>(set1);
//        result.removeAll(set2);
//        System.out.println(result);

        List<String> list=new ArrayList<>(16);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        List<List<String>> lists = ListUtils.splitList(list, 1000);
        System.out.println(lists);


    }

    public static <T> void forEach(Collection<T> list, Consumer<? super T> callback) {
        if (list != null && !list.isEmpty()) {
            list.forEach(callback);
        }
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

    public static Integer conversionCategoryId(Integer sequence){
        String binaryStr = intToBinary12(sequence, 31);

        String idBinaryStr = binaryStr.substring(12, 31);

        //转为10进制
        return Integer.parseUnsignedInt(idBinaryStr, 2);
    }

    /**
     * int 转换为二进制，不足bitNum位补0
     * @param i
     * @param bitNum
     * @return
     */
    public static String intToBinary12(int i, int bitNum){
        String binaryStr = Integer.toBinaryString(i);
        while (binaryStr.length() < bitNum){
            binaryStr = "0" + binaryStr;
        }

        return binaryStr;
    }

}
