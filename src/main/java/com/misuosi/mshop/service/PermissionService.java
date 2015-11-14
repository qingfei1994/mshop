/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.common.context.ApplicationContextHolder;
import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.Permission;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 PermissionService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class PermissionService {

	@Resource(name = "permissionDao")
	private Dao<Permission> permissionDao;

	@CacheEvict(value = "user-permissionCache", allEntries=true)
	public int addPermission(Permission permission) {
		Permission parentPermission = permissionDao.get(permission.getPermParentId());
		permission.setPermParentTree(parentPermission.makeSelfAsParentTree());
		int rows = permissionDao.save(permission);
		return rows;
	}

	@CacheEvict(value = "user-permissionCache", allEntries=true)
	public int updatePermission(Permission permission) {
		int rows = permissionDao.dynamicUpdate(permission);
		return rows;
	}

	@CacheEvict(value = "user-permissionCache", allEntries=true)
	public int deletePermission(Integer id) {
		int rows = 0;
		List<Permission> subPermissions = permissionDao.findByProperty("perm_parent_id", id);
		for(Permission subPermission : subPermissions) {
			rows += deletePermission(subPermission.getPermId());
		}
		rows += permissionDao.delete(id);
		return rows;
	}

	@CacheEvict(value = "user-permissionCache", allEntries=true)
	public int batchDeletePermissions(Integer[] ids) {
		int rows = permissionDao.batchDelete(ids);
		return rows;
	}

	@Cacheable(value = "user-permissionCache", key = "#id")
	public Permission getPermission(Integer id) {
		Permission permission = permissionDao.get(id);
		return permission;
	}

	@Cacheable(value = "user-permissionCache", key = "#type + 'type'")
	public List<Permission> getAllPermissions(int type) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT * FROM permission");
		sb.append(" WHERE perm_type = ?");
		sb.append(" ORDER BY CONCAT(perm_parent_tree, perm_id),perm_sort ASC");

		List<Permission> list = permissionDao.find(sb.toString(), type);
		return list;
	}

	@Cacheable(value = "user-permissionCache", key = "#roleId + 'role'")
	public List<Permission> getPermissions(int roleId) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT permission.*");
		sb.append(" FROM permission");
		sb.append(" INNER JOIN role_permission");
		sb.append(" ON role_permission.perm_id = permission.perm_id");
		sb.append(" INNER JOIN role");
		sb.append(" ON role.role_id = role_permission.role_id");
		sb.append(" WHERE role.role_id = ?");
		List<Permission> permissions = permissionDao.find(sb.toString(), roleId);
		return permissions;
	}

	@Cacheable(value = "user-permissionCache", key = "#permId + 'subPermissions'")
	public List<Permission> getSubPermissions(int permId) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT * FROM permission");
		sb.append(" WHERE perm_parent_id = ?");
		sb.append(" ORDER BY perm_sort ASC");

		List<Permission> subPermissions = permissionDao.find(sb.toString(), permId);
		return subPermissions;
	}

	@Cacheable(value = "user-permissionCache", key = "#permId + 'availableSubPermissions'")
	public List<Permission> getAvailableSubPermissions(int permId) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT * FROM permission");
		sb.append(" WHERE perm_parent_id = ? AND perm_status = 1");
		sb.append(" ORDER BY perm_sort ASC");

		Dao<Permission> permissionDao = ApplicationContextHolder.getBean("permissionDao");
		List<Permission> subPermissions = permissionDao.find(sb.toString(), permId);
		return subPermissions;
	}

	@Cacheable(value = "user-permissionCache", key = "#adminId + 'admin' +#permId + 'availableSubPermissions'")
	public List<Permission> getAdminAvailableSubPermissions(int adminId, Integer permId) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT permission.* FROM permission");
		sb.append(" INNER JOIN role_permission");
		sb.append(" ON role_permission.perm_id = permission.perm_id");
		sb.append(" INNER JOIN role");
		sb.append(" ON role.role_id = role_permission.role_id");
		sb.append(" INNER JOIN administrator_role");
		sb.append(" ON administrator_role.role_id = role.role_id");
		sb.append(" INNER JOIN administrator");
		sb.append(" ON administrator.admi_id = administrator_role.admi_id");
		sb.append(" WHERE administrator.admi_id = ?");
		sb.append(" AND perm_parent_id = ? AND perm_status = 1");
		sb.append(" ORDER BY perm_sort ASC");

		List<Permission> subPermissions = permissionDao.find(sb.toString(), adminId, permId);
		return subPermissions;
	}

}

