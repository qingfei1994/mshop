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
 * Description	 : 实体类 ShoppingCart
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class ShoppingCart extends Entity {

	@Id
	private Integer shcaId;
	private Integer gpstId;
	private Integer weinId;
	private Integer shcaCount;
	private Timestamp shcaModifyTime;
	private Timestamp shcaCreateTime;

	public Integer getShcaId() {
		return shcaId;
	}

	public void setShcaId(Integer shcaId) {
		this.shcaId = shcaId;
	}

	public Integer getGpstId() {
		return gpstId;
	}

	public void setGpstId(Integer gpstId) {
		this.gpstId = gpstId;
	}

	public Integer getWeinId() {
		return weinId;
	}

	public void setWeinId(Integer weinId) {
		this.weinId = weinId;
	}

	public Integer getShcaCount() {
		return shcaCount;
	}

	public void setShcaCount(Integer shcaCount) {
		this.shcaCount = shcaCount;
	}

	public Timestamp getShcaModifyTime() {
		return shcaModifyTime;
	}

	public void setShcaModifyTime(Timestamp shcaModifyTime) {
		this.shcaModifyTime = shcaModifyTime;
	}

	public Timestamp getShcaCreateTime() {
		return shcaCreateTime;
	}

	public void setShcaCreateTime(Timestamp shcaCreateTime) {
		this.shcaCreateTime = shcaCreateTime;
	}

}

