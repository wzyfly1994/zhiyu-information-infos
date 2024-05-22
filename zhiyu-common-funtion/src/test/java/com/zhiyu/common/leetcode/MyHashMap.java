package com.zhiyu.common.leetcode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashMap {

    private LinkedList<Node>[] datas;

    private final int base = 7;

    public MyHashMap() {
        datas = new LinkedList[base];
        for (int i = 0; i < base; i++) {
            datas[i] = new LinkedList<>();
        }
    }


    public void put(int key, int value) {
        int hash = hash(key);
        LinkedList<Node> dataList = datas[hash];
        for (Node node : dataList) {
            if (node != null && node.getKey() == key) {
                node.setValue(value);
                return;
            }
        }
        dataList.add(new Node(key, value));
    }

    public int get(int key) {
        LinkedList<Node> dataList = datas[hash(key)];
        if (dataList != null && !dataList.isEmpty()) {
            for (Node node : dataList) {
                if (node != null) {
                    if (node.getKey() == key) {
                        return node.getValue();
                    }
                }
            }
        }
        return -1;
    }

    public void remove(int key) {
        Iterator<Node> iterator = datas[hash(key)].iterator();
        while (iterator.hasNext()) {
            Node next = iterator.next();
            if (next.getKey() == key) {
                iterator.remove();
                return;
            }
        }
    }


    public static class Node {

        private int key;

        private int value;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }


    }

    private int hash(int key) {
        return key % base;
    }

    public static void main(String[] args) {
        HashMap map = new HashMap<>();
        map.put("1", "1");
        map.get("1");

//        MyHashMap myHashMap = new MyHashMap();
//        myHashMap.put(1, 13);
//        myHashMap.put(1, 6);
//        myHashMap.put(6, 15);
//        myHashMap.put(8, 8);
//        myHashMap.put(11, 0);
        // myHashMap.put(1, 3);
        // myHashMap.remove(1);
        // myHashMap.put(2, 4);
        // System.out.printf(String.valueOf(myHashMap.get(1)) + "%n");
        //System.out.printf(String.valueOf(myHashMap.get(2)));

        //    System.out.printf(String.valueOf(-5 % 5));
    }
}
