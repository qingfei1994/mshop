/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.orders.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.misuosi.mshop.entity.ClientAppeal;
import com.misuosi.mshop.module.admin.orders.service.AdminOrderAppealService;
import com.misuosi.mshop.module.common.controller.ErrorController;
import com.misuosi.mshop.service.ClientAppealService;

/**
 * Description :管理员维权管理
 * <p/>
 * <br>
 * <br>
 * Time : 2015-7-30 上午9:41:18
 * 
 * @author CHQ
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/admin/orders/appeal")
public class AdminOrderAppealController {

	@Autowired
	private AdminOrderAppealService adminOrderAppealService;
	@Autowired
	private ClientAppealService clientAppealService;

	/**
	 * 维权信息列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Map<String, Object>> entitys = adminOrderAppealService
				.getGoodsAppealMap();
		model.addAttribute("entitys", entitys);
		return "/admin/orders/appeal_list";
	}

	/**
	 * 跳转到审核模态框
	 * 
	 * @param goorId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{goorId}/check", method = RequestMethod.GET)
	public String check(@PathVariable Integer goorId, Model model) {
		model.addAttribute("goorId", goorId);
		return "/admin/orders/check_appeal";
	}

	/**
	 * 保存审核记录
	 * 
	 * @param clientAppeal
	 * @param model
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public String saveCheck(ClientAppeal clientAppeal, Model model,
			RedirectAttributes attr) {
		boolean isSaveCheckSuccess = adminOrderAppealService
				.saveCheck(clientAppeal);
		if (isSaveCheckSuccess) {
			return "redirect:list";
		} else {
			attr.addFlashAttribute(ErrorController.MESSAGE, "审核失败");
			return ErrorController.ERROR;
		}
	}
	/**
	 * 确认退款
	 * @param refundId
	 * @param attr
	 * @return
	 */
	@RequestMapping(value = "/{refundId}/refund", method = RequestMethod.GET)
	public String confirmRefund(@PathVariable Integer refundId,
			RedirectAttributes attr) {
		boolean isConfirmSuccess = adminOrderAppealService
				.confirmRefund(refundId);
		if (isConfirmSuccess) {
			return "redirect:/admin/orders/appeal/list";
		} else {
			attr.addFlashAttribute(ErrorController.MESSAGE, "审核失败");
			return ErrorController.ERROR;
		}

	}

}
