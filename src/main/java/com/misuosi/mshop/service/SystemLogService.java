/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.SystemLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 SystemLogService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class SystemLogService {

	@Resource(name = "systemLogDao")
	private Dao<SystemLog> systemLogDao;

	public int addSystemLog(SystemLog systemLog) {
		int rows = systemLogDao.save(systemLog);
		return rows;
	}

	public int updateSystemLog(SystemLog systemLog) {
		int rows = systemLogDao.dynamicUpdate(systemLog);
		return rows;
	}

	public int deleteSystemLog(Integer id) {
		int rows = systemLogDao.delete(id);
		return rows;
	}

	public int batchDeleteSystemLogs(Integer[] ids) {
		int rows = systemLogDao.batchDelete(ids);
		return rows;
	}

	public SystemLog getSystemLog(Integer id) {
		SystemLog systemLog = systemLogDao.get(id);
		return systemLog;
	}

	public List<SystemLog> getAllSystemLogs() {
		List<SystemLog> list = systemLogDao.findAll();
		return list;
	}

}

