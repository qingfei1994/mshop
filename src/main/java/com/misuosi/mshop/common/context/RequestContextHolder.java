/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.common.context;

import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Description		: request上下文管理工具
 * <p/>
 * <br><br>Time		: 2015/4/15 20:02
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class RequestContextHolder {

    private static final String PAGING_PATH = "pagingPath";

    /**
     * 获取列表返回路径
     *
     * @return
     */
    public static String getPagingPath() {
        HttpServletRequest req = ((ServletRequestAttributes)
                org.springframework.web.context.request.RequestContextHolder.getRequestAttributes())
                .getRequest();
        String pagingPath = req.getParameter(PAGING_PATH);
        try {
            pagingPath = URLDecoder.decode(pagingPath, "UTF-8");
            pagingPath = "redirect:/".concat(pagingPath);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return pagingPath;
    }

    /**
     * 处理列表返回路径
     */
    public static void handlePagingPath() {
        HttpServletRequest req = ((ServletRequestAttributes)
                org.springframework.web.context.request.RequestContextHolder.getRequestAttributes())
                .getRequest();
        req.setAttribute(PAGING_PATH, req.getParameter(PAGING_PATH));
    }

}
