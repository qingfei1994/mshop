/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.orders.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.misuosi.mshop.common.constants.OrderConstants;
import com.misuosi.mshop.common.context.RequestContextHolder;
import com.misuosi.mshop.entity.GoodsOrder;
import com.misuosi.mshop.entity.OrderInformation;
import com.misuosi.mshop.module.admin.orders.service.AdminOrderInformationService;
import com.misuosi.mshop.pojo.OrderPrice;
import com.misuosi.mshop.service.GoodsOrderService;
import com.misuosi.mshop.service.OrderInformationService;

/**
 * Description		: 订单管理Controller.
 * <p/>
 * <br><br>Time		: 2015年6月8日 上午9:15:49
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/admin/orders/information")
public class AdminOrderInformationController {

    @Autowired
    private AdminOrderInformationService adminOrderInformationService;

    @Autowired
    private OrderInformationService orderInformationService;
    @Autowired
    private GoodsOrderService goodsOrderService;
    @RequiresPermissions("admin:orders:information")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {

//		List<Integer> orinIds = new ArrayList<Integer>();
        List<Map<String, Object>> pagination = adminOrderInformationService.getOrderInformationMaps();

//		for(Map<String, Object> map : pagination){
//			orinIds.add((Integer)map.get("orin_id"));
//		}
//		List<GoodsOrder> goodsOrders = goodsOrderService.getGoodsOrderByOrinIds(orinIds);

        model.addAttribute("pagination", pagination);
//		model.addAttribute("goodsOrders", goodsOrders);
        return "/admin/orders/order_list";
    }

    /**
     * 订单详情
     *
     * @param orinId 订单Id
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail/{orinId}", method = RequestMethod.GET)
    public String detail(@PathVariable("orinId") Integer orinId, Model model) {
        Map<String, Object> entity = adminOrderInformationService.getOrderInformationMapsByOrinId(orinId);
        List<Integer> orinIds = new ArrayList<Integer>();
        orinIds.add(orinId);
        List<GoodsOrder> goodsOrders = goodsOrderService.getGoodsOrderByOrinIds(orinIds);

        model.addAttribute("entity", entity);
        model.addAttribute("goodsOrders", goodsOrders);
        return "/admin/orders/order_detail";
    }

    @RequestMapping(value = "/batchDelete", method = RequestMethod.GET)
    public String batchDelete(Integer[] ids) {
        adminOrderInformationService.batchDeleteOrderInformation(ids);
        return RequestContextHolder.getPagingPath();
    }

    /**
     * 跳转到标记modal
     *
     * @param orinId
     * @param model
     * @return
     */
    @RequestMapping(value = "/toMark/{orinId}")
    public String toMark(@PathVariable("orinId") Integer orinId, Model model, Byte type) {
        OrderInformation orderInformation = orderInformationService.getOrderInformation(orinId);
        model.addAttribute("orinId", orinId);
        model.addAttribute("orderInformation", orderInformation);
        model.addAttribute("type", type);
        return "/admin/orders/order_mark";
    }

    /**
     * 标记
     *
     * @param orderInformation
     * @return
     */
    @RequestMapping("/mark")
    public String mark(OrderInformation orderInformation, Byte type) {
        orderInformationService.updateOrderInformation(orderInformation);
        if (type == 1) {
            return RequestContextHolder.getPagingPath();
        } else {
            return "redirect:/admin/orders/information/detail/" + orderInformation.getOrinId();
        }
    }

    /**
     * 跳转到修改价格的modal
     *
     * @param orinId
     * @param model
     * @return
     */
    @RequestMapping(value = "/toChangePrice/{orinId}", method = RequestMethod.GET)
    public String toChangePrice(@PathVariable("orinId") Integer orinId, Model model) {
        List<Integer> orinIds = new ArrayList<Integer>();
        orinIds.add(orinId);
        OrderInformation orderInformation = orderInformationService.getOrderInformation(orinId);
        List<GoodsOrder> goodsOrders = goodsOrderService.getGoodsOrderByOrinIds(orinIds);

        model.addAttribute("orinId", orinId);
        model.addAttribute("goodsOrders", goodsOrders);
        model.addAttribute("orderInformation", orderInformation);
        return "/admin/orders/order_price";
    }

    /**
     * 修改价格
     *
     * @param orderPrice
     * @param orinId
     * @param orinFreight
     * @return
     */
    @RequestMapping(value = "/price", method = RequestMethod.POST)
    public String price(OrderPrice orderPrice, Integer orinId, Double orinFreight) {
        Double orinTotal = 0d;
        GoodsOrder[] goodsOrders = orderPrice.getLstPrice();
        List<GoodsOrder> list = new ArrayList<GoodsOrder>();
        for (GoodsOrder order : goodsOrders) {
            list.add(order);
            orinTotal += order.getGoorRealityPay();
        }

        orinTotal += orinFreight;
        OrderInformation orderInformation = new OrderInformation();
        orderInformation.setOrinId(orinId);
        orderInformation.setOrinFreight(orinFreight);
        orderInformation.setOrinTotal(orinTotal);

        adminOrderInformationService.updateOrderInformationAndGoodsOrder(list, orderInformation);
        return "redirect:/admin/orders/information/detail/" + orinId;
    }

    /**
     * 关闭订单
     *
     * @param orinId
     * @return
     */
    @RequestMapping(value = "/closeOrder/{orinId}", method = RequestMethod.GET)
    public String closeOrder(@PathVariable("orinId") Integer orinId) {
        OrderInformation orderInformation = new OrderInformation();
        orderInformation.setOrinId(orinId);
        orderInformation.setOrinStatus(OrderConstants.ORIN_STATUS_CLOSE);
        orderInformationService.updateOrderInformation(orderInformation);
        return "redirect:/admin/orders/information/detail/" + orinId;
    }

}
