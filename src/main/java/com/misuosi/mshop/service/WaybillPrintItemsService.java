/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.WaybillPrintItems;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

/**
 * Description	 : service类 WaybillPrintItemsService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class WaybillPrintItemsService {

	@Resource(name = "waybillPrintItemsDao")
	private Dao<WaybillPrintItems> waybillPrintItemsDao;

	public int addWaybillPrintItems(WaybillPrintItems waybillPrintItems) {
		int rows = waybillPrintItemsDao.save(waybillPrintItems);
		return rows;
	}

	public int updateWaybillPrintItems(WaybillPrintItems waybillPrintItems) {
		int rows = waybillPrintItemsDao.dynamicUpdate(waybillPrintItems);
		return rows;
	}

	public int deleteWaybillPrintItems(Integer id) {
		int rows = waybillPrintItemsDao.delete(id);
		return rows;
	}

	public int batchDeleteWaybillPrintItemss(Integer[] ids) {
		int rows = waybillPrintItemsDao.batchDelete(ids);
		return rows;
	}

	public WaybillPrintItems getWaybillPrintItems(Integer id) {
		WaybillPrintItems waybillPrintItems = waybillPrintItemsDao.get(id);
		return waybillPrintItems;
	}

	public List<WaybillPrintItems> getAllWaybillPrintItemss() {
		List<WaybillPrintItems> list = waybillPrintItemsDao.findAll();
		return list;
	}

	/**
	 * 根据运单模板id，查询出对应的打印项列表.
	 * 
	 * @param wateId 运单模板id.
	 * @return
	 */
	public List<WaybillPrintItems> getWaybillPrintItemsByWateId(Integer wateId) {
		List<WaybillPrintItems> waybillPrintItems = waybillPrintItemsDao.findByProperty("wateId", wateId);
		return waybillPrintItems;
	}

}

