/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.ShopInformation;

/**
 * Description	 : service类 ShopInformationService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class ShopInformationService {

	@Resource(name = "shopInformationDao")
	private Dao<ShopInformation> shopInformationDao;

	public int addShopInformation(ShopInformation shopInformation) {
		int rows = shopInformationDao.save(shopInformation);
		return rows;
	}

	public int updateShopInformation(ShopInformation shopInformation) {
		int rows = shopInformationDao.dynamicUpdate(shopInformation);
		return rows;
	}

	public int deleteShopInformation(Integer id) {
		int rows = shopInformationDao.delete(id);
		return rows;
	}

	public int batchDeleteShopInformations(Integer[] ids) {
		int rows = shopInformationDao.batchDelete(ids);
		return rows;
	}

	public ShopInformation getShopInformation(Integer id) {
		ShopInformation shopInformation = shopInformationDao.get(id);
		return shopInformation;
	}

	public List<ShopInformation> getAllShopInformations() {
		List<ShopInformation> list = shopInformationDao.findAll();
		return list;
	}

	public ShopInformation getShopInformation() {
		List<ShopInformation> list = shopInformationDao.findAll();
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}
	}

}

