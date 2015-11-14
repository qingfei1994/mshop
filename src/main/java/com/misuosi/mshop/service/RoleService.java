/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.common.constants.UserConstants;
import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.Role;
import com.misuosi.mshop.entity.RolePermission;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 RoleService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */
@Service
public class RoleService {

	@Resource(name = "roleDao")
	private Dao<Role> roleDao;
	@Resource(name = "rolePermissionDao")
	private Dao<RolePermission> rolePermissionDao;

	public int addRole(Role role) {
		int rows = roleDao.save(role);
		return rows;
	}

	public int updateRole(Role role) {
		int rows = roleDao.dynamicUpdate(role);
		return rows;
	}

	public int deleteRole(Integer id) {
		int rows = roleDao.delete(id);
		rolePermissionDao.batchDelete("DELETE FROM role_permission WHERE role_id = ?", id);
		return rows;
	}

	public int batchDeleteRoles(Integer[] ids) {
		int rows = 0;
		for (int id : ids) {
			rows += deleteRole(id);
		}
		return rows;
	}

	public Role getRole(Integer id) {
		Role role = roleDao.get(id);
		return role;
	}
	
	public List<Role> getRoleByDiacId(Integer diacId){
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT * ");
		sb.append(" FROM role");
		sb.append(" WHERE diac_id = ?");
		List<Role> list = roleDao.find(sb.toString(), diacId);
		return list;
	}

	public List<Role> getAllRoles(int userType) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT *");
		sb.append(" FROM role");
		sb.append(" WHERE role_type = ?");
		List<Role> list = roleDao.find(sb.toString(), userType);
		return list;
	}

	@CacheEvict(value = "user-permissionCache", allEntries=true)
	public void addRole(Role role, Integer[] permIds) {
		roleDao.save(role);
		if (permIds != null) {
			for (int permId : permIds) {
				RolePermission rolePermission = new RolePermission();
				rolePermission.setRoleId(role.getRoleId());
				rolePermission.setPermId(permId);
				rolePermissionDao.save(rolePermission);
			}
		}

	}

	@CacheEvict(value = "user-permissionCache", allEntries=true)
	public int updateRole(Role role, Integer[] permIds) {
		int result = roleDao.dynamicUpdate(role);

		//删除原来所有的角色权限关系
		rolePermissionDao.batchDelete("DELETE FROM role_permission WHERE role_id = ?", role.getRoleId());

		//重新插入新的角色权限关系
		if (permIds != null) {
			for (int permId : permIds) {
				RolePermission rolePermission = new RolePermission();
				rolePermission.setRoleId(role.getRoleId());
				rolePermission.setPermId(permId);
				rolePermissionDao.save(rolePermission);
			}
		}
		return result;
	}

	/**
	 * 获取管理员用户类型的角色列表
	 * @return
	 */
	public List<Role> getAdminRoles() {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT *");
		sb.append(" FROM role");
		sb.append(" WHERE role_type = ?");
		sb.append(" AND role_status = ?");

		int AVAILABLE = 1;
		List<Role> roles = roleDao.find(sb.toString(), UserConstants.ADMINISTRATOR_TYPE, AVAILABLE);
		return roles;
	}

	/**
	 * 获取管理员用户类型的角色列表
	 * @return
	 */
	public List<Role> getSupplierRoles() {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT *");
		sb.append(" FROM role");
		sb.append(" WHERE role_type = ?");
		sb.append(" AND role_status = ?");

		int AVAILABLE = 1;
		List<Role> roles = roleDao.find(sb.toString(), UserConstants.SUPPLIER_TYPE, AVAILABLE);
		return roles;
	}

}

