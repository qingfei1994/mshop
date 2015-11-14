/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.WeixinPaySetting;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 WeixinPaySettingService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class WeixinPaySettingService {

	@Resource(name = "weixinPaySettingDao")
	private Dao<WeixinPaySetting> weixinPaySettingDao;

	public int addWeixinPaySetting(WeixinPaySetting weixinPaySetting) {
		int rows = weixinPaySettingDao.save(weixinPaySetting);
		return rows;
	}

	public int updateWeixinPaySetting(WeixinPaySetting weixinPaySetting) {
		int rows = weixinPaySettingDao.dynamicUpdate(weixinPaySetting);
		return rows;
	}

	public int deleteWeixinPaySetting(Integer id) {
		int rows = weixinPaySettingDao.delete(id);
		return rows;
	}

	public int batchDeleteWeixinPaySettings(Integer[] ids) {
		int rows = weixinPaySettingDao.batchDelete(ids);
		return rows;
	}

	public WeixinPaySetting getWeixinPaySetting(Integer id) {
		WeixinPaySetting weixinPaySetting = weixinPaySettingDao.get(id);
		return weixinPaySetting;
	}

	public List<WeixinPaySetting> getAllWeixinPaySettings() {
		List<WeixinPaySetting> list = weixinPaySettingDao.findAll();
		return list;
	}

}

