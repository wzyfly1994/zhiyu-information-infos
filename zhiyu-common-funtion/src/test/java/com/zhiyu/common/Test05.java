package com.zhiyu.common;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author wengzhiyu
 * @date 2021/3/31
 */
public class Test05 {

    @Test
    public void test() {
//        int[] arr = new int[]{3, 1, 2, 10, 1};
//        AtomicInteger atomicInteger=new AtomicInteger();
//        atomicInteger.incrementAndGet();
//        System.out.println(Arrays.toString(runningSum(arr)));


        List<String> list1=new ArrayList<>(1);
        List<String> list2=new ArrayList<>();
        list1.addAll(list2);
        list1=list1.stream().distinct().collect(Collectors.toList());
        System.out.println(list1.size());

    }

    public int[] runningSum(int[] nums) {
//        if (nums == null || nums.length == 0) {
//            return null;
//        }
//        int[] arr = new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            int sum = 0;
//            for (int j = 0; j <= i; j++) {
//                sum += nums[j];
//            }
//            arr[i] = sum;
//        }
//        return arr;
        for(int i=1 ; i<nums.length; i++){
            nums[i] +=nums[i-1];
        }
        return nums;
    }
}
