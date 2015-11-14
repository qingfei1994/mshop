/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.ActivityLabel;
import com.misuosi.mshop.entity.PriceLabel;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 ActivityLabelService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class ActivityLabelService {

	@Resource(name = "activityLabelDao")
	private Dao<ActivityLabel> activityLabelDao;

	public int addActivityLabel(ActivityLabel activityLabel) {
		int rows = activityLabelDao.save(activityLabel);
		return rows;
	}

	public int updateActivityLabel(ActivityLabel activityLabel) {
		int rows = activityLabelDao.dynamicUpdate(activityLabel);
		return rows;
	}

	public int deleteActivityLabel(Integer id) {
		int rows = activityLabelDao.delete(id);
		return rows;
	}

	public int batchDeleteActivityLabels(Integer[] ids) {
		int rows = activityLabelDao.batchDelete(ids);
		return rows;
	}

	public ActivityLabel getActivityLabel(Integer id) {
		ActivityLabel activityLabel = activityLabelDao.get(id);
		return activityLabel;
	}

	public List<ActivityLabel> getAllActivityLabels() {
		List<ActivityLabel> list = activityLabelDao.findAll();
		return list;
	}

	/**
	 * 获取内置的活动标签 add by chq
	 * @return
	 */
	public List<ActivityLabel> getAllSystemActivityLabels() {
		List<ActivityLabel> list=activityLabelDao.findByProperty("acla_type", "1");
		return list;
	}

}

