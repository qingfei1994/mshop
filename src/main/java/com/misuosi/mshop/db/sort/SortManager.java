/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.sort;

import com.misuosi.mshop.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Description		: 排序管理器
 * <p/>
 * <br><br>Time		: 2015/4/29 21:00
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class SortManager {

    private static final ThreadLocal<String> property = new ThreadLocal<String>();
    private static final ThreadLocal<String> order = new ThreadLocal<String>();

    private static final ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<HttpServletRequest>();

    private static SortManager instance;

    private SortManager() {

    }

    public static SortManager instance() {
        if (instance == null) {
            instance = new SortManager();
        }
        return instance;
    }

    /**
     * 清空上次排序的残留参数
     */
    public void clearAll() {
        property.set(null);
        order.set(null);
        currentRequest.set(null);
    }

    public boolean isNeedSort() {
        if (!StringUtils.isBlank(property.get()) && !StringUtils.isBlank(order.get())) {
            return true;
        }
        return false;
    }

    public String getProperty() {
        return property.get();
    }

    public void setProperty(String property) {
        this.property.set(property);
    }

    public String getOrder() {
        return order.get();
    }

    public void setOrder(String order) {
        this.order.set(order);
    }

    public HttpServletRequest getCurrentRequest() {
        return currentRequest.get();
    }

    public void setCurrentRequest(HttpServletRequest currentRequest) {
        this.currentRequest.set(currentRequest);
    }

}
