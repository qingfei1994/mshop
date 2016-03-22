/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.common.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.misuosi.mshop.common.constants.OrderConstants;
import com.misuosi.mshop.common.context.SessionContextHolder;
import com.misuosi.mshop.common.exception.ExcelException;
import com.misuosi.mshop.module.common.service.ReportService;
import com.misuosi.mshop.pojo.ReportItem;
import com.misuosi.mshop.pojo.StatementItem;
import com.misuosi.mshop.util.ExcelUtil;

/**
 * Description		: 报表导出
 * <p/>
 * <br><br>Time		: 2015年6月18日 下午9:02:05
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
@RequestMapping("/common/report")
@Controller
public class ReportController {
	
	/**
	 * 对账单
	 */
	private static final LinkedHashMap<String, String> statementMap = new LinkedHashMap<String, String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("orinNo", "订单编号");
			put("orinStatus", "订单状态");
			put("orinOrderTime", "下单时间");
			put("painPayNo", "支付单号");
			put("painSerialNumber", "客户订单号");
			put("painPayMoney", "支付金额");
			put("painPayWay", "支付方式");
			put("painPayTime", "支付时间");
		}
	};
	/**
	 * 订单
	 */
	private static final LinkedHashMap<String, String> orderMap = new LinkedHashMap<String, String>(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		{
			put("orinNo", "订单编号");
			put("orinStatus", "订单状态");
			put("orinOrderTime", "下单时间");
			put("orinSource", "订单来源");
			put("orinPayStatus", "支付状态");
			put("orinTotal", "订单总额");
			put("orinFreight", "订单重量");
			put("orinPayWay", "订单支付方式");
			put("weinNickname", "微信昵称");
			put("weinOpenid", "微信openid");
			put("painPayTime", "支付时间");
			put("orinDeliveryMethod", "运送方式");
			put("excoName", "快递名称");
			put("coadName", "收件人姓名");
			put("coadPhone", "收件人电话");
			put("coadDetailedAddress", "收件人详细地址");
			put("counRegiName", "收件人国家");
			put("cityRegiName", "收件人城市");
			put("provRegiName", "收件人省份");
			put("shinRemark", "快递备注");
			put("shinExpressNo", "快递单号");
			put("orinMark", "订单备注");
			
		}
	};
	@Autowired
	private ReportService reportService;

	/**
	 * 导出对账单
	 * 
	 * @param response
	 * @param fromdate 起始时间
	 * @param todate 最后时间
	 */
	@RequestMapping(value = "/statement", method = RequestMethod.GET)
	public void statement(HttpServletResponse response, Date fromdate, Date todate){
		Integer suppId = 1;
		Integer diacId = 1;
		
		Timestamp ftime = new Timestamp(fromdate.getTime());
		Timestamp ttime = new Timestamp(todate.getTime());
		//从数据库中获取数据
		List<StatementItem> statementItems = reportService.getStatementItem(suppId, diacId, ftime, ttime);
		
		try {
			//转化为excel
			ExcelUtil.listToExcel(statementItems, statementMap, "MyStatement", response);
		} catch (ExcelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出订单
	 * 
	 * @param request
	 * @param response
	 * @param fromdate 起始时间
	 * @param todate 最后时间
	 */
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public void order(HttpServletRequest request, HttpServletResponse response, Date fromdate, Date todate){
		Integer suppId = 1;
		Integer diacId = 1;
		
		Timestamp ftime = new Timestamp(fromdate.getTime());
		Timestamp ttime = new Timestamp(todate.getTime());
		List<ReportItem> reportItems = reportService.getReportItem(suppId, diacId, ftime, ttime);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportItems", reportItems);
		
		String realTemplatePath = request.getSession().getServletContext().getRealPath(OrderConstants.TEMPLATE_PATH);
		/*DocumentUtils.exportExcel(response, map, realTemplatePath, 
			"report_excel.ftl", FileNameUtils.getFileName(".xls").toString(), realTemplatePath);*/
		try {
			ExcelUtil.listToExcel(reportItems, orderMap, "My Order", response);
		} catch (ExcelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
