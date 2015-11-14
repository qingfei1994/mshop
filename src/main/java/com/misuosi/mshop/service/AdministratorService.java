/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.MultiTableDao;
import com.misuosi.mshop.entity.Administrator;
import com.misuosi.mshop.entity.AdministratorRole;
import com.misuosi.mshop.entity.Permission;
import com.misuosi.mshop.entity.Role;
import com.misuosi.mshop.util.EncryptUtils;
import com.misuosi.mshop.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description	 : service类 AdministratorService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class AdministratorService {

	@Resource(name = "administratorDao")
	private Dao<Administrator> administratorDao;
	@Resource(name = "roleDao")
	private Dao<Role> roleDao;
	@Resource(name = "permissionDao")
	private Dao<Permission> permissionDao;
	@Resource(name = "administratorRoleDao")
	private Dao<AdministratorRole> administratorRoleDao;

	public int addAdministrator(Administrator administrator, Integer[] roleIds) {
		administrator.setAdmiSalt(EncryptUtils.getSalt());

		String admiPassword = EncryptUtils.getPassword(
				administrator.getAdmiPassword(),
				administrator.getCredentialsSalt());
		administrator.setAdmiPassword(admiPassword);
		int rows = administratorDao.save(administrator);

		if(roleIds != null) {
			for (int roleId : roleIds) {
				AdministratorRole administratorRole = new AdministratorRole();
				administratorRole.setAdmiId(administrator.getAdmiId());
				administratorRole.setAdroCreateTime(new Timestamp(System.currentTimeMillis()));
				administratorRole.setRoleId(roleId);
				administratorRoleDao.save(administratorRole);
			}
		}
		return rows;
	}

	public int updateAdministrator(Administrator administrator, Integer[] roleIds) {
		if(StringUtils.isNotBlank(administrator.getAdmiPassword())) {
			administrator.setAdmiSalt(EncryptUtils.getSalt());
			String admiPassword = EncryptUtils.getPassword(
					administrator.getAdmiPassword(),
					administrator.getCredentialsSalt());
			administrator.setAdmiPassword(admiPassword);
		}

		int rows = administratorDao.dynamicUpdate(administrator);

		//删除已经存在的管理员角色关联
		administratorRoleDao.batchDelete("DELETE FROM administrator_role WHERE admi_id = ?", administrator.getAdmiId());

		//重新插入新的管理员角色关联
		if(roleIds != null) {
			for (int roleId : roleIds) {
				AdministratorRole administratorRole = new AdministratorRole();
				administratorRole.setAdmiId(administrator.getAdmiId());
				administratorRole.setAdroCreateTime(new Timestamp(System.currentTimeMillis()));
				administratorRole.setRoleId(roleId);
				administratorRoleDao.save(administratorRole);
			}
		}
		return rows;
	}

	public int deleteAdministrator(Integer id) {
		int rows = administratorDao.delete(id);
		administratorRoleDao.batchDelete("DELETE FROM administrator_role WHERE admi_id = ?", id);
		return rows;
	}

	public int batchDeleteAdministrators(Integer[] ids) {
		int rows = 0;
		for (int id : ids) {
			rows += deleteAdministrator(id);
		}
		return rows;
	}

	public Administrator getAdministrator(Integer id) {
		Administrator administrator = administratorDao.get(id);
		return administrator;
	}

	public List<Administrator> getAllAdministrators() {
		String sql = "SELECT * FROM administrator ORDER BY admi_id ASC";
		List<Administrator> list = administratorDao.find(sql);
		return list;
	}

	public Administrator getAdministrator(String username) {
		Administrator administrator = administratorDao.findUniqueByProperty("admi_account", username);
		return administrator;
	}

	public Set<String> getPermissions(String username) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT permission.*");
		sb.append(" FROM administrator");
		sb.append(" INNER JOIN administrator_role");
		sb.append(" ON administrator_role.admi_id = administrator.admi_id");
		sb.append(" INNER JOIN role");
		sb.append(" ON role.role_id = administrator_role.role_id");
		sb.append(" INNER JOIN role_permission");
		sb.append(" ON role_permission.role_id = role.role_id");
		sb.append(" INNER JOIN permission");
		sb.append(" ON permission.perm_id = role_permission.perm_id");
		sb.append(" WHERE administrator.admi_account = ?");
		sb.append(" AND permission.perm_permission != ''");
		List<Map<String, Object>> permissions = ((MultiTableDao)permissionDao).queryForList(sb.toString(), username);
		Set<String> permissionSet = new HashSet<String>();
		for (Map<String, Object> permission : permissions) {
			permissionSet.add((String) permission.get("perm_permission"));
		}
		return permissionSet;
	}

	public Set<String> getRoles(String username) {
		StringBuilder sb = new StringBuilder();
		sb.append(" SELECT role.*");
		sb.append(" FROM administrator");
		sb.append(" INNER JOIN administrator_role");
		sb.append(" ON administrator_role.admi_id = administrator.admi_id");
		sb.append(" INNER JOIN role");
		sb.append(" ON role.role_id = role.role_id");
		sb.append(" WHERE administrator.admi_account = ?");
		List<Role> roles =  roleDao.find(sb.toString(), username);
		Set<String> roleSet = new HashSet<String>();
		for (Role role : roles) {
			roleSet.add(role.getRoleName());
		}
		return roleSet;
	}

}

