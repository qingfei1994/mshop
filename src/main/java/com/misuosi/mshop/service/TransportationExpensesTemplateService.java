/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.MultiTableDao;
import com.misuosi.mshop.entity.TransportationExpensesTemplate;

/**
 * Description : service类 TransportationExpensesTemplateService <br>
 * <br>
 * Time : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class TransportationExpensesTemplateService {

	@Resource(name = "transportationExpensesTemplateDao")
	private Dao<TransportationExpensesTemplate> transportationExpensesTemplateDao;

	public int addTransportationExpensesTemplate(
			TransportationExpensesTemplate transportationExpensesTemplate) {
		int rows = transportationExpensesTemplateDao
				.save(transportationExpensesTemplate);
		return rows;
	}

	public int updateTransportationExpensesTemplate(
			TransportationExpensesTemplate transportationExpensesTemplate) {
		int rows = transportationExpensesTemplateDao
				.dynamicUpdate(transportationExpensesTemplate);
		return rows;
	}

	public int deleteTransportationExpensesTemplate(Integer id) {
		int rows = transportationExpensesTemplateDao.delete(id);
		return rows;
	}

	public int batchDeleteTransportationExpensesTemplates(Integer[] ids) {
		int rows = transportationExpensesTemplateDao.batchDelete(ids);
		return rows;
	}

	public TransportationExpensesTemplate getTransportationExpensesTemplate(
			Integer id) {
		TransportationExpensesTemplate transportationExpensesTemplate = transportationExpensesTemplateDao
				.get(id);
		return transportationExpensesTemplate;
	}

	public List<TransportationExpensesTemplate> getAllTransportationExpensesTemplates() {
		List<TransportationExpensesTemplate> list = transportationExpensesTemplateDao
				.findAll();
		return list;
	}

	public List<TransportationExpensesTemplate> getTransportationExpensesTemplateBySuppId(Integer suppId) {
		List<TransportationExpensesTemplate> list = transportationExpensesTemplateDao
				.findByProperty("suppId", suppId);
		return list;
	}

	/**
	 * 通过供货商Id，查询出运费模板列表信息.
	 *
	 * @return 运费模板列表信息.
	 */
	public List<Map<String, Object>> getTransportationExpensesTemplates() {
		String sql = "SELECT * FROM transportation_expenses_template "
				+ "ORDER BY transportation_expenses_template.tete_id DESC";
		List<Map<String, Object>> maps = ((MultiTableDao) transportationExpensesTemplateDao)
				.queryForList(sql);
		return maps;
	}

}
