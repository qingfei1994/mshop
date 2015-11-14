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
 * Description	 : 实体类 Administrator
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class Administrator extends Entity {

	@Id
	private Integer admiId;
	private String admiAccount;
	private String admiPassword;
	private String admiSalt;
	private Timestamp admiStartTime;
	private Timestamp admiEndTime;
	private Boolean admiStatus;
	private Timestamp admiModifyTime;
	private Timestamp admiCreateTime;

	public Integer getAdmiId() {
		return admiId;
	}

	public void setAdmiId(Integer admiId) {
		this.admiId = admiId;
	}

	public String getAdmiAccount() {
		return admiAccount;
	}

	public void setAdmiAccount(String admiAccount) {
		this.admiAccount = admiAccount;
	}

	public String getAdmiPassword() {
		return admiPassword;
	}

	public void setAdmiPassword(String admiPassword) {
		this.admiPassword = admiPassword;
	}

	public String getAdmiSalt() {
		return admiSalt;
	}

	public void setAdmiSalt(String admiSalt) {
		this.admiSalt = admiSalt;
	}

	public Timestamp getAdmiStartTime() {
		return admiStartTime;
	}

	public void setAdmiStartTime(Timestamp admiStartTime) {
		this.admiStartTime = admiStartTime;
	}

	public Timestamp getAdmiEndTime() {
		return admiEndTime;
	}

	public void setAdmiEndTime(Timestamp admiEndTime) {
		this.admiEndTime = admiEndTime;
	}

	public Boolean getAdmiStatus() {
		return admiStatus;
	}

	public void setAdmiStatus(Boolean admiStatus) {
		this.admiStatus = admiStatus;
	}

	public Timestamp getAdmiModifyTime() {
		return admiModifyTime;
	}

	public void setAdmiModifyTime(Timestamp admiModifyTime) {
		this.admiModifyTime = admiModifyTime;
	}

	public Timestamp getAdmiCreateTime() {
		return admiCreateTime;
	}

	public void setAdmiCreateTime(Timestamp admiCreateTime) {
		this.admiCreateTime = admiCreateTime;
	}

	public String getCredentialsSalt() {
		return admiAccount + admiSalt;
	}

}

