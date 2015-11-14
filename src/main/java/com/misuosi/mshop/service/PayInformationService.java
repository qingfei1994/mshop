/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.PayInformation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 PayInformationService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class PayInformationService {

	@Resource(name = "payInformationDao")
	private Dao<PayInformation> payInformationDao;

	public int addPayInformation(PayInformation payInformation) {
		int rows = payInformationDao.save(payInformation);
		return rows;
	}

	public int updatePayInformation(PayInformation payInformation) {
		int rows = payInformationDao.dynamicUpdate(payInformation);
		return rows;
	}

	public int deletePayInformation(Integer id) {
		int rows = payInformationDao.delete(id);
		return rows;
	}

	public int batchDeletePayInformations(Integer[] ids) {
		int rows = payInformationDao.batchDelete(ids);
		return rows;
	}

	public PayInformation getPayInformation(Integer id) {
		PayInformation payInformation = payInformationDao.get(id);
		return payInformation;
	}

	public List<PayInformation> getAllPayInformations() {
		List<PayInformation> list = payInformationDao.findAll();
		return list;
	}

}

