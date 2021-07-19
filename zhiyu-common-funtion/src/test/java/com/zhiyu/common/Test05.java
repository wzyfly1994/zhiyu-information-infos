package com.zhiyu.common;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wengzhiyu
 * @date 2021/3/31
 */
public class Test05 {

    @Test
    public void test() {
        int[] arr = new int[]{3, 1, 2, 10, 1};
        AtomicInteger atomicInteger=new AtomicInteger();
        atomicInteger.incrementAndGet();
        System.out.println(Arrays.toString(runningSum(arr)));
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
