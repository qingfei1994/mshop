/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.logistics.controller;

import com.misuosi.mshop.common.context.RequestContextHolder;
import com.misuosi.mshop.common.context.SessionContextHolder;
import com.misuosi.mshop.entity.Addresses;
import com.misuosi.mshop.pojo.ThreeStageArea;
import com.misuosi.mshop.service.AddressesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 * Description :
 * <p/>
 * <br>
 * <br>
 * Time : 2015年5月29日 上午9:06:48
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/admin/logistics/addresses")
public class AdminAddressController {

	@Autowired
	private AddressesService addressesService;

	/**
	 * 地址库列表.
	 * 
	 * @param model
	 * @return
	 */
	//@RequiresPermissions("admin:permission:addresses:view")
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Map<String, Object>> addressess = addressesService.getAddresses();
		model.addAttribute("addressess", addressess);
		return "/admin/logistics/addresses_list";
	}

	/**
	 * 弹出添加地址库的model
	 * 
	 * @return
	 */
	@RequestMapping(value = "/toAddAddress", method = RequestMethod.GET)
	public String toAddAddress() {
		return "/admin/logistics/addresses_edit";
	}
	
	/**
	 * @param addrId
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/edit/{addrId}", method = RequestMethod.GET)
	public String edit(@PathVariable("addrId") Integer addrId, RedirectAttributes redirectAttributes) {
		Map<String, Object> map = addressesService.getAddressesMap(addrId);
		redirectAttributes.addFlashAttribute("map", map);
		redirectAttributes.addFlashAttribute("provinceId", map.get("province_regi_id"));
		redirectAttributes.addFlashAttribute("cityId", map.get("city_regi_id"));
		redirectAttributes.addFlashAttribute("countyId", map.get("county_regi_id"));
		return "redirect:/admin/logistics/addresses/toAddAddress";
	}

	/**
	 * 添加地址库
	 * 
	 * @param addresses
	 * @param threeStageArea
	 * @return
	 */
	@RequestMapping(value = "/addAddresses", method = RequestMethod.POST)
	public String addAddresses(Addresses addresses,
			ThreeStageArea threeStageArea) {
		addresses.setRegiId(threeStageArea.getCounty());
		if(addresses.getAddrId() != null){
			addressesService.updateAddresses(addresses);
		}else{
			addressesService.addAddresses(addresses);
		}

		return "redirect:/admin/logistics/addresses/list";
	}

	/**
	 * 批量删除地址库
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/batchDelete", method = RequestMethod.GET)
	public String batchDelete(Integer[] ids) {
		addressesService.batchDeleteAddressess(ids);
		return RequestContextHolder.getPagingPath();
	}

	/**
	 * 删除地址库
	 * 
	 * @param addrId
	 * @return
	 */
	@RequestMapping(value = "/delete/{addrId}", method = RequestMethod.GET)
	public String delete(@PathVariable("addrId") Integer addrId) {
		addressesService.deleteAddresses(addrId);
		return RequestContextHolder.getPagingPath();
	}
}
