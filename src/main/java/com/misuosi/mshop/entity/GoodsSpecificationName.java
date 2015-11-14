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
 * Description	 : 实体类 GoodsSpecificationName
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class GoodsSpecificationName extends Entity {

	@Id
	private Integer gsnaId;
	private String gsnaName;
	private Byte gsnaType;
	private Timestamp gsnaModifyTime;
	private Timestamp gsnaCreateTime;

	public Integer getGsnaId() {
		return gsnaId;
	}

	public void setGsnaId(Integer gsnaId) {
		this.gsnaId = gsnaId;
	}

	public String getGsnaName() {
		return gsnaName;
	}

	public void setGsnaName(String gsnaName) {
		this.gsnaName = gsnaName;
	}

	public Byte getGsnaType() {
		return gsnaType;
	}

	public void setGsnaType(Byte gsnaType) {
		this.gsnaType = gsnaType;
	}

	public Timestamp getGsnaModifyTime() {
		return gsnaModifyTime;
	}

	public void setGsnaModifyTime(Timestamp gsnaModifyTime) {
		this.gsnaModifyTime = gsnaModifyTime;
	}

	public Timestamp getGsnaCreateTime() {
		return gsnaCreateTime;
	}

	public void setGsnaCreateTime(Timestamp gsnaCreateTime) {
		this.gsnaCreateTime = gsnaCreateTime;
	}

}

