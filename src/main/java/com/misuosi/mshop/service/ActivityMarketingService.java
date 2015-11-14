/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.ActivityMarketing;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 ActivityMarketingService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class ActivityMarketingService {

	@Resource(name = "activityMarketingDao")
	private Dao<ActivityMarketing> activityMarketingDao;

	public int addActivityMarketing(ActivityMarketing activityMarketing) {
		int rows = activityMarketingDao.save(activityMarketing);
		return rows;
	}

	public int updateActivityMarketing(ActivityMarketing activityMarketing) {
		int rows = activityMarketingDao.dynamicUpdate(activityMarketing);
		return rows;
	}

	public int deleteActivityMarketing(Integer id) {
		int rows = activityMarketingDao.delete(id);
		return rows;
	}

	public int batchDeleteActivityMarketings(Integer[] ids) {
		int rows = activityMarketingDao.batchDelete(ids);
		return rows;
	}

	public ActivityMarketing getActivityMarketing(Integer id) {
		ActivityMarketing activityMarketing = activityMarketingDao.get(id);
		return activityMarketing;
	}

	public List<ActivityMarketing> getAllActivityMarketings() {
		List<ActivityMarketing> list = activityMarketingDao.findAll();
		return list;
	}

}

