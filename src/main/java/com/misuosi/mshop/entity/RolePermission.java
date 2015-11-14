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
 * Description	 : 实体类 RolePermission
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class RolePermission extends Entity {

	@Id
	private Integer ropeId;
	private Integer permId;
	private Integer roleId;
	private Timestamp ropeModifyTime;
	private Timestamp ropeCreateTime;

	public Integer getRopeId() {
		return ropeId;
	}

	public void setRopeId(Integer ropeId) {
		this.ropeId = ropeId;
	}

	public Integer getPermId() {
		return permId;
	}

	public void setPermId(Integer permId) {
		this.permId = permId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Timestamp getRopeModifyTime() {
		return ropeModifyTime;
	}

	public void setRopeModifyTime(Timestamp ropeModifyTime) {
		this.ropeModifyTime = ropeModifyTime;
	}

	public Timestamp getRopeCreateTime() {
		return ropeCreateTime;
	}

	public void setRopeCreateTime(Timestamp ropeCreateTime) {
		this.ropeCreateTime = ropeCreateTime;
	}

}

