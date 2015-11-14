/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.wap.payment.controller;

import com.github.cuter44.wxpay.servlet.WxpayNotifyGatewayServlet;
import com.misuosi.mshop.entity.OrderInformation;
import com.misuosi.mshop.module.wap.payment.service.WapPaymentService;
import com.misuosi.mshop.service.OrderInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ServletConfigAware;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Description	 :
 * <br><br>Time	 : 2015/6/17 16:15
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/wap/payment")
public class WapPaymentController implements ServletConfigAware {

    @Autowired
    private OrderInformationService orderInformationService;
    @Autowired
    private WapPaymentService wapPaymentService;

    private ServletConfig servletConfig;

    @Override
    public void setServletConfig(ServletConfig servletConfig) {
        this.servletConfig = servletConfig;
    }

    /**
     * 调用统一支付接口，以获取jsAPI支付所需参数
     *
     * @param model
     * @param req
     * @param orinNo
     * @return
     */
    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public String toPay(Model model, HttpServletRequest req, String orinNo) {
        OrderInformation orderInformation = orderInformationService.getTreeOrderInformationByOrinNo(orinNo);

        String ip = req.getRemoteAddr();
        String jsonGbwxpr = wapPaymentService.getPayParameter(orderInformation, ip);

        model.addAttribute("orderInformation", orderInformation);
        model.addAttribute("jsonGbwxpr", jsonGbwxpr);
        return "/wap/payment/pay";
    }

    /**
     * 支付回调路径
     *
     * @param req
     * @param resp
     */
    @RequestMapping(value = "/notify")
    public void notify(HttpServletRequest req, HttpServletResponse resp) {
        try {
            WxpayNotifyGatewayServlet notifyGatewayServlet = new WxpayNotifyGatewayServlet();
            notifyGatewayServlet.init(servletConfig);
            wapPaymentService.addNotifyListener(notifyGatewayServlet);
            notifyGatewayServlet.doPost(req, resp);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

}
