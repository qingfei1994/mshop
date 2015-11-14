/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description		: 字符串工具
 * <p/>
 * <br><br>Time		: 2015/4/8 13:28
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String firstToUpperCase(String str) {
        return new StringBuilder().append(str.substring(0, 1).toUpperCase())
                .append(str.substring(1)).toString();
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String firstToLowerCase(String str) {
        return new StringBuilder().append(str.substring(0, 1).toLowerCase())
                .append(str.substring(1)).toString();
    }

    /**
     * 判断是否是数字（包括小数）
     *
     * @param str
     * @return
     */
    public final static boolean isDigit(String str) {
        if (!isBlank(str)) {
            Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]*");
            return pattern.matcher(str).matches();
        }
        return false;
    }

    /**
     * 加了空判断的substring
     *
     * @param str
     * @param index
     * @param length
     * @return
     */
    public static String substring(String str, int index, int length) {
        if (str == null) {
            return null;
        }
        int len = str.length();
        if (index + length > len) {
            length = len - index;
        }
        return str.substring(index, length);
    }

    /**
     * 取字符串值
     *
     * @param obj
     * @param def
     * @return
     */
    public static String stringValue(Object obj, String def) {
        if (obj == null) {
            return def;
        }
        return obj.toString();
    }

    /**
     * 取整数值
     *
     * @param obj
     * @param def
     * @return
     */
    public static int intValue(Object obj, int def) {
        String str = stringValue(obj, "");
        if (!isBlank(str) && isNumeric(str)) {
            return Integer.parseInt(str);
        }
        return def;
    }

    /**
     * 取整数值（short）
     *
     * @param obj
     * @param def
     * @return
     */
    public static short shortValue(Object obj, short def) {
        String str = stringValue(obj, "");
        if (!isBlank(str) && isNumeric(str)) {
            return Short.parseShort(str);
        }
        return def;
    }

    /**
     * 取整数值（byte）
     *
     * @param obj
     * @param def
     * @return
     */
    public static byte byteValue(Object obj, byte def) {
        String str = stringValue(obj, "");
        if (!isBlank(str) && isNumeric(str)) {
            return Byte.parseByte(str);
        }
        return def;
    }

    /**
     * 取浮点数值
     *
     * @param obj
     * @param def
     * @return
     */
    public static float floatValue(Object obj, float def) {
        String str = stringValue(obj, "");
        if (!isBlank(str) && isDigit(str)) {
            return Float.parseFloat(str);
        }
        return def;
    }

    /**
     * 取浮点数值
     *
     * @param obj
     * @param def
     * @return
     */
    public static double doubleValue(Object obj, double def) {
        String str = stringValue(obj, "");
        if (!isBlank(str) && isDigit(str)) {
            return Double.parseDouble(str);
        }
        return def;
    }

    /**
     * 取布尔值
     *
     * @param obj
     * @param def
     * @return
     */
    public static boolean booleanValue(Object obj, boolean def) {
        String str = stringValue(obj, "");
        if (!isBlank(str) && ("true".equals(str) || "false".equals(str))) {
            return Boolean.parseBoolean(str);
        }
        return def;
    }

    /**
     * 在提供的字符前填0补足位数
     *
     * @param sourceStr 需要补0的字符串
     * @param length    补足的长度
     * @return
     */
    public static String fillLengthWith0(String sourceStr, int length) {
        int add0Num = length - sourceStr.length();
        String fill0Str = sourceStr;
        for (int i = 0; i < add0Num; i++) {
            fill0Str = 0 + fill0Str;
        }
        return fill0Str;
    }

    /**
     * 生成length长度的随机数字
     *
     * @param length 生成随机数字的长度
     * @return
     */
    public static String createNonceNum(int length) {
        String chars = "0123456789";
        String res = "";
        for (int i = 0; i < length; i++) {
            Random rd = new Random();
            res += chars.charAt(rd.nextInt(chars.length() - 1));
        }
        return res;
    }

    /**
     * 生成length长度的随机字符串
     *
     * @param length 生成随机字符串的长度
     * @return
     */
    public static String createNonceStr(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String res = "";
        for (int i = 0; i < length; i++) {
            Random rd = new Random();
            res += chars.charAt(rd.nextInt(chars.length() - 1));
        }
        return res;
    }

    /**
     * 生成随机字符串，当前时间加上4位随机字符串
     *
     * @return
     */
    public static String createNonceStr() {
        return com.misuosi.mshop.util.DateUtils.getDate("yyyyMMddHHmmss").concat(StringUtils.createNonceStr(4));
    }

    /**
     * 将多个空格替换成一个空格
     *
     * @param str
     * @return
     */
    public static String trimSpace(String str) {
        Pattern p = Pattern.compile("\\s+");
        Matcher m = p.matcher(str);
        return m.replaceAll(" ");
    }

}
