/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.util;

/**
 * Description		: 数字操作工具类
 * <p/>
 * <br><br>Time		: 2015/6/9 9:31
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class NumberUtils {

    /**
     * 限定数字的范围
     *
     * @param num
     * @param min
     * @param max
     * @return
     */
    public static Integer rangeInteger(Integer num, Integer min, Integer max) {
        if (num == null) {
            return num;
        }
        if (num < min) {
            num = min;
        }
        if (num > max) {
            num = max;
        }
        return num;
    }

    /**
     * 限定数字的范围
     *
     * @param num
     * @param min
     * @param max
     * @return
     */
    public static Double rangeDouble(Double num, Double min, Double max) {
        if (num == null) {
            return num;
        }
        if (num < min) {
            num = min;
        }
        if (num > max) {
            num = max;
        }
        return num;
    }

    /**
     * 取得数组中的最大值
     *
     * @param arr
     * @return
     */
    public static double getMaxNumber(double[] arr) {
        double max = arr[0];
        for (double num : arr) {
            if (max < num) {
                max = num;
            }
        }
        return max;
    }

}
