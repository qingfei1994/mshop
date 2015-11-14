package com.misuosi.mshop.module.admin.shop.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.MultiTableDao;
import com.misuosi.mshop.entity.Goods;
import com.misuosi.mshop.entity.IndexBanner;

@Service
public class AdminShopCarouselService {
	@Resource(name = "goodsDao")
	private Dao<Goods> goodsDao;
	@Resource(name = "indexBannerDao")
	private Dao<IndexBanner> indexBannerDao;
	private final String GOOD_URL_HEAD="wap/goods/";
	private final String GOOD_URL_TAIL="/detail";
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
	public List<Map<String,Object>> getIndexBannerInformation() {
		StringBuilder sb = new StringBuilder();
		sb.append(" select  banner.*,goods.good_name from index_banner banner");
		sb.append(" left JOIN goods ON");
		sb.append(" banner.good_id = goods.good_id");
		List<Map<String,Object>> goods = ((MultiTableDao)goodsDao).queryForList(sb.toString());
		return goods;
	}
	public  void updateIndexBanner(List<Map<String,Object>> list) {
		for(int i=0;i<list.size();i++) {
			int inacId=Integer.parseInt((String) list.get(i).get("id"));
			IndexBanner indexBanner = indexBannerDao.get(inacId);
			int goodId=Integer.parseInt((String) list.get(i).get("goodId"));
			String image=(String) list.get(i).get("image");
			indexBanner.setGoodId(goodId);
			indexBanner.setInbaDetailUrl(GOOD_URL_HEAD+goodId+GOOD_URL_TAIL);
			indexBanner.setInbaImageUrl(image);
			indexBanner.setInbaIsVisible(true);
			indexBanner.setInbaSort(Byte.valueOf(i+""));
			indexBannerDao.dynamicUpdate(indexBanner);
			
		}
	}
}
