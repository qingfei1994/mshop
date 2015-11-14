/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.sort;

import com.misuosi.mshop.common.test.TimeHolder;
import com.misuosi.mshop.db.Constants;
import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.builder.SimpleSqlBuilder;
import com.misuosi.mshop.db.builder.SortSqlBuilder;
import com.misuosi.mshop.db.page.PaginationDao;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Description		: 排序数据访问对象
 * <p/>
 * <br><br>Time		: 2015/4/29 22:00
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
@Repository
public class SortDao<T extends Entity> extends PaginationDao {

    public SortDao(Class clazz) {
        super(clazz);
    }

    /**
     * 根据属性值查找实体列表（自动排序）
     *
     * @param property
     * @param value
     * @return
     */
    @Override
    public List<T> findByProperty(String property, Object value) {
        SortManager sortManager = SortManager.instance();
        boolean needSort = sortManager.isNeedSort();
        if (!needSort) {
            return super.findByProperty(property, value);
        } else {
            String sortProperty = sortManager.getProperty();
            String sortOrder = sortManager.getOrder();

            String sql = SimpleSqlBuilder.getQuerySql(entityClass, property);
            String sortSql = SortSqlBuilder.getSortSql(sql, sortProperty, sortOrder);

            HttpServletRequest request = sortManager.getCurrentRequest();
            if (request != null) {
                request.setAttribute(Constants.SORT_KEY,
                        property.concat(Constants.SORT_SEPARATOR).concat(sortOrder));
            }

            sortManager.clearAll();

            return super.find(sortSql, value);
        }
    }

    /**
     * 查找实体列表（自动排序）
     *
     * @param sql
     * @param values
     * @return
     */
    @Override
    public List<T> find(String sql, Object... values) {
        SortManager sortManager = SortManager.instance();
        boolean needSort = sortManager.isNeedSort();
        if (!needSort) {
            return super.find(sql, values);
        } else {
            String sortProperty = sortManager.getProperty();
            String sortOrder = sortManager.getOrder();

            String sortSql = SortSqlBuilder.getSortSql(sql, sortProperty, sortOrder);

            HttpServletRequest request = sortManager.getCurrentRequest();
            if (request != null) {
                request.setAttribute(Constants.SORT_KEY,
                        sortProperty.concat(Constants.SORT_SEPARATOR).concat(sortOrder));
            }

            sortManager.clearAll();

            return super.find(sortSql, values);
        }
    }

    /**
     * 查找所有记录（自动排序）
     *
     * @return
     */
    @Override
    public List<T> findAll() {
        SortManager sortManager = SortManager.instance();
        boolean needSort = sortManager.isNeedSort();
        if (!needSort) {
            return super.findAll();
        } else {
            String sortProperty = sortManager.getProperty();
            String sortOrder = sortManager.getOrder();

            String sql = SimpleSqlBuilder.getQuerySql(entityClass);
            String sortSql = SortSqlBuilder.getSortSql(sql, sortProperty, sortOrder);

            HttpServletRequest request = sortManager.getCurrentRequest();
            if (request != null) {
                request.setAttribute(Constants.SORT_KEY,
                        sortProperty.concat(Constants.SORT_SEPARATOR).concat(sortOrder));
            }

            sortManager.clearAll();

            return super.find(sortSql);
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

        SortManager sortManager = SortManager.instance();
        boolean needSort = sortManager.isNeedSort();
        if (!needSort) {

            TimeHolder.end();
            TimeHolder.trace("SortDao.queryForList");

            return super.queryForList(sql, values);
        } else {
            String sortProperty = sortManager.getProperty();
            String sortOrder = sortManager.getOrder();

            String sortSql = SortSqlBuilder.getSortSql(sql, sortProperty, sortOrder);

            HttpServletRequest request = sortManager.getCurrentRequest();
            if (request != null) {
                request.setAttribute(Constants.SORT_KEY,
                        sortProperty.concat(Constants.SORT_SEPARATOR).concat(sortOrder));
            }

            sortManager.clearAll();

            TimeHolder.end();
            TimeHolder.trace("SortDao.queryForList");

            return super.queryForList(sortSql, values);
        }
    }

}
