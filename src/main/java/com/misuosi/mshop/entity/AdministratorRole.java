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
 * Description	 : 实体类 AdministratorRole
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class AdministratorRole extends Entity {

	@Id
	private Integer adroId;
	private Integer roleId;
	private Integer admiId;
	private Timestamp adroModifyTime;
	private Timestamp adroCreateTime;

	public Integer getAdroId() {
		return adroId;
	}

	public void setAdroId(Integer adroId) {
		this.adroId = adroId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getAdmiId() {
		return admiId;
	}

	public void setAdmiId(Integer admiId) {
		this.admiId = admiId;
	}

	public Timestamp getAdroModifyTime() {
		return adroModifyTime;
	}

	public void setAdroModifyTime(Timestamp adroModifyTime) {
		this.adroModifyTime = adroModifyTime;
	}

	public Timestamp getAdroCreateTime() {
		return adroCreateTime;
	}

	public void setAdroCreateTime(Timestamp adroCreateTime) {
		this.adroCreateTime = adroCreateTime;
	}

}

