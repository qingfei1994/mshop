/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.GoodsSpecificationName;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 GoodsSpecificationNameService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class GoodsSpecificationNameService {

	@Resource(name = "goodsSpecificationNameDao")
	private Dao<GoodsSpecificationName> goodsSpecificationNameDao;

	public int addGoodsSpecificationName(GoodsSpecificationName goodsSpecificationName) {
		int rows = goodsSpecificationNameDao.save(goodsSpecificationName);
		return rows;
	}

	public int updateGoodsSpecificationName(GoodsSpecificationName goodsSpecificationName) {
		int rows = goodsSpecificationNameDao.dynamicUpdate(goodsSpecificationName);
		return rows;
	}

	public int deleteGoodsSpecificationName(Integer id) {
		int rows = goodsSpecificationNameDao.delete(id);
		return rows;
	}

	public int batchDeleteGoodsSpecificationNames(Integer[] ids) {
		int rows = goodsSpecificationNameDao.batchDelete(ids);
		return rows;
	}

	public GoodsSpecificationName getGoodsSpecificationName(Integer id) {
		GoodsSpecificationName goodsSpecificationName = goodsSpecificationNameDao.get(id);
		return goodsSpecificationName;
	}

	public List<GoodsSpecificationName> getAllGoodsSpecificationNames() {
		List<GoodsSpecificationName> list = goodsSpecificationNameDao.findAll();
		return list;
	}

}

