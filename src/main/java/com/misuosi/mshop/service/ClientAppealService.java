/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.ClientAppeal;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 ClientAppealService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class ClientAppealService {

	@Resource(name = "clientAppealDao")
	private Dao<ClientAppeal> clientAppealDao;

	public int addClientAppeal(ClientAppeal clientAppeal) {
		int rows = clientAppealDao.save(clientAppeal);
		return rows;
	}

	public int updateClientAppeal(ClientAppeal clientAppeal) {
		int rows = clientAppealDao.dynamicUpdate(clientAppeal);
		return rows;
	}
	

	public int deleteClientAppeal(Integer id) {
		int rows = clientAppealDao.delete(id);
		return rows;
	}

	public int batchDeleteClientAppeals(Integer[] ids) {
		int rows = clientAppealDao.batchDelete(ids);
		return rows;
	}

	public ClientAppeal getClientAppeal(Integer id) {
		ClientAppeal clientAppeal = clientAppealDao.get(id);
		return clientAppeal;
	}

	public List<ClientAppeal> getAllClientAppeals() {
		List<ClientAppeal> list = clientAppealDao.findAll();
		return list;
	}

	public ClientAppeal getClientAppealByGoorId(Integer goorId) {
		List<ClientAppeal> clientAppeals = clientAppealDao.findByProperty("goor_id", goorId);
		if (clientAppeals != null && !clientAppeals.isEmpty()) {
			return clientAppeals.get(0);
		}
		return null;
	}

}

