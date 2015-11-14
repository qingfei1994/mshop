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
 * Description	 : 实体类 GoodsLabelGoods
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class GoodsLabelGoods extends Entity {

	@Id
	private Integer glgoId;
	private Integer golaId;
	private Integer goodId;
	private Timestamp glgoModifyTime;
	private Timestamp glgoCreateTime;

	public Integer getGlgoId() {
		return glgoId;
	}

	public void setGlgoId(Integer glgoId) {
		this.glgoId = glgoId;
	}

	public Integer getGolaId() {
		return golaId;
	}

	public void setGolaId(Integer golaId) {
		this.golaId = golaId;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Timestamp getGlgoModifyTime() {
		return glgoModifyTime;
	}

	public void setGlgoModifyTime(Timestamp glgoModifyTime) {
		this.glgoModifyTime = glgoModifyTime;
	}

	public Timestamp getGlgoCreateTime() {
		return glgoCreateTime;
	}

	public void setGlgoCreateTime(Timestamp glgoCreateTime) {
		this.glgoCreateTime = glgoCreateTime;
	}

}

