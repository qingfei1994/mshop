/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.AlipayPaySetting;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 AlipayPaySettingService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class AlipayPaySettingService {

	@Resource(name = "alipayPaySettingDao")
	private Dao<AlipayPaySetting> alipayPaySettingDao;

	public int addAlipayPaySetting(AlipayPaySetting alipayPaySetting) {
		int rows = alipayPaySettingDao.save(alipayPaySetting);
		return rows;
	}

	public int updateAlipayPaySetting(AlipayPaySetting alipayPaySetting) {
		int rows = alipayPaySettingDao.dynamicUpdate(alipayPaySetting);
		return rows;
	}

	public int deleteAlipayPaySetting(Integer id) {
		int rows = alipayPaySettingDao.delete(id);
		return rows;
	}

	public int batchDeleteAlipayPaySettings(Integer[] ids) {
		int rows = alipayPaySettingDao.batchDelete(ids);
		return rows;
	}

	public AlipayPaySetting getAlipayPaySetting(Integer id) {
		AlipayPaySetting alipayPaySetting = alipayPaySettingDao.get(id);
		return alipayPaySetting;
	}

	public List<AlipayPaySetting> getAllAlipayPaySettings() {
		List<AlipayPaySetting> list = alipayPaySettingDao.findAll();
		return list;
	}

}

