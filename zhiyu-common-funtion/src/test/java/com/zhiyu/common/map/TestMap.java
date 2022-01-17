package com.zhiyu.common.map;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author wengzhiyu
 * @date 2021/3/23
 */
public class TestMap {

    static Node<Integer, String>[] table = new Node[2];

    public static void main(String[] args) {
//        Node[] nodes;
//        nodes = table;
//        Node<Integer, String> node = new Node<>(123, 1, "ss", null);
//        nodes[0] = node;
//        System.out.println(table[0]);
//
//        int[] arr1 = new int[2];
//        int[] arr2;
//        arr2 = arr1;
//        arr2[0]=1;
//        System.out.println(Arrays.toString(arr1));

        People people1=new People();
        people1.setAge(1);

        People people2=new People();
        people2.setAge(2);

        People people3=new People();
        people3.setAge(3);

        System.out.println(people1.hashCode());
        System.out.println(people2.hashCode());
        HashMap<People, String> map = new HashMap<>(16);
        map.put(people1, "1");
        map.put(people2, "2");
        map.put(people3, "3");
        map.get(people1);
        System.out.println(map.toString());

//        HashMap<String, String> map = new HashMap<>(16);
//        map.put("A", "1");
//        map.put("A", "2");
        //map.get("A");
//        Node<String, String> node1 = new Node<>(11111, "B", "C", null);
//        Node<String, String> node = new Node<>(11111, "A", "B", node1);
//        System.out.println(node);
//        Node[] nodeArr = new Node[2];
//        nodeArr[0] = node;
//        System.out.println(Arrays.toString(nodeArr));
//        String cc = "c";
//        String aa = "b";
//        HashSet<String> set = new HashSet<>();
//        set.add("1");
//        "eq".equals("BB");
//
//        ArrayList<String> list = new ArrayList<>(16) ;
//        list.add("1");
//        LinkedList<String> linkedList=new LinkedList<>();
//        linkedList.add("3");
//        LinkedHashMap<String,String> linkedHashMap=new LinkedHashMap(16);
//        linkedHashMap.put("123","333");


    }
}
