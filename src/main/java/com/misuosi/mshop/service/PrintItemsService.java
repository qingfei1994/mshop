/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.PrintItems;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 PrintItemsService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class PrintItemsService {

	@Resource(name = "printItemsDao")
	private Dao<PrintItems> printItemsDao;

	public int addPrintItems(PrintItems printItems) {
		int rows = printItemsDao.save(printItems);
		return rows;
	}

	public int updatePrintItems(PrintItems printItems) {
		int rows = printItemsDao.dynamicUpdate(printItems);
		return rows;
	}

	public int deletePrintItems(Integer id) {
		int rows = printItemsDao.delete(id);
		return rows;
	}

	public int batchDeletePrintItemss(Integer[] ids) {
		int rows = printItemsDao.batchDelete(ids);
		return rows;
	}

	public PrintItems getPrintItems(Integer id) {
		PrintItems printItems = printItemsDao.get(id);
		return printItems;
	}

	public List<PrintItems> getAllPrintItemss() {
		List<PrintItems> list = printItemsDao.findAll();
		return list;
	}

}

