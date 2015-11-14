/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.Alipay;
import com.misuosi.mshop.entity.Contacts;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 ContactsService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class ContactsService {

	@Resource(name = "contactsDao")
	private Dao<Contacts> contactsDao;

	public int addContacts(Contacts contacts) {
		int rows = contactsDao.save(contacts);
		return rows;
	}

	public int updateContacts(Contacts contacts) {
		int rows = contactsDao.dynamicUpdate(contacts);
		return rows;
	}

	public int deleteContacts(Integer id) {
		int rows = contactsDao.delete(id);
		return rows;
	}

	public int batchDeleteContactss(Integer[] ids) {
		int rows = contactsDao.batchDelete(ids);
		return rows;
	}

	public Contacts getContacts(Integer id) {
		Contacts contacts = contactsDao.get(id);
		return contacts;
	}

	public List<Contacts> getAllContactss() {
		List<Contacts> list = contactsDao.findAll();
		return list;
	}

}

