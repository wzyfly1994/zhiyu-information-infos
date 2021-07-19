package com.zhiyu;

/**
 * @author wengzhiyu
 * @date 2021/2/6
 */
public class Test02 {
    public  static  volatile int a=0;

    public Test02(Test03 test03) {
    }

    public Test02() {
    }

    public void tcp(){

    }

    public void tcp(int cc){
        int a=0;

    }

    public int tcp(double cc1){
        return 0;
    }

    public static void main(String[] args) {

        Test02 test02=new Test02(() -> {

        });
    }

}
