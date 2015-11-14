/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.orders.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.MultiTableDao;
import com.misuosi.mshop.entity.GoodsComment;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Description		: 
 * <p/>
 * <br><br>Time		: 2015年6月8日 下午6:13:52
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
@Service
public class AdminOrderCommentService {
	
	@Resource(name = "goodsCommentDao")
	private Dao<GoodsComment> goodsCommentDao;

	/**
	 * 根据供货商Id,获得评论列表信息.
	 *
	 * @return
	 */
	public List<Map<String, Object>> getGoodsCommentMap() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT goods_comment.*, ");
		sb.append("goods_order.goor_name,goods_order.goor_price, goods_order.goor_first_picture, ");
		sb.append("order_information.orin_id, ");
		sb.append("wechat_information.wein_nickname ");
		sb.append("FROM goods_comment ");
		sb.append("INNER JOIN goods_order ON goods_order.goor_id = goods_comment.goor_id ");
		sb.append("INNER JOIN order_information ON order_information.orin_id = goods_order.orin_id ");
		sb.append("INNER JOIN wechat_information ON wechat_information.wein_id = order_information.wein_id ");
		
		String sql = sb.toString();
		List<Map<String, Object>> maps = ((MultiTableDao)goodsCommentDao).queryForList(sql);
		return maps;
	}

}
