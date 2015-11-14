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
 * Description	 : 实体类 GoodsLabel
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class GoodsLabel extends Entity {

	@Id
	private Integer golaId;
	private String golaName;
	private Byte golaSort;
	private Timestamp golaModifyTime;
	private Timestamp golaCreateTime;

	public Integer getGolaId() {
		return golaId;
	}

	public void setGolaId(Integer golaId) {
		this.golaId = golaId;
	}

	public String getGolaName() {
		return golaName;
	}

	public void setGolaName(String golaName) {
		this.golaName = golaName;
	}

	public Byte getGolaSort() {
		return golaSort;
	}

	public void setGolaSort(Byte golaSort) {
		this.golaSort = golaSort;
	}

	public Timestamp getGolaModifyTime() {
		return golaModifyTime;
	}

	public void setGolaModifyTime(Timestamp golaModifyTime) {
		this.golaModifyTime = golaModifyTime;
	}

	public Timestamp getGolaCreateTime() {
		return golaCreateTime;
	}

	public void setGolaCreateTime(Timestamp golaCreateTime) {
		this.golaCreateTime = golaCreateTime;
	}

}

