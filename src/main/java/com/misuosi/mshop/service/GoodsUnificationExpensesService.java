/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.GoodsUnificationExpenses;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 GoodsUnificationExpensesService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class GoodsUnificationExpensesService {

	@Resource(name = "goodsUnificationExpensesDao")
	private Dao<GoodsUnificationExpenses> goodsUnificationExpensesDao;

	public int addGoodsUnificationExpenses(GoodsUnificationExpenses goodsUnificationExpenses) {
		int rows = goodsUnificationExpensesDao.save(goodsUnificationExpenses);
		return rows;
	}

	public int updateGoodsUnificationExpenses(GoodsUnificationExpenses goodsUnificationExpenses) {
		int rows = goodsUnificationExpensesDao.dynamicUpdate(goodsUnificationExpenses);
		return rows;
	}

	public int deleteGoodsUnificationExpenses(Integer id) {
		int rows = goodsUnificationExpensesDao.delete(id);
		return rows;
	}

	public int batchDeleteGoodsUnificationExpensess(Integer[] ids) {
		int rows = goodsUnificationExpensesDao.batchDelete(ids);
		return rows;
	}

	public GoodsUnificationExpenses getGoodsUnificationExpenses(Integer id) {
		GoodsUnificationExpenses goodsUnificationExpenses = goodsUnificationExpensesDao.get(id);
		return goodsUnificationExpenses;
	}

	public List<GoodsUnificationExpenses> getAllGoodsUnificationExpensess() {
		List<GoodsUnificationExpenses> list = goodsUnificationExpensesDao.findAll();
		return list;
	}

	public GoodsUnificationExpenses getGoodsUnificationExpensesByGoodId(Integer goodId) {
		GoodsUnificationExpenses goodsUnificationExpenses =
				goodsUnificationExpensesDao.findUniqueByProperty("goodId", goodId);
		return goodsUnificationExpenses;
	}

}

