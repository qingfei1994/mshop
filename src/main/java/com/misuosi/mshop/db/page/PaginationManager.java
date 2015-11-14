/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.page;

import com.misuosi.mshop.db.Constants;
import com.misuosi.mshop.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Description		: 分页管理器
 * <p/>
 * <br><br>Time		: 2015/4/28 21:00
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class PaginationManager {

    private static final ThreadLocal<Boolean> needPaging = new ThreadLocal<Boolean>();
    private static final ThreadLocal<Integer> maxPageSize = new ThreadLocal<Integer>();
    private static final ThreadLocal<Integer> defaultPageSize = new ThreadLocal<Integer>();

    private static final ThreadLocal<Integer> pageNo = new ThreadLocal<Integer>();
    private static final ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();

    private static final ThreadLocal<HttpServletRequest> currentRequest = new ThreadLocal<HttpServletRequest>();

    private static PaginationManager instance;

    private PaginationManager() {

    }

    public static PaginationManager instance() {
        if (instance == null) {
            instance = new PaginationManager();
        }
        return instance;
    }

    /**
     * 清空上次分页的残留参数
     */
    public void clearAll() {
        needPaging.set(null);
        maxPageSize.set(null);
        defaultPageSize.set(null);
        pageNo.set(null);
        pageSize.set(null);
        currentRequest.set(null);
    }

    public boolean isNeedPaging() {
        return StringUtils.booleanValue(needPaging.get(), false);
    }

    public void setNeedPaging(boolean needPaging) {
        this.needPaging.set(needPaging);
    }

    public int getMaxPageSize() {
        return StringUtils.intValue(maxPageSize.get(), Constants.MAX_PAGE_SIZE);
    }

    public void setMaxPageSize(Integer maxPageSize) {
        this.maxPageSize.set(maxPageSize);
    }

    public int getDefaultPageSize() {
        return StringUtils.intValue(defaultPageSize.get(), Constants.DEFAULT_PAGE_SIZE);
    }

    public void setDefaultPageSize(Integer defaultPageSize) {
        this.defaultPageSize.set(defaultPageSize);
    }

    public int getPageNo() {
        return StringUtils.intValue(pageNo.get(), Constants.MIN_PAGE_NO);
    }

    public void setPageNo(Object pageNoObj) {
        int pageNo = StringUtils.intValue(pageNoObj, Constants.MIN_PAGE_NO);
        pageNo = pageNo < Constants.MIN_PAGE_NO ? Constants.MIN_PAGE_NO : pageNo;
        this.pageNo.set(pageNo);
    }

    public int getPageSize() {
        return StringUtils.intValue(pageSize.get(), getDefaultPageSize());
    }

    public void setPageSize(Object pageSizeObj) {
        int pageSize = StringUtils.intValue(pageSizeObj, getDefaultPageSize());
        pageSize = pageSize < Constants.MIN_PAGE_SIZE ? getDefaultPageSize()
                : pageSize > getMaxPageSize() ? getMaxPageSize() : pageSize;
        this.pageSize.set(pageSize);
    }

    public HttpServletRequest getCurrentRequest() {
        return currentRequest.get();
    }

    public void setCurrentRequest(HttpServletRequest currentRequest) {
        this.currentRequest.set(currentRequest);
    }

}
