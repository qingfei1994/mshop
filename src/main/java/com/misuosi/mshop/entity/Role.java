/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.entity;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.Id;

import java.sql.Timestamp;

/**
 * Description	 : 实体类 Role
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class Role extends Entity {

	@Id
	private Integer roleId;
	private String roleZhName;
	private String roleName;
	private String roleDescription;
	private Boolean roleStatus;
	private Boolean roleSystem;
	private Byte roleType;
	private Timestamp roleModifyTime;
	private Timestamp roleCreateTime;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleZhName() {
		return roleZhName;
	}

	public void setRoleZhName(String roleZhName) {
		this.roleZhName = roleZhName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Boolean getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(Boolean roleStatus) {
		this.roleStatus = roleStatus;
	}

	public Boolean getRoleSystem() {
		return roleSystem;
	}

	public void setRoleSystem(Boolean roleSystem) {
		this.roleSystem = roleSystem;
	}

	public Byte getRoleType() {
		return roleType;
	}

	public void setRoleType(Byte roleType) {
		this.roleType = roleType;
	}

	public Timestamp getRoleModifyTime() {
		return roleModifyTime;
	}

	public void setRoleModifyTime(Timestamp roleModifyTime) {
		this.roleModifyTime = roleModifyTime;
	}

	public Timestamp getRoleCreateTime() {
		return roleCreateTime;
	}

	public void setRoleCreateTime(Timestamp roleCreateTime) {
		this.roleCreateTime = roleCreateTime;
	}

}

