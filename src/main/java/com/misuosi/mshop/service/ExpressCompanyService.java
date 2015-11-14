/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.ExpressCompany;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 ExpressCompanyService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class ExpressCompanyService {

	@Resource(name = "expressCompanyDao")
	private Dao<ExpressCompany> expressCompanyDao;

	public int addExpressCompany(ExpressCompany expressCompany) {
		int rows = expressCompanyDao.save(expressCompany);
		return rows;
	}

	public int updateExpressCompany(ExpressCompany expressCompany) {
		int rows = expressCompanyDao.dynamicUpdate(expressCompany);
		return rows;
	}

	public int deleteExpressCompany(Integer id) {
		int rows = expressCompanyDao.delete(id);
		return rows;
	}

	public int batchDeleteExpressCompanys(Integer[] ids) {
		int rows = expressCompanyDao.batchDelete(ids);
		return rows;
	}

	public ExpressCompany getExpressCompany(Integer id) {
		ExpressCompany expressCompany = expressCompanyDao.get(id);
		return expressCompany;
	}

	public List<ExpressCompany> getAllExpressCompanys() {
		List<ExpressCompany> list = expressCompanyDao.findAll();
		return list;
	}

}

