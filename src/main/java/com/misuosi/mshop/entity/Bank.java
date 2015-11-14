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
 * Description	 : 实体类 Bank
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class Bank extends Entity {

	@Id
	private Integer bankId;
	private String bankName;
	private Timestamp bankModifyTime;
	private Timestamp bankCreateTime;

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Timestamp getBankModifyTime() {
		return bankModifyTime;
	}

	public void setBankModifyTime(Timestamp bankModifyTime) {
		this.bankModifyTime = bankModifyTime;
	}

	public Timestamp getBankCreateTime() {
		return bankCreateTime;
	}

	public void setBankCreateTime(Timestamp bankCreateTime) {
		this.bankCreateTime = bankCreateTime;
	}

}

