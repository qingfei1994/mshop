/*
 * Copyright (c) 2015
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.IndexGroup;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 IndexGroupService
 * <br><br>Time	 : 2015/07/28 11:28
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class IndexGroupService {

	@Resource(name = "indexGroupDao")
	private Dao<IndexGroup> indexGroupDao;

	public int addIndexGroup(IndexGroup indexGroup) {
		int rows = indexGroupDao.save(indexGroup);
		return rows;
	}

	public int updateIndexGroup(IndexGroup indexGroup) {
		int rows = indexGroupDao.dynamicUpdate(indexGroup);
		return rows;
	}

	public int deleteIndexGroup(Integer id) {
		int rows = indexGroupDao.delete(id);
		return rows;
	}

	public int batchDeleteIndexGroups(Integer[] ids) {
		int rows = indexGroupDao.batchDelete(ids);
		return rows;
	}

	public IndexGroup getIndexGroup(Integer id) {
		IndexGroup indexGroup = indexGroupDao.get(id);
		return indexGroup;
	}

	public List<IndexGroup> getAllIndexGroups() {
		List<IndexGroup> list = indexGroupDao.findAll();
		return list;
	}

}

