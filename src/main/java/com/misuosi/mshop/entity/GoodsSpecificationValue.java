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
 * Description	 : 实体类 GoodsSpecificationValue
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class GoodsSpecificationValue extends Entity {

	@Id
	private Integer gsvaId;
	private Integer gsnaId;
	private String gsvaValue;
	private Byte gsvaType;
	private Timestamp gsvaModifyTime;
	private Timestamp gsvaCreateTime;

	public Integer getGsvaId() {
		return gsvaId;
	}

	public void setGsvaId(Integer gsvaId) {
		this.gsvaId = gsvaId;
	}

	public Integer getGsnaId() {
		return gsnaId;
	}

	public void setGsnaId(Integer gsnaId) {
		this.gsnaId = gsnaId;
	}

	public String getGsvaValue() {
		return gsvaValue;
	}

	public void setGsvaValue(String gsvaValue) {
		this.gsvaValue = gsvaValue;
	}

	public Byte getGsvaType() {
		return gsvaType;
	}

	public void setGsvaType(Byte gsvaType) {
		this.gsvaType = gsvaType;
	}

	public Timestamp getGsvaModifyTime() {
		return gsvaModifyTime;
	}

	public void setGsvaModifyTime(Timestamp gsvaModifyTime) {
		this.gsvaModifyTime = gsvaModifyTime;
	}

	public Timestamp getGsvaCreateTime() {
		return gsvaCreateTime;
	}

	public void setGsvaCreateTime(Timestamp gsvaCreateTime) {
		this.gsvaCreateTime = gsvaCreateTime;
	}

}

