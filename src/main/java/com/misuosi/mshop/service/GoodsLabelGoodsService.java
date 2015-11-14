/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.GoodsLabelGoods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 GoodsLabelGoodsService
 * <br><br>Time	 : 2015/07/14 08:48
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class GoodsLabelGoodsService {

	@Resource(name = "goodsLabelGoodsDao")
	private Dao<GoodsLabelGoods> goodsLabelGoodsDao;

	public int addGoodsLabelGoods(GoodsLabelGoods goodsLabelGoods) {
		int rows = goodsLabelGoodsDao.save(goodsLabelGoods);
		return rows;
	}

	public int updateGoodsLabelGoods(GoodsLabelGoods goodsLabelGoods) {
		int rows = goodsLabelGoodsDao.dynamicUpdate(goodsLabelGoods);
		return rows;
	}

	public int deleteGoodsLabelGoods(Integer id) {
		int rows = goodsLabelGoodsDao.delete(id);
		return rows;
	}

	public int batchDeleteGoodsLabelGoodss(Integer[] ids) {
		int rows = goodsLabelGoodsDao.batchDelete(ids);
		return rows;
	}

	public GoodsLabelGoods getGoodsLabelGoods(Integer id) {
		GoodsLabelGoods goodsLabelGoods = goodsLabelGoodsDao.get(id);
		return goodsLabelGoods;
	}

	public List<GoodsLabelGoods> getAllGoodsLabelGoodss() {
		List<GoodsLabelGoods> list = goodsLabelGoodsDao.findAll();
		return list;
	}

}

