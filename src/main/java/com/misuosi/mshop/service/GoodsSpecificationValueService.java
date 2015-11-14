/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.GoodsSpecificationValue;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 GoodsSpecificationValueService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class GoodsSpecificationValueService {

	@Resource(name = "goodsSpecificationValueDao")
	private Dao<GoodsSpecificationValue> goodsSpecificationValueDao;

	public int addGoodsSpecificationValue(GoodsSpecificationValue goodsSpecificationValue) {
		int rows = goodsSpecificationValueDao.save(goodsSpecificationValue);
		return rows;
	}

	public int updateGoodsSpecificationValue(GoodsSpecificationValue goodsSpecificationValue) {
		int rows = goodsSpecificationValueDao.dynamicUpdate(goodsSpecificationValue);
		return rows;
	}

	public int deleteGoodsSpecificationValue(Integer id) {
		int rows = goodsSpecificationValueDao.delete(id);
		return rows;
	}

	public int batchDeleteGoodsSpecificationValues(Integer[] ids) {
		int rows = goodsSpecificationValueDao.batchDelete(ids);
		return rows;
	}

	public GoodsSpecificationValue getGoodsSpecificationValue(Integer id) {
		GoodsSpecificationValue goodsSpecificationValue = goodsSpecificationValueDao.get(id);
		return goodsSpecificationValue;
	}

	public List<GoodsSpecificationValue> getAllGoodsSpecificationValues() {
		List<GoodsSpecificationValue> list = goodsSpecificationValueDao.findAll();
		return list;
	}

}

