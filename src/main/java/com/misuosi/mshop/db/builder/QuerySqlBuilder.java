/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.builder;

import com.misuosi.mshop.db.Constants;
import com.misuosi.mshop.db.NameConverter;
import com.misuosi.mshop.db.query.QueryItem;
import com.misuosi.mshop.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Description		: 查询sql语句构造器
 * <p/>
 * <br><br>Time		: 2015/5/4 12:06
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class QuerySqlBuilder {

    private static final Logger log = LoggerFactory.getLogger(QuerySqlBuilder.class);
    private static final String WHERE = "where";
    private static final String AND = "and";
    private static final String OR = "or";
    private static final String ORDER_BY = "order by";
    private static final String GROUP_BY = "group by";

    private QuerySqlBuilder() {
    }

    /**
     * 构造复杂查询sql
     *
     * @param sql
     * @param queryList
     * @return
     */
    public static String getQuerySql(String sql, List<QueryItem> queryList) {
        Assert.notNull(sql, "sql不能为空");
        Assert.notNull(queryList, "queryList不能为空");

        sql = StringUtils.trimSpace(sql.toLowerCase());

        if (queryList.isEmpty()) {
            return sql;
        }

        StringBuffer buf = new StringBuffer();

        int index = sql.indexOf(GROUP_BY);
        if (index < 0) {
            index = sql.indexOf(ORDER_BY);
            if (index < 0) {
                index = sql.length();
            } else {
                index -= 1;
            }
        } else {
            index -= 1;
        }

        buf.append(sql.substring(0, index));
        if (sql.contains(WHERE)) {
            buf.append(" ").append(AND).append(" ");
        } else {
            buf.append(" ").append(WHERE).append(" ");
        }

        for (QueryItem queryItem : queryList) {
            buf.append(parseQueryItem(queryItem));
            buf.append(AND).append(" ");
        }

        int len = AND.length() + 2;
        buf.delete(buf.length() - len, buf.length());
        buf.append(sql.substring(index));

        log.debug("Query SQL: {}", buf.toString());

        return buf.toString();
    }

    private static String parseQueryItem(QueryItem queryItem) {
        StringBuffer buf = new StringBuffer();
        if (queryItem.getAndItem() != null || queryItem.getOrItem() != null) {
            buf.append("(");
        }
        if (Constants.OPERATOR_LIKE.equals(queryItem.getOperator())) {
            buf.append(NameConverter.toColumnName(Constants.DEFAULT_COLUMN_PREFIX,
                    queryItem.getProperty()))
                    .append(" ").append(queryItem.getOperator())
                    .append(" '%").append(queryItem.getValue()).append("%' ");
        } else if (Constants.OPERATOR_IS.equals(queryItem.getOperator())) {
            buf.append(NameConverter.toColumnName(Constants.DEFAULT_COLUMN_PREFIX,
                    queryItem.getProperty()))
                    .append(" ").append(queryItem.getOperator())
                    .append(" ").append(queryItem.getValue()).append(" ");
        } else {
            if (Constants.QUERY_TYPE_STRING.equals(queryItem.getType())
                    || Constants.QUERY_TYPE_DATE.equals(queryItem.getType())) {
                buf.append(NameConverter.toColumnName(Constants.DEFAULT_COLUMN_PREFIX,
                        queryItem.getProperty()))
                        .append(queryItem.getOperator())
                        .append("'").append(queryItem.getValue()).append("' ");
            } else {
                buf.append(NameConverter.toColumnName(Constants.DEFAULT_COLUMN_PREFIX,
                        queryItem.getProperty()))
                        .append(queryItem.getOperator())
                        .append(queryItem.getValue()).append(" ");
            }
        }
        if (queryItem.getAndItem() != null || queryItem.getOrItem() != null) {
            if (queryItem.getAndItem() != null) {
                buf.append(AND).append(" ");
                buf.append(parseQueryItem(queryItem.getAndItem()));
            }
            if (queryItem.getOrItem() != null) {
                buf.append(OR).append(" ");
                buf.append(parseQueryItem(queryItem.getOrItem()));
            }
            buf.append(") ");
        }
        return buf.toString();
    }

}
