/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.dao;

import com.misuosi.mshop.common.test.TimeHolder;
import com.misuosi.mshop.db.*;
import com.misuosi.mshop.db.page.Pagination;
import com.misuosi.mshop.db.builder.BuildResult;
import com.misuosi.mshop.db.builder.PagingSqlBuilder;
import com.misuosi.mshop.db.builder.SimpleSqlBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Description		: 简单数据访问对象
 * <p/>
 * <br><br>Time		: 2015/4/7 11:48
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
@Repository
public class SimpleDao<T extends Entity> extends AbstractEventDao<T> implements MultiTableDao, TreeDao<T> {

    private static final Logger log = LoggerFactory.getLogger(SimpleDao.class);

    @Autowired
    protected JdbcTemplate jdbcTemplate;
    protected Class<T> entityClass;

    protected EntityMapper<T> entityMapper = new EntityMapper<T>();

    public SimpleDao(Class<T> clazz) {
        this.entityClass = clazz;
    }

    /**
     * 保存实体
     *
     * @param entity
     * @return
     */
    @Override
    protected int saveWithEvent(T entity) {
        BuildResult buildResult = SimpleSqlBuilder.getSaveResult(entity, false);
        final String sql = buildResult.getSql();
        final Object[] params = buildResult.getParams().toArray();
        log.debug(sql + "\t" + Arrays.deepToString(params));

        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rows = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
                return ps;
            }
        }, keyHolder);
        Coupler<T> coupler = CouplerFactory.getCoupler(entityClass);
        try {
            Method method = entityClass.getMethod(coupler.getMajorFieldSetterName(),
                    coupler.getFieldTypeMap().get(coupler.getMajorFieldName()));
            method.invoke(entity, keyHolder.getKey().intValue());
        } catch (NoSuchMethodException e) {
            log.error("SimpleDao.saveWithEvent throws NoSuchMethodException", e);
        } catch (InvocationTargetException e) {
            log.error("SimpleDao.saveWithEvent throws InvocationTargetException", e);
        } catch (IllegalAccessException e) {
            log.error("SimpleDao.saveWithEvent throws IllegalAccessException", e);
        }
        return rows;
    }

    /**
     * 保存实体，连同ID一起保存
     *
     * @param entity
     * @return
     */
    @Override
    protected int saveWithIdWithEvent(T entity) {
        BuildResult buildResult = SimpleSqlBuilder.getSaveResult(entity, true);
        log.debug(buildResult.getSql() + "\t" + Arrays.deepToString(buildResult.getParams().toArray()));
        int rows = jdbcTemplate.update(buildResult.getSql(), buildResult.getParams().toArray());
        return rows;
    }

    /**
     * 更新实体
     *
     * @param entity
     * @return
     */
    @Override
    protected int updateWithEvent(T entity) {
        BuildResult buildResult = SimpleSqlBuilder.getUpdateResult(entity, false);
        log.debug(buildResult.getSql() + "\t" + Arrays.deepToString(buildResult.getParams().toArray()));
        int rows = jdbcTemplate.update(buildResult.getSql(), buildResult.getParams().toArray());
        return rows;
    }

    /**
     * 动态更新实体，不更新值为空的域
     *
     * @param entity
     */
    @Override
    protected int dynamicUpdateWithEvent(T entity) {
        BuildResult buildResult = SimpleSqlBuilder.getUpdateResult(entity, true);
        log.debug(buildResult.getSql() + "\t" + Arrays.deepToString(buildResult.getParams().toArray()));
        int rows = jdbcTemplate.update(buildResult.getSql(), buildResult.getParams().toArray());
        return rows;
    }

    /**
     * 根据id删除实体
     *
     * @param id
     */
    @Override
    protected int deleteWithEvent(Integer id) {
        String sql = SimpleSqlBuilder.getDeleteSql(entityClass, id);
        log.debug(sql);
        int rows = jdbcTemplate.update(sql);
        return rows;
    }

    /**
     * 根据id查找实体
     *
     * @param id
     * @return
     */
    @Override
    public T get(Integer id) {
        String sql = SimpleSqlBuilder.getQuerySql(entityClass, id);
        log.debug(sql);
        T entity = null;
        try {
            entity = jdbcTemplate.queryForObject(sql, entityMapper);
        } catch (EmptyResultDataAccessException e) {
            //log.warn("SimpleDao.get throws EmptyResultDataAccessException", e);
            log.warn("SimpleDao.get throws EmptyResultDataAccessException");
        }
        return entity;
    }

    /**
     * 根据属性值查找唯一实体
     *
     * @param property
     * @param value
     * @return
     */
    @Override
    public T findUniqueByProperty(String property, Object value) {
        String sql = SimpleSqlBuilder.getQuerySql(entityClass, property);
        log.debug(sql + "\t[" + value + "]");
        T entity = null;
        try {
            entity = jdbcTemplate.queryForObject(sql, entityMapper, value);
        } catch(EmptyResultDataAccessException e) {
            //log.warn("SimpleDao.findUniqueByProperty throws EmptyResultDataAccessException", e);
            log.warn("SimpleDao.findUniqueByProperty throws EmptyResultDataAccessException");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * 查找唯一实体
     *
     * @param sql
     * @param values
     * @return
     */
    @Override
    public T findUnique(String sql, Object... values) {
        log.debug(sql + "\t" + Arrays.deepToString(values));
        T entity = null;
        try {
            entity = jdbcTemplate.queryForObject(sql, entityMapper, values);
        } catch(EmptyResultDataAccessException e) {
            //log.warn("SimpleDao.findUnique throws EmptyResultDataAccessException", e);
            log.warn("SimpleDao.findUnique throws EmptyResultDataAccessException");
        }
        return entity;
    }

    /**
     * 根据属性值查找实体列表
     *
     * @param property
     * @param value
     * @return
     */
    @Override
    public List<T> findByProperty(String property, Object value) {
        String sql = SimpleSqlBuilder.getQuerySql(entityClass, property);
        log.debug(sql + "\t[" + value + "]");
        return jdbcTemplate.query(sql, entityMapper, value);
    }

    /**
     * 查找实体列表
     *
     * @param sql
     * @param values
     * @return
     */
    @Override
    public List<T> find(String sql, Object... values) {
        log.debug(sql + "\t" + Arrays.deepToString(values));
        return jdbcTemplate.query(sql, entityMapper, values);
    }

    /**
     * 查找所有记录
     *
     * @return
     */
    @Override
    public List<T> findAll() {
        String sql = SimpleSqlBuilder.getQuerySql(entityClass);
        log.debug(sql);
        return jdbcTemplate.query(sql, entityMapper);
    }

    /**
     * 查找分页对象
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Pagination<T> findPagination(int pageNo, int pageSize) {
        String countSql = SimpleSqlBuilder.getQuerySql(entityClass);
        int count = count(countSql);

        String sql = PagingSqlBuilder.getPagingSql(entityClass, pageNo, pageSize);
        log.debug(sql);
        List<T> list = jdbcTemplate.query(sql, entityMapper);

        return new Pagination<T>(pageNo, pageSize, count, list);
    }

    /**
     * 查找分页对象
     *
     * @param sql
     * @param pageNo
     * @param pageSize
     * @param values
     * @return
     */
    @Override
    public Pagination<T> findPagination(String sql, int pageNo, int pageSize, Object... values) {
        int count = count(sql, values);

        String pagingSql = PagingSqlBuilder.getPagingSql(sql, pageNo, pageSize);
        log.debug(pagingSql + "\t" + Arrays.deepToString(values));
        List<T> list = jdbcTemplate.query(pagingSql, entityMapper, values);

        return new Pagination<T>(pageNo, pageSize, count, list);
    }

    /**
     * 计算记录总数
     *
     * @param sql
     * @param values
     * @return
     */
    @Override
    public int count(String sql, Object... values) {
        String countSql = SimpleSqlBuilder.getCountSql(sql);
        log.debug(countSql + "\t" + Arrays.deepToString(values));
        int count = jdbcTemplate.queryForObject(countSql, Integer.class, values);
        return count;
    }

    /**
     * 批量保存实体
     *
     * @param entities
     * @return
     */
    @Override
    public int batchSave(List<T> entities) {
        BuildResult buildResult = SimpleSqlBuilder.getBatchSaveResult((List<Entity>) entities, false);
        if (buildResult == null) {
            return 0;
        }
        log.debug(buildResult.getSql() + "\t" + Arrays.deepToString(buildResult.getParams().toArray()));
        int rows = jdbcTemplate.update(buildResult.getSql(), buildResult.getParams().toArray());
        return rows;
    }

    /**
     * 批量保存实体，连同ID一起保存
     *
     * @param entities
     * @return
     */
    @Override
    public int batchSaveWithId(List<T> entities) {
        BuildResult buildResult = SimpleSqlBuilder.getBatchSaveResult((List<Entity>) entities, true);
        if (buildResult == null) {
            return 0;
        }
        log.debug(buildResult.getSql() + "\t" + Arrays.deepToString(buildResult.getParams().toArray()));
        int rows = jdbcTemplate.update(buildResult.getSql(), buildResult.getParams().toArray());
        return rows;
    }

    /**
     * 批量保存实体
     *
     * @param sql
     * @param values
     * @return
     */
    @Override
    public int batchSave(String sql, Object... values) {
        log.debug(sql + "\t" + Arrays.deepToString(values));
        int rows = jdbcTemplate.update(sql, values);
        return rows;
    }

    /**
     * 批量更新实体
     *
     * @param entities
     * @return
     */
    @Override
    public int batchUpdate(List<T> entities) {
        BuildResult buildResult0 = SimpleSqlBuilder.getUpdateResult(entities.get(0), false);
        String sql = buildResult0.getSql();
        List<Object[]> list = new ArrayList<Object[]>();

        for (T entity : entities) {
            BuildResult buildResult = SimpleSqlBuilder.getUpdateResult(entity, false);
            list.add(buildResult.getParams().toArray());
        }

        log.debug(sql + "\t" + Arrays.deepToString(list.toArray()));

        int[] rows = jdbcTemplate.batchUpdate(sql, list);
        int rowsCount = 0;
        for (int i : rows) {
            rowsCount += i;
        }
        return rowsCount;
    }

    /**
     * 批量动态更新实体，不更新值为空的域
     *
     * @param entities
     * @return
     */
    @Override
    public int batchDynamicUpdate(List<T> entities) {
        int rows = 0;
        for (T entity : entities) {
            rows += dynamicUpdateWithEvent(entity);
        }
        return rows;
    }

    /**
     * 根据属性键值对和主键数组批量更新实体
     *
     * @param property
     * @param value
     * @param ids
     */
    @Override
    public int batchUpdate(String property, Object value, Integer[] ids) {
        String sql = SimpleSqlBuilder.getBatchUpdateSql(entityClass, property, ids);
        log.debug(sql + "\t[" + value + "]");
        int rows = jdbcTemplate.update(sql, value);
        return rows;
    }

    /**
     * 批量更新实体
     *
     * @param sql
     * @param values
     */
    @Override
    public int batchUpdate(String sql, Object... values) {
        log.debug(sql + "\t" + Arrays.deepToString(values));
        int rows = jdbcTemplate.update(sql, values);
        return rows;
    }

    /**
     * 根据主键数组批量删除实体
     *
     * @param ids
     */
    @Override
    public int batchDelete(Integer[] ids) {
        String sql = SimpleSqlBuilder.getBatchDeleteSql(entityClass, ids);
        log.debug(sql);
        int rows = jdbcTemplate.update(sql);
        return rows;
    }

    /**
     * 批量删除实体
     *
     * @param sql
     * @param values
     */
    @Override
    public int batchDelete(String sql, Object... values) {
        log.debug(sql + "\t" + Arrays.deepToString(values));
        int rows = jdbcTemplate.update(sql, values);
        return rows;
    }

    /**
     * 多表查询（返回单列数据）
     *
     * @param sql
     * @param values
     * @return
     */
    @Override
    public Map<String, Object> queryForMap(String sql, Object... values) {
        Map<String, Object> map = null;
        try {
            log.debug(sql + "\t" + Arrays.deepToString(values));
            List<Map<String, Object>> results = jdbcTemplate.query(sql, values,
                    new RowMapperResultSetExtractor<Map<String, Object>>(new ColumnMapRowMapper(), 1));
            map = DataAccessUtils.uniqueResult(results);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return map;
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

        List<Map<String, Object>> list = null;
        try {
            log.debug(sql + "\t" + Arrays.deepToString(values));
            list = jdbcTemplate.queryForList(sql, values);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TimeHolder.end();
        TimeHolder.trace("SimpleDao.queryForList");

        return list;
    }

    /**
     * 多表查询（返回多列数据）
     *
     * @param sql
     * @param pageNo
     * @param pageSize
     * @param values
     * @return
     */
    @Override
    public Pagination<Map<String, Object>> queryForPagination(String sql,  int pageNo, int pageSize,
            Object... values) {
        int count = count(sql, values);

        String pagingSql = PagingSqlBuilder.getPagingSql(sql, pageNo, pageSize);
        log.debug(pagingSql + "\t" + Arrays.deepToString(values));
        List<Map<String, Object>> list = jdbcTemplate.queryForList(pagingSql, values);

        return new Pagination<Map<String, Object>>(pageNo, pageSize, count, list);
    }

    /**
     * 树形结构的数据查询
     *
     * @return
     */
    @Override
    public List<T> queryForTree() {
        TreeDao<T> treeDao = DaoFactory.getTreeDao(entityClass);
        return treeDao.queryForTree();
    }

    /**
     * 树形结构的数据查询
     *
     * @param sql
     * @param values
     * @return
     */
    @Override
    public List<T> queryForTree(String sql, Object... values) {
        TreeDao<T> treeDao = DaoFactory.getTreeDao(entityClass);
        return treeDao.queryForTree(sql, values);
    }

    /**
     * 树形结构的分页数据查询
     *
     * @param sql
     * @param pageNo
     * @param pageSize
     * @param values
     * @return
     */
    @Override
    public Pagination<T> queryForPaginationTree(String sql, int pageNo, int pageSize, Object... values) {
        int count = count(sql, values);

        String pagingSql = PagingSqlBuilder.getPagingSql(sql, pageNo, pageSize);
        List<T> list = queryForTree(pagingSql, values);

        return new Pagination<T>(pageNo, pageSize, count, list);
    }

    /**
     * 将数据集解析成实体对象
     *
     * @param <T>
     */
    private class EntityMapper<T extends Entity> implements RowMapper<T> {
        @Override
        public T mapRow(ResultSet rs, int rowNum) throws SQLException {
            Coupler<T> coupler = (Coupler<T>) CouplerFactory.getCoupler(entityClass);
            T entity = coupler.parseResultSet(rs);
            return entity;
        }
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
