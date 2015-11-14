/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Description		:
 * <p/>
 * <br><br>Time		: 2015/5/22 16:29
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/common")
public class ErrorController {

    public static final String MESSAGE = "message";
    public static final String ERROR = "redirect:/common/error";

    @RequestMapping("/error")
    @ResponseBody
    public String error(HttpServletRequest request) {
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        String message = (String) map.get("message");
        return message;
    }

}
