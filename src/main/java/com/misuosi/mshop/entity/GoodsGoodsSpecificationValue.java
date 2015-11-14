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
 * Description	 : 实体类 GoodsGoodsSpecificationValue
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class GoodsGoodsSpecificationValue extends Entity {

	@Id
	private Integer ggsvId;
	private Integer gpstId;
	private Integer goodId;
	private Integer gsvaId;
	private Timestamp ggsvModifyTime;
	private Timestamp ggsvCreateTime;

	public Integer getGgsvId() {
		return ggsvId;
	}

	public void setGgsvId(Integer ggsvId) {
		this.ggsvId = ggsvId;
	}

	public Integer getGpstId() {
		return gpstId;
	}

	public void setGpstId(Integer gpstId) {
		this.gpstId = gpstId;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Integer getGsvaId() {
		return gsvaId;
	}

	public void setGsvaId(Integer gsvaId) {
		this.gsvaId = gsvaId;
	}

	public Timestamp getGgsvModifyTime() {
		return ggsvModifyTime;
	}

	public void setGgsvModifyTime(Timestamp ggsvModifyTime) {
		this.ggsvModifyTime = ggsvModifyTime;
	}

	public Timestamp getGgsvCreateTime() {
		return ggsvCreateTime;
	}

	public void setGgsvCreateTime(Timestamp ggsvCreateTime) {
		this.ggsvCreateTime = ggsvCreateTime;
	}

}

