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
 * Description	 : 实体类 BankCard
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class BankCard extends Entity {

	@Id
	private Integer brcaId;
	private Integer abcaId;
	private Integer bankId;
	private String brcaName;
	private Byte brcaType;
	private String brcaAccount;
	private String brcaSubbranchName;
	private Timestamp brcaModifyTime;
	private Timestamp brcaCreateTime;

	public Integer getBrcaId() {
		return brcaId;
	}

	public void setBrcaId(Integer brcaId) {
		this.brcaId = brcaId;
	}

	public Integer getAbcaId() {
		return abcaId;
	}

	public void setAbcaId(Integer abcaId) {
		this.abcaId = abcaId;
	}

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getBrcaName() {
		return brcaName;
	}

	public void setBrcaName(String brcaName) {
		this.brcaName = brcaName;
	}

	public Byte getBrcaType() {
		return brcaType;
	}

	public void setBrcaType(Byte brcaType) {
		this.brcaType = brcaType;
	}

	public String getBrcaAccount() {
		return brcaAccount;
	}

	public void setBrcaAccount(String brcaAccount) {
		this.brcaAccount = brcaAccount;
	}

	public String getBrcaSubbranchName() {
		return brcaSubbranchName;
	}

	public void setBrcaSubbranchName(String brcaSubbranchName) {
		this.brcaSubbranchName = brcaSubbranchName;
	}

	public Timestamp getBrcaModifyTime() {
		return brcaModifyTime;
	}

	public void setBrcaModifyTime(Timestamp brcaModifyTime) {
		this.brcaModifyTime = brcaModifyTime;
	}

	public Timestamp getBrcaCreateTime() {
		return brcaCreateTime;
	}

	public void setBrcaCreateTime(Timestamp brcaCreateTime) {
		this.brcaCreateTime = brcaCreateTime;
	}

}

