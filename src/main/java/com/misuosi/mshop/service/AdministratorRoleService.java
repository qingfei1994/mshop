/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.AdministratorRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 AdministratorRoleService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class AdministratorRoleService {

	@Resource(name = "administratorRoleDao")
	private Dao<AdministratorRole> administratorRoleDao;

	public int addAdministratorRole(AdministratorRole administratorRole) {
		int rows = administratorRoleDao.save(administratorRole);
		return rows;
	}

	public int updateAdministratorRole(AdministratorRole administratorRole) {
		int rows = administratorRoleDao.dynamicUpdate(administratorRole);
		return rows;
	}

	public int deleteAdministratorRole(Integer id) {
		int rows = administratorRoleDao.delete(id);
		return rows;
	}

	public int batchDeleteAdministratorRoles(Integer[] ids) {
		int rows = administratorRoleDao.batchDelete(ids);
		return rows;
	}

	public AdministratorRole getAdministratorRole(Integer id) {
		AdministratorRole administratorRole = administratorRoleDao.get(id);
		return administratorRole;
	}

	public List<AdministratorRole> getAllAdministratorRoles() {
		List<AdministratorRole> list = administratorRoleDao.findAll();
		return list;
	}

	public List<AdministratorRole> getAdministratorRoles(Integer admiId) {
		List<AdministratorRole> list = administratorRoleDao.findByProperty("admi_id", admiId);
		return list;
	}

}

