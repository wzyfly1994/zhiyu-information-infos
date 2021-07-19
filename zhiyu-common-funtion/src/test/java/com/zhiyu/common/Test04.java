package com.zhiyu.common;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author wengzhiyu
 * @date 2021/3/16
 */
public class Test04 {

    @Test
    public void test() {
        BigCar bigCar=new BigCar();
        Car car= (Car) Proxy.newProxyInstance(Test04.class.getClassLoader(), bigCar.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("---"+method.invoke(bigCar,args));
                System.out.println("11111111111111");
                return null;
            }
        });
        car.start();
    }
}
