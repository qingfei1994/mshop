/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.common.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description		: 时间测试工具类
 * <p/>
 * <br><br>Time		: 2015/5/11 9:09
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class TimeHolder {

    private static final Logger log = LoggerFactory.getLogger(TimeHolder.class);

    private static long startTime;
    private static long endTime;
    private static long interval; // 时间间隔

    public static void start() {
        startTime = System.currentTimeMillis();
    }

    public static void end() {
        endTime = System.currentTimeMillis();
        interval = endTime - startTime;
    }

    public static void trace(String msg) {
        log.debug("{}: {}", msg, interval);
    }

    public long getInterval() {
        return interval;
    }

}
