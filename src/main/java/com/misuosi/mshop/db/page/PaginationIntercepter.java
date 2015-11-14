/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.db.page;

import com.misuosi.mshop.db.Constants;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description		: 分页拦截器
 * <p/>
 * <br><br>Time		: 2015/4/15 20:35
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class PaginationIntercepter extends HandlerInterceptorAdapter {

    private boolean needPaging;
    private Integer maxPageSize;
    private Integer defaultPageSize;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        PaginationManager paginationManager = PaginationManager.instance();
        if (needPaging) {
            paginationManager.setMaxPageSize(maxPageSize);
            paginationManager.setDefaultPageSize(defaultPageSize);

            paginationManager.setPageNo(request.getParameter(Constants.PAGE_NO_KEY));
            paginationManager.setPageSize(request.getParameter(Constants.PAGE_SIZE_KEY));
            paginationManager.setCurrentRequest(request);
        } else {
            paginationManager.setPageNo(null);
            paginationManager.setPageSize(null);
            paginationManager.setCurrentRequest(null);
        }

        paginationManager.setNeedPaging(needPaging);
        request.setAttribute(Constants.NEED_PAGING_KEY, needPaging);

        return true;
    }

    public void setNeedPaging(boolean needPaging) {
        this.needPaging = needPaging;
    }

    public void setMaxPageSize(Integer maxPageSize) {
        this.maxPageSize = maxPageSize;
    }

    public void setDefaultPageSize(Integer defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
    }

}
