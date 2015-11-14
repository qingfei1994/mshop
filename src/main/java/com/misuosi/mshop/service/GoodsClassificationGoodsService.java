/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.GoodsClassificationGoods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 GoodsClassificationGoodsService
 * <br><br>Time	 : 2015/07/14 08:48
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class GoodsClassificationGoodsService {

	@Resource(name = "goodsClassificationGoodsDao")
	private Dao<GoodsClassificationGoods> goodsClassificationGoodsDao;

	public int addGoodsClassificationGoods(GoodsClassificationGoods goodsClassificationGoods) {
		int rows = goodsClassificationGoodsDao.save(goodsClassificationGoods);
		return rows;
	}

	public int updateGoodsClassificationGoods(GoodsClassificationGoods goodsClassificationGoods) {
		int rows = goodsClassificationGoodsDao.dynamicUpdate(goodsClassificationGoods);
		return rows;
	}

	public int deleteGoodsClassificationGoods(Integer id) {
		int rows = goodsClassificationGoodsDao.delete(id);
		return rows;
	}

	public int batchDeleteGoodsClassificationGoodss(Integer[] ids) {
		int rows = goodsClassificationGoodsDao.batchDelete(ids);
		return rows;
	}

	public GoodsClassificationGoods getGoodsClassificationGoods(Integer id) {
		GoodsClassificationGoods goodsClassificationGoods = goodsClassificationGoodsDao.get(id);
		return goodsClassificationGoods;
	}

	public List<GoodsClassificationGoods> getAllGoodsClassificationGoodss() {
		List<GoodsClassificationGoods> list = goodsClassificationGoodsDao.findAll();
		return list;
	}

}

