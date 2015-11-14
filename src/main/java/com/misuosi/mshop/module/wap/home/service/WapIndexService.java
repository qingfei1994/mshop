/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.wap.home.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.TreeDao;
import com.misuosi.mshop.entity.Goods;
import com.misuosi.mshop.entity.IndexBanner;
import com.misuosi.mshop.entity.IndexGoods;
import com.misuosi.mshop.entity.IndexGroup;

/**
 * Description :
 * <p/>
 * <br>
 * <br>
 * Time : 2015-8-12 上午9:14:17
 * 
 * @author CHQ
 * @version 1.0
 * @since 1.0
 */
@Service
public class WapIndexService {
	@Resource(name = "goodsDao")
	private Dao<Goods> goodsDao;
	@Resource(name = "indexBannerDao")
	private Dao<IndexBanner> indexBannerDao;
	@Resource(name = "indexGroupDao")
	private Dao<IndexGroup> indexGroupDao;
	@Resource(name = "indexGoodsDao")
	private Dao<IndexGoods> indexGoodsDao;

	public List<IndexGroup> queryForIndexGroup() {
		List<IndexGroup> groups = ((TreeDao) indexGroupDao)
				.queryForTree("select * from index_group order by ingr_sort asc");
		return groups;
	}

}
