package com.zhiyu.common;

import java.text.DecimalFormat;

/**
 * @author: wengzhiyu
 * -Xms500m -Xmx500m -XX:SurvivorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC -XX:+PrintCommandLineFlags
 * -XX:+PrintGC      每次触发GC的时候打印相关日志
 * -XX:+UseSerialGC      串行回收
 * -XX:+PrintGCDetails  更详细的GC日志
 * -Xms               堆初始值
 * -Xmx               堆最大可用值
 * -Xmn               新生代堆最大可用值 新生代大小，一般设为整个堆的1/3到1/4左右
 * -Xss              设置最大调用深度 解决栈溢出
 * -XX:SurvivorRatio     用来设置新生代中eden空间和from/to空间的比例.eden区和from/to空间的比例关系n/1
 *                        如-XX:SurvivorRatio=2，eden为512K 则from/to均为256K
 * -XX:NewRatio   老年代/新生代比例 -XX:NewRatio=2  如新生代为512K 则 老年代为512K*2
 * 建议-Xms与-Xmx相等，
 * 这样的好处是可以减少程序运行时垃圾回收次数，从而提高效率
 **/
public class JvmTest {
    public static void main(String[] args) throws InterruptedException {
        byte[] b1 = new byte[100 * 1024 * 1024];
        System.out.println("分配了100m");
        jvmInfo();
        Thread.sleep(3000);
        byte[] b2 = new byte[50* 1024 * 1024];
        System.out.println("分配了50m");
        Thread.sleep(3000);
        jvmInfo();
    }

    /**
     * 转换为m
     *
     * @param maxMemory
     * @return
     */
    static private String toM(long maxMemory) {
        float num = (float) maxMemory / (1024 * 1024);
        DecimalFormat df = new DecimalFormat("0.00");// 格式化小数
        String s = df.format(num);// 返回的是String类型
        return s;
    }

    static private void jvmInfo() {
        // 最大内存
        long maxMemory = Runtime.getRuntime().maxMemory();
        System.out.println("maxMemory:" + maxMemory + ",转换为M:" + toM(maxMemory));
        // 当前空闲内存
        long freeMemory = Runtime.getRuntime().freeMemory();
        System.out.println("freeMemory:" +freeMemory+",转换为M:"+toM(freeMemory));
        // 已经使用内存
        long totalMemory = Runtime.getRuntime().totalMemory();
        System.out.println("totalMemory:" +totalMemory+",转换为M:"+toM(totalMemory));

    }
}
