/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.TreeDao;
import com.misuosi.mshop.entity.Goods;

/**
 * Description	 : service类 GoodsService
 * <br><br>Time	 : 2015/05/30 21:37
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class GoodsService {

	@Resource(name = "goodsDao")
	private Dao<Goods> goodsDao;

	public int addGoods(Goods goods) {
		int rows = goodsDao.save(goods);
		return rows;
	}

	public int updateGoods(Goods goods) {
		int rows = goodsDao.dynamicUpdate(goods);
		return rows;
	}

	public int deleteGoods(Integer id) {
		int rows = goodsDao.delete(id);
		return rows;
	}

	public int batchDeleteGoodss(Integer[] ids) {
		int rows = goodsDao.batchDelete(ids);
		return rows;
	}

	public Goods getGoods(Integer id) {
		Goods goods = goodsDao.get(id);
		return goods;
	}

	public List<Goods> getAllGoodss() {
		List<Goods> list = goodsDao.findAll();
		return list;
	}
	/**
	 * 根据供货商Id获取商品信息 add by chq
	 * @param suppId
	 * @return
	 */
	public List<Goods> getAllGoodssBySuppId(Integer suppId) {
		List<Goods> list=goodsDao.findByProperty("suppId",suppId);
		return list;
	}

	/**
	 * @return
	 */
	public List<Goods> getGoodsByGoodsIds(List<String> goodsIds) {
		List<Goods> goods=new ArrayList<Goods>();
		for(String id:goodsIds) {
			Goods temp=getGoods(Integer.parseInt(id));
			goods.add(temp);
		}
		return goods;
	}

	public List<Goods> getGoodsTree() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DISTINCT good.* FROM goods AS good ");
		sql.append("INNER JOIN goods_classification_goods AS gcgo ");
		sql.append("ON good.good_id=gcgo.good_id ");
		sql.append("INNER JOIN goods_goods_specification_value AS ggsv ");
		sql.append("ON good.good_id=ggsv.good_id ");
		sql.append("INNER JOIN goods_price_stock AS gpst ");
		sql.append("ON ggsv.gpst_id=gpst.gpst_id");
		sql.append(" ORDER BY good_id DESC");
		List<Goods> list = ((TreeDao<Goods>) goodsDao).queryForTree(sql.toString());
		return list;
	}

	/**
	 * 批量更新商品上/下架状态
	 *
	 * @param ids
	 * @param goodsPutawayType
	 * @return
	 */
	public int batchUpdateGoodPutAwayType(Integer[] ids, Byte goodsPutawayType) {
		int rows = goodsDao.batchUpdate("goodPutawayType", goodsPutawayType, ids);
		return rows;
	}

}

