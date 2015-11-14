/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.HelpDocument;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 HelpDocumentService
 * <br><br>Time	 : 2015/06/11 16:34
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class HelpDocumentService {

	@Resource(name = "helpDocumentDao")
	private Dao<HelpDocument> helpDocumentDao;

	public int addHelpDocument(HelpDocument helpDocument) {
		int rows = helpDocumentDao.save(helpDocument);
		return rows;
	}

	public int updateHelpDocument(HelpDocument helpDocument) {
		int rows = helpDocumentDao.dynamicUpdate(helpDocument);
		return rows;
	}

	public int deleteHelpDocument(Integer id) {
		int rows = helpDocumentDao.delete(id);
		return rows;
	}

	public int batchDeleteHelpDocuments(Integer[] ids) {
		int rows = helpDocumentDao.batchDelete(ids);
		return rows;
	}

	public HelpDocument getHelpDocument(Integer id) {
		HelpDocument helpDocument = helpDocumentDao.get(id);
		return helpDocument;
	}

	public List<HelpDocument> getAllHelpDocuments() {
		List<HelpDocument> list = helpDocumentDao.findAll();
		return list;
	}

}

