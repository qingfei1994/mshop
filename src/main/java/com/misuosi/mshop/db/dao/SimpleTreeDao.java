/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.dao;

import com.misuosi.mshop.db.*;
import com.misuosi.mshop.db.annotation.*;
import com.misuosi.mshop.db.builder.SimpleSqlBuilder;
import com.misuosi.mshop.db.page.Pagination;
import com.misuosi.mshop.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description		: 简单树形结构数据访问对象
 * <p/>
 * <br><br>Time		: 2015/6/1 9:01
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class SimpleTreeDao<T extends Entity> implements TreeDao<T> {

    private static final Logger log = LoggerFactory.getLogger(SimpleTreeDao.class);

    protected Class<T> entityClass;

    private Dao<T> dao;
    private TreeCoupler treeCoupler;
    private Coupler<T> coupler;
    private Map<String, Coupler<Entity>> childCouplers;
    private Map<String, OneToOne> oneToOneMap;
    private Map<String, OneToMany> oneToManyMap;
    private Map<String, ManyToOne> manyToOneMap;
    private Map<String, Orders> ordersMap;

    public SimpleTreeDao(Class<T> clazz) {
        this.entityClass = clazz;
        dao = DaoFactory.getDao(entityClass);
        treeCoupler = CouplerFactory.getTreeCoupler(entityClass);
        coupler = treeCoupler.getCoupler();
        childCouplers = treeCoupler.getChildCouplers();
        oneToOneMap = coupler.getOneToOneMap();
        oneToManyMap = coupler.getOneToManyMap();
        manyToOneMap = coupler.getManyToOneMap();
        ordersMap = coupler.getOrdersMap();
    }

    /**
     * 树形结构的数据查询
     *
     * @return
     */
    @Override
    public List<T> queryForTree() {
        String sql = SimpleSqlBuilder.getQuerySql(entityClass);
        log.debug(sql);
        return queryForTree(sql);
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
        List<T> list = dao.find(sql, values);
        if (list == null || list.isEmpty()) {
            return list;
        }

        if (childCouplers.isEmpty()) {
            return list;
        }

        try {
            Method majorMethod = entityClass.getDeclaredMethod(coupler.getMajorFieldGetterName());

            StringBuffer ids = new StringBuffer();
            int i = 0;
            for (Entity entity : list) {
                ids.append(majorMethod.invoke(entity));
                if (i++ < list.size() - 1) {
                    ids.append(",");
                }
            }

            for (String fieldName : oneToOneMap.keySet()) {
                OneToOne oneToOne = oneToOneMap.get(fieldName);
                String table = oneToOne.table();
                String refKey = oneToOne.foreignKey();
                parseReference(list, fieldName, majorMethod, table, refKey, ids);
            }
            for (String fieldName : oneToManyMap.keySet()) {
                OneToMany oneToMany = oneToManyMap.get(fieldName);
                String table = oneToMany.table();
                String refKey = oneToMany.foreignKey();
                parseReference(list, fieldName, majorMethod, table, refKey, ids);
            }
            for (String fieldName : manyToOneMap.keySet()) {
                ManyToOne manyToOne = manyToOneMap.get(fieldName);
                String table = manyToOne.table();
                String refKey = manyToOne.referenceKey();
                Method keyMethod = entityClass.getDeclaredMethod(
                        "get".concat(StringUtils.firstToUpperCase(refKey)));
                StringBuffer refIds = new StringBuffer();
                int j= 0;
                for (Entity entity : list) {
                	refIds.append(keyMethod.invoke(entity));
                    if (j++ < list.size() - 1) {
                    	refIds.append(",");
                    }
                }
                parseReference(list, fieldName, keyMethod, table, refKey, refIds);
            }
        } catch (NoSuchMethodException e) {
            log.error("SimpleTreeDao.queryForTree throws NoSuchMethodException", e);
        } catch (InvocationTargetException e) {
            log.error("SimpleTreeDao.queryForTree throws InvocationTargetException", e);
        } catch (IllegalAccessException e) {
            log.error("SimpleTreeDao.queryForTree throws IllegalAccessException", e);
        } catch (NoSuchFieldException e) {
            log.error("SimpleTreeDao.queryForTree throws NoSuchFieldException", e);
        }
        return list;
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
        throw new RuntimeException("IllegalAccessError: Can not call this method!");
    }

    /**
     * 解析数据关联关系
     *
     * @param list
     * @param fieldName
     * @param keyMethod
     * @param table
     * @param refKey
     * @param ids
     * @throws NoSuchFieldException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private void parseReference(List<T> list, String fieldName, Method keyMethod,
            String table, String refKey, StringBuffer ids) throws NoSuchFieldException,
            NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Coupler<Entity> childCoupler = childCouplers.get(fieldName);
        Class<Entity> childClass = childCoupler.getClazz();
        TreeDao<Entity> childDao = DaoFactory.getTreeDao(childClass);

        String childSql = buildChildQuerySql(table, refKey, ids, ordersMap, fieldName);

        if (entityClass == childClass) {
            int count = dao.count(childSql);
            if (count == 0) {
                return;
            }
        }

        List<Entity> childEntities = childDao.queryForTree(childSql);

        Class<?> fileType = entityClass.getDeclaredField(fieldName).getType();
        if (fileType == List.class) {
            setListValue(list, keyMethod, fieldName, childEntities, childClass, refKey);
        } else {
            setSingleValue(list, keyMethod, fieldName, childEntities, childClass, fileType, refKey);
        }
    }

    /**
     * 构造子查询sql
     *
     * @param table
     * @param refKey
     * @param ids
     * @param ordersMap
     * @param fieldName
     * @return
     */
    private String buildChildQuerySql(String table, String refKey, StringBuffer ids, Map<String, Orders> ordersMap,
            String fieldName) {
        StringBuffer childSql = new StringBuffer();
        childSql.append("select * from ")
                .append(NameConverter.toTableName(Constants.DEFAULT_TABLE_PREFIX, table))
                .append(" where ")
                .append(NameConverter.toColumnName(Constants.DEFAULT_COLUMN_PREFIX, refKey))
                .append(" in (");
        childSql.append(ids);
        childSql.append(")");

        Orders orders = ordersMap.get(fieldName);
        if (orders != null && orders.value().length > 0) {
            childSql.append(" order by ");
            for (Order order : orders.value()) {
                childSql.append(NameConverter.toColumnName(Constants.DEFAULT_COLUMN_PREFIX, order.key()))
                        .append(" ").append(order.order()).append(",");
            }
            childSql.deleteCharAt(childSql.length() - 1);
        }

        return childSql.toString();
    }

    /**
     * 将查询结构设置给对应的域（一对多）
     *
     * @param list
     * @param majorMethod
     * @param fieldName
     * @param childEntities
     * @param childClass
     * @param foreignKey
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void setListValue(List<T> list, Method majorMethod, String fieldName,
           List<Entity> childEntities, Class<Entity> childClass, String foreignKey)
           throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<Object, List<Entity>> childListMap = new HashMap<Object, List<Entity>>();

        for (Entity childEntity : childEntities) {
            Method foreignKeyMethod = childClass.getDeclaredMethod(
                    "get".concat(StringUtils.firstToUpperCase(foreignKey)));
            Object id = foreignKeyMethod.invoke(childEntity);

            List<Entity> childList = childListMap.get(id);
            if (childList == null) {
                childList = new ArrayList<Entity>();
                childListMap.put(id, childList);
            }
            childList.add(childEntity);
        }

        String listMethodName = "set".concat(StringUtils.firstToUpperCase(fieldName));
        for (T entity : list) {
            Object id = majorMethod.invoke(entity);
            List<Entity> childLis = childListMap.get(id);

            Method listMethod = entityClass.getDeclaredMethod(listMethodName, List.class);
            listMethod.invoke(entity, childLis);
        }
    }

    /**
     * 将查询结构设置给对应的域（一对一/多对一）
     * 
     * @param list
     * @param keyMethod
     * @param fieldName
     * @param childEntities
     * @param childClass
     * @param fileType
     * @param refKey
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private void setSingleValue(List<T> list, Method keyMethod, String fieldName, 
            List<Entity> childEntities, Class<Entity> childClass, Class<?> fileType, String refKey)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<Object, Entity> childMap = new HashMap<Object, Entity>();

        for (Entity childEntity : childEntities) {
            Method refKeyMethod = childClass.getDeclaredMethod(
                    "get".concat(StringUtils.firstToUpperCase(refKey)));
            Object id = refKeyMethod.invoke(childEntity);

            childMap.put(id, childEntity);
        }

        String fieldMethodName = "set".concat(StringUtils.firstToUpperCase(fieldName));
        for (T entity : list) {
            Object id = keyMethod.invoke(entity);
            Entity child = childMap.get(id);

            Method fieldMethod = entityClass.getDeclaredMethod(fieldMethodName, fileType);
            fieldMethod.invoke(entity, child);
        }
    }

}
