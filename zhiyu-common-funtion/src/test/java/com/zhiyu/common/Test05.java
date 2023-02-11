package com.zhiyu.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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


        //List<String> list1 = new ArrayList<>(1);
        //List<String> list2 = new ArrayList<>();
        //list1.addAll(list2);
        //list1 = list1.stream().distinct().collect(Collectors.toList());
        //System.out.println(list1.size());

        People people1 = new People("json1", 18);
        People people2 = new People("json2", 20);
        valid(people1, people2);
        System.out.println("people == >" + people1);
        System.out.println("people == >" + people2);

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
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }


    /**
     * 引用传递例子
     *
     * @param people1
     * @param people2
     */
    private void valid(People people1, People people2) {
        if (Objects.nonNull(people1) && Objects.nonNull(people2)) {
            people1.setName("ketty1");
            people1.setAge(21);

            people2.setName("ketty2");
            people2.setAge(22);

            //People temp = people1;
            //people1 = people2;
            //people2 = temp;

            System.out.println("people-v == >" + people1);
            System.out.println("people-v == >" + people2);
        }
    }

    //@Data
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class People {
        private String name;
        private Integer age;

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
