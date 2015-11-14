/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.wx.controller;

import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.servlet.WeixinSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * Description		:
 * 微信公众平台交互操作基类，提供几乎所有微信公众平台交互方式
 * 基于springmvc框架，方便使用此框架的项目集成
 * <br><br>Time		: 2015/4/25 1:38
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
@Controller
public abstract class CustomWeixinControllerSupport extends WeixinSupport {

    /**
     * 绑定微信服务器
     *
     * @param request 请求
     * @return 响应内容
     */
    @RequestMapping(method = RequestMethod.GET)
    protected final void oauth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (isLegal(request)) {
            //绑定微信服务器成功
            String echostr = request.getParameter("echostr");
            ServletOutputStream outputStream = response.getOutputStream();
            outputStreamWrite(outputStream, echostr);
        } else {
            //绑定微信服务器失败
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    protected final String processMessage(HttpServletRequest request) throws ServletException, IOException {
        if (!isLegal(request)) {
            return "";
        }
        return processRequest(request);
    }

    @Override
    protected BaseMsg handleSubscribe(BaseEvent event) {
        return null;
    }

    /**
     * 数据流输出
     *
     * @param outputStream
     * @param text
     * @return
     */
    private final boolean outputStreamWrite(OutputStream outputStream, String text) {
        try {
            outputStream.write(text.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
