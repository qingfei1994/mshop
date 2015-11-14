/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.WeixinReplyTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 WeixinReplyTemplateService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class WeixinReplyTemplateService {

	@Resource(name = "weixinReplyTemplateDao")
	private Dao<WeixinReplyTemplate> weixinReplyTemplateDao;

	public int addWeixinReplyTemplate(WeixinReplyTemplate weixinReplyTemplate) {
		int rows = weixinReplyTemplateDao.save(weixinReplyTemplate);
		return rows;
	}

	public int updateWeixinReplyTemplate(WeixinReplyTemplate weixinReplyTemplate) {
		int rows = weixinReplyTemplateDao.dynamicUpdate(weixinReplyTemplate);
		return rows;
	}

	public int deleteWeixinReplyTemplate(Integer id) {
		int rows = weixinReplyTemplateDao.delete(id);
		return rows;
	}

	public int batchDeleteWeixinReplyTemplates(Integer[] ids) {
		int rows = weixinReplyTemplateDao.batchDelete(ids);
		return rows;
	}

	public WeixinReplyTemplate getWeixinReplyTemplate(Integer id) {
		WeixinReplyTemplate weixinReplyTemplate = weixinReplyTemplateDao.get(id);
		return weixinReplyTemplate;
	}

	public List<WeixinReplyTemplate> getAllWeixinReplyTemplates() {
		List<WeixinReplyTemplate> list = weixinReplyTemplateDao.findAll();
		return list;
	}

}

