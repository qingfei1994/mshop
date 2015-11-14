/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.wap.payment.controller;

import com.misuosi.mshop.common.context.SessionContextHolder;
import com.misuosi.mshop.entity.ConsigneesAddress;
import com.misuosi.mshop.module.wap.payment.service.WapConsigneesAddressService;
import com.misuosi.mshop.service.ConsigneesAddressService;
import com.misuosi.mshop.service.RegionalismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Description		: 微信端收货地址页面的controller
 * <p/>
 * <br><br>Time		: 2015-6-1 下午6:08:12
 *
 * @author YLM
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/wap/payment/consigneesAddress")
public class WapConsigneesAddressController {

	@Autowired
	ConsigneesAddressService consigneesAddressService;
	
	@Autowired
	WapConsigneesAddressService wapConsigneesAddressService;
	
	/**
	 * 跳转到收货地址列表页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String consigneesAddress(Model model) {
		List<Map<String, Object>> consigneesAddressList = consigneesAddressService.getAllConsigneesAddresssByWeinId(SessionContextHolder.getWechatInformationId());
		model.addAttribute("addressList", consigneesAddressList);
		return "/wap/payment/consigneesaddress/consignees_address";
	}

	/**
	 * 选择收货人地址
	 * @return
	 */
	@RequestMapping(value = "/choose", method = RequestMethod.GET)
	public String chooseConsigneesAddress() {
		return "/wap/payment/consigneesaddress/consignees_address_choose";
	}
	
	/**
	 * 跳转到增加收货地址页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editConsigneesAddress() {
		return "/wap/payment/consigneesaddress/consignees_address_edit";
	}
	
	/**
	 * 跳转到编辑收货地址页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{coadId}/edit", method = RequestMethod.GET)
	public String editConsigneesAddress(Model model, @PathVariable Integer coadId) {
		ConsigneesAddress consigneesAddress = consigneesAddressService.getConsigneesAddress(coadId);
		int countyId = consigneesAddress.getRegiId();
		int cityId = RegionalismService.getParentId(countyId);
		int provinceId = RegionalismService.getParentId(cityId);
		model.addAttribute("consigneesAddress", consigneesAddress);
		model.addAttribute("countyId", countyId);
		model.addAttribute("cityId", cityId);
		model.addAttribute("provinceId", provinceId);
		return "/wap/payment/consigneesaddress/consignees_address_edit";
	}
	
	/**
	 * 新增收货地址
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public String addConsigneesAddress(ConsigneesAddress consigneesAddress) {
		wapConsigneesAddressService.addConsigneesAddress(consigneesAddress);
		return "wap/payment/consigneesAddress";
	}
	
	/**
	 * 保存收货地址
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String saveConsigneesAddress(ConsigneesAddress consigneesAddress) {
		wapConsigneesAddressService.updateConsigneesAddress(consigneesAddress);
		return "wap/payment/consigneesAddress";
	}
	
	/**
	 * 保存收货地址
	 */
	@RequestMapping(value = "/{coadId}/delete", method = RequestMethod.GET)
	public String deleteConsigneesAddress(@PathVariable Integer coadId) {
		consigneesAddressService.deleteConsigneesAddress(coadId);
		return "redirect:/wap/payment/consigneesAddress";
	}
}
