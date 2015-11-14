/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.GoodsLabel;
import com.misuosi.mshop.module.common.manager.GoodsLabelManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 GoodsLabelService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class GoodsLabelService {

	@Resource(name = "goodsLabelDao")
	private Dao<GoodsLabel> goodsLabelDao;

	public int addGoodsLabel(GoodsLabel goodsLabel) {
		int rows = goodsLabelDao.save(goodsLabel);
		GoodsLabelManager.refresh();
		return rows;
	}

	public int updateGoodsLabel(GoodsLabel goodsLabel) {
		int rows = goodsLabelDao.dynamicUpdate(goodsLabel);
		GoodsLabelManager.refresh();
		return rows;
	}

	public int deleteGoodsLabel(Integer id) {
		int rows = goodsLabelDao.delete(id);
		GoodsLabelManager.refresh();
		return rows;
	}

	public int batchDeleteGoodsLabels(Integer[] ids) {
		int rows = goodsLabelDao.batchDelete(ids);
		GoodsLabelManager.refresh();
		return rows;
	}

	public GoodsLabel getGoodsLabel(Integer id) {
		GoodsLabel goodsLabel = goodsLabelDao.get(id);
		return goodsLabel;
	}

	public List<GoodsLabel> getAllGoodsLabels() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM goods_label ");
		sql.append("ORDER BY gola_sort ASC, gola_id ASC");
		List<GoodsLabel> list = goodsLabelDao.find(sql.toString());
		return list;
	}

	public List<GoodsLabel> getAllGoodsLabelsBySuppId(Integer suppId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM goods_label ");
		sql.append("WHERE supp_id=? ");
		sql.append("ORDER BY gola_sort ASC, gola_id ASC");
		List<GoodsLabel> list = goodsLabelDao.find(sql.toString(), suppId);
		return list;
	}
	
	//添加一个根据商品标签名查找全部符合条件的记录的查询方法
	public List<GoodsLabel> getGoodsLabelByGolaName(String golaName) {
		String sql = "SELECT * FROM goods_label WHERE gola_name = ?";
		List<GoodsLabel> goodsLabels = goodsLabelDao.find(sql, golaName);
		return goodsLabels;
	}

	public List<GoodsLabel> getGoodsLabelsByGoodId(Integer goodId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT gola.* FROM goods_label AS gola ");
		sql.append("INNER JOIN goods_label_goods glgo ");
		sql.append("ON gola.gola_id=glgo.gola_id ");
		sql.append("WHERE glgo.good_id=?");
		List<GoodsLabel> list = goodsLabelDao.find(sql.toString(), goodId);
		return list;
	}

}

