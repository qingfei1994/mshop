/*
 * Copyright (c) 2015
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.IndexBanner;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 IndexBannerService
 * <br><br>Time	 : 2015/07/28 11:28
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class IndexBannerService {

	@Resource(name = "indexBannerDao")
	private Dao<IndexBanner> indexBannerDao;

	public int addIndexBanner(IndexBanner indexBanner) {
		int rows = indexBannerDao.save(indexBanner);
		return rows;
	}

	public int updateIndexBanner(IndexBanner indexBanner) {
		int rows = indexBannerDao.dynamicUpdate(indexBanner);
		return rows;
	}

	public int deleteIndexBanner(Integer id) {
		int rows = indexBannerDao.delete(id);
		return rows;
	}

	public int batchDeleteIndexBanners(Integer[] ids) {
		int rows = indexBannerDao.batchDelete(ids);
		return rows;
	}

	public IndexBanner getIndexBanner(Integer id) {
		IndexBanner indexBanner = indexBannerDao.get(id);
		return indexBanner;
	}

	public List<IndexBanner> getAllIndexBanners() {
		List<IndexBanner> list = indexBannerDao.findAll();
		return list;
	}

}

