/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db;

import com.misuosi.mshop.db.annotation.*;
import com.misuosi.mshop.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Description		: 实体和数据表的联结器
 * <p/>
 * <br><br>Time		: 2015/4/13 8:39
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class Coupler<T extends Entity> {

    private static final Logger log = LoggerFactory.getLogger(Coupler.class);

    private Class<T> clazz;
    private String tableName;
    private String majorKeyName;
    private List<String> columnNames = new ArrayList<String>();
    private String entityName;
    private String majorFieldName;
    private String majorFieldGetterName;
    private String majorFieldSetterName;
    private List<String> fieldNames = new ArrayList<String>();
    private List<Class<?>> fieldTypes = new ArrayList<Class<?>>();
    private List<String> getterNames = new ArrayList<String>();
    private List<String> setterNames = new ArrayList<String>();
    private Map<String, Class<?>> fieldTypeMap = new HashMap<String, Class<?>>();

    private Map<String, OneToOne> oneToOneMap = new HashMap<String, OneToOne>();
    private Map<String, OneToMany> oneToManyMap = new HashMap<String, OneToMany>();
    private Map<String, ManyToOne> manyToOneMap = new HashMap<String, ManyToOne>();
    private Map<String, Orders> ordersMap = new HashMap<String, Orders>();

    public Coupler(Class<T> clazz) {
        this.clazz = clazz;
        this.entityName = clazz.getSimpleName();
        this.tableName = NameConverter.toTableName(Constants.DEFAULT_TABLE_PREFIX, entityName);

        Field fields[] = clazz.getDeclaredFields();
        for (Field field : fields) {
            Class<?> fieldType = field.getType();

            if (fieldType == Byte.TYPE || fieldType == Short.TYPE
                    || fieldType == Integer.TYPE || fieldType == Long.TYPE
                    || fieldType == Float.TYPE || fieldType == Double.TYPE
                    || fieldType == Character.TYPE || fieldType == Boolean.TYPE
                    || fieldType == Byte.class || fieldType == Short.class
                    || fieldType == Integer.class || fieldType == Long.class
                    || fieldType == Float.class || fieldType == Double.class
                    || fieldType == Character.class || fieldType == Boolean.class
                    || fieldType == String.class
                    || fieldType == Date.class || fieldType == Timestamp.class
                    || fieldType == BigDecimal.class) {

                Transient tran = field.getAnnotation(Transient.class);
                if (tran != null) {
                    continue; // 临时对象不写入数据库
                }

                String fieldName = field.getName();
                String fieldNameTemp = StringUtils.firstToUpperCase(fieldName);
                String getterName = "get".concat(fieldNameTemp);
                String setterName = "set".concat(fieldNameTemp);
                String columnName = NameConverter.toColumnName(Constants.DEFAULT_COLUMN_PREFIX, fieldName);

                this.columnNames.add(columnName);
                this.fieldNames.add(fieldName);
                this.fieldTypes.add(fieldType);
                this.getterNames.add(getterName);
                this.setterNames.add(setterName);
                this.fieldTypeMap.put(fieldName, fieldType);

                Id id = field.getAnnotation(Id.class);
                if (id != null) {
                    this.majorKeyName = columnName;
                    this.majorFieldName = fieldName;
                    this.majorFieldGetterName = getterName;
                    this.majorFieldSetterName = setterName;
                }
            } else {
                OneToOne oneToOne = field.getAnnotation(OneToOne.class);
                if (oneToOne != null) {
                    oneToOneMap.put(field.getName(), oneToOne);
                }
                OneToMany oneToMany = field.getAnnotation(OneToMany.class);
                if (oneToMany != null) {
                    oneToManyMap.put(field.getName(), oneToMany);
                }
                ManyToOne manyToOne = field.getAnnotation(ManyToOne.class);
                if (manyToOne != null) {
                    manyToOneMap.put(field.getName(), manyToOne);
                }
                Orders orders = field.getAnnotation(Orders.class);
                if (orders != null) {
                    ordersMap.put(field.getName(), orders);
                }
            }
        }
    }

    /**
     * 将实体解析成表字段键值对
     *
     * @param entity
     * @return
     */
    public Map<String, Object> parseEntity(Entity entity) {
        Map<String, Object> columnMap = new HashMap<String, Object>();
        try {
            Class<?> clazz = entity.getClass();
            for (int i = 0; i < columnNames.size(); i++) {
                Object value = clazz.getDeclaredMethod(getterNames.get(i)).invoke(entity);
                columnMap.put(columnNames.get(i), value);
            }
        } catch (Exception e) {
            log.error("Coupler.parseEntity throws Exception", e);
        }
        return columnMap;
    }

    /**
     * 将数据集解析成实体对象
     *
     * @param rs
     * @return
     */
    public T parseResultSet(ResultSet rs) {
        T entity = null;
        try {
            entity = clazz.newInstance();
            for (int i = 0; i < columnNames.size(); i++) {
                Object fieldValue = null;
                try {
                    fieldValue = rs.getObject(columnNames.get(i));
                } catch (SQLException e) {
                    //log.warn("Coupler.parseResultSet throws SQLException", e);
                    log.warn("Coupler.parseResultSet throws SQLException: {}", e.getMessage());
                }
                if (fieldValue == null) {
                    continue;
                }

                Method method = clazz.getDeclaredMethod(setterNames.get(i), fieldTypes.get(i));

                if (fieldTypes.get(i) == Byte.TYPE || fieldTypes.get(i) == Byte.class) {
                    Byte newValue = Byte.parseByte(String.valueOf(fieldValue));
                    method.invoke(entity, newValue);
                } else if (fieldTypes.get(i) == Short.TYPE || fieldTypes.get(i) == Short.class) {
                    Short newValue = Short.parseShort(String.valueOf(fieldValue));
                    method.invoke(entity, newValue);
                } else if (fieldTypes.get(i) == Double.TYPE || fieldTypes.get(i) == Double.class) {
                    Double newValue = Double.parseDouble(String.valueOf(fieldValue));
                    method.invoke(entity, newValue);
                } else {
                    method.invoke(entity, fieldValue);
                }
            }
        } catch (InstantiationException e) {
            log.error("Coupler.parseResultSet throws InstantiationException", e);
        } catch (IllegalAccessException e) {
            log.error("Coupler.parseResultSet throws IllegalAccessException", e);
        } catch (NoSuchMethodException e) {
            log.error("Coupler.parseResultSet throws NoSuchMethodException", e);
        } catch (InvocationTargetException e) {
            log.error("Coupler.parseResultSet throws InvocationTargetException", e);
        }
        return entity;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getMajorKeyName() {
        return majorKeyName;
    }

    public void setMajorKeyName(String majorKeyName) {
        this.majorKeyName = majorKeyName;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(List<String> columnNames) {
        this.columnNames = columnNames;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getMajorFieldName() {
        return majorFieldName;
    }

    public void setMajorFieldName(String majorFieldName) {
        this.majorFieldName = majorFieldName;
    }

    public String getMajorFieldGetterName() {
        return majorFieldGetterName;
    }

    public void setMajorFieldGetterName(String majorFieldGetterName) {
        this.majorFieldGetterName = majorFieldGetterName;
    }

    public String getMajorFieldSetterName() {
        return majorFieldSetterName;
    }

    public void setMajorFieldSetterName(String majorFieldSetterName) {
        this.majorFieldSetterName = majorFieldSetterName;
    }
    
    public List<String> getFieldNames() {
        return fieldNames;
    }

    public void setFieldNames(List<String> fieldNames) {
        this.fieldNames = fieldNames;
    }

    public List<Class<?>> getFieldTypes() {
        return fieldTypes;
    }

    public void setFieldTypes(List<Class<?>> fieldTypes) {
        this.fieldTypes = fieldTypes;
    }

    public List<String> getGetterNames() {
        return getterNames;
    }

    public void setGetterNames(List<String> getterNames) {
        this.getterNames = getterNames;
    }

    public List<String> getSetterNames() {
        return setterNames;
    }

    public void setSetterNames(List<String> setterNames) {
        this.setterNames = setterNames;
    }

    public Map<String, Class<?>> getFieldTypeMap() {
        return fieldTypeMap;
    }

    public void setFieldTypeMap(Map<String, Class<?>> fieldTypeMap) {
        this.fieldTypeMap = fieldTypeMap;
    }

    public Map<String, OneToOne> getOneToOneMap() {
        return oneToOneMap;
    }

    public void setOneToOneMap(Map<String, OneToOne> oneToOneMap) {
        this.oneToOneMap = oneToOneMap;
    }

    public Map<String, OneToMany> getOneToManyMap() {
        return oneToManyMap;
    }

    public void setOneToManyMap(Map<String, OneToMany> oneToManyMap) {
        this.oneToManyMap = oneToManyMap;
    }

    public Map<String, ManyToOne> getManyToOneMap() {
        return manyToOneMap;
    }

    public void setManyToOneMap(Map<String, ManyToOne> manyToOneMap) {
        this.manyToOneMap = manyToOneMap;
    }

    public Map<String, Orders> getOrdersMap() {
        return ordersMap;
    }

    public void setOrdersMap(Map<String, Orders> ordersMap) {
        this.ordersMap = ordersMap;
    }
    
}
