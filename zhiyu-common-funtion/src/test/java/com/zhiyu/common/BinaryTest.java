package com.zhiyu.common;


import lombok.extern.slf4j.Slf4j;

/**
 * @author wengzhiyu
 * @since 2021/12/13 11:29
 */
@Slf4j
public class BinaryTest {


    public static void main(String[] args) {
        xorTest();
    }


    private static void xorTest() {
        int num1 = 15;
        int num2 = 12;
        log.info("{} 二进制 {}", num1, Integer.toBinaryString(num1));
        log.info("{} 二进制 {}", num2, Integer.toBinaryString(num2));
        // 两个数的二进制对比 都为1，结果才为1，否则结果为0 1111 & 1100 ->1100
        log.info("与运算{}^{}-->{}-->二进制 {}", num1, num2, num1 & num2, Integer.toBinaryString(num1 & num2));
        // 两个数的二进制只要有一个为1，那么结果就是1，否则就为0 1111 | 1100 -> 1111
        log.info("或运算{}^{}-->{}-->二进制 {}", num1, num2, num1 | num2, Integer.toBinaryString(num1 | num2));
        // 两个数的二进制相同则结果为0，不同则结果为1  1111 ^ 1100 -> 0011
        // 1. a ^ b = b ^ a
        // 2. a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c
        // 3. d = a ^ b ^ c 可以推出 a = d ^ b ^ c
        // 4. a ^ b ^ a = b
        // 1^1=0
        // 0^0=0
        // 1^0=1
        // 0^1=1
        log.info("异或运算{}^{}-->{}-->二进制 {}", num1, num2, num1 ^ num2, Integer.toBinaryString(num1 ^ num2));



        // a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c;
//        int[] numarry = new int[]{5,2,3,2,3};
//        int aim = numarry[0];
//        for(int i = 1; i < 5; i++)
//        {
//            aim = aim ^ numarry[i];
//        }
//        System.out.println("最后："+aim);

    }


    private static void bitTest() {
        int num = 15;
        int location = 2;
        log.info("{} 二进制-->{}", num, Integer.toBinaryString(num));
        log.info("<< {}位值为:{},二进制:{}", location, num << location, Integer.toBinaryString(num << location));
        log.info(">> {}位值为:{},二进制:{}", location, num >> location, Integer.toBinaryString(num >> location));
    }


}
