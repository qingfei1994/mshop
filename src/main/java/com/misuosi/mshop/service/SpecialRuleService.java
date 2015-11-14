/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.SpecialRule;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 SpecialRuleService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class SpecialRuleService {

	@Resource(name = "specialRuleDao")
	private Dao<SpecialRule> specialRuleDao;

	public int addSpecialRule(SpecialRule specialRule) {
		int rows = specialRuleDao.save(specialRule);
		return rows;
	}

	public int updateSpecialRule(SpecialRule specialRule) {
		int rows = specialRuleDao.dynamicUpdate(specialRule);
		return rows;
	}

	public int deleteSpecialRule(Integer id) {
		int rows = specialRuleDao.delete(id);
		return rows;
	}

	public int batchDeleteSpecialRules(Integer[] ids) {
		int rows = specialRuleDao.batchDelete(ids);
		return rows;
	}

	public SpecialRule getSpecialRule(Integer id) {
		SpecialRule specialRule = specialRuleDao.get(id);
		return specialRule;
	}

	public List<SpecialRule> getAllSpecialRules() {
		List<SpecialRule> list = specialRuleDao.findAll();
		return list;
	}

}

