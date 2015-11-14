/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description		: 时间工具类
 * <p/>
 * <br><br>Time		: 2015/4/16 9:15
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class DateUtils {

    private static SimpleDateFormat sdf = new SimpleDateFormat();
    private static String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /**
     * 根据格式返回日期
     *
     * @param format
     * @return
     */
    public static String getDate(String format) {
        sdf.applyPattern(format);
        return sdf.format(new Date());
    }

    /**
     * 获取今天是星期几
     *
     * @return
     */
    public static String getWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 输入时期是否今天
     *
     * @param date
     * @return
     */
    public static boolean isToday(Date date) {
        if (date == null) {
            return false;
        }

        Calendar nowCalendar = Calendar.getInstance();
        nowCalendar.setTime(new Date());

        Calendar inputDate = Calendar.getInstance();
        inputDate.setTime(date);

        if (nowCalendar.get(Calendar.YEAR) == inputDate.get(Calendar.YEAR)
                && nowCalendar.get(Calendar.MONTH) == inputDate.get(Calendar.MONTH)
                && nowCalendar.get(Calendar.DAY_OF_MONTH) == inputDate.get(Calendar.DAY_OF_MONTH)) {
            return true;
        }
        return false;
    }

    /**
     * 获得当前时间
     *
     * @return
     */
    public static Timestamp getCurrentTime() {
        Timestamp nousedate = new Timestamp(System.currentTimeMillis());
        return nousedate;
    }

    /**
     * 将字符串转成日期
     *
     * @param format
     * @param str
     * @return
     */
    public static Date toDate(String format, String str) {
        Date date = null;
        if (format == null) {
            sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        } else {
            sdf.applyPattern(format);
        }
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将字符串转成时间
     *
     * @param format
     * @param str
     * @return
     */
    public static Timestamp toTime(String format, String str) {
        Date date = toDate(format, str);
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }

}
