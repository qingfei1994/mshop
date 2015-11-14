/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.TreeDao;
import com.misuosi.mshop.entity.GoodsOrder;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Description	 : service类 GoodsOrderService
 * <br><br>Time	 : 2015/06/09 08:56
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class GoodsOrderService {

	@Resource(name = "goodsOrderDao")
	private Dao<GoodsOrder> goodsOrderDao;

	public int addGoodsOrder(GoodsOrder goodsOrder) {
		int rows = goodsOrderDao.save(goodsOrder);
		return rows;
	}

	public int updateGoodsOrder(GoodsOrder goodsOrder) {
		int rows = goodsOrderDao.dynamicUpdate(goodsOrder);
		return rows;
	}

	public int deleteGoodsOrder(Integer id) {
		int rows = goodsOrderDao.delete(id);
		return rows;
	}

	public int batchDeleteGoodsOrders(Integer[] ids) {
		int rows = goodsOrderDao.batchDelete(ids);
		return rows;
	}

	public GoodsOrder getGoodsOrder(Integer id) {
		GoodsOrder goodsOrder = goodsOrderDao.get(id);
		return goodsOrder;
	}

	public List<GoodsOrder> getAllGoodsOrders() {
		List<GoodsOrder> list = goodsOrderDao.findAll();
		return list;
	}

	/**
	 * 批量查询
	 * 
	 * @param orinIds
	 * @return
	 */
	public List<GoodsOrder> getGoodsOrderByOrinIds(List<Integer> orinIds) {
//		StringBuilder sb = new StringBuilder();
//		sb.append("SELECT goods_order.* FROM goods_order ");
//		sb.append("INNER JOIN order_information ON order_information.orin_id = goods_order.orin_id ");
//		sb.append("WHERE order_information.orin_id = ? ");
//		
//		String sql = sb.toString();
		List<GoodsOrder> goodsOrders = new ArrayList<GoodsOrder>();
		for(Integer orinId : orinIds){
//			List<GoodsOrder> list = goodsOrderDao.find(sql, orinId);//
			List<GoodsOrder> list = goodsOrderDao.findByProperty("orin_id", orinId);
			goodsOrders.addAll(list);
		}
		
		return goodsOrders;
	}

	/**
	 * 获取已经申请了退款的商品订单
	 *
	 * @param weinId
	 * @return
	 */
	public List<GoodsOrder> getRefundGoodsOrdersByWeinId(Integer weinId) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM goods_order ");
		sql.append("INNER JOIN order_information ");
		sql.append("ON goods_order.orin_id=order_information.orin_id ");
		sql.append("WHERE goods_order.goor_refund_status=1 ");
		sql.append("AND order_information.wein_id=? ");

		List<GoodsOrder> goodsOrders = ((TreeDao) goodsOrderDao).queryForTree(sql.toString(), weinId);

		return goodsOrders;
	}

}

