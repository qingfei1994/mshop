/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.orders.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.MultiTableDao;
import com.misuosi.mshop.entity.ShipmentsInformation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description :
 * <p/>
 * <br>
 * <br>
 * Time : 2015年6月8日 下午9:17:41
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
@Service
public class AdminOrderShipmentsService {

	@Resource(name = "shipmentsInformationDao")
	private Dao<ShipmentsInformation> shipmentsInformationDao;

	/**
	 * 根据供货商Id，获得发货信息.
	 *
	 * @return
	 */
	public List<Map<String, Object>> getShipmentsInformation() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT order_information.*, ");
		sb.append("shipments_information.shin_id, shipments_information.shin_shipments_status, shipments_information.shin_express_no, ");
		sb.append("express_company.exco_id, express_company.exco_name, ");
		sb.append("consignees_address.coad_detailed_address, consignees_address.coad_name, consignees_address.coad_phone, ");
		sb.append("area.regi_name AS area_regi_name, city.regi_name AS city_regi_name, province.regi_name AS prov_regi_name, ");
		sb.append("wechat_information.wein_nickname, ");
		sb.append("goods_order.goor_name, goods_order.goor_code, goods_order.goor_price, goods_order.goor_count, goods_order.goor_first_picture ");
		sb.append("FROM order_information ");
		sb.append("INNER JOIN shipments_information ON shipments_information.orin_id = order_information.orin_id ");
		sb.append("LEFT JOIN express_company ON express_company.exco_id = shipments_information.exco_id ");
		sb.append("INNER JOIN consignees_address ON consignees_address.coad_id = shipments_information.coad_id ");
		sb.append("INNER JOIN regionalism AS area ON area.regi_id = consignees_address.regi_id ");
		sb.append("INNER JOIN regionalism AS city ON city.regi_id = area.regi_parent_id ");
		sb.append("INNER JOIN regionalism AS province ON province.regi_id = city.regi_parent_id ");
		sb.append("INNER JOIN wechat_information ON wechat_information.wein_id = order_information.wein_id ");
		sb.append("INNER JOIN goods_order ON goods_order.orin_id = order_information.orin_id ");
		sb.append("LEFT JOIN pay_information ON pay_information.pain_id = order_information.pain_id ");
		sb.append("WHERE order_information.orin_pay_status = 1 ");
		sb.append("AND order_information.orin_status != 0 ");
		sb.append("ORDER BY order_information.orin_id DESC ");

		String sql = sb.toString();
		List<Map<String, Object>> maps = ((MultiTableDao) shipmentsInformationDao).queryForList(sql);
		return maps;
	}

	/**
	 * @param shinId
	 * @return
	 */
	public Map<String, Object> getShipmentsInformationByShinId(Integer shinId) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT order_information.orin_id, order_information.orin_no, order_information.orin_order_time, ");
		sb.append("shipments_information.shin_express_no, shipments_information.shin_shipments_status, shipments_information.exco_id, ");
		sb.append("consignees_address.coad_id, consignees_address.coad_name, consignees_address.coad_phone ");
		sb.append("FROM order_information ");
		sb.append("INNER JOIN shipments_information ON shipments_information.orin_id = order_information.orin_id ");
		sb.append("INNER JOIN consignees_address ON consignees_address.coad_id = shipments_information.coad_id ");
		sb.append("WHERE shipments_information.shin_id = ? ");
		
		String sql = sb.toString();
		Map<String, Object> map = ((MultiTableDao) shipmentsInformationDao).queryForMap(sql, shinId);
		return map;
	}

	/**
	 * @param orinIds
	 * @return
	 */
	public List<Map<String, Object>> getShipmentsInformationByOrinIds(
			List<Integer> orinIds) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT order_information.*, ");
		sb.append("shipments_information.shin_remark, ");
		sb.append("shipments_information.exco_id, ");
		sb.append("consignees_address.coad_name, consignees_address.coad_phone,consignees_address.coad_detailed_address, ");
		sb.append("county.regi_name AS coun_regi_name, ");
		sb.append("city.regi_name AS city_regi_name, ");
		sb.append("province.regi_name AS prov_regi_name ");
		sb.append("FROM order_information ");
		sb.append("INNER JOIN shipments_information ON shipments_information.orin_id = order_information.orin_id ");
		sb.append("INNER JOIN consignees_address ON consignees_address.coad_id = shipments_information.coad_id ");
		sb.append("INNER JOIN regionalism AS county ON county.regi_id = consignees_address.regi_id ");
		sb.append("INNER JOIN regionalism AS city ON city.regi_id = county.regi_parent_id ");
		sb.append("INNER JOIN regionalism AS province ON province.regi_id = city.regi_parent_id ");
		sb.append("WHERE order_information.orin_id = ? ");
		
		String sql = sb.toString();
		List<Map<String, Object>> maps = new ArrayList<Map<String,Object>>();
		for(Integer id : orinIds){
			Map<String, Object> map = ((MultiTableDao) shipmentsInformationDao).queryForMap(sql, id);
			maps.add(map);
		}
				
		return maps;
	}

	/**
	 * @return
	 */
	public List<Map<String, Object>> getReportInformation() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append("order_information.orin_no, order_information.orin_order_time,order_information.orin_status, ");
		sb.append("order_information.orin_source, order_information.orin_freight, order_information.orin_total, ");
		sb.append("order_information.orin_pay_way, order_information.orin_delivery_method, ");
		sb.append("pay_information.pain_id, pay_information.pain_pay_time, ");
		sb.append("wechat_information.wein_nickname, wechat_information.wein_openid, ");
		sb.append("shipments_information.shin_express_no, ");
		sb.append("express_company.exco_name, ");
		sb.append("consignees_address.coad_name, consignees_address.coad_phone,consignees_address.coad_detailed_address, ");
		sb.append("county.regi_name AS coun_regi_name, ");
		sb.append("city.regi_name AS city_regi_name, ");
		sb.append("province.regi_name AS prov_regi_name, ");
		sb.append("goods_order.goor_name, goods_order.goor_code, goods_order.goor_price, goods_order.goor_count ");
		sb.append("FROM order_information ");
		sb.append("INNER JOIN goods_order ON goods_order.orin_id = order_information.orin_id ");
		sb.append(" JOIN pay_information ON pay_information.orin_id = order_information.orin_id ");
		sb.append("INNER JOIN wechat_information ON wechat_information.wein_id = order_information.wein_id ");
		sb.append("INNER JOIN shipments_information ON shipments_information.orin_id = order_information.orin_id ");
		sb.append("INNER JOIN express_company ON express_company.exco_id = shipments_information.exco_id ");
		sb.append("INNER JOIN consignees_address ON consignees_address.coad_id = shipments_information.coad_id ");
		sb.append("LEFT JOIN regionalism AS county ON county.regi_id = consignees_address.regi_id ");
		sb.append("LEFT JOIN regionalism AS city ON city.regi_id = county.regi_parent_id ");
		sb.append("LEFT JOIN regionalism AS province ON province.regi_id = city.regi_parent_id ");
		sb.append("ORDER BY pay_information.pain_id DESC ");
		
		String sql = sb.toString();
		List<Map<String, Object>> maps = ((MultiTableDao) shipmentsInformationDao).queryForList(sql);
		return maps;
	}

}
