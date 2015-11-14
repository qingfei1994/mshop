/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.ClientAppealPicture;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 ClientAppealPictureService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class ClientAppealPictureService {

	@Resource(name = "clientAppealPictureDao")
	private Dao<ClientAppealPicture> clientAppealPictureDao;

	public int addClientAppealPicture(ClientAppealPicture clientAppealPicture) {
		int rows = clientAppealPictureDao.save(clientAppealPicture);
		return rows;
	}

	public int updateClientAppealPicture(ClientAppealPicture clientAppealPicture) {
		int rows = clientAppealPictureDao.dynamicUpdate(clientAppealPicture);
		return rows;
	}

	public int deleteClientAppealPicture(Integer id) {
		int rows = clientAppealPictureDao.delete(id);
		return rows;
	}

	public int batchDeleteClientAppealPictures(Integer[] ids) {
		int rows = clientAppealPictureDao.batchDelete(ids);
		return rows;
	}

	public ClientAppealPicture getClientAppealPicture(Integer id) {
		ClientAppealPicture clientAppealPicture = clientAppealPictureDao.get(id);
		return clientAppealPicture;
	}

	public List<ClientAppealPicture> getAllClientAppealPictures() {
		List<ClientAppealPicture> list = clientAppealPictureDao.findAll();
		return list;
	}

}

