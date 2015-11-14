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
 * Description	 : 实体类 GoodsPicture
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class GoodsPicture extends Entity {

	@Id
	private Integer gopiId;
	private Integer goodId;
	private String gopiUrl;
	private Byte gopiSort;
	private Timestamp gopiModifyTime;
	private Timestamp gopiCreateTime;

	public Integer getGopiId() {
		return gopiId;
	}

	public void setGopiId(Integer gopiId) {
		this.gopiId = gopiId;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public String getGopiUrl() {
		return gopiUrl;
	}

	public void setGopiUrl(String gopiUrl) {
		this.gopiUrl = gopiUrl;
	}

	public Byte getGopiSort() {
		return gopiSort;
	}

	public void setGopiSort(Byte gopiSort) {
		this.gopiSort = gopiSort;
	}

	public Timestamp getGopiModifyTime() {
		return gopiModifyTime;
	}

	public void setGopiModifyTime(Timestamp gopiModifyTime) {
		this.gopiModifyTime = gopiModifyTime;
	}

	public Timestamp getGopiCreateTime() {
		return gopiCreateTime;
	}

	public void setGopiCreateTime(Timestamp gopiCreateTime) {
		this.gopiCreateTime = gopiCreateTime;
	}

}

