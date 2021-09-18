package com.zhiyu.common.utils;

/**
 * 雪花算法
 *
 * @author wengzhiyu
 * @since 2021/6/18 15:13
 */
public class SnowflakeIdWorker {

    // 起始时间戳
    private final static long START_TIMESTAMP = 1611573333870L;
    // 序号占用的位数
    private final static long SEQUENCE_BIT = 12;
    // 机器标识占用的位数
    private final static long MACHINE_BIT = 5;
    // 数据中心占用的位数
    private final static long IDC_BIT = 5;
    /*
    数据中心位数最大值
     */
    private final static long MAX_IDC_NUM = ~(-1L << IDC_BIT);
    /*
    机器标识位数最大值
     */
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    /*
    序号占用位数最大值
     */
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);
    /*
    各部分索引
     */
    private final static long MACHINE_INDEX = SEQUENCE_BIT;
    private final static long IDC_INDEX = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_INDEX = IDC_INDEX + IDC_BIT;

    /*
    数据中心标识
     */
    private final long idcId;
    /*
    机器标识
     */
    private final long machineId;
    /*
    序列号
     */
    private long sequence = 0L;
    /*
    上一次时间戳
     */
    private long lastStamp = -1L;


    private static volatile SnowflakeIdWorker snowflakeIdWorker = null;


    public static SnowflakeIdWorker getInstance() {
        if (snowflakeIdWorker == null) {
            synchronized (SnowflakeIdWorker.class) {
                if (snowflakeIdWorker == null) {
                    snowflakeIdWorker = new SnowflakeIdWorker(1, 8);
                }
            }
        }
        return snowflakeIdWorker;
    }

    private SnowflakeIdWorker(long idcId, long machineId) {
        if (idcId > MAX_IDC_NUM || idcId < 0) {
            throw new IllegalArgumentException("idcId error");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId error");
        }
        this.idcId = idcId;
        this.machineId = machineId;
    }

    /**
     * ID生成
     *
     * @return
     */
    public synchronized long generateId() {
        long currentTime = getCurrentTime();
        // 时钟回拨，抛异常
        if (currentTime < lastStamp) {
            throw new RuntimeException("clock moved backwards.  Refusing to generate id");
        }

        // 同一毫秒内，做自增处理
        if (currentTime == lastStamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // 位运算，同一毫秒序列数达到最大
            if (sequence == 0L) {
                currentTime = getNextMillis();
            }
        } else {
            // 下一毫秒，从0开始
            sequence = 0L;
        }

        lastStamp = currentTime;

        // 位或计算
        return          // 时间戳部分值
                (currentTime - START_TIMESTAMP) << TIMESTAMP_INDEX
                        // 数据中心部分值
                        | idcId << IDC_INDEX
                        //  机器标识部分值
                        | machineId << MACHINE_INDEX
                        // 序号递增部分值
                        | sequence;
    }

    /**
     * 获取当前毫秒
     *
     * @return
     */
    private long getCurrentTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取下一毫秒
     *
     * @return
     */
    private long getNextMillis() {
        long millis = getCurrentTime();
        while (millis <= lastStamp) {
            millis = getCurrentTime();
        }
        return millis;
    }


}