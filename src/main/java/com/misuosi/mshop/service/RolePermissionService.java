/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.RolePermission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 RolePermissionService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class RolePermissionService {

	@Resource(name = "rolePermissionDao")
	private Dao<RolePermission> rolePermissionDao;

	public int addRolePermission(RolePermission rolePermission) {
		int rows = rolePermissionDao.save(rolePermission);
		return rows;
	}

	public int updateRolePermission(RolePermission rolePermission) {
		int rows = rolePermissionDao.dynamicUpdate(rolePermission);
		return rows;
	}

	public int deleteRolePermission(Integer id) {
		int rows = rolePermissionDao.delete(id);
		return rows;
	}

	public int batchDeleteRolePermissions(Integer[] ids) {
		int rows = rolePermissionDao.batchDelete(ids);
		return rows;
	}

	public RolePermission getRolePermission(Integer id) {
		RolePermission rolePermission = rolePermissionDao.get(id);
		return rolePermission;
	}

	public List<RolePermission> getAllRolePermissions() {
		List<RolePermission> list = rolePermissionDao.findAll();
		return list;
	}

}

