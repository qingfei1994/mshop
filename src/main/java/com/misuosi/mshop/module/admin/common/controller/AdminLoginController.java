/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.common.controller;

import com.misuosi.mshop.common.constants.UserConstants;
import com.misuosi.mshop.common.context.SessionContextHolder;
import com.misuosi.mshop.entity.Permission;
import com.misuosi.mshop.service.PermissionService;
import com.misuosi.mshop.util.DateUtils;
import com.misuosi.mshop.util.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description		: 管理员登录
 * <p/>
 * <br><br>Time		: 2015/4/14 19:34
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping({"", "/", "/index"})
    public String indexPage(Model model, HttpServletRequest request) {
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

        int adminId = SessionContextHolder.getAdminId();
        int root = UserConstants.ADMINISTRATOR_TYPE;
        List<Permission> permissionList = permissionService.getAdminAvailableSubPermissions(adminId, root);
        model.addAttribute("permissionList", permissionList);
        return "/admin/index";
    }

    @RequestMapping("/login")
    public String loginPage(HttpServletRequest request, Model model) {

        // 判断是否登录
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            return "redirect:/admin/index";
        }

        String exceptionClassName = (String) request.getAttribute("shiroLoginFailure");
        String error = null;
        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (AuthenticationException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if (exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        return "admin/login";
    }

}
