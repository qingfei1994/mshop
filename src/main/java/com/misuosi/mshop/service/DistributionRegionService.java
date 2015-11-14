/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.DistributionRegion;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 DistributionRegionService
 * <br><br>Time	 : 2015/06/03 21:16
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class DistributionRegionService {

	@Resource(name = "distributionRegionDao")
	private Dao<DistributionRegion> distributionRegionDao;

	public int addDistributionRegion(DistributionRegion distributionRegion) {
		int rows = distributionRegionDao.save(distributionRegion);
		return rows;
	}

	public int updateDistributionRegion(DistributionRegion distributionRegion) {
		int rows = distributionRegionDao.dynamicUpdate(distributionRegion);
		return rows;
	}

	public int deleteDistributionRegion(Integer id) {
		int rows = distributionRegionDao.delete(id);
		return rows;
	}

	public int batchDeleteDistributionRegions(Integer[] ids) {
		int rows = distributionRegionDao.batchDelete(ids);
		return rows;
	}

	public DistributionRegion getDistributionRegion(Integer id) {
		DistributionRegion distributionRegion = distributionRegionDao.get(id);
		return distributionRegion;
	}

	public List<DistributionRegion> getAllDistributionRegions() {
		List<DistributionRegion> list = distributionRegionDao.findAll();
		return list;
	}

}

