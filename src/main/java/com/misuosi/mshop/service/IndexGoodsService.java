/*
 * Copyright (c) 2015
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.IndexGoods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 IndexGoodsService
 * <br><br>Time	 : 2015/07/28 11:28
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class IndexGoodsService {

	@Resource(name = "indexGoodsDao")
	private Dao<IndexGoods> indexGoodsDao;

	public int addIndexGoods(IndexGoods indexGoods) {
		int rows = indexGoodsDao.save(indexGoods);
		return rows;
	}

	public int updateIndexGoods(IndexGoods indexGoods) {
		int rows = indexGoodsDao.dynamicUpdate(indexGoods);
		return rows;
	}

	public int deleteIndexGoods(Integer id) {
		int rows = indexGoodsDao.delete(id);
		return rows;
	}

	public int batchDeleteIndexGoodss(Integer[] ids) {
		int rows = indexGoodsDao.batchDelete(ids);
		return rows;
	}

	public IndexGoods getIndexGoods(Integer id) {
		IndexGoods indexGoods = indexGoodsDao.get(id);
		return indexGoods;
	}

	public List<IndexGoods> getAllIndexGoodss() {
		List<IndexGoods> list = indexGoodsDao.findAll();
		return list;
	}

}

