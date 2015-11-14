/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.builder;

import com.misuosi.mshop.db.*;
import com.misuosi.mshop.util.StringUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description		: sql语句构造器
 * <p/>
 * <br><br>Time		: 2015/4/7 15:05
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class SimpleSqlBuilder {

    private SimpleSqlBuilder() {
    }

    /**
     * 构造保存实体的sql和参数
     *
     * @param entity
     * @param saveWithId 是否保存ID
     * @return
     */
    public static BuildResult getSaveResult(Entity entity, boolean saveWithId) {
        Assert.notNull(entity, "实体对象不能为空");

        Coupler<?> coupler = CouplerFactory.getCoupler(entity.getClass());
        Map<String, Object> columnMap = coupler.parseEntity(entity);

        StringBuilder sqlBuilder = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        sqlBuilder.append("insert into ").append(coupler.getTableName()).append("(");
        StringBuilder values = new StringBuilder();
        values.append("values(");

        for (String key : columnMap.keySet()) {
            if (!coupler.getMajorKeyName().equals(key) || saveWithId) {
                Object value = columnMap.get(key);
                sqlBuilder.append(key);
                values.append("?");
                params.add(value);
                sqlBuilder.append(",");
                values.append(",");
            }
        }

        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        values.deleteCharAt(values.length() - 1);
        values.append(")");
        sqlBuilder.append(") ").append(values.toString());

        BuildResult buildResult = new BuildResult();
        buildResult.setSql(sqlBuilder.toString());
        buildResult.setParams(params);

        return buildResult;
    }

    /**
     * 构造保存实体的sql和参数
     *
     * @param entities
     * @param saveWithId
     * @return 是否保存ID
     */
    public static BuildResult getBatchSaveResult(List<Entity> entities, boolean saveWithId) {
        Assert.notNull(entities, "实体数组不能为空");

        if (entities.isEmpty()) {
            return null;
        }
        Entity entity0 = entities.get(0);

        Coupler<?> coupler = CouplerFactory.getCoupler(entity0.getClass());
        Map<String, Object> columnMap0 = coupler.parseEntity(entity0);

        StringBuilder sqlBuilder = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        sqlBuilder.append("insert into ").append(coupler.getTableName()).append("(");
        StringBuilder values = new StringBuilder();
        values.append("(");

        for (String key : columnMap0.keySet()) {
            if (!coupler.getMajorKeyName().equals(key) || saveWithId) {
                sqlBuilder.append(key);
                values.append("?");
                sqlBuilder.append(",");
                values.append(",");
            }
        }

        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        values.deleteCharAt(values.length() - 1);
        values.append(")");

        String valuesStr = values.toString();
        values.delete(0, values.length());

        for (Entity entity : entities) {
            values.append(valuesStr).append(",");
            Map<String, Object> columnMap = coupler.parseEntity(entity);
            for (String key : columnMap.keySet()) {
                if (!coupler.getMajorKeyName().equals(key) || saveWithId) {
                    Object value = columnMap.get(key);
                    params.add(value);
                }
            }
        }

        values.insert(0, "values");
        values.deleteCharAt(values.length() - 1);
        sqlBuilder.append(") ").append(values.toString());

        BuildResult buildResult = new BuildResult();
        buildResult.setSql(sqlBuilder.toString());
        buildResult.setParams(params);

        return buildResult;
    }

    /**
     * 构造更新实体的sql和参数
     *
     * @param entity
     * @param isDynamic 是否是动态更新（动态更新不更新值为空的域）
     * @return
     */
    public static BuildResult getUpdateResult(Entity entity, boolean isDynamic) {
        Assert.notNull(entity, "实体对象不能为空");

        Coupler<?> coupler = CouplerFactory.getCoupler(entity.getClass());
        Map<String, Object> columnMap = coupler.parseEntity(entity);

        StringBuilder sqlBuilder = new StringBuilder();
        List<Object> params = new ArrayList<Object>();
        sqlBuilder.append("update ").append(coupler.getTableName()).append(" set ");

        for (String key : columnMap.keySet()) {
            if (!coupler.getMajorKeyName().equals(key)) {
                Object value = columnMap.get(key);
                if (!isDynamic || value != null) {
                    sqlBuilder.append(key).append("=?");
                    params.add(value);
                    sqlBuilder.append(",");
                }
            }
        }

        sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);
        sqlBuilder.append(" where ").append(coupler.getMajorKeyName()).append("=?");
        params.add(columnMap.get(coupler.getMajorKeyName()));

        BuildResult buildResult = new BuildResult();
        buildResult.setSql(sqlBuilder.toString());
        buildResult.setParams(params);

        return buildResult;
    }

    /**
     * 构造查询实体的sql
     *
     * @param clazz
     * @return
     */
    public static String getQuerySql(Class<? extends Entity> clazz) {
        Assert.notNull(clazz, "class不能为空");

        Coupler<?> coupler = CouplerFactory.getCoupler(clazz);
        StringBuffer buf = new StringBuffer();
        buf.append("select * from ").append(coupler.getTableName()).append(" order by ")
                .append(coupler.getMajorKeyName()).append(" desc");
        return buf.toString();
    }

    /**
     * 构造查询实体的sql
     *
     * @param clazz
     * @param id
     * @return
     */
    public static String getQuerySql(Class<? extends Entity> clazz, Integer id) {
        Assert.notNull(clazz, "class不能为空");
        Assert.notNull(id, "ID不能为空");

        Coupler<?> coupler = CouplerFactory.getCoupler(clazz);
        StringBuffer buf = new StringBuffer();
        buf.append("select * from ").append(coupler.getTableName()).append(" where ")
                .append(coupler.getMajorKeyName()).append("=").append(id);
        return buf.toString();
    }

    /**
     * 构造查询实体的sql
     *
     * @param clazz
     * @param property
     * @return
     */
    public static String getQuerySql(Class<? extends Entity> clazz, String property) {
        Assert.notNull(clazz, "class不能为空");
        Assert.notNull(property, "property不能为空");

        Coupler<?> coupler = CouplerFactory.getCoupler(clazz);
        StringBuffer buf = new StringBuffer();
        buf.append("select * from ").append(coupler.getTableName()).append(" where ")
                .append(NameConverter.toColumnName(Constants.DEFAULT_COLUMN_PREFIX, property))
                .append("=? order by ").append(coupler.getMajorKeyName()).append(" desc");

        return buf.toString();
    }

    /**
     * 构造删除实体的sql
     *
     * @param clazz
     * @param id
     * @return
     */
    public static String getDeleteSql(Class<? extends Entity> clazz, Integer id) {
        Assert.notNull(clazz, "class不能为空");
        Assert.notNull(id, "ID不能为空");

        Coupler<?> coupler = CouplerFactory.getCoupler(clazz);
        StringBuffer buf = new StringBuffer();
        buf.append("delete from ").append(coupler.getTableName()).append(" where ")
                .append(coupler.getMajorKeyName()).append("=").append(id);
        return buf.toString();
    }

    /**
     * 构造计算总记录数的sql
     *
     * @param sql
     * @return
     */
    public static String getCountSql(String sql) {
        Assert.notNull(sql, "sql不能为空");

        sql = StringUtils.trimSpace(sql.toLowerCase());

        StringBuffer buf = new StringBuffer();
        buf.append("select count(*) from (").append(sql).append(") t");
        return buf.toString();
    }

    /**
     * 构造批量更新实体的sql
     *
     * @param property
     * @param ids
     * @return
     */
    public static String getBatchUpdateSql(Class<? extends Entity> clazz, String property, Integer[] ids) {
        Assert.notNull(clazz, "class不能为空");
        Assert.notNull(property, "property不能为空");
        Assert.notNull(ids, "ids不能为空");

        Coupler<?> coupler = CouplerFactory.getCoupler(clazz);
        StringBuffer buf = new StringBuffer();
        buf.append("update ").append(coupler.getTableName()).append(" set ")
                .append(NameConverter.toColumnName(Constants.DEFAULT_COLUMN_PREFIX, property))
                .append("=?");
        if (ids.length > 0) {
            buf.append(" where ").append(coupler.getMajorKeyName()).append(" in (");
            for (Integer id : ids) {
                buf.append(id).append(",");
            }
            buf.deleteCharAt(buf.length() - 1);
            buf.append(")");
        }
        return buf.toString();
    }

    /**
     * 构造批量删除实体的sql
     *
     * @param ids
     * @return
     */
    public static String getBatchDeleteSql(Class<? extends Entity> clazz, Integer[] ids) {
        Assert.notNull(clazz, "class不能为空");
        Assert.notNull(ids, "ids不能为空");

        Coupler<?> coupler = CouplerFactory.getCoupler(clazz);
        StringBuffer buf = new StringBuffer();
        buf.append("delete from ").append(coupler.getTableName());
        if (ids.length > 0) {
            buf.append(" where ").append(coupler.getMajorKeyName()).append(" in (");
            for (Integer id : ids) {
                buf.append(id).append(",");
            }
            buf.deleteCharAt(buf.length() - 1);
            buf.append(")");
        }
        return buf.toString();
    }

}
