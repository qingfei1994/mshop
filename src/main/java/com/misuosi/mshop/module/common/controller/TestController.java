/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.common.controller;

import javax.servlet.http.HttpServletRequest;

import com.misuosi.mshop.db.query.QueryItem;
import com.misuosi.mshop.db.query.QueryManager;
import com.misuosi.mshop.module.common.manager.*;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description		: 测试用
 * <p/>
 * <br><br>Time		: 2015/4/23 18:18
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */

@Controller
public class TestController {
    
    @RequestMapping("/test/index")
    public String index(HttpServletRequest request, Model model) throws Exception {
        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
    	//测试错误页面
        return "/admin/test";
    }
    
    @RequestMapping("/index1")
    public String index1(Model model, HttpServletRequest request, String q_eq_n_goodPutawayType) {
        System.out.println(request.getRequestURL());
        System.out.println(request.getServerName());

        QueryItem queryItem = QueryManager.instance().parseQueryItem("q_eq_n_goodPutawayType",
                q_eq_n_goodPutawayType);

    	//测试错误页面
    	return "/wap/usercenter/user_center";
    }

    @RequestMapping("/refresh")
    @ResponseBody
    public String refresh() {
        GoodsClassificationManager.refresh();
        GoodsLabelManager.refresh();
        TransportationExpensesTemplateManager.refresh();
        return "success";
    }

}
