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
 * Description	 : 实体类 ActivityCommodity
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class ActivityCommodity extends Entity {

	@Id
	private Integer accoId;
	private Integer goodId;
	private Integer acmaId;
	private Timestamp accoModifyTime;
	private Timestamp accoCreateTime;

	public Integer getAccoId() {
		return accoId;
	}

	public void setAccoId(Integer accoId) {
		this.accoId = accoId;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Integer getAcmaId() {
		return acmaId;
	}

	public void setAcmaId(Integer acmaId) {
		this.acmaId = acmaId;
	}

	public Timestamp getAccoModifyTime() {
		return accoModifyTime;
	}

	public void setAccoModifyTime(Timestamp accoModifyTime) {
		this.accoModifyTime = accoModifyTime;
	}

	public Timestamp getAccoCreateTime() {
		return accoCreateTime;
	}

	public void setAccoCreateTime(Timestamp accoCreateTime) {
		this.accoCreateTime = accoCreateTime;
	}

}

