/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.common.controller;

import com.misuosi.mshop.util.DateUtils;
import com.misuosi.mshop.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Description	 :
 * 首页的内容<br>
 * PS:因为三类角色均使用，暂时存放于common包
 * <br><br>Time	 : 2015/6/1 10:43
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/common")
public class IndexContentController {

    @RequestMapping("/index/content")
    public String indexContent(Model model, HttpServletRequest request) {
        model.addAttribute("week", DateUtils.getWeek());
        model.addAttribute("date", DateUtils.getDate("yyyy年MM月dd日"));

        model.addAttribute("javaVersion", System.getProperty("java.version")); // 获取JAVA版本
        model.addAttribute("osName", System.getProperty("os.name")); // 获取系统名称
        model.addAttribute("osArch", System.getProperty("os.arch")); // 获取系统构架
        model.addAttribute("osVersion", System.getProperty("os.version")); // 获取系统版本
        model.addAttribute("serverInfo", StringUtils.substring(
                request.getServletContext().getServerInfo(), 0, 30)); // 获取Server信息
        model.addAttribute("loginIP", request.getRemoteAddr()); // 获取登录IP地址
        model.addAttribute("servletVersion", request.getServletContext().getMajorVersion() + "."
                + request.getServletContext().getMinorVersion()); // 获取Servlet版本
        return "/common/index/content";
    }
}