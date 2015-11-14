/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.MultiTableDao;
import com.misuosi.mshop.entity.Addresses;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

/**
 * Description	 : service类 AddressesService
 * <br><br>Time	 : 2015/05/29 11:46
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class AddressesService {

	@Resource(name = "addressesDao")
	private Dao<Addresses> addressesDao;

	public int addAddresses(Addresses addresses) {
		int rows = addressesDao.save(addresses);
		return rows;
	}

	public int updateAddresses(Addresses addresses) {
		int rows = addressesDao.dynamicUpdate(addresses);
		return rows;
	}

	public int deleteAddresses(Integer id) {
		int rows = addressesDao.delete(id);
		return rows;
	}

	public int batchDeleteAddressess(Integer[] ids) {
		int rows = addressesDao.batchDelete(ids);
		return rows;
	}

	public Addresses getAddresses(Integer id) {
		Addresses addresses = addressesDao.get(id);
		return addresses;
	}

	public List<Addresses> getAllAddressess() {
		List<Addresses> list = addressesDao.findAll();
		return list;
	}

	/**
	 * 通过供货商ID，查询地址库.
	 *
	 * @return
	 */
	public List<Map<String, Object>> getAddresses() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT addresses.*,");
		sb.append(" county_regionalism.regi_id AS county_regi_id, county_regionalism.regi_name AS county_regi_name,");
		sb.append(" city_regionalism.regi_id AS city_regi_id, city_regionalism.regi_name AS city_regi_name,");
		sb.append(" province_regionalism.regi_id AS province_regi_id,province_regionalism.regi_name AS province_regi_name");
		sb.append(" FROM addresses");
		sb.append(" INNER JOIN regionalism AS county_regionalism ON county_regionalism.regi_id = addresses.regi_id");
		sb.append(" INNER JOIN regionalism AS city_regionalism ON city_regionalism.regi_id = county_regionalism.regi_parent_id");
		sb.append(" INNER JOIN regionalism AS province_regionalism ON province_regionalism.regi_id = city_regionalism.regi_parent_id");
		sb.append(" ORDER BY addr_id DESC");
		
		String sql = sb.toString();
		//List<Addresses> addresses = addressesDao.find(sql, suppId);
		List<Map<String, Object>> maps = ((MultiTableDao)addressesDao).queryForList(sql);
		return maps;
	}

	/**
	 * 通过地址库Id，查询地址库
	 * 
	 * @param addrId
	 * @return
	 */
	public Map<String, Object> getAddressesMap(Integer addrId) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT addresses.*,");
		sb.append(" county_regionalism.regi_id AS county_regi_id, county_regionalism.regi_name AS county_regi_name,");
		sb.append(" city_regionalism.regi_id AS city_regi_id, city_regionalism.regi_name AS city_regi_name,");
		sb.append(" province_regionalism.regi_id AS province_regi_id,province_regionalism.regi_name AS province_regi_name");
		sb.append(" FROM addresses");
		sb.append(" INNER JOIN regionalism AS county_regionalism ON county_regionalism.regi_id = addresses.regi_id");
		sb.append(" INNER JOIN regionalism AS city_regionalism ON city_regionalism.regi_id = county_regionalism.regi_parent_id");
		sb.append(" INNER JOIN regionalism AS province_regionalism ON province_regionalism.regi_id = city_regionalism.regi_parent_id");
		sb.append(" WHERE addresses.addr_id = ?");
		
		String sql = sb.toString();
		Map<String, Object> map = ((MultiTableDao)addressesDao).queryForMap(sql, addrId);
		return map;
	}

}

