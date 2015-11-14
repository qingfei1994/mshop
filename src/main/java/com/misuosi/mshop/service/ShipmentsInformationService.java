/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.ShipmentsInformation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 ShipmentsInformationService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class ShipmentsInformationService {

	@Resource(name = "shipmentsInformationDao")
	private Dao<ShipmentsInformation> shipmentsInformationDao;

	public int addShipmentsInformation(ShipmentsInformation shipmentsInformation) {
		int rows = shipmentsInformationDao.save(shipmentsInformation);
		return rows;
	}

	public int updateShipmentsInformation(ShipmentsInformation shipmentsInformation) {
		int rows = shipmentsInformationDao.dynamicUpdate(shipmentsInformation);
		return rows;
	}

	public int deleteShipmentsInformation(Integer id) {
		int rows = shipmentsInformationDao.delete(id);
		return rows;
	}

	public int batchDeleteShipmentsInformations(Integer[] ids) {
		int rows = shipmentsInformationDao.batchDelete(ids);
		return rows;
	}

	public ShipmentsInformation getShipmentsInformation(Integer id) {
		ShipmentsInformation shipmentsInformation = shipmentsInformationDao.get(id);
		return shipmentsInformation;
	}

	public List<ShipmentsInformation> getAllShipmentsInformations() {
		List<ShipmentsInformation> list = shipmentsInformationDao.findAll();
		return list;
	}

}

