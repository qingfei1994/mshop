/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.orders.controller;

import com.misuosi.mshop.entity.OrderSetting;
import com.misuosi.mshop.service.OrderSettingService;
import com.misuosi.mshop.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Description		: 订单管理
 * <p/>
 * <br><br>Time		: 2015-5-30 上午11:36:47
 *
 * @author ZXL
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/orders/setting")
public class AdminOrderSettingController {
	@Autowired
	private OrderSettingService orderSettingService;
	
	List<OrderSetting> lists = null;
	
	/**
	 * 展示页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model){
		OrderSetting orderSetting = orderSettingService.findOrderSettings();
		model.addAttribute("orderSetting", orderSetting);
		
		return "/admin/orders/order_setting";
	}
	
	/**
	 * 保存设置
	 * @param orderSetting
	 * @param orseId
	 * @return
	 */
	@RequestMapping("/save")
	public String save(OrderSetting orderSetting,Integer orseId) {

		if(orderSetting.getOrseId()!=null){
			orderSettingService.updateOrderSetting(orderSetting);
		//如果订单设置ID为空，则插入数据
		}else{
			orderSetting.setOrseCreateTime(DateUtils.getCurrentTime());
			orderSettingService.addOrderSetting(orderSetting);
		}
		return "redirect:/admin/orders/setting/list";
	}

}
