package com.misuosi.mshop.module.admin.shop.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.MultiTableDao;
import com.misuosi.mshop.db.dao.TreeDao;
import com.misuosi.mshop.entity.Goods;
import com.misuosi.mshop.entity.IndexGoods;
import com.misuosi.mshop.entity.IndexGroup;

@Service
public class AdminShopShowService {
	@Resource(name = "goodsDao")
	private Dao<Goods> goodsDao;
	@Resource(name = "indexGroupDao")
	private Dao<IndexGroup> indexGroupDao;
	@Resource(name = "indexGoodsDao")
	private Dao<IndexGoods> indexGoodsDao;

	/**
	 * 取得所需商品的id,name,url,price
	 */
	public List<Map<String, Object>> getGoodsInformation() {
		StringBuilder sb = new StringBuilder();
		sb.append(" select g1.good_name,g1.good_id,g1.good_price,g2.gopi_url from goods g1 ");
		sb.append(" inner JOIN goods_picture  g2 ON");
		sb.append(" g1.good_id = g2.good_id");
		List<Map<String, Object>> goods = ((MultiTableDao) goodsDao)
				.queryForList(sb.toString());
		return goods;

	}
	
	/**
	 * 获取分组内容，以QueryTree方法查询
	 */
	public List<IndexGroup> getIndexGroups(){
		List<IndexGroup> groups = ((TreeDao)indexGroupDao).queryForTree("select * from index_group order by ingr_sort asc");
		return groups;
		
	}
	
	/**
	 * 删除父分组
	 */
	public int deleteParent(Integer ingrId){

		StringBuilder sb = new StringBuilder();
		sb.append("delete from index_goods where ingr_id = ?");
		int row = indexGoodsDao.batchDelete(sb.toString(), ingrId);
		return row ;
	}
	
	public void updateIndexGroupSort(List<Map<String,Object>> data) {
			for(int i=0;i<data.size();i++) {
				Map<String,Object> group=data.get(i);
				String temp=((String)group.get("id")).substring(6);
				int id=Integer.parseInt(temp);
				IndexGroup indexGroup = indexGroupDao.get(id);
				indexGroup.setIngrSort(Byte.valueOf(Integer.toString(i)));
				indexGroupDao.dynamicUpdate(indexGroup);
				List<Map<String,Object>> children=(List<Map<String, Object>>) group.get("children");
				if(children!=null) {
					for(int j=0;j<children.size();j++) {
						String child=((String)(children.get(j).get("id"))).substring(5);
						int childId=Integer.parseInt(child);
						IndexGoods indexGoods = indexGoodsDao.get(childId);
						indexGoods.setIngoSort(Byte.valueOf(Integer.toString(j)));
						indexGoods.setIngrId(id);
						indexGoodsDao.dynamicUpdate(indexGoods);
					}
				}
				
 			}
	}
}
