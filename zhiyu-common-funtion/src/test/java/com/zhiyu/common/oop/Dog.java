package com.zhiyu.common.oop;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: wengzhiyu
 * @create: 2023-03-04 17:37
 **/
@Slf4j
public class Dog extends Animal {

    void eat() {
        log.info("dog --- eat");
    }

    void dump() {
        log.info("dog --- degDump");
    }


    public static void main(String[] args) {

        Animal animal = new Dog();
        animal.dump();

        new Animal();

    }

}
