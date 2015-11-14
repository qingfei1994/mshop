/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.ActivityCommodity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 ActivityCommodityService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class ActivityCommodityService {

	@Resource(name = "activityCommodityDao")
	private Dao<ActivityCommodity> activityCommodityDao;

	public int addActivityCommodity(ActivityCommodity activityCommodity) {
		int rows = activityCommodityDao.save(activityCommodity);
		return rows;
	}

	public int updateActivityCommodity(ActivityCommodity activityCommodity) {
		int rows = activityCommodityDao.dynamicUpdate(activityCommodity);
		return rows;
	}

	public int deleteActivityCommodity(Integer id) {
		int rows = activityCommodityDao.delete(id);
		return rows;
	}

	public int batchDeleteActivityCommoditys(Integer[] ids) {
		int rows = activityCommodityDao.batchDelete(ids);
		return rows;
	}

	public ActivityCommodity getActivityCommodity(Integer id) {
		ActivityCommodity activityCommodity = activityCommodityDao.get(id);
		return activityCommodity;
	}

	public List<ActivityCommodity> getAllActivityCommoditys() {
		List<ActivityCommodity> list = activityCommodityDao.findAll();
		return list;
	}

}

