/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.MultiTableDao;
import com.misuosi.mshop.entity.WaybillPrintItems;
import com.misuosi.mshop.entity.WaybillTemplate;
import com.misuosi.mshop.util.DateUtils;

/**
 * Description : service类 WaybillTemplateService <br>
 * <br>
 * Time : 2015/05/30 16:42
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class WaybillTemplateService {
	private static final Byte WATE_DEFAULT_STYLE_ZORE = 0;
	private static final Byte WATE_DEFAULT_STYLE_ONE = 1;

	/**
	 * 供货商更新运单模板状态的SQL
	 */
	private static final String supp_sql = "UPDATE waybill_template SET waybill_template.wate_default = ? WHERE waybill_template.supp_id = ?";
	
	private static final String diac_sql = "UPDATE waybill_template SET waybill_template.wate_default = ? WHERE waybill_template.diac_id = ?";
	
	@Resource(name = "waybillTemplateDao")
	private Dao<WaybillTemplate> waybillTemplateDao;

	@Resource(name = "waybillPrintItemsDao")
	private Dao<WaybillPrintItems> waybillPrintItemsDao;

	public int addWaybillTemplate(WaybillTemplate waybillTemplate) {
		int rows = waybillTemplateDao.save(waybillTemplate);
		return rows;
	}

	public int updateWaybillTemplate(WaybillTemplate waybillTemplate) {
		int rows = waybillTemplateDao.dynamicUpdate(waybillTemplate);
		return rows;
	}

	public int deleteWaybillTemplate(Integer id) {
		int rows = waybillTemplateDao.delete(id);
		return rows;
	}

	public int batchDeleteWaybillTemplates(Integer[] ids) {
		int rows = waybillTemplateDao.batchDelete(ids);
		return rows;
	}

	public WaybillTemplate getWaybillTemplate(Integer id) {
		WaybillTemplate waybillTemplate = waybillTemplateDao.get(id);
		return waybillTemplate;
	}

	public List<WaybillTemplate> getAllWaybillTemplates() {
		List<WaybillTemplate> list = waybillTemplateDao.findAll();
		return list;
	}

	/**
	 * 根据供货商id，查询运单模板列表信息.
	 *
	 * @return
	 */
	public List<Map<String, Object>> getWaybillTemplates() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append(" waybill_template.wate_id,waybill_template.wate_name,waybill_template.wate_default,");
		sb.append(" express_company.exco_name");
		sb.append(" FROM waybill_template");
		sb.append(" INNER JOIN express_company ON express_company.exco_id = waybill_template.exco_id");
		sb.append(" ORDER BY waybill_template.wate_id DESC");

		String sql = sb.toString();
		List<Map<String, Object>> maps = ((MultiTableDao) waybillTemplateDao)
				.queryForList(sql);
		return maps;
	}

	/**
	 * 添加运单模板、运单模板对应的打印项
	 * 
	 * @param waybillTemplate
	 *            运单模板实体
	 * @param printItems
	 *            打印项列表
	 * @return 受影响的行数
	 */
	public Integer addWaybillTemplate(WaybillTemplate waybillTemplate,
			Integer[] printItems) {

		int rows = waybillTemplateDao.save(waybillTemplate);
		Integer wateId = waybillTemplate.getWateId();

		if(printItems != null){
			List<WaybillPrintItems> printItemsList = new ArrayList<WaybillPrintItems>();
			for (Integer printItem : printItems) {
				WaybillPrintItems waybillPrintItems = new WaybillPrintItems();
				waybillPrintItems.setPritId(printItem);
				waybillPrintItems.setWateId(wateId);
				waybillPrintItems.setWpitCreateTime(DateUtils.getCurrentTime());

				printItemsList.add(waybillPrintItems);
			}
			waybillPrintItemsDao.batchSave(printItemsList);
		}

		return rows;
	}

	/**
	 * 批量删除运单模板，每个运单模板对应的打印项.
	 * 
	 * @param wateIds
	 *            运单模板id
	 * @return 受影响的行数.
	 */
	public Integer batchDeleteWaybillTemplatesAndWaybillPrintItems(
			Integer[] wateIds) {
		String sql = "DELETE FROM waybill_print_items WHERE waybill_print_items.wate_id = ?";
		int rows = waybillTemplateDao.batchDelete(wateIds);
		for (Integer wateId : wateIds) {
			waybillPrintItemsDao.batchDelete(sql, wateId);
		}

		return rows;
	}

	/**
	 * 更新运单模板.
	 * 
	 * @param waybillTemplate
	 * @return 受影响的行数
	 */
	public Integer updateWaybillTemplate(WaybillTemplate waybillTemplate,
			Integer[] printItems) {

		int rows = waybillTemplateDao.update(waybillTemplate);
		Integer wateId = waybillTemplate.getWateId();

		String deleteSql = "DELETE FROM waybill_print_items WHERE waybill_print_items.wate_id = ?";
		waybillPrintItemsDao.batchDelete(deleteSql, wateId);

		if(printItems != null){
			List<WaybillPrintItems> printItemsList = new ArrayList<WaybillPrintItems>();
			for (Integer printItem : printItems) {
				WaybillPrintItems waybillPrintItems = new WaybillPrintItems();
				waybillPrintItems.setPritId(printItem);
				waybillPrintItems.setWateId(wateId);
				waybillPrintItems.setWpitCreateTime(DateUtils.getCurrentTime());

				printItemsList.add(waybillPrintItems);
			}
			waybillPrintItemsDao.batchSave(printItemsList);
		}		

		return rows;
	}

	/**
	 * @param diacId
	 * @return
	 */
	public List<Map<String, Object>> getWaybillTemplateByDiacId(Integer diacId) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT");
		sb.append(" waybill_template.wate_id,waybill_template.wate_name,waybill_template.wate_default,");
		sb.append(" express_company.exco_name");
		sb.append(" FROM waybill_template");
		sb.append(" INNER JOIN express_company ON express_company.exco_id = waybill_template.exco_id");
		sb.append(" WHERE waybill_template.diac_id = ?");
		sb.append(" ORDER BY waybill_template.wate_id DESC");

		String sql = sb.toString();
		List<Map<String, Object>> maps = ((MultiTableDao) waybillTemplateDao).queryForList(sql, diacId);
		return maps;
	}

	/**
	 * @param expressId
	 * @return
	 */
	public WaybillTemplate getAllWaybillTemplatesByExcoId(Integer expressId) {
		StringBuilder sb = new StringBuilder("SELECT * FROM waybill_template WHERE waybill_template.exco_id = ? ");
		String sql = sb.toString();
		WaybillTemplate waybillTemplate = waybillTemplateDao.findUnique(sql, expressId);
		return waybillTemplate;
	}

}
