/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.common.tools;

import com.misuosi.mshop.db.Constants;
import com.misuosi.mshop.db.NameConverter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

/**
 * Description		: 自动生成实体类
 * <p/>
 * <br><br>Time		: 2015/5/21 23:24
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class GenEntityMysql {

    // 指定实体生成所在包的路径
    private static final String CLASS_PATH = com.misuosi.mshop.common.tools.GenCommon.CLASS_PATH_ROOT.concat(".").concat("entity");
    private static final String AUTHOR_NAME = "GenEntity"; // 作者名字

    private static final String FILE_PATH = "/src/main/java";

    private static String[] colnames; // 列名数组
    private static String[] colTypes; // 列名类型数组
    private static int[] colSizes; // 列名大小数组
    private static boolean f_util = false; // 是否需要导入包java.util.*
    private static boolean f_sql = false; // 是否需要导入包java.sql.*
    private static boolean f_timestamp = false; // 是否需要导入类java.sql.Timestamp;

    public GenEntityMysql() {
    }

    /**
     * 生成所有实体
     */
    public static void genEntities() {
        List<String> list = com.misuosi.mshop.common.tools.GenCommon.getTableNames();
        for (int p = 0; p < list.size(); p++) {
            String tablename = list.get(p);
            genEntity(tablename);
        }
        System.out.println("生成完毕！");
    }

    /**
     * 根据数据表生成实体类
     *
     * @param tablename
     */
    public static void genEntity(String tablename) {
        f_sql = false;
        f_util = false;
        f_timestamp = false;

        // 创建连接
        Connection con = null;
        // 查要生成实体类的表
        String sql = "select * from " + tablename;
        try {
            try {
                Class.forName(com.misuosi.mshop.common.tools.GenCommon.JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            con = DriverManager.getConnection(com.misuosi.mshop.common.tools.GenCommon.JDBC_URL,
                    com.misuosi.mshop.common.tools.GenCommon.JDBC_USERNAME, com.misuosi.mshop.common.tools.GenCommon.JDBC_PASSWORD);
            PreparedStatement pStemt = con.prepareStatement(sql);
            ResultSetMetaData rsmd = pStemt.getMetaData();

            int size = rsmd.getColumnCount(); // 统计总共有多少列
            colnames = new String[size];
            colTypes = new String[size];
            colSizes = new int[size];
            for (int i = 0; i < size; i++) {
                colnames[i] = rsmd.getColumnName(i + 1);
                colTypes[i] = rsmd.getColumnTypeName(i + 1);

                if (colTypes[i].equalsIgnoreCase("datetime")
                        || colTypes[i].equalsIgnoreCase("date")
                        || colTypes[i].equalsIgnoreCase("time")) {
                    f_util = true;
                }
                if (colTypes[i].equalsIgnoreCase("image")
                        && colTypes[i].equalsIgnoreCase("timestamp")) {
                    f_sql = true;
                } else if (colTypes[i].equalsIgnoreCase("timestamp")) {
                    f_timestamp = true;
                }
                colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
            }

            String content = parse(tablename);

            try {
                File directory = new File("");
                String outputPath = directory.getAbsolutePath() + FILE_PATH + "/"
                        + CLASS_PATH.replace(".", "/") + "/";

                File dir = new File(outputPath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                outputPath += initcap(tablename) + ".java";

                FileWriter fw = new FileWriter(outputPath);
                PrintWriter pw = new PrintWriter(fw);
                pw.println(content);
                pw.flush();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("生成：" + initcap(tablename) + ".java");
    }

    /**
     * 功能：生成实体类主体代码
     *
     * @return
     */
    private static String parse(String tablename) {
        StringBuffer sb = new StringBuffer();
        sb.append(com.misuosi.mshop.common.tools.GenCommon.getCopyright());
        sb.append("package " + CLASS_PATH + ";\r\n");
        sb.append("\r\n");

        sb.append("import " + com.misuosi.mshop.common.tools.GenCommon.CLASS_PATH_ROOT + ".db.Entity;\r\n");
        sb.append("import " + com.misuosi.mshop.common.tools.GenCommon.CLASS_PATH_ROOT + ".db.annotation.Id;\r\n");
        sb.append("\r\n");

        // 判断是否导入工具包
        if (f_util) {
            sb.append("import java.util.Date;\r\n");
        }
        if (f_sql) {
            sb.append("import java.sql.*;\r\n");
        } else if (f_timestamp) {
            sb.append("import java.sql.Timestamp;\r\n");
        }
        if (f_util || f_sql || f_timestamp) {
            sb.append("\r\n");
        }

        // 注释部分
        sb.append(com.misuosi.mshop.common.tools.GenCommon.getClassComment("实体类 "
                + NameConverter.toEntityName(Constants.DEFAULT_TABLE_PREFIX, tablename), AUTHOR_NAME));
        sb.append("\r\n");

        // 实体部分
        sb.append("public class " + initcap(tablename) + " extends Entity {\r\n\r\n");
        processAllAttrs(sb); // 属性
        processAllMethod(sb); // get set 方法
        sb.append("}\r\n");

        return sb.toString();
    }

    /**
     * 功能：生成所有属性
     *
     * @param sb
     */
    private static void processAllAttrs(StringBuffer sb) {
        for (int i = 0; i < colnames.length; i++) {
            if (i == 0) {
                sb.append("\t@Id\r\n");
            }
            sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " "
                    + NameConverter.toFieldName(Constants.DEFAULT_COLUMN_PREFIX, colnames[i])
                    + ";\r\n");
        }
        sb.append("\r\n");
    }

    /**
     * 功能：生成所有方法
     *
     * @param sb
     */
    private static void processAllMethod(StringBuffer sb) {
        for (int i = 0; i < colnames.length; i++) {
            sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colnames[i]) + "() {\r\n");
            sb.append("\t\treturn " + NameConverter.toFieldName(Constants.DEFAULT_COLUMN_PREFIX, colnames[i]) + ";\r\n");
            sb.append("\t}\r\n\r\n");
            sb.append("\tpublic void set" + initcap(colnames[i]) + "("
                    + sqlType2JavaType(colTypes[i]) + " "
                    + NameConverter.toFieldName(Constants.DEFAULT_COLUMN_PREFIX, colnames[i])
                    + ") {\r\n");
            sb.append("\t\tthis."
                    + NameConverter.toFieldName(Constants.DEFAULT_COLUMN_PREFIX, colnames[i])
                    + " = "
                    + NameConverter.toFieldName(Constants.DEFAULT_COLUMN_PREFIX, colnames[i])
                    + ";\r\n");
            sb.append("\t}\r\n\r\n");
        }
    }

    /**
     * 功能：将输入字符串的首字母改成大写，并改成驼峰式命名
     *
     * @param str
     * @return
     */
    private static String initcap(String str) {
        return NameConverter.toEntityName(Constants.DEFAULT_TABLE_PREFIX, str);
    }

    /**
     * 功能：获取列的数据类型
     *
     * @param sqlType
     * @return
     */
    private static String sqlType2JavaType(String sqlType) {
        if (sqlType.equalsIgnoreCase("bit")) {
            return "Boolean";
        } else if (sqlType.equalsIgnoreCase("tinyint")) {
            return "Byte";
        } else if (sqlType.equalsIgnoreCase("smallint")) {
            return "Short";
        } else if (sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("INT UNSIGNED")) {
            //INT UNSIGNED无符号整形
            return "Integer";
        } else if (sqlType.equalsIgnoreCase("bigint")) {
            return "Long";
        } else if (sqlType.equalsIgnoreCase("float")) {
            return "Float";
        } else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
                || sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
                || sqlType.equalsIgnoreCase("smallmoney")) {
            return "Double";
        } else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
                || sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
                || sqlType.equalsIgnoreCase("text")) {
            return "String";
        } else if (sqlType.equalsIgnoreCase("datetime") || sqlType.equalsIgnoreCase("date")
                || sqlType.equalsIgnoreCase("time")) {
            return "Date";
        } else if (sqlType.equalsIgnoreCase("timestamp")) {
            return "Timestamp";
        } else if (sqlType.equalsIgnoreCase("image")) {
            return "Blod";
        }
        return null;
    }

    /**
     * 出口
     *
     * @param args
     */
    public static void main(String[] args) {
        GenEntityMysql.genEntities(); // 生成所有实体类
        //GenEntityMysql.genEntity("refund_information"); // 根据表生成单个实体类
    }

}
