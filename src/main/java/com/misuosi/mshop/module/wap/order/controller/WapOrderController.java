/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.wap.order.controller;

import com.misuosi.mshop.common.context.SessionContextHolder;
import com.misuosi.mshop.db.page.Pagination;
import com.misuosi.mshop.entity.*;
import com.misuosi.mshop.module.wap.order.service.WapOrderService;
import com.misuosi.mshop.pojo.OrderItem;
import com.misuosi.mshop.pojo.ShoppingCartItem;
import com.misuosi.mshop.pojo.ShoppingCartItemArray;
import com.misuosi.mshop.service.ConsigneesAddressService;
import com.misuosi.mshop.service.OrderInformationService;
import com.misuosi.mshop.service.PayInformationService;
import com.misuosi.mshop.service.RegionalismService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Description		: 微信端我的订单页面的controller
 * <p/>
 * <br><br>Time		: 2015-6-8 下午8:08:12
 *
 * @author YLM
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/wap/order")
public class WapOrderController {

	private static Logger log = LoggerFactory.getLogger(WapOrderController.class);

	@Autowired
	private WapOrderService wapOrderService;
	@Autowired
	private ConsigneesAddressService consigneesAddressService;
	@Autowired
	private OrderInformationService orderInformationService;
	@Autowired
	private RegionalismService regionalismService;
	@Autowired
	private PayInformationService payInformationService;

	/**
	 * 跳转确认订单页
	 *
	 * @param model
	 * @param shoppingCartItemArray
	 * @return
	 */
	@RequestMapping(value = "/preview")
	public String preview(Model model, ShoppingCartItemArray shoppingCartItemArray) {
		Integer weinId = SessionContextHolder.getWechatInformationId();

		// 更新购物车商品数量
		wapOrderService.updateShoppingCart(shoppingCartItemArray.getShoppingCartItems(),weinId);
		// 收货地址
		ConsigneesAddress consigneesAddress = consigneesAddressService.getDefaultConsigneesAddressByWeinId(weinId);

		Integer regiId = consigneesAddress == null ? 0 : consigneesAddress.getRegiId();
		
		//刚传进来的购物车拥有全部商品，只选择用户选中要付款的商品去结算
		ShoppingCartItem[] shoppingCartItems = shoppingCartItemArray.getShoppingCartItems();
		int leng = 0;
		for (int index = 0; index<shoppingCartItems.length; index++) {
			if (shoppingCartItems[index].getGpstId()!=null) {
				leng ++;
			}
		}
		
		ShoppingCartItem[] newshoppingCartItems = new ShoppingCartItem[leng];
		for (int index = 0; index<shoppingCartItems.length; index++) {
			if (shoppingCartItems[index].getGpstId()!=null) {
				newshoppingCartItems[index] = shoppingCartItems[index];
			}
		}
		
		OrderItem orderItem = wapOrderService.statisticsOrder(weinId,
				regiId, newshoppingCartItems);

		model.addAttribute("consigneesAddress", consigneesAddress);
		model.addAttribute("orderItem", orderItem);

		return "wap/order/order_preview";
	}

	/**
	 * 提交订单
	 *
	 * @return
	 */
	@RequestMapping("/submit")
	public String submit(Integer coadId,
			ShoppingCartItemArray shoppingCartItemArray, Byte shippingMethod, String shinRemark) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer weinId = SessionContextHolder.getWechatInformationId();

		String orinNo = "";

		try {
			// 收货地址
			ConsigneesAddress consigneesAddress;
			if (coadId != null) {
				consigneesAddress = consigneesAddressService.getConsigneesAddress(coadId);
			} else {
				consigneesAddress = consigneesAddressService.getDefaultConsigneesAddressByWeinId(weinId);
			}

			OrderItem orderItem = wapOrderService.statisticsOrder(weinId,
					consigneesAddress.getRegiId(), shoppingCartItemArray.getShoppingCartItems());
			orderItem.setShippingMethod(shippingMethod);

			orinNo = wapOrderService.submitOrder(weinId, coadId, orderItem, shinRemark);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("WXOrderController.submit throws Exception", e);
			map.put("error", 0);
			map.put("message", "提交失败");
		}

