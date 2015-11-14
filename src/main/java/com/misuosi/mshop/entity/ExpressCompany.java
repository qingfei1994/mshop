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
 * Description	 : 实体类 ExpressCompany
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class ExpressCompany extends Entity {

	@Id
	private Integer excoId;
	private String excoName;
	private Timestamp excoModifyTime;
	private Timestamp excoCreateTime;

	public Integer getExcoId() {
		return excoId;
	}

	public void setExcoId(Integer excoId) {
		this.excoId = excoId;
	}

	public String getExcoName() {
		return excoName;
	}

	public void setExcoName(String excoName) {
		this.excoName = excoName;
	}

	public Timestamp getExcoModifyTime() {
		return excoModifyTime;
	}

	public void setExcoModifyTime(Timestamp excoModifyTime) {
		this.excoModifyTime = excoModifyTime;
	}

	public Timestamp getExcoCreateTime() {
		return excoCreateTime;
	}

	public void setExcoCreateTime(Timestamp excoCreateTime) {
		this.excoCreateTime = excoCreateTime;
	}

}

