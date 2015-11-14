/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.GuideFollow;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 GuideFollowService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class GuideFollowService {

	@Resource(name = "guideFollowDao")
	private Dao<GuideFollow> guideFollowDao;

	public int addGuideFollow(GuideFollow guideFollow) {
		int rows = guideFollowDao.save(guideFollow);
		return rows;
	}

	public int updateGuideFollow(GuideFollow guideFollow) {
		int rows = guideFollowDao.dynamicUpdate(guideFollow);
		return rows;
	}

	public int deleteGuideFollow(Integer id) {
		int rows = guideFollowDao.delete(id);
		return rows;
	}

	public int batchDeleteGuideFollows(Integer[] ids) {
		int rows = guideFollowDao.batchDelete(ids);
		return rows;
	}

	public GuideFollow getGuideFollow(Integer id) {
		GuideFollow guideFollow = guideFollowDao.get(id);
		return guideFollow;
	}

	public List<GuideFollow> getAllGuideFollows() {
		List<GuideFollow> list = guideFollowDao.findAll();
		return list;
	}

}

