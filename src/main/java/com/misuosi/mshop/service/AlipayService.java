/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.Alipay;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 AlipayService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class AlipayService {

	@Resource(name = "alipayDao")
	private Dao<Alipay> alipayDao;

	public int addAlipay(Alipay alipay) {
		int rows = alipayDao.save(alipay);
		return rows;
	}

	public int updateAlipay(Alipay alipay) {
		int rows = alipayDao.dynamicUpdate(alipay);
		return rows;
	}

	public int deleteAlipay(Integer id) {
		int rows = alipayDao.delete(id);
		return rows;
	}

	public int batchDeleteAlipays(Integer[] ids) {
		int rows = alipayDao.batchDelete(ids);
		return rows;
	}

	public Alipay getAlipay(Integer id) {
		Alipay alipay = alipayDao.get(id);
		return alipay;
	}

	public List<Alipay> getAllAlipays() {
		List<Alipay> list = alipayDao.findAll();
		return list;
	}

	public Alipay getAlipayByAbcaId(Integer abcaId){
		Alipay alipay=alipayDao.findUniqueByProperty("abcaId", abcaId);
		return alipay;
	}

}

