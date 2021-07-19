package com.zhiyu;

/**
 * @author wengzhiyu
 * @date 2021/2/6
 */
public interface Test03 extends Test04, Test05 {


    void kill();

    default String keep() {
        return "jsons";
    }
}
