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
 * Description	 : 实体类 ActivityLabel
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class ActivityLabel extends Entity {

	@Id
	private Integer aclaId;
	private String aclaName;
	private Byte aclaType;
	private Timestamp aclaModifyTime;
	private Timestamp aclaCreateTime;

	public Integer getAclaId() {
		return aclaId;
	}

	public void setAclaId(Integer aclaId) {
		this.aclaId = aclaId;
	}

	public String getAclaName() {
		return aclaName;
	}

	public void setAclaName(String aclaName) {
		this.aclaName = aclaName;
	}

	public Byte getAclaType() {
		return aclaType;
	}

	public void setAclaType(Byte aclaType) {
		this.aclaType = aclaType;
	}

	public Timestamp getAclaModifyTime() {
		return aclaModifyTime;
	}

	public void setAclaModifyTime(Timestamp aclaModifyTime) {
		this.aclaModifyTime = aclaModifyTime;
	}

	public Timestamp getAclaCreateTime() {
		return aclaCreateTime;
	}

	public void setAclaCreateTime(Timestamp aclaCreateTime) {
		this.aclaCreateTime = aclaCreateTime;
	}

}

