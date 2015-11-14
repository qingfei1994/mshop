/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.BankCard;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 BankCardService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class BankCardService {

	@Resource(name = "bankCardDao")
	private Dao<BankCard> bankCardDao;

	public int addBankCard(BankCard bankCard) {
		int rows = bankCardDao.save(bankCard);
		return rows;
	}

	public int updateBankCard(BankCard bankCard) {
		int rows = bankCardDao.dynamicUpdate(bankCard);
		return rows;
	}

	public int deleteBankCard(Integer id) {
		int rows = bankCardDao.delete(id);
		return rows;
	}

	public int batchDeleteBankCards(Integer[] ids) {
		int rows = bankCardDao.batchDelete(ids);
		return rows;
	}

	public BankCard getBankCard(Integer id) {
		BankCard bankCard = bankCardDao.get(id);
		return bankCard;
	}

	public List<BankCard> getAllBankCards() {
		List<BankCard> list = bankCardDao.findAll();
		return list;
	}

	public BankCard getBankCardByAbcaId(Integer abcaId){
		BankCard bankCard=bankCardDao.findUniqueByProperty("abcaId", abcaId);
		return bankCard;
	}
	
}

