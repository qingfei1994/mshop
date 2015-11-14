/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.orders.controller;

import com.misuosi.mshop.common.constants.OrderConstants;
import com.misuosi.mshop.common.context.RequestContextHolder;
import com.misuosi.mshop.common.exception.ExcelException;
import com.misuosi.mshop.entity.ExpressCompany;
import com.misuosi.mshop.entity.GoodsOrder;
import com.misuosi.mshop.entity.OrderInformation;
import com.misuosi.mshop.entity.ShipmentsInformation;
import com.misuosi.mshop.module.admin.orders.service.AdminOrderInformationService;
import com.misuosi.mshop.module.admin.orders.service.AdminOrderShipmentsService;
import com.misuosi.mshop.service.*;
import com.misuosi.mshop.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Description : 发货管理Controller
 * <p/>
 * <br>
 * <br>
 * Time : 2015年6月8日 下午8:59:59
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/admin/orders/shipments")
public class AdminOrderShipmentsController {

	@Autowired
	private AdminOrderShipmentsService adminOrderShipmentsService;
	@Autowired
	private GoodsOrderService goodsOrderService;
	@Autowired
	private ExpressCompanyService expressCompanyService;
	@Autowired
	private AddressesService addressesService;
	@Autowired
	private ShipmentsInformationService shipmentsInformationService;
	@Autowired
	private ShopInformationService shopInformationService;
	@Autowired
	private WaybillTemplateService waybillTemplateService;
	@Autowired
	private AdminOrderInformationService adminOrderInformationService;
	
