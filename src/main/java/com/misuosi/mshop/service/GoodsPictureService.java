/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.GoodsPicture;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 GoodsPictureService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class GoodsPictureService {

	@Resource(name = "goodsPictureDao")
	private Dao<GoodsPicture> goodsPictureDao;

	public int addGoodsPicture(GoodsPicture goodsPicture) {
		int rows = goodsPictureDao.save(goodsPicture);
		return rows;
	}

	public int updateGoodsPicture(GoodsPicture goodsPicture) {
		int rows = goodsPictureDao.dynamicUpdate(goodsPicture);
		return rows;
	}

	public int deleteGoodsPicture(Integer id) {
		int rows = goodsPictureDao.delete(id);
		return rows;
	}

	public int batchDeleteGoodsPictures(Integer[] ids) {
		int rows = goodsPictureDao.batchDelete(ids);
		return rows;
	}

	public GoodsPicture getGoodsPicture(Integer id) {
		GoodsPicture goodsPicture = goodsPictureDao.get(id);
		return goodsPicture;
	}

	public List<GoodsPicture> getAllGoodsPictures() {
		List<GoodsPicture> list = goodsPictureDao.findAll();
		return list;
	}

	public List<GoodsPicture> getGoodsPicturesByGoodId(Integer goodId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM goods_picture ");
		sql.append("WHERE good_id=? ");
		sql.append("ORDER BY gopi_sort ASC, gopi_id ASC ");
		List<GoodsPicture> list = goodsPictureDao.find(sql.toString(), goodId);
		return list;
	}

}

