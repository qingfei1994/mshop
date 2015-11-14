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
 * Description	 : 实体类 WaybillPrintItems
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class WaybillPrintItems extends Entity {

	@Id
	private Integer wpitId;
	private Integer pritId;
	private Integer wateId;
	private Timestamp wpitModifyTime;
	private Timestamp wpitCreateTime;

	public Integer getWpitId() {
		return wpitId;
	}

	public void setWpitId(Integer wpitId) {
		this.wpitId = wpitId;
	}

	public Integer getPritId() {
		return pritId;
	}

	public void setPritId(Integer pritId) {
		this.pritId = pritId;
	}

	public Integer getWateId() {
		return wateId;
	}

	public void setWateId(Integer wateId) {
		this.wateId = wateId;
	}

	public Timestamp getWpitModifyTime() {
		return wpitModifyTime;
	}

	public void setWpitModifyTime(Timestamp wpitModifyTime) {
		this.wpitModifyTime = wpitModifyTime;
	}

	public Timestamp getWpitCreateTime() {
		return wpitCreateTime;
	}

	public void setWpitCreateTime(Timestamp wpitCreateTime) {
		this.wpitCreateTime = wpitCreateTime;
	}

}

