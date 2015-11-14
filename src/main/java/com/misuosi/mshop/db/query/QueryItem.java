/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.query;

/**
 * Description		: 查询子项
 * <p/>
 * <br><br>Time		: 2015/5/4 10:46
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class QueryItem {

    private String queryKey;
    private String property;
    private String value;
    private String type;
    private String operator;
    private QueryItem orItem;
    private QueryItem andItem;

    public String getQueryKey() {
        return queryKey;
    }

    public void setQueryKey(String queryKey) {
        this.queryKey = queryKey;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public QueryItem getOrItem() {
        return orItem;
    }

    public void setOrItem(QueryItem orItem) {
        this.orItem = orItem;
    }

    public QueryItem getAndItem() {
        return andItem;
    }

    public void setAndItem(QueryItem andItem) {
        this.andItem = andItem;
    }

}
