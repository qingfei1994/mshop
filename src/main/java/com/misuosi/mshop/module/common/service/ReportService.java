/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.common.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.DaoFactory;
import com.misuosi.mshop.db.dao.TreeDao;
import com.misuosi.mshop.pojo.ReportItem;
import com.misuosi.mshop.pojo.StatementItem;

/**
 * Description		: 
 * <p/>
 * <br><br>Time		: 2015年6月18日 下午9:08:14
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
@Service
public class ReportService {
	
	/**
	 * @param suppId
	 * @param diacId
	 * @return
	 */
	public List<StatementItem> getStatementItem(Integer suppId, Integer diacId, Timestamp fromdate, Timestamp todate) {
		List<StatementItem> statementItems = null;
		
		Dao<StatementItem> statementItemDao = DaoFactory.getDao(StatementItem.class);
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("order_information.orin_no, order_information.orin_status, order_information.orin_order_time, ");
		sb.append("pay_information.pain_pay_no, pay_information.pain_pay_money, pay_information.pain_pay_time, ");
		sb.append("pay_information.pain_serial_number, pay_information.pain_pay_way ");
		sb.append("FROM order_information ");
		sb.append("INNER JOIN pay_information ON pay_information.orin_id = order_information.orin_id ");
		sb.append("WHERE order_information.orin_pay_status = 1 ");
		sb.append("AND order_information.orin_order_time >= ? AND order_information.orin_order_time <= ? ");
		if(suppId != null){
			sb.append("AND order_information.supp_id = ? ");
			String sql = sb.toString();
			statementItems = statementItemDao.find(sql, fromdate, todate, suppId);
		} else if(diacId != null){
			sb.append("AND order_information.diac_id = ? ");
			String sql = sb.toString();
			statementItems = statementItemDao.find(sql, fromdate, todate, diacId);
		}
		
		return statementItems;
	}

	/**
	 * @param suppId
	 * @param diacId
	 * @param ftime
	 * @param ttime
	 * @return
	 */
	public List<ReportItem> getReportItem(Integer suppId, Integer diacId,
			Timestamp ftime, Timestamp ttime) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("order_information.orin_no, order_information.orin_status, order_information.orin_order_time, ");
		sb.append("order_information.orin_source, order_information.orin_pay_status, order_information.orin_total, ");
		sb.append("order_information.orin_freight, order_information.orin_pay_way, order_information.orin_delivery_method, ");
		sb.append("order_information.orin_mark, order_information.orin_id, ");
		sb.append("pay_information.pain_pay_no, pay_information.pain_pay_money, pay_information.pain_pay_time, ");
		sb.append("pay_information.pain_serial_number, pay_information.pain_pay_way, ");
		sb.append("wechat_information.wein_openid, wechat_information.wein_nickname, ");
		sb.append("express_company.exco_name, ");
		sb.append("shipments_information.shin_express_no, shipments_information.shin_remark, ");
		sb.append("consignees_address.coad_name, consignees_address.coad_phone, consignees_address.coad_detailed_address, ");
		sb.append("county.regi_name AS coun_regi_name, ");
		sb.append("city.regi_name AS city_regi_name, ");
		sb.append("province.regi_name AS prov_regi_name ");
		//sb.append("goods_order.* ");
		sb.append("FROM order_information ");
		sb.append("LEFT JOIN pay_information ON pay_information.orin_id = order_information.orin_id ");
		//sb.append("INNER JOIN goods_order ON goods_order.orin_id = order_information.orin_id ");
		sb.append("INNER JOIN shipments_information ON shipments_information.orin_id = order_information.orin_id ");
		sb.append("INNER JOIN express_company ON express_company.exco_id = shipments_information.exco_id ");
		sb.append("INNER JOIN consignees_address ON consignees_address.coad_id = shipments_information.coad_id ");
		sb.append("INNER JOIN regionalism AS county ON county.regi_id = consignees_address.regi_id ");
		sb.append("INNER JOIN regionalism AS city ON city.regi_id = county.regi_parent_id ");
		sb.append("INNER JOIN regionalism AS province ON province.regi_id = city.regi_parent_id ");
		sb.append("INNER JOIN wechat_information ON wechat_information.wein_id = order_information.wein_id ");
		sb.append("WHERE order_information.orin_order_time >= ? ");
		sb.append("AND order_information.orin_order_time <= ? ");
		
		TreeDao<ReportItem> reportItemDao = DaoFactory.getTreeDao(ReportItem.class);
		List<ReportItem> reportItems = null;
				
		if(suppId != null){
			sb.append("AND order_information.supp_id = ? ");
			sb.append("ORDER BY order_information.orin_id DESC ");
			String sql = sb.toString();
			reportItems = reportItemDao.queryForTree(sql, ftime, ttime, suppId);
		}else if(diacId != null){
			sb.append("AND order_information.diac_id = ? ");
			sb.append("ORDER BY order_information.orin_id DESC ");
			String sql = sb.toString();
			reportItems = reportItemDao.queryForTree(sql, ftime, ttime, diacId);
		}
		return reportItems;
	}

}
