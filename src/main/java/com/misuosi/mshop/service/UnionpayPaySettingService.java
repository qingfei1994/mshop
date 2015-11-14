/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.UnionpayPaySetting;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 UnionpayPaySettingService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class UnionpayPaySettingService {

	@Resource(name = "unionpayPaySettingDao")
	private Dao<UnionpayPaySetting> unionpayPaySettingDao;

	public int addUnionpayPaySetting(UnionpayPaySetting unionpayPaySetting) {
		int rows = unionpayPaySettingDao.save(unionpayPaySetting);
		return rows;
	}

	public int updateUnionpayPaySetting(UnionpayPaySetting unionpayPaySetting) {
		int rows = unionpayPaySettingDao.dynamicUpdate(unionpayPaySetting);
		return rows;
	}

	public int deleteUnionpayPaySetting(Integer id) {
		int rows = unionpayPaySettingDao.delete(id);
		return rows;
	}

	public int batchDeleteUnionpayPaySettings(Integer[] ids) {
		int rows = unionpayPaySettingDao.batchDelete(ids);
		return rows;
	}

	public UnionpayPaySetting getUnionpayPaySetting(Integer id) {
		UnionpayPaySetting unionpayPaySetting = unionpayPaySettingDao.get(id);
		return unionpayPaySetting;
	}

	public List<UnionpayPaySetting> getAllUnionpayPaySettings() {
		List<UnionpayPaySetting> list = unionpayPaySettingDao.findAll();
		return list;
	}

}

