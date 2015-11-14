/*
 * Copyright (c) 2015
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.module.wap.usercenter.service;

import com.misuosi.mshop.common.context.SessionContextHolder;
import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.MultiTableDao;
import com.misuosi.mshop.db.page.Pagination;
import com.misuosi.mshop.entity.CollectGoods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Description		: 
 * <p/>
 * <br><br>Time		: 2015-6-19 下午8:22:44
 *
 * @author YLM
 * @version 1.0
 * @since 1.0
 */
@Service
public class WapCollectService {

	@Resource(name = "collectGoodsDao")
	private Dao<CollectGoods> collectGoodsDao;
	
	public Pagination<Map<String, Object>> getCollectList(int pageNo, int pageSize) {
		int weinId = SessionContextHolder.getWechatInformationId();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT collect_goods.cogo_id, goods.good_id, good_name, good_price,")
			.append(" good_first_picture, good_sales FROM collect_goods")
			.append(" INNER JOIN goods ON goods.good_id = collect_goods.good_id")
			.append(" WHERE collect_goods.wein_id = ?")
			.append(" ORDER BY collect_goods.cogo_id DESC");
		
		Pagination<Map<String, Object>> pagination = ((MultiTableDao)collectGoodsDao).queryForPagination(sql.toString(), pageNo, pageSize, weinId);
		return pagination;
        
	}
	
	/**
	 * 通过商品ID获得购物车pojo
	 * 
	 * @param goodId
	 * @return
	 */
	public Map<String, Object> getGoodsInfoBygoodId(int goodId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT good.good_id, good.good_price, gpst.gpst_id, gpst.gpst_stock")
        	.append(" FROM goods AS good")
        	.append(" INNER JOIN goods_goods_specification_value AS ggsv")
        	.append(" ON good.good_id=ggsv.good_id")
        	.append(" INNER JOIN goods_price_stock AS gpst")
        	.append(" ON ggsv.gpst_id=gpst.gpst_id")
        	.append(" WHERE good.good_id = ?");
		return ((MultiTableDao)collectGoodsDao).queryForMap(sql.toString(), goodId);
	}

}

