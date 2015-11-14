/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.MultiTableDao;
import com.misuosi.mshop.entity.ConsigneesAddress;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Description	 : service类 ConsigneesAddressService
 * <br><br>Time	 : 2015/06/03 10:02
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class ConsigneesAddressService {

	@Resource(name = "consigneesAddressDao")
	private Dao<ConsigneesAddress> consigneesAddressDao;

	public int addConsigneesAddress(ConsigneesAddress consigneesAddress) {
		int rows = consigneesAddressDao.save(consigneesAddress);
		return rows;
	}

	public int updateConsigneesAddress(ConsigneesAddress consigneesAddress) {
		int rows = consigneesAddressDao.dynamicUpdate(consigneesAddress);
		return rows;
	}

	public int deleteConsigneesAddress(Integer id) {
		int rows = consigneesAddressDao.delete(id);
		return rows;
	}

	public int batchDeleteConsigneesAddresss(Integer[] ids) {
		int rows = consigneesAddressDao.batchDelete(ids);
		return rows;
	}

	public ConsigneesAddress getConsigneesAddress(Integer id) {
		ConsigneesAddress consigneesAddress = consigneesAddressDao.get(id);
		return consigneesAddress;
	}

	public List<ConsigneesAddress> getAllConsigneesAddresss() {
		List<ConsigneesAddress> list = consigneesAddressDao.findAll();
		return list;
	}
	
	/**
	 * 通过微信ID获得所有的收货地址的数量  add by YLM
	 * @param weinId
	 * @return
	 */
	public int getAllConsigneesAddresssCountByWeinId(int weinId) {
		List<ConsigneesAddress> list = consigneesAddressDao.findByProperty("wein_id", weinId);
		return  list.size();
	}

	public ConsigneesAddress getDefaultConsigneesAddressByWeinId(Integer weinId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM consignees_address ");
		sql.append("WHERE coad_default_address=1 ");
		sql.append("AND wein_id=?");
		ConsigneesAddress consigneesAddress = consigneesAddressDao.findUnique(sql.toString(), weinId);
		if (consigneesAddress == null) {
			sql.delete(0, sql.length());
			sql.append("SELECT * FROM consignees_address ");
			sql.append("WHERE wein_id=? ");
			sql.append("ORDER BY coad_id DESC ");
			List<ConsigneesAddress> list = consigneesAddressDao.find(sql.toString(), weinId);
			if (list != null && !list.isEmpty()) {
				consigneesAddress = list.get(0);
			}
		}
		return consigneesAddress;
	}

	/**
	 * 通过微信ID获得所有的收货地址  add by YLM
	 * @param weinId
	 * @return
	 */
	public List<Map<String, Object>> getAllConsigneesAddresssByWeinId(int weinId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT province.regi_id AS province_id, city.regi_id AS city_id, county.regi_id AS county_id,")
				.append(" province.regi_name AS province_name, city.regi_name AS city_name, county.regi_name AS county_name,")
				.append(" coad_id, coad_name, coad_phone, coad_detailed_address, coad_default_address")
				.append(" FROM consignees_address INNER JOIN regionalism AS county ON consignees_address.regi_id = county.regi_id")
				.append(" INNER JOIN regionalism AS city ON city.regi_id = county.regi_parent_id")
				.append(" INNER JOIN regionalism AS province ON province.regi_id = city.regi_parent_id")
				.append(" WHERE consignees_address.wein_id = ?");
		return ((MultiTableDao)consigneesAddressDao).queryForList(sql.toString(), weinId);
	}

}

