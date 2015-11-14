/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.page;

import com.misuosi.mshop.common.test.TimeHolder;
import com.misuosi.mshop.db.Constants;
import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.builder.PagingSqlBuilder;
import com.misuosi.mshop.db.builder.SimpleSqlBuilder;
import com.misuosi.mshop.db.dao.SimpleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Description		: 分页数据访问对象
 * <p/>
 * <br><br>Time		: 2015/4/28 22:00
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
@Repository
public class PaginationDao<T extends Entity> extends SimpleDao {

    private static final Logger log = LoggerFactory.getLogger(PaginationDao.class);

    public PaginationDao(Class clazz) {
        super(clazz);
    }

    /**
     * 根据属性值查找实体列表（自动分页）
     *
     * @param property
     * @param value
     * @return
     */
    @Override
    public List<T> findByProperty(String property, Object value) {
        PaginationManager paginationManager = PaginationManager.instance();
        boolean needPaging = paginationManager.isNeedPaging();
        if (!needPaging) {
            return super.findByProperty(property, value);
        } else {
            String sql = SimpleSqlBuilder.getQuerySql(entityClass, property);
            int count = count(sql, value);

            int pageNo = paginationManager.getPageNo();
            int pageSize = paginationManager.getPageSize();

            String pagingSql = PagingSqlBuilder.getPagingSql(sql, pageNo, pageSize);
            log.debug(pagingSql);
            List<T> list = jdbcTemplate.query(pagingSql, entityMapper, value);

            HttpServletRequest request = paginationManager.getCurrentRequest();
            if (request != null) {
                request.setAttribute(Constants.PAGE_NO_KEY, pageNo);
                request.setAttribute(Constants.PAGE_SIZE_KEY, pageSize);
                request.setAttribute(Constants.TOTAL_COUNT_KEY, count);
            }

            paginationManager.clearAll();

            return list;
        }
    }

    /**
     * 查找实体列表（自动分页）
     *
     * @param sql
     * @param values
     * @return
     */
    @Override
    public List<T> find(String sql, Object... values) {
        PaginationManager paginationManager = PaginationManager.instance();
        boolean needPaging = paginationManager.isNeedPaging();
        if (!needPaging) {
            return super.find(sql, values);
        } else {
            int count = count(sql, values);

            int pageNo = paginationManager.getPageNo();
            int pageSize = paginationManager.getPageSize();

            String pagingSql = PagingSqlBuilder.getPagingSql(sql, pageNo, pageSize);
            log.debug(pagingSql);
            List<T> list = jdbcTemplate.query(pagingSql, entityMapper, values);

            HttpServletRequest request = paginationManager.getCurrentRequest();
            if (request != null) {
                request.setAttribute(Constants.PAGE_NO_KEY, pageNo);
                request.setAttribute(Constants.PAGE_SIZE_KEY, pageSize);
                request.setAttribute(Constants.TOTAL_COUNT_KEY, count);
            }

            paginationManager.clearAll();

            return list;
        }
    }

    /**
     * 查找所有记录（自动分页）
     *
     * @return
     */
    @Override
    public List<T> findAll() {
        PaginationManager paginationManager = PaginationManager.instance();
        boolean needPaging = paginationManager.isNeedPaging();
        if (!needPaging) {
            return super.findAll();
        } else {
            String sql = SimpleSqlBuilder.getQuerySql(entityClass);
            int count = count(sql);

            int pageNo = paginationManager.getPageNo();
            int pageSize = paginationManager.getPageSize();

            String pagingSql = PagingSqlBuilder.getPagingSql(sql, pageNo, pageSize);
            log.debug(pagingSql);
            List<T> list = jdbcTemplate.query(pagingSql, entityMapper);

            HttpServletRequest request = paginationManager.getCurrentRequest();
            if (request != null) {
                request.setAttribute(Constants.PAGE_NO_KEY, pageNo);
                request.setAttribute(Constants.PAGE_SIZE_KEY, pageSize);
                request.setAttribute(Constants.TOTAL_COUNT_KEY, count);
            }

            paginationManager.clearAll();

            return list;
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

        PaginationManager paginationManager = PaginationManager.instance();
        boolean needPaging = paginationManager.isNeedPaging();
        if (!needPaging) {

            TimeHolder.end();
            TimeHolder.trace("PaginationDao.queryForList");

            return super.queryForList(sql, values);
        } else {
            int count = count(sql, values);

            int pageNo = paginationManager.getPageNo();
            int pageSize = paginationManager.getPageSize();

            String pagingSql = PagingSqlBuilder.getPagingSql(sql, pageNo, pageSize);
            log.debug(pagingSql);
            List<Map<String, Object>> list = jdbcTemplate.queryForList(pagingSql, values);

            HttpServletRequest request = paginationManager.getCurrentRequest();
            if (request != null) {
                request.setAttribute(Constants.PAGE_NO_KEY, pageNo);
                request.setAttribute(Constants.PAGE_SIZE_KEY, pageSize);
                request.setAttribute(Constants.TOTAL_COUNT_KEY, count);
            }

            paginationManager.clearAll();

            TimeHolder.end();
            TimeHolder.trace("PaginationDao.queryForList");

            return list;
        }
    }

}
