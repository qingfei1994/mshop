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
 * Description	 : 实体类 PrintItems
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class PrintItems extends Entity {

	@Id
	private Integer pritId;
	private String pritName;
	private Timestamp pritModifyTime;
	private Timestamp pritCreateTime;

	public Integer getPritId() {
		return pritId;
	}

	public void setPritId(Integer pritId) {
		this.pritId = pritId;
	}

	public String getPritName() {
		return pritName;
	}

	public void setPritName(String pritName) {
		this.pritName = pritName;
	}

	public Timestamp getPritModifyTime() {
		return pritModifyTime;
	}

	public void setPritModifyTime(Timestamp pritModifyTime) {
		this.pritModifyTime = pritModifyTime;
	}

	public Timestamp getPritCreateTime() {
		return pritCreateTime;
	}

	public void setPritCreateTime(Timestamp pritCreateTime) {
		this.pritCreateTime = pritCreateTime;
	}

}

