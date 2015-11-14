/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db;

import com.misuosi.mshop.util.StringUtils;

/**
 * Description		: 实体名/表名、属性名/列名转换工具
 * <p/>
 * <br><br>Time		: 2015/4/8 12:13
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class NameConverter {

    /**
     * 表名转实体名
     *
     * @param tableName
     * @return
     */
    public static String toEntityName(String prefix, String tableName) {
        String str = tableName.substring(prefix.length());
        str = toCamelCase(str);
        return StringUtils.firstToUpperCase(str);
    }

    /**
     * 实体名转表名
     *
     * @param entityName
     * @return
     */
    public static String toTableName(String prefix, String entityName) {
        String str = toUnderlineCase(entityName);
        str = str.toLowerCase();
        return prefix.concat(str);
    }

    /**
     * 列名转属性名
     *
     * @param columnName
     * @return
     */
    public static String toFieldName(String prefix, String columnName) {
        String str = columnName.substring(prefix.length());
        str = toCamelCase(str);
        return StringUtils.firstToLowerCase(str);
    }

    /**
     * 属性名转列名
     *
     * @param fieldName
     * @return
     */
    public static String toColumnName(String prefix, String fieldName) {
        String str = toUnderlineCase(fieldName);
        str = str.toLowerCase();
        return prefix.concat(str);
    }

    /**
     * 下划线分隔的命名转成驼峰式命名
     *
     * @param str
     * @return
     */
    private static String toCamelCase(String str) {
        StringBuffer buf = new StringBuffer();
        String arr[] = str.split("_");
        buf.append(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            buf.append(StringUtils.firstToUpperCase(arr[i]));
        }
        return buf.toString();
    }

    /**
     * 驼峰式命名转成下划线分隔的命名
     *
     * @param str
     * @return
     */
    private static  String toUnderlineCase(String str) {
        StringBuffer buf = new StringBuffer();
        char c[] = str.toCharArray();
        buf.append(c[0]);
        for (int i = 1; i < c.length; i++) {
            if (Character.isUpperCase(c[i])) {
                buf.append("_").append(Character.toLowerCase(c[i]));
            } else {
                buf.append(c[i]);
            }
        }
        return buf.toString();
    }

}
