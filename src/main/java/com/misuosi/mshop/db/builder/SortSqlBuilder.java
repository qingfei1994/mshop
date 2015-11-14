/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.builder;

import com.misuosi.mshop.db.Constants;
import com.misuosi.mshop.db.NameConverter;
import com.misuosi.mshop.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * Description		: 排序sql语句构造器
 * <p/>
 * <br><br>Time		: 2015/5/1 14:19
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class SortSqlBuilder {

    private static final Logger log = LoggerFactory.getLogger(SortSqlBuilder.class);
    private static final String ORDER_BY = "order by";

    private SortSqlBuilder() {
    }

    /**
     * 构造排序查询sql
     *
     * @param sql
     * @param property
     * @param order
     * @return
     */
    public static String getSortSql(String sql, String property, String order) {
        Assert.notNull(sql, "sql不能为空");
        Assert.notNull(property, "排序属性不能为空");
        Assert.notNull(order, "排序顺序不能为空");

        sql = StringUtils.trimSpace(sql.toLowerCase());

        StringBuffer buf = new StringBuffer();
        if (sql.contains(ORDER_BY)) {
            int index = sql.indexOf(ORDER_BY) + ORDER_BY.length();
            buf.append(sql.substring(0, index)).append(" ")
                    .append(NameConverter.toColumnName(Constants.DEFAULT_COLUMN_PREFIX, property))
                    .append(" ").append(order).append(",")
                    .append(sql.substring(index));
        } else {
            buf.append(sql).append(" ").append(ORDER_BY).append(" ")
                    .append(NameConverter.toColumnName(Constants.DEFAULT_COLUMN_PREFIX, property))
                    .append(" ").append(order);
        }

        log.debug("Order SQL: {}", buf.toString());

        return buf.toString();
    }

}
