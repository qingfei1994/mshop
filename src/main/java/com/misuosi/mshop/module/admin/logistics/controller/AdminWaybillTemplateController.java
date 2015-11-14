/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.logistics.controller;

import com.misuosi.mshop.common.context.RequestContextHolder;
import com.misuosi.mshop.common.context.SessionContextHolder;
import com.misuosi.mshop.entity.ExpressCompany;
import com.misuosi.mshop.entity.PrintItems;
import com.misuosi.mshop.entity.WaybillPrintItems;
import com.misuosi.mshop.entity.WaybillTemplate;
import com.misuosi.mshop.service.ExpressCompanyService;
import com.misuosi.mshop.service.PrintItemsService;
import com.misuosi.mshop.service.WaybillPrintItemsService;
import com.misuosi.mshop.service.WaybillTemplateService;
import com.misuosi.mshop.util.DateUtils;
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
 * Description : 运单模板Controller
 * <p/>
 * <br>
 * <br>
 * Time : 2015年5月30日 下午12:06:59
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/admin/logistics/waybilltemplate")
public class AdminWaybillTemplateController {

	@Autowired
	private WaybillTemplateService waybillTemplateService;
	
	@Autowired
	private ExpressCompanyService expressCompanyService;
	
	@Autowired
	private PrintItemsService printItemsService;
	
	@Autowired
	private WaybillPrintItemsService waybillPrintItemsService;

	/**
	 * 获得供货商的运单模板的列表信息.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Map<String, Object>> entitys = waybillTemplateService.getWaybillTemplates();
		model.addAttribute("entitys", entitys);
		return "/admin/logistics/waybilltemplate_list";
	}

	/**
	 * 跳转到添加运单模板的页面.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toAddWaybillTemplate", method = RequestMethod.GET)
	public String toAddWaybillTemplate(Model model) {
		List<ExpressCompany> expressCompanys = expressCompanyService.getAllExpressCompanys();
		List<PrintItems> printItems = printItemsService.getAllPrintItemss();
		
		model.addAttribute("expressCompanys", expressCompanys);
		model.addAttribute("printItems", printItems);
		return "/admin/logistics/waybilltemplate_edit";
	}
	
	/**
	 * 编辑快递模板.
	 * 
	 * @param wateId
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/edit/{wateId}", method = RequestMethod.GET)
	public String edit(@PathVariable("wateId") Integer wateId, RedirectAttributes redirectAttributes){
		WaybillTemplate waybillTemplate = waybillTemplateService.getWaybillTemplate(wateId);
		List<WaybillPrintItems> waybillPrintItems = waybillPrintItemsService.getWaybillPrintItemsByWateId(wateId);
		redirectAttributes.addFlashAttribute("waybillTemplate", waybillTemplate);
		redirectAttributes.addFlashAttribute("waybillPrintItems", waybillPrintItems);
		return "redirect:/admin/logistics/waybilltemplate/toAddWaybillTemplate";
	}
	
	/**
	 * 保存运单信息.
	 * 
	 * @param waybillTemplate
	 * @param printItem
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(WaybillTemplate waybillTemplate, Integer[] printItem){
		waybillTemplate.setWateCreateTime(DateUtils.getCurrentTime());
		if(waybillTemplate.getWateId() != null){
			waybillTemplateService.updateWaybillTemplate(waybillTemplate, printItem);
		}else{
			waybillTemplateService.addWaybillTemplate(waybillTemplate, printItem);
		}
		
		return "redirect:/admin/logistics/waybilltemplate/list";
	}
	
	/**
	 * 批量删除运单模板.
	 * 
	 * @param ids 运单模板id列表.
	 * @return
	 */
	@RequestMapping(value = "/batchDelete", method = RequestMethod.GET)
	public String batchDelete(Integer[] ids){
		waybillTemplateService.batchDeleteWaybillTemplatesAndWaybillPrintItems(ids);
		
		return RequestContextHolder.getPagingPath();
	}
	
	/**
	 * 删除运单模板.
	 * 
	 * @param wateId
	 * @return
	 */
	@RequestMapping(value = "/delete/{wateId}", method = RequestMethod.GET)
	public String delete(@PathVariable("wateId") Integer wateId){
		Integer[] ids = {wateId};
		waybillTemplateService.batchDeleteWaybillTemplatesAndWaybillPrintItems(ids);
		
		return RequestContextHolder.getPagingPath();
	}
}
