/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.db.sort;

import com.misuosi.mshop.db.Constants;
import com.misuosi.mshop.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description		: 排序拦截器
 * <p/>
 * <br><br>Time		: 2015/4/29 20:35
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class SortIntercepter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        SortManager sortManager = SortManager.instance();
        // sort_key由字段名和排序方式（asc/desc）组成，如：age,asc
        String sortKey = request.getParameter(Constants.SORT_KEY);
        if (!StringUtils.isBlank(sortKey)) {
            String[] temp = sortKey.split(Constants.SORT_SEPARATOR);
            if (temp.length == 2) {
                sortManager.setProperty(temp[0]);
                sortManager.setOrder(temp[1]);
                sortManager.setCurrentRequest(request);
            }
        } else {
            sortManager.setProperty(null);
            sortManager.setOrder(null);
            sortManager.setCurrentRequest(null);
        }
        return true;
    }

}
