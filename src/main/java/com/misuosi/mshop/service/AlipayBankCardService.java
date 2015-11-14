/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.AlipayBankCard;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 AlipayBankCardService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class AlipayBankCardService {

	@Resource(name = "alipayBankCardDao")
	private Dao<AlipayBankCard> alipayBankCardDao;

	public int addAlipayBankCard(AlipayBankCard alipayBankCard) {
		int rows = alipayBankCardDao.save(alipayBankCard);
		return rows;
	}

	public int updateAlipayBankCard(AlipayBankCard alipayBankCard) {
		int rows = alipayBankCardDao.dynamicUpdate(alipayBankCard);
		return rows;
	}

	public int deleteAlipayBankCard(Integer id) {
		int rows = alipayBankCardDao.delete(id);
		return rows;
	}

	public int batchDeleteAlipayBankCards(Integer[] ids) {
		int rows = alipayBankCardDao.batchDelete(ids);
		return rows;
	}

	public AlipayBankCard getAlipayBankCard(Integer id) {
		AlipayBankCard alipayBankCard = alipayBankCardDao.get(id);
		return alipayBankCard;
	}

	public List<AlipayBankCard> getAllAlipayBankCards() {
		List<AlipayBankCard> list = alipayBankCardDao.findAll();
		return list;
	}

	public AlipayBankCard getAlipayBankCardByDiacId(Integer diacId) {
		AlipayBankCard alipayBankCard = alipayBankCardDao.findUniqueByProperty("diacId", diacId);
		return alipayBankCard;
	}

}

