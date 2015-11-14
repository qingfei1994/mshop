/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.common.tools;

import com.misuosi.mshop.util.DateUtils;

import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Description		: 自动生成实体和配置的公共类
 * <p/>
 * <br><br>Time		: 2015/5/23 11:52
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class GenCommon {

    //数据库连接
    public static final String JDBC_URL = "jdbc:mysql://192.168.1.104:3306/mshop20150714";
    public static final String JDBC_USERNAME = "root";
    public static final String JDBC_PASSWORD = "admin";
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    public static final String CLASS_PATH_ROOT = "com.misuosi.mshop"; // package根路径

    /**
     * 获取当前数据库下所有的表名
     */
    public static List<String> getTableNames() {
        List<String> list = new ArrayList<String>();
        try {
            try {
                Class.forName(GenCommon.JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            DatabaseMetaData meta = DriverManager.getConnection(GenCommon.JDBC_URL,
                    GenCommon.JDBC_USERNAME, GenCommon.JDBC_PASSWORD).getMetaData();
            ResultSet rs = meta.getTables(null, null, null, new String[] { "TABLE" });
            while (rs.next()) {
                list.add(rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取版权信息
     *
     * @return
     */
    public static String getCopyright() {
        StringBuilder content = new StringBuilder();
        content.append("/*\r\n");
        content.append(" * Copyright (c) 2014\r\n");
        content.append(" * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)\r\n");
        content.append(" * All rights reserved.\r\n");
        content.append(" */\r\n");
        return content.toString();
    }

    /**
     * 获取类注释
     *
     * @return
     */
    public static String getClassComment(String description, String authorName) {
        StringBuilder content = new StringBuilder();
        content.append("/**\r\n");
        content.append(" * Description\t : " + description + "\r\n");
        content.append(" * <br><br>Time\t : " + DateUtils.getDate("yyyy/MM/dd HH:mm") + "\r\n");
        content.append(" *\r\n");
        content.append(" * @author " + authorName + "\r\n");
        content.append(" * @version 1.0\r\n");
        content.append(" * @since 1.0\r\n");
        content.append(" */\r\n");
        return content.toString();
    }

}
