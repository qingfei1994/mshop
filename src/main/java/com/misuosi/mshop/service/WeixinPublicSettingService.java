/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.WeixinPublicSetting;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 WeixinPublicSettingService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class WeixinPublicSettingService {

	@Resource(name = "weixinPublicSettingDao")
	private Dao<WeixinPublicSetting> weixinPublicSettingDao;

	public int addWeixinPublicSetting(WeixinPublicSetting weixinPublicSetting) {
		int rows = weixinPublicSettingDao.save(weixinPublicSetting);
		return rows;
	}

	public int updateWeixinPublicSetting(WeixinPublicSetting weixinPublicSetting) {
		int rows = weixinPublicSettingDao.dynamicUpdate(weixinPublicSetting);
		return rows;
	}

	public int deleteWeixinPublicSetting(Integer id) {
		int rows = weixinPublicSettingDao.delete(id);
		return rows;
	}

	public int batchDeleteWeixinPublicSettings(Integer[] ids) {
		int rows = weixinPublicSettingDao.batchDelete(ids);
		return rows;
	}

	public WeixinPublicSetting getWeixinPublicSetting(Integer id) {
		WeixinPublicSetting weixinPublicSetting = weixinPublicSettingDao.get(id);
		return weixinPublicSetting;
	}

	public List<WeixinPublicSetting> getAllWeixinPublicSettings() {
		List<WeixinPublicSetting> list = weixinPublicSettingDao.findAll();
		return list;
	}

}

