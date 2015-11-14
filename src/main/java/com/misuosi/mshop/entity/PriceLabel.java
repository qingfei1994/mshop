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
 * Description	 : 实体类 PriceLabel
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class PriceLabel extends Entity {

	@Id
	private Integer prlaId;
	private String prlaName;
	private Byte prlaType;
	private Timestamp prlaModifyTime;
	private Timestamp prlaCreateTime;

	public Integer getPrlaId() {
		return prlaId;
	}

	public void setPrlaId(Integer prlaId) {
		this.prlaId = prlaId;
	}

	public String getPrlaName() {
		return prlaName;
	}

	public void setPrlaName(String prlaName) {
		this.prlaName = prlaName;
	}

	public Byte getPrlaType() {
		return prlaType;
	}

	public void setPrlaType(Byte prlaType) {
		this.prlaType = prlaType;
	}

	public Timestamp getPrlaModifyTime() {
		return prlaModifyTime;
	}

	public void setPrlaModifyTime(Timestamp prlaModifyTime) {
		this.prlaModifyTime = prlaModifyTime;
	}

	public Timestamp getPrlaCreateTime() {
		return prlaCreateTime;
	}

	public void setPrlaCreateTime(Timestamp prlaCreateTime) {
		this.prlaCreateTime = prlaCreateTime;
	}

}

