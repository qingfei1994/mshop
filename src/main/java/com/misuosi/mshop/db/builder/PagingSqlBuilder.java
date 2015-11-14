/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.builder;

import com.misuosi.mshop.db.Coupler;
import com.misuosi.mshop.db.CouplerFactory;
import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.util.StringUtils;
import org.springframework.util.Assert;

/**
 * Description		: 分页sql语句构造器
 * <p/>
 * <br><br>Time		: 2015/4/13 12:11
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class PagingSqlBuilder {

    // TODO 目前只支持MySQL的分页，可继续扩展
    private static final String ORACLE = "oracle";
    private static final String MYSQL = "mysql";
    private static final String SQLSERVER = "sqlserver";

    private PagingSqlBuilder() {
    }

    /**
     * 构造分页查询sql
     *
     * @param clazz
     * @param pageNo
     * @param pageSize
     * @return
     */
    public static String getPagingSql(Class<? extends Entity> clazz, int pageNo, int pageSize) {
        Assert.notNull(clazz, "class不能为空");

        Coupler<?> coupler = CouplerFactory.getCoupler(clazz);
        StringBuffer buf = new StringBuffer();
        buf.append("select * from ").append(coupler.getTableName()).append(" order by ")
                .append(coupler.getMajorKeyName()).append(" desc limit ")
                .append((pageNo - 1) * pageSize).append(",").append(pageSize);
        return buf.toString();
    }

    /**
     * 构造分页查询sql
     *
     * @param sql
     * @param pageNo
     * @param pageSize
     * @return
     */
    public static String getPagingSql(String sql, int pageNo, int pageSize) {
        Assert.notNull(sql, "sql不能为空");

        sql = StringUtils.trimSpace(sql.toLowerCase());

        StringBuffer buf = new StringBuffer();
        buf.append(sql).append(" limit ")
                .append((pageNo - 1) * pageSize).append(",").append(pageSize);
        return buf.toString();
    }

}
