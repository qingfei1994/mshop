/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.MultiTableDao;
import com.misuosi.mshop.entity.TransportationExpenses;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;
import java.util.Map;

/**
 * Description	 : service类 TransportationExpensesService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class TransportationExpensesService {

	@Resource(name = "transportationExpensesDao")
	private Dao<TransportationExpenses> transportationExpensesDao;

	public int addTransportationExpenses(TransportationExpenses transportationExpenses) {
		int rows = transportationExpensesDao.save(transportationExpenses);
		return rows;
	}

	public int updateTransportationExpenses(TransportationExpenses transportationExpenses) {
		int rows = transportationExpensesDao.dynamicUpdate(transportationExpenses);
		return rows;
	}

	public int deleteTransportationExpenses(Integer id) {
		int rows = transportationExpensesDao.delete(id);
		return rows;
	}

	public int batchDeleteTransportationExpensess(Integer[] ids) {
		int rows = transportationExpensesDao.batchDelete(ids);
		return rows;
	}

	public TransportationExpenses getTransportationExpenses(Integer id) {
		TransportationExpenses transportationExpenses = transportationExpensesDao.get(id);
		return transportationExpenses;
	}

	public List<TransportationExpenses> getAllTransportationExpensess() {
		List<TransportationExpenses> list = transportationExpensesDao.findAll();
		return list;
	}

	/**
	 * 通过供货商Id，查询出运费列表信息.
	 * 
	 * @param suppId
	 * @return
	 */
	public List<Map<String, Object>> getTransportationExpensesBySuppId(
			Integer suppId) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT transportation_expenses.*, ");
		sb.append("distribution_region.regi_id AS dire_regi_id, distribution_region.trex_id AS dire_trex_id, ");
		sb.append("regionalism.regi_id, regionalism.regi_name ");
		sb.append("FROM transportation_expenses_template ");
		sb.append("INNER JOIN transportation_expenses ON transportation_expenses.tete_id = transportation_expenses_template.tete_id ");
		sb.append("LEFT JOIN distribution_region ON distribution_region.trex_id = transportation_expenses.trex_id ");
		sb.append("LEFT JOIN regionalism ON regionalism.regi_id = distribution_region.regi_id ");
		sb.append("WHERE transportation_expenses_template.supp_id = ? ");
		
		String sql = sb.toString();
		List<Map<String, Object>> maps = ((MultiTableDao)transportationExpensesDao).queryForList(sql, suppId);
		return maps;
	}

	/**
	 * @param teteId
	 * @return
	 */
	public List<TransportationExpenses> getTransportationExpensesByTeteId(
			Integer teteId) {
		List<TransportationExpenses> transportationExpenses = transportationExpensesDao.findByProperty("teteId", teteId);
		return transportationExpenses;
	}

}

