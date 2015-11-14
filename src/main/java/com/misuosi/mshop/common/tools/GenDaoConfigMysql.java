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
import java.util.List;

/**
 * Description		: 自动生成dao配置
 * <p/>
 * <br><br>Time		: 2015/5/22 10:02
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class GenDaoConfigMysql {

    private static final String DAO_CLASS_PATH = com.misuosi.mshop.common.tools.GenCommon.CLASS_PATH_ROOT.concat(".").concat("db.query.QueryDao");
    private static final String ENTITY_CLASS_PATH_ROOT = com.misuosi.mshop.common.tools.GenCommon.CLASS_PATH_ROOT.concat(".").concat("entity");
    private static final String FILE_PATH = "/src/main/resources/spring";

    public GenDaoConfigMysql() {
    }

    /**
     * 生成dao配置
     */
    public static void genDaoConfig() {
        List<String> list = com.misuosi.mshop.common.tools.GenCommon.getTableNames();

        StringBuilder content = new StringBuilder();
        content.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
        content.append("<beans xmlns=\"http://www.springframework.org/schema/beans\"\r\n");
        content.append("\t   xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\r\n");
        content.append("\t   xsi:schemaLocation=\"http://www.springframework.org/schema/beans\r\n");
        content.append("\t\thttp://www.springframework.org/schema/beans/spring-beans-3.2.xsd\">\r\n");
        content.append("\r\n");

        for (int p = 0; p < list.size(); p++) {
            String tablename = list.get(p);

            content.append("\t<bean id=\""
                    + NameConverter.toFieldName(Constants.DEFAULT_COLUMN_PREFIX, tablename)
                    + "Dao\" class=\"" + DAO_CLASS_PATH + "\">\r\n");
            content.append("\t\t<constructor-arg value=\""
                    + ENTITY_CLASS_PATH_ROOT.concat(".")
                    + NameConverter.toEntityName(Constants.DEFAULT_COLUMN_PREFIX, tablename)
                    +"\"/>\r\n");
            content.append("\t</bean>\r\n");
        }

        content.append("\r\n");
        content.append("</beans>");

        try {
            File directory = new File("");
            String outputPath = directory.getAbsolutePath() + FILE_PATH + "/";
            File dir = new File(outputPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            outputPath += "spring-dao.xml";

            FileWriter fw = new FileWriter(outputPath);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("生成完毕！");
    }

    /**
     * 出口
     *
     * @param args
     */
    public static void main(String[] args) {
        GenDaoConfigMysql.genDaoConfig();
    }

}
