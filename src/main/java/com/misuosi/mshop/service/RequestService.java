/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.Request;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 RequestService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class RequestService {

	@Resource(name = "requestDao")
	private Dao<Request> requestDao;

	public int addRequest(Request request) {
		int rows = requestDao.save(request);
		return rows;
	}

	public int updateRequest(Request request) {
		int rows = requestDao.dynamicUpdate(request);
		return rows;
	}

	public int deleteRequest(Integer id) {
		int rows = requestDao.delete(id);
		return rows;
	}

	public int batchDeleteRequests(Integer[] ids) {
		int rows = requestDao.batchDelete(ids);
		return rows;
	}

	public Request getRequest(Integer id) {
		Request request = requestDao.get(id);
		return request;
	}

	public List<Request> getAllRequests() {
		List<Request> list = requestDao.findAll();
		return list;
	}

}

