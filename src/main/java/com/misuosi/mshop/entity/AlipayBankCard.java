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
 * Description	 : 实体类 AlipayBankCard
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class AlipayBankCard extends Entity {

	@Id
	private Integer abcaId;
	private Byte abcaType;
	private Timestamp abcaModifyTime;
	private Timestamp abcaCreateTime;

	public Integer getAbcaId() {
		return abcaId;
	}

	public void setAbcaId(Integer abcaId) {
		this.abcaId = abcaId;
	}

	public Byte getAbcaType() {
		return abcaType;
	}

	public void setAbcaType(Byte abcaType) {
		this.abcaType = abcaType;
	}

	public Timestamp getAbcaModifyTime() {
		return abcaModifyTime;
	}

	public void setAbcaModifyTime(Timestamp abcaModifyTime) {
		this.abcaModifyTime = abcaModifyTime;
	}

	public Timestamp getAbcaCreateTime() {
		return abcaCreateTime;
	}

	public void setAbcaCreateTime(Timestamp abcaCreateTime) {
		this.abcaCreateTime = abcaCreateTime;
	}

}

