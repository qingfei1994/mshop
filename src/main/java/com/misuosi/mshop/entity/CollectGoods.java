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
 * Description	 : 实体类 CollectGoods
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class CollectGoods extends Entity {

	@Id
	private Integer cogoId;
	private Integer weinId;
	private Integer goodId;
	private Timestamp cogoModifyTime;
	private Timestamp cogoCreateTime;

	public Integer getCogoId() {
		return cogoId;
	}

	public void setCogoId(Integer cogoId) {
		this.cogoId = cogoId;
	}

	public Integer getWeinId() {
		return weinId;
	}

	public void setWeinId(Integer weinId) {
		this.weinId = weinId;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Timestamp getCogoModifyTime() {
		return cogoModifyTime;
	}

	public void setCogoModifyTime(Timestamp cogoModifyTime) {
		this.cogoModifyTime = cogoModifyTime;
	}

	public Timestamp getCogoCreateTime() {
		return cogoCreateTime;
	}

	public void setCogoCreateTime(Timestamp cogoCreateTime) {
		this.cogoCreateTime = cogoCreateTime;
	}

}

