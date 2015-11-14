/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.db.query;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description		: 查询拦截器
 * <p/>
 * <br><br>Time		: 2015/5/4 9:30
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class QueryIntercepter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        QueryManager queryManager = QueryManager.instance();
        queryManager.collectQueryItem(request);
        if (queryManager.isNeedQuery()) {
            queryManager.setCurrentRequest(request);
        } else {
            queryManager.setCurrentRequest(null);
        }
        return true;
    }

}
