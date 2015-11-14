/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.PriceLabel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 PriceLabelService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class PriceLabelService {

	@Resource(name = "priceLabelDao")
	private Dao<PriceLabel> priceLabelDao;

	public int addPriceLabel(PriceLabel priceLabel) {
		int rows = priceLabelDao.save(priceLabel);
		return rows;
	}

	public int updatePriceLabel(PriceLabel priceLabel) {
		int rows = priceLabelDao.dynamicUpdate(priceLabel);
		return rows;
	}

	public int deletePriceLabel(Integer id) {
		int rows = priceLabelDao.delete(id);
		return rows;
	}

	public int batchDeletePriceLabels(Integer[] ids) {
		int rows = priceLabelDao.batchDelete(ids);
		return rows;
	}

	public PriceLabel getPriceLabel(Integer id) {
		PriceLabel priceLabel = priceLabelDao.get(id);
		return priceLabel;
	}

	public List<PriceLabel> getAllPriceLabels() {
		List<PriceLabel> list = priceLabelDao.findAll();
		return list;
	}

	/**
	 * 获取内置的价格标签 add by chq
	 * @return
	 */
	public List<PriceLabel> getAllSystemPrizeLabels() {
		List<PriceLabel> list=priceLabelDao.findByProperty("prla_type", "1");
		return list;
	}

}

