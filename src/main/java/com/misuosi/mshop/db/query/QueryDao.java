/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.query;

import com.misuosi.mshop.common.test.TimeHolder;
import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.builder.QuerySqlBuilder;
import com.misuosi.mshop.db.builder.SimpleSqlBuilder;
import com.misuosi.mshop.db.sort.SortDao;

import java.util.List;
import java.util.Map;

/**
 * Description		: 查询数据访问对象
 * <p/>
 * <br><br>Time		: 2015/5/4 9:28
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class QueryDao<T extends Entity> extends SortDao {

    public QueryDao(Class clazz) {
        super(clazz);
    }

    /**
     * 根据属性值查找实体列表（自动构建查询）
     *
     * @param property
     * @param value
     * @return
     */
    @Override
    public List<T> findByProperty(String property, Object value) {
        QueryManager queryManager = QueryManager.instance();
        boolean needQuery = queryManager.isNeedQuery();
        if (!needQuery) {
            return super.findByProperty(property, value);
        } else {
            List<QueryItem> queryList = queryManager.getQueryList();

            String sql = SimpleSqlBuilder.getQuerySql(entityClass, property);
            String querySql = QuerySqlBuilder.getQuerySql(sql, queryList);

            queryManager.sendBackQuery();

            return super.find(querySql, value);
        }
    }

    /**
     * 查找实体列表（自动构建查询）
     *
     * @param sql
     * @param values
     * @return
     */
    @Override
    public List<T> find(String sql, Object... values) {
        QueryManager queryManager = QueryManager.instance();
        boolean needQuery = queryManager.isNeedQuery();
        if (!needQuery) {
            return super.find(sql, values);
        } else {
            List<QueryItem> queryList = queryManager.getQueryList();

            String querySql = QuerySqlBuilder.getQuerySql(sql, queryList);

            queryManager.sendBackQuery();

            return super.find(querySql, values);
        }
    }

    /**
     * 查找所有记录（自动构建查询）
     *
     * @return
     */
    @Override
    public List<T> findAll() {
        QueryManager queryManager = QueryManager.instance();
        boolean needQuery = queryManager.isNeedQuery();
        if (!needQuery) {
            return super.findAll();
        } else {
            List<QueryItem> queryList = queryManager.getQueryList();

            String sql = SimpleSqlBuilder.getQuerySql(entityClass);
            String querySql = QuerySqlBuilder.getQuerySql(sql, queryList);

            queryManager.sendBackQuery();

            return super.find(querySql);
        }
    }

    /**
     * 多表查询（返回多列数据）
     *
     * @param sql
     * @param values
     * @return
     */
    @Override
    public List<Map<String, Object>> queryForList(String sql, Object... values) {
        TimeHolder.start();

        QueryManager queryManager = QueryManager.instance();
        boolean needQuery = queryManager.isNeedQuery();
        if (!needQuery) {

            TimeHolder.end();
            TimeHolder.trace("QueryDao.queryForList");

            return super.queryForList(sql, values);
        } else {
            List<QueryItem> queryList = queryManager.getQueryList();

            String querySql = QuerySqlBuilder.getQuerySql(sql, queryList);

            queryManager.sendBackQuery();

            TimeHolder.end();
            TimeHolder.trace("QueryDao.queryForList");

            return super.queryForList(querySql, values);
        }
    }

}
