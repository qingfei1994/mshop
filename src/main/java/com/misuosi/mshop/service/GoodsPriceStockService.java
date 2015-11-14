/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.MultiTableDao;
import com.misuosi.mshop.entity.GoodsPriceStock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description	 : service类 GoodsPriceStockService
 * <br><br>Time	 : 2015/05/30 21:37
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class GoodsPriceStockService {

	@Resource(name = "goodsPriceStockDao")
	private Dao<GoodsPriceStock> goodsPriceStockDao;

	public int addGoodsPriceStock(GoodsPriceStock goodsPriceStock) {
		int rows = goodsPriceStockDao.save(goodsPriceStock);
		return rows;
	}

	public int updateGoodsPriceStock(GoodsPriceStock goodsPriceStock) {
		int rows = goodsPriceStockDao.dynamicUpdate(goodsPriceStock);
		return rows;
	}

	public int deleteGoodsPriceStock(Integer id) {
		int rows = goodsPriceStockDao.delete(id);
		return rows;
	}

	public int batchDeleteGoodsPriceStocks(Integer[] ids) {
		int rows = goodsPriceStockDao.batchDelete(ids);
		return rows;
	}

	public GoodsPriceStock getGoodsPriceStock(Integer id) {
		GoodsPriceStock goodsPriceStock = goodsPriceStockDao.get(id);
		return goodsPriceStock;
	}

	public List<GoodsPriceStock> getAllGoodsPriceStocks() {
		List<GoodsPriceStock> list = goodsPriceStockDao.findAll();
		return list;
	}

	public List<GoodsPriceStock> getGoodsPriceStocksByGoodId(Integer goodId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT gpst.* FROM goods_price_stock AS gpst ");
		sql.append("INNER JOIN goods_goods_specification_value AS ggsv ");
		sql.append("ON gpst.gpst_id=ggsv.gpst_id ");
		sql.append("WHERE ggsv.good_id=?");
		List<GoodsPriceStock> list = goodsPriceStockDao.find(sql.toString(), goodId);
		return list;
	}

	/**
	 * 通过商品ID获取价格&库存的ID
	 *
	 * @param goodIds
	 * @return
	 */
	public List<Integer> getGpstIdsByGoodIds(Integer[] goodIds) {
		StringBuilder strBuf = new StringBuilder();
		for (Integer goodId : goodIds) {
			strBuf.append(goodId).append(",");
		}
		String str = strBuf.deleteCharAt(strBuf.length() - 1).toString();

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT gpst.gpst_id FROM goods_price_stock AS gpst ");
		sql.append("INNER JOIN goods_goods_specification_value AS ggsv ");
		sql.append("ON gpst.gpst_id=ggsv.gpst_id ");
		sql.append("WHERE ggsv.good_id in(").append(str).append(")");
		List<Map<String, Object>> list = ((MultiTableDao) goodsPriceStockDao).queryForList(sql.toString());

		List<Integer> resultList = new ArrayList<Integer>();
		for (Map<String, Object> map : list) {
			resultList.add(Integer.parseInt(map.get("gpst_id").toString()));
		}
		return resultList;
	}

}

