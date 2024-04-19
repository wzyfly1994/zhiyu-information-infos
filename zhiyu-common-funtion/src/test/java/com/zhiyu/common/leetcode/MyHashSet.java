package com.zhiyu.common.leetcode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashSet {

    private LinkedList<Node>[] datas;

    private final int base = 7;

    public MyHashSet() {
        datas = new LinkedList[base];
        for (int i = 0; i < base; i++) {
            datas[i] = new LinkedList<>();
        }
    }


    public void add(int key) {
        LinkedList<Node> dataList = datas[hash(key)];
        for (Node node : dataList) {
            if (node != null && node.getKey() == key) {
                node.setKey(key);
                return;
            }
        }
        dataList.add(new Node(key));
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

    public boolean contains(int key) {
        LinkedList<Node> dataList = datas[hash(key)];
        for (Node node : dataList) {
            if (node.getKey() == key) {
                return true;
            }
        }
        return false;
    }

    public long size() {
        return Arrays.stream(datas).filter(node -> !node.isEmpty()).count();
    }

    public static class Node {

        private int key;

        private Object value;

        private final Object object = new Object();

        public Node(int key) {
            this.key = key;
            this.value = object;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

    private int hash(int key) {
        return key % base;
    }

    public static void main(String[] args) {
        MyHashSet myHashSet = new MyHashSet();
        myHashSet.add(1);
        myHashSet.add(2);
        myHashSet.add(4);
        myHashSet.add(5);
        myHashSet.add(7);
        myHashSet.remove(7);
        System.out.printf(myHashSet.size() + "%n");
        System.out.printf(myHashSet.contains(7) + "");


    }
}
