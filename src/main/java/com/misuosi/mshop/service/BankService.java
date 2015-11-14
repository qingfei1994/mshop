/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.Bank;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 BankService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class BankService {

	@Resource(name = "bankDao")
	private Dao<Bank> bankDao;

	public int addBank(Bank bank) {
		int rows = bankDao.save(bank);
		return rows;
	}

	public int updateBank(Bank bank) {
		int rows = bankDao.dynamicUpdate(bank);
		return rows;
	}

	public int deleteBank(Integer id) {
		int rows = bankDao.delete(id);
		return rows;
	}

	public int batchDeleteBanks(Integer[] ids) {
		int rows = bankDao.batchDelete(ids);
		return rows;
	}

	public Bank getBank(Integer id) {
		Bank bank = bankDao.get(id);
		return bank;
	}

	public List<Bank> getAllBanks() {
		List<Bank> list = bankDao.findAll();
		return list;
	}

}

