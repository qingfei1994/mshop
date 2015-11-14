/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.GoodsGoodsSpecificationValue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 GoodsGoodsSpecificationValueService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class GoodsGoodsSpecificationValueService {

	@Resource(name = "goodsGoodsSpecificationValueDao")
	private Dao<GoodsGoodsSpecificationValue> goodsGoodsSpecificationValueDao;

	public int addGoodsGoodsSpecificationValue(GoodsGoodsSpecificationValue goodsGoodsSpecificationValue) {
		int rows = goodsGoodsSpecificationValueDao.save(goodsGoodsSpecificationValue);
		return rows;
	}

	public int updateGoodsGoodsSpecificationValue(GoodsGoodsSpecificationValue goodsGoodsSpecificationValue) {
		int rows = goodsGoodsSpecificationValueDao.dynamicUpdate(goodsGoodsSpecificationValue);
		return rows;
	}

	public int deleteGoodsGoodsSpecificationValue(Integer id) {
		int rows = goodsGoodsSpecificationValueDao.delete(id);
		return rows;
	}

	public int batchDeleteGoodsGoodsSpecificationValues(Integer[] ids) {
		int rows = goodsGoodsSpecificationValueDao.batchDelete(ids);
		return rows;
	}

	public GoodsGoodsSpecificationValue getGoodsGoodsSpecificationValue(Integer id) {
		GoodsGoodsSpecificationValue goodsGoodsSpecificationValue = goodsGoodsSpecificationValueDao.get(id);
		return goodsGoodsSpecificationValue;
	}

	public List<GoodsGoodsSpecificationValue> getAllGoodsGoodsSpecificationValues() {
		List<GoodsGoodsSpecificationValue> list = goodsGoodsSpecificationValueDao.findAll();
		return list;
	}

}

