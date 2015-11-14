/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.CollectGoods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 CollectGoodsService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class CollectGoodsService {

	@Resource(name = "collectGoodsDao")
	private Dao<CollectGoods> collectGoodsDao;

	public int addCollectGoods(CollectGoods collectGoods) {
		int rows = collectGoodsDao.save(collectGoods);
		return rows;
	}

	public int updateCollectGoods(CollectGoods collectGoods) {
		int rows = collectGoodsDao.dynamicUpdate(collectGoods);
		return rows;
	}

	public int deleteCollectGoods(Integer id) {
		int rows = collectGoodsDao.delete(id);
		return rows;
	}

	public int batchDeleteCollectGoodss(Integer[] ids) {
		int rows = collectGoodsDao.batchDelete(ids);
		return rows;
	}

	public CollectGoods getCollectGoods(Integer id) {
		CollectGoods collectGoods = collectGoodsDao.get(id);
		return collectGoods;
	}

	public List<CollectGoods> getAllCollectGoodss() {
		List<CollectGoods> list = collectGoodsDao.findAll();
		return list;
	}

	public int addCollectGoodsOrCancel(CollectGoods collectGoods) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT *");
		sb.append(" FROM collect_goods");
		sb.append(" WHERE wein_id = ?");
		sb.append(" AND good_id = ?");

		int rows = 0;
		List<CollectGoods> checkCollectGoods = collectGoodsDao.find(sb.toString(), collectGoods.getWeinId(), collectGoods.getGoodId());
		if (checkCollectGoods.size() <= 0) {
			rows = collectGoodsDao.save(collectGoods);
		} else {
			rows = collectGoodsDao.delete(checkCollectGoods.get(0).getCogoId());
		}

		return rows;
	}

}

