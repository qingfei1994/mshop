package com.misuosi.mshop.module.wap.shop.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.MultiTableDao;
import com.misuosi.mshop.entity.Goods;

@Service
public class WapShopCarouselService {
	@Resource(name = "goodsDao")
	private Dao<Goods> goodsDao;
	
	/**
	 * 取得所需商品的id,name,url,price
	 */
	public List<Map<String, Object>> getGoodsInformation(){
		StringBuilder sb = new StringBuilder();
		sb.append(" select g1.good_name,g1.good_id,g1.good_price,g2.gopi_url from goods g1 ");
		sb.append(" inner JOIN goods_picture  g2 ON");
		sb.append(" g1.good_id = g2.good_id");
		List<Map<String,Object>> goods = ((MultiTableDao)goodsDao).queryForList(sb.toString());
		return goods;
		
	}
}
