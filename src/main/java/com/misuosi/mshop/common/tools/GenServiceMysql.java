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
 * Description		: 自动生成service
 * <p/>
 * <br><br>Time		: 2015/5/22 10:36
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class GenServiceMysql {

    // 指定service文件所在包的路径
    private static final String CLASS_PATH = com.misuosi.mshop.common.tools.GenCommon.CLASS_PATH_ROOT.concat(".service");
    private static final String AUTHOR_NAME = "GenService"; // 作者名字

    private static final String DAO_CLASS_PATH = com.misuosi.mshop.common.tools.GenCommon.CLASS_PATH_ROOT.concat(".").concat("db.dao.Dao");
    private static final String ENTITY_CLASS_PATH_ROOT = com.misuosi.mshop.common.tools.GenCommon.CLASS_PATH_ROOT.concat(".").concat("entity");

    private static final String FILE_PATH = "/src/main/java";

    public GenServiceMysql() {
    }

    /**
     * 生成所有service
     */
    public static void genServices() {
        List<String> list = com.misuosi.mshop.common.tools.GenCommon.getTableNames();
        for (int p = 0; p < list.size(); p++) {
            String tablename = list.get(p);
            genService(tablename);
        }
        System.out.println("生成完毕！");
    }

    /**
     * 根据数据表生成service
     *
     * @param tablename
     */
    public static void genService(String tablename) {
        String entityName = NameConverter.toEntityName(Constants.DEFAULT_TABLE_PREFIX, tablename);

        StringBuilder content = new StringBuilder();
        content.append(com.misuosi.mshop.common.tools.GenCommon.getCopyright());
        content.append("package " + CLASS_PATH + ";\r\n");
        content.append("\r\n");
        content.append("import " + DAO_CLASS_PATH + ";\r\n");
        content.append("import " + ENTITY_CLASS_PATH_ROOT.concat(".") + entityName + ";\r\n");
        content.append("import org.springframework.stereotype.Service;\r\n");
        content.append("\r\n");
        content.append("import javax.annotation.Resource;\r\n");
        content.append("import java.util.List;\r\n");
        content.append("\r\n");

        // 注释部分
        content.append(com.misuosi.mshop.common.tools.GenCommon.getClassComment("service类 "
                + NameConverter.toEntityName(Constants.DEFAULT_TABLE_PREFIX, tablename)
                + "Service", AUTHOR_NAME));
        content.append("\r\n");

        // 实体部分
        buildBody(content, tablename);

        try {
            File directory = new File("");
            String outputPath = directory.getAbsolutePath() + FILE_PATH +"/"
                    + CLASS_PATH.replace(".", "/") + "/";

            File dir = new File(outputPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            outputPath += entityName + "Service.java";

            FileWriter fw = new FileWriter(outputPath);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(content);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("生成：" + entityName + "Service.java");
    }

    /**
     * 实体主体部分
     *
     * @param content
     * @param tablename
     */
    private static void buildBody(StringBuilder content, String tablename) {
        String entityName = NameConverter.toEntityName(Constants.DEFAULT_TABLE_PREFIX, tablename);
        String fieldName = NameConverter.toFieldName(Constants.DEFAULT_TABLE_PREFIX, tablename);

        content.append("@Service\r\n");
        content.append("public class " + entityName + "Service {\r\n\r\n");
        content.append("\t@Resource(name = \"" + fieldName + "Dao\")\r\n");
        content.append("\tprivate Dao<" + entityName + "> " + fieldName + "Dao;\r\n");
        content.append("\r\n");

        content.append("\tpublic int add" + entityName + "(" + entityName + " " + fieldName + ") {\r\n");
        content.append("\t\tint rows = " + fieldName + "Dao.save(" + fieldName + ");\r\n");
        content.append("\t\treturn rows;\r\n");
        content.append("\t}\r\n");
        content.append("\r\n");

        content.append("\tpublic int update" + entityName + "(" + entityName + " " + fieldName + ") {\r\n");
        content.append("\t\tint rows = " + fieldName + "Dao.dynamicUpdate(" + fieldName + ");\r\n");
        content.append("\t\treturn rows;\r\n");
        content.append("\t}\r\n");
        content.append("\r\n");

        content.append("\tpublic int delete" + entityName + "(Integer id) {\r\n");
        content.append("\t\tint rows = " + fieldName + "Dao.delete(id);\r\n");
        content.append("\t\treturn rows;\r\n");
        content.append("\t}\r\n");
        content.append("\r\n");

        content.append("\tpublic int batchDelete" + entityName + "s(Integer[] ids) {\r\n");
        content.append("\t\tint rows = " + fieldName + "Dao.batchDelete(ids);\r\n");
        content.append("\t\treturn rows;\r\n");
        content.append("\t}\r\n");
        content.append("\r\n");

        content.append("\tpublic " + entityName + " get" + entityName + "(Integer id) {\r\n");
        content.append("\t\t" + entityName + " " + fieldName + " = " + fieldName + "Dao.get(id);\r\n");
        content.append("\t\treturn " + fieldName + ";\r\n");
        content.append("\t}\r\n");
        content.append("\r\n");

        content.append("\tpublic List<" + entityName + "> getAll" + entityName + "s() {\r\n");
        content.append("\t\tList<" + entityName + "> list = " + fieldName + "Dao.findAll();\r\n");
        content.append("\t\treturn list;\r\n");
        content.append("\t}\r\n");
        content.append("\r\n");

        content.append("}\r\n");
    }

    /**
     * 出口
     *
     * @param args
     */
    public static void main(String[] args) {
        //GenServiceMysql.genServices(); // 生成所有service
        GenServiceMysql.genService("role_permission"); // 根据表生成单个service
    }

}