	private static LinkedHashMap<String, String> map = new LinkedHashMap<String, String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("orin_no", "订单编号");
			put("orin_order_time", "下单时间");
			put("orin_status", "订单状态");
			put("orin_source", "订单来源");
			put("orin_freight", "运费");
			put("orin_total", "总费用");
			put("orin_pay_way", "支付方式");
			put("orin_delivery_method", "配送方式");
			put("pain_pay_time", "支付时间");
			put("goor_name", "商品名称");
			put("goor_count", "商品数量");
			put("goor_price", "商品单价");
		}
	};

	/**
	 * 发货信息列表.
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Map<String, Object>> entitys = adminOrderShipmentsService.getShipmentsInformation();
		
		List<Integer> orinIds = new ArrayList<Integer>();
		for(Map<String, Object> map : entitys){
			orinIds.add((Integer) map.get("orin_id"));
		}
		List<GoodsOrder> goodsOrders = goodsOrderService.getGoodsOrderByOrinIds(orinIds);
		//List<ExpressCompany> expressCompanies = expressCompanyService.getAllExpressCompanys();
		
		model.addAttribute("entitys", entitys);
		model.addAttribute("goodsOrders", goodsOrders);
		//model.addAttribute("expressCompanies", expressCompanies);
		return "/admin/orders/shipments_list";
	}
	
	@RequestMapping(value = "/toShipments/{shinId}", method = RequestMethod.GET)
	public String toShipments(@PathVariable("shinId") Integer shinId, Model model){
		Map<String, Object> map = adminOrderShipmentsService.getShipmentsInformationByShinId(shinId);
		List<ExpressCompany> expressCompanies = expressCompanyService.getAllExpressCompanys();
		List<Map<String, Object>> addressess = addressesService.getAddresses();
		
		model.addAttribute("entity", map);
		model.addAttribute("expressCompanies", expressCompanies);
		model.addAttribute("addressess", addressess);
		model.addAttribute("shinId", shinId);
		return "/admin/orders/shipments_modal";
	}
	
	@RequestMapping(value = "/shipments", method = RequestMethod.POST)
	public String shipments(ShipmentsInformation shipmentsInformation){
		shipmentsInformation.setShinShipmentsStatus(OrderConstants.SHIN_SHINMENT_STATUS_YES);
		shipmentsInformationService.updateShipmentsInformation(shipmentsInformation);
		return "redirect:/admin/orders/information/detail/"+shipmentsInformation.getOrinId();
	}
	
	@RequestMapping(value = "/toModify/{shinId}", method = RequestMethod.GET)
	public String toModify(@PathVariable("shinId") Integer shinId, Model model){
		Map<String, Object> entity = adminOrderShipmentsService.getShipmentsInformationByShinId(shinId);
		List<ExpressCompany> expressCompanies = expressCompanyService.getAllExpressCompanys();
		
		model.addAttribute("entity", entity);
		model.addAttribute("expressCompanies", expressCompanies);
		model.addAttribute("shinId", shinId);
		return "/admin/orders/shipments_modify";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(ShipmentsInformation shipmentsInformation){
		shipmentsInformationService.updateShipmentsInformation(shipmentsInformation);
		//return "redirect:/admin/orders/shipments/list";
		return RequestContextHolder.getPagingPath();
	}
	
//	/**
//	 * 打印配送单
//	 * 
//	 * @param ids
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "/printDistribution", method = RequestMethod.POST)
//	public String printDistribution(Integer[] ids, Model model){
//		Integer suppId = SessionContextHolder.getSupplierId();
//		List<Integer> orinIds = new ArrayList<Integer>();
//		for(Integer id : ids){
//			orinIds.add(id);
//		}
//		
//		List<Map<String, Object>> entitys = adminOrderShipmentsService.getShipmentsInformationByOrinIds(orinIds);
//		List<GoodsOrder> goodsOrders = goodsOrderService.getGoodsOrderByOrinIds(orinIds);
//		ShopInformation shopInformation = shopInformationService.getShopInformationBySuppId(suppId);
//		model.addAttribute("entitys", entitys);
//		model.addAttribute("goodsOrders", goodsOrders);
//		model.addAttribute("shopInformation", shopInformation);
//		return "/admin/orders/print_distribution";
//	}
//	
//	/**
//	 * 打印快递单
//	 * 
//	 * @param ids 订单id列表
//	 * @param expressId 快递id
//	 * @param address 发货地址Id
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "/printExpress")
//	public String printExpress(Integer[] ids, Integer expressId, Integer address,  Model model){
//		Integer suppId = SessionContextHolder.getSupplierId();
//		List<Integer> orinIds = new ArrayList<Integer>();
//		for(Integer id : ids){
//			orinIds.add(id);
//		}
//		
//		//获得快递列表
//		List<ExpressCompany> expressCompanies = expressCompanyService.getAllExpressCompanys();
//		//获得发货地址列表
//		List<Map<String, Object>> addresses = addressesService.getAddressesBySuppId(suppId);
//		
//		//获得订单信息列表
//		List<Map<String, Object>> entitys = adminOrderShipmentsService.getShipmentsInformationByOrinIds(orinIds);
//		
//		
//		WaybillTemplate waybillTemplate = null;
//		if(expressId != null){
//			waybillTemplate = waybillTemplateService.getAllWaybillTemplatesByExcoId(expressId, suppId);
//			model.addAttribute("expressId", expressId);
//		}
//		
//		Map<String, Object> targetAddr = null;
//		if(address != null){
//			targetAddr = addressesService.getAddressesMap(address);
//			model.addAttribute("addressId", address);
//		}
//		
//		List<WaybillTemplate> waybillTemplates = this.covertWaybillTemplate(waybillTemplate, entitys, targetAddr);
//		
//		model.addAttribute("orinIds", orinIds);
//		model.addAttribute("expressCompanies", expressCompanies);
//		model.addAttribute("addresses", addresses);
//		model.addAttribute("waybillTemplates", waybillTemplates);
//		return "/admin/orders/print_express";
//	}
	
	@RequestMapping(value = "/toMark", method = RequestMethod.GET)
	public String toMark(Integer[] ids, Model model){
		model.addAttribute("orinIds", ids);
		return "/admin/orders/order_mark";
	}
	
	@RequestMapping(value = "/mark", method = RequestMethod.POST)
	public String mark(Integer[] orinIds, String orinMarkContent, Byte orinMark){
		List<OrderInformation> orderInformations = new ArrayList<OrderInformation>();
		for(Integer orinId : orinIds){
			OrderInformation orderInformation = new OrderInformation();
			orderInformation.setOrinId(orinId);
			orderInformation.setOrinMark(orinMark);
			orderInformation.setOrinMarkContent(orinMarkContent);
			
			orderInformations.add(orderInformation);
		}
		adminOrderInformationService.batchUpdateOrderInformation(orderInformations);
		
		return RequestContextHolder.getPagingPath();
	}
	
	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public void report(HttpServletResponse response){
		List<Map<String, Object>> maps = adminOrderShipmentsService.getReportInformation();
		try{
			ExcelUtil.listToExcel(maps, map, "myExcelFile", response);
		}catch (ExcelException e){
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	private List<WaybillTemplate> covertWaybillTemplate(
//			WaybillTemplate waybillTemplate, List<Map<String, Object>> shipInformations, Map<String, Object> address){
//		List<WaybillTemplate> waybillTemplates = new ArrayList<WaybillTemplate>();
//		if(waybillTemplate == null || shipInformations == null || address == null){
//			return waybillTemplates;
//		}
//		
//		for(Map<String, Object> map : shipInformations){
//			WaybillTemplate template = new WaybillTemplate();
//			template = waybillTemplate;
//			
//			String content = template.getWateContent();
//			content = content.replaceAll("订单编号", (String)map.get("orin_no"));
//			content = content.replaceAll("发件人姓名", (String)address.get("addr_contacts"));
//			content = content.replaceAll("发件人电话", (String)address.get("addr_phone"));
//			content = content.replaceAll("发件人公司", (String)address.get("addr_company_name"));
//			content = content.replaceAll("发件人地址", (String)address.get("province_regi_name")+(String)address.get("city_regi_name")+
//					(String)address.get("county_regi_name")+(String)address.get("addr_detailed_address"));	
//			content = content.replaceAll("发件人邮编", "");
//			
//			content = content.replaceAll("收件人姓名", (String)map.get("coad_name"));
//			content = content.replaceAll("收件人电话", (String)map.get("coad_phone"));
//			content = content.replaceAll("收件人地址", (String)map.get("prov_regi_name")+(String)map.get("city_regi_name")+
//					(String)map.get("coun_regi_name")+(String)map.get("coad_detailed_address"));
//			content = content.replaceAll("收件人邮编", "");
//			
//			content = content.replaceAll("代收金额", "");
//			content = content.replaceAll("货到付款物流编号", "");
//			System.out.println(content);
//			template.setWateContent(content);
//			waybillTemplates.add(template);
//		}
//		
//		return waybillTemplates;
//	}
}
