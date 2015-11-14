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
 * Description	 : 实体类 GoodsClassificationGoods
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class GoodsClassificationGoods extends Entity {

	@Id
	private Integer gcgoId;
	private Integer goclId;
	private Integer goodId;
	private Timestamp gcgoModifyTime;
	private Timestamp gcgoCreateTime;

	public Integer getGcgoId() {
		return gcgoId;
	}

	public void setGcgoId(Integer gcgoId) {
		this.gcgoId = gcgoId;
	}

	public Integer getGoclId() {
		return goclId;
	}

	public void setGoclId(Integer goclId) {
		this.goclId = goclId;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Timestamp getGcgoModifyTime() {
		return gcgoModifyTime;
	}

	public void setGcgoModifyTime(Timestamp gcgoModifyTime) {
		this.gcgoModifyTime = gcgoModifyTime;
	}

	public Timestamp getGcgoCreateTime() {
		return gcgoCreateTime;
	}

	public void setGcgoCreateTime(Timestamp gcgoCreateTime) {
		this.gcgoCreateTime = gcgoCreateTime;
	}

}

