package com.zhiyu.common.map;

import java.util.*;

/**
 * @author wengzhiyu
 * @date 2021/3/23
 */
public class TestMap {


    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>(16);
        map.put("A", "1");
        map.get("A");
        Node<String, String> node1 = new Node<>(11111, "B", "C", null);
        Node<String, String> node = new Node<>(11111, "A", "B", node1);
        System.out.println(node);
        Node[] nodeArr = new Node[2];
        nodeArr[0] = node;
        System.out.println(Arrays.toString(nodeArr));
        String cc = "c";
        String aa = "b";
        HashSet<String> set = new HashSet<>();
        set.add("1");
        "eq".equals("BB");

        ArrayList<String> list = new ArrayList<>(16) ;
        list.add("1");
        LinkedList<String> linkedList=new LinkedList<>();
        linkedList.add("3");
        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap(16);
        linkedHashMap.put("123","333");
    }
}
