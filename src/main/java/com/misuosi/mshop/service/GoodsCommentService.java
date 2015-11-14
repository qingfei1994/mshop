/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.GoodsComment;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.List;

/**
 * Description	 : service类 GoodsCommentService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class GoodsCommentService {

	@Resource(name = "goodsCommentDao")
	private Dao<GoodsComment> goodsCommentDao;

	public int addGoodsComment(GoodsComment goodsComment) {
		int rows = goodsCommentDao.save(goodsComment);
		return rows;
	}

	public int updateGoodsComment(GoodsComment goodsComment) {
		int rows = goodsCommentDao.dynamicUpdate(goodsComment);
		return rows;
	}

	public int deleteGoodsComment(Integer id) {
		int rows = goodsCommentDao.delete(id);
		return rows;
	}

	public int batchDeleteGoodsComments(Integer[] ids) {
		int rows = goodsCommentDao.batchDelete(ids);
		return rows;
	}

	public GoodsComment getGoodsComment(Integer id) {
		GoodsComment goodsComment = goodsCommentDao.get(id);
		return goodsComment;
	}

	public List<GoodsComment> getAllGoodsComments() {
		List<GoodsComment> list = goodsCommentDao.findAll();
		return list;
	}

	public GoodsComment getGoodsCommentByGoorId(Integer goorId) {
		List<GoodsComment> goodsComments = goodsCommentDao.findByProperty("goor_id", goorId);
		if (goodsComments == null || goodsComments.isEmpty()) {
			return null;
		}
		return goodsComments.get(0);
	}

}

