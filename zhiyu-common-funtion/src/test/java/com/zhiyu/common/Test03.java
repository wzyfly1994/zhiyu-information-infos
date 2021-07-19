package com.zhiyu.common;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.HashMap;

/**
 * @author wengzhiyu
 * @date 2021/3/4
 */
public  class Test03 {

    public static void main(String[] args) {
//        Map<Integer, Integer> treeMap = new TreeMap<>();
//        treeMap.put(3, 1);
//        treeMap.put(1, 1);
//        treeMap.put(2, 1);
//        treeMap.put(5, 1);
//        treeMap.put(6, 1);
//        treeMap.put(7, 1);
//        treeMap.put(8, 1);
//        treeMap.put(9, 1);
//        treeMap.put(10, 1);
//        Iterator<Map.Entry<Integer, Integer>> iterator = treeMap.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<Integer, Integer> entry = iterator.next();
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }
//
//
//        TreeSet<String> set = new TreeSet<>();
//        set.add("1");


//        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>(16);
//        concurrentHashMap.put("11", "1");
//        concurrentHashMap.get("11");

        HashMap<String, String> hashMap = new HashMap<>(16);
        hashMap.put("11", "1");
        // hashMap.get("11");
//
//        int MAXIMUM_CAPACITY = 1 << 30;
//        System.out.println(MAXIMUM_CAPACITY);



        ByteBuffer buf= ByteBuffer.allocate(100);
        // 2.向缓冲区存放5个数据
        buf.put("abcd1".getBytes());
        buf.put("CCC".getBytes());
        System.out.println("--------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        // 3.开启读模式
        buf.flip();
        System.out.println("----------开启读模式...----------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        byte[] bytes = new byte[buf.limit()];
        buf.get(bytes);
        System.out.println(new String(bytes, 0, bytes.length));
        System.out.println("----------重复读模式...----------");
        // 4.开启重复读模式
        buf.rewind();
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        byte[] bytes2 = new byte[buf.limit()];
        buf.get(bytes2);
        System.out.println(new String(bytes2, 0, bytes2.length));
        // 5.clean 清空缓冲区  数据依然存在,只不过数据被遗忘
        System.out.println("----------清空缓冲区...----------");
        buf.clear();
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        System.out.println((char)buf.get());

    }

    @Test
    public  void test(){

    }


}