		return "redirect:/wap/payment/pay/?orinNo=" + orinNo;
	}
	
	/**
	 * 跳转到我的订单页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(Model model) {
		return "/wap/order/order_list";
	}
	
	/**
	 * 获取订单列表数据
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/orderData", method = RequestMethod.GET)
	@ResponseBody
	public Pagination<OrderInformation> getOrderData(Integer type, Integer pageNo, Integer pageSize) {
		Pagination<OrderInformation> pagination = wapOrderService.getOrderDataByWeinId(
				SessionContextHolder.getWechatInformationId(), type, pageNo, pageSize);
		return pagination;
	}
	
	
	/**
	 * 确认收货
	 * 
	 * @param orinId
	 * @return
	 */
	@RequestMapping(value="/{orinId}/confirmReception", method = RequestMethod.GET)
	public String confirmReception(@PathVariable Integer orinId) {
		wapOrderService.updateConfirmReception(orinId);
		return "redirect:/wap/order/list";
	}
	
	/**
	 * 跳转到订单详情页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{orinId}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("orinId") Integer orinId, Model model) {
		OrderInformation orderInformation = orderInformationService.getOrderInformationTree(orinId);

		Integer painId = orderInformation.getPainId();
		PayInformation payInformation = null;
		if (painId != null) {
			payInformation = payInformationService.getPayInformation(painId);
		}

		ShipmentsInformation shipmentsInformation = orderInformation.getShipmentsInformation();
		Integer coadId = shipmentsInformation.getCoadId();
		ConsigneesAddress consigneesAddress = consigneesAddressService.getConsigneesAddress(coadId);

		Integer regiId = consigneesAddress.getRegiId();
		String regionalismStr = regionalismService.getRegionalismStr(regiId);

		model.addAttribute("orderInformation", orderInformation);
		model.addAttribute("payInformation", payInformation);
		model.addAttribute("consigneesAddress", consigneesAddress);
		model.addAttribute("regionalism", regionalismStr);
		return "/wap/order/order_detail";
	}

	/**
	 * 取消订单
	 *
	 * @param orinId
	 * @return
	 */
	@RequestMapping(value="/{orinId}/cancel", method = RequestMethod.GET)
	public String cancelOrder(@PathVariable Integer orinId) {
		wapOrderService.cancelOrder(orinId);
		return "redirect:/wap/order/list";
	}

	/**
	 * 删除订单
	 *
	 * @param orinId
	 * @return
	 */
	@RequestMapping(value="/{orinId}/delete")
	@ResponseBody
	public Map<String, Object> delete(@PathVariable("orinId") Integer orinId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Integer weinId = SessionContextHolder.getWechatInformationId();

		try {
			int flag = wapOrderService.deleteOrder(orinId, weinId);
			if (flag == 200) {
				map.put("success", 1);
			} else if (flag == 300) {
				map.put("error", 300);
				map.put("message", "订单不存在");
			} else if (flag == 301) {
				map.put("error", 301);
				map.put("message", "非本人操作");
			} else if (flag == 302) {
				map.put("error", 302);
				map.put("message", "已支付订单不可删除");
			}
		} catch (Exception e) {
			log.error("WXOrderController.delete throws Exception", e);
			map.put("error", 0);
			map.put("message", "删除失败");
		}

		return map;
	}
	
	/**
	 * 跳转到查看物流信息页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{orinId}/logistics", method = RequestMethod.GET)
	public String logistics(@PathVariable("orinId") Integer orinId, Model model) {
		return "/wap/order/logistics";
	}
	
	/**
	 * 跳转到评价页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{orinId}/evaluation", method = RequestMethod.GET)
	public String toEvaluation(@PathVariable("orinId") Integer orinId, Model model) {
		OrderInformation orderInformation = orderInformationService.getOrderInformationTree(orinId);
		model.addAttribute("orderInformation", orderInformation);
		return "/wap/order/evaluation";
	}
	/**
	 * 跳转到查看回复页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/{orinId}/reply", method = RequestMethod.GET)
	public String getReply(@PathVariable("orinId") Integer orinId, Model model) {
		OrderInformation orderInformation = orderInformationService.getOrderInformationTree(orinId);
		model.addAttribute("orderInformation", orderInformation);
		return "/wap/order/reply";
	}
	/**
	 * 评价商品
	 *
	 * @param data
	 * @param stars
	 * @param content
	 * @return
	 */
	@RequestMapping(value="/evaluation", method = RequestMethod.POST)
	@ResponseBody
	public String evaluation(Integer[] data, Byte[] stars, String[] content) {
		int row = wapOrderService.evaluation(data, stars, content);
		String str = row > 0 ? "评价成功" : "评价失败";
		return str;
	}
	
}