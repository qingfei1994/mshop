/*
 * Copyright (c) 2015
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.entity;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.Id;

import java.sql.Timestamp;

/**
 * Description	 : 实体类 IndexBanner
 * <br><br>Time	 : 2015/07/28 11:34
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class IndexBanner extends Entity {

	@Id
	private Integer inbaId;
	private Integer goodId;
	private String inbaImageUrl;
	private String inbaDetailUrl;
	private Byte inbaSort;
	private Boolean inbaIsVisible;
	private Timestamp inbaModifyTime;
	private Timestamp inbaCreateTime;

	public Integer getInbaId() {
		return inbaId;
	}

	public void setInbaId(Integer inbaId) {
		this.inbaId = inbaId;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public String getInbaImageUrl() {
		return inbaImageUrl;
	}

	public void setInbaImageUrl(String inbaImageUrl) {
		this.inbaImageUrl = inbaImageUrl;
	}

	public String getInbaDetailUrl() {
		return inbaDetailUrl;
	}

	public void setInbaDetailUrl(String inbaDetailUrl) {
		this.inbaDetailUrl = inbaDetailUrl;
	}

	public Byte getInbaSort() {
		return inbaSort;
	}

	public void setInbaSort(Byte inbaSort) {
		this.inbaSort = inbaSort;
	}

	public Boolean getInbaIsVisible() {
		return inbaIsVisible;
	}

	public void setInbaIsVisible(Boolean inbaIsVisible) {
		this.inbaIsVisible = inbaIsVisible;
	}

	public Timestamp getInbaModifyTime() {
		return inbaModifyTime;
	}

	public void setInbaModifyTime(Timestamp inbaModifyTime) {
		this.inbaModifyTime = inbaModifyTime;
	}

	public Timestamp getInbaCreateTime() {
		return inbaCreateTime;
	}

	public void setInbaCreateTime(Timestamp inbaCreateTime) {
		this.inbaCreateTime = inbaCreateTime;
	}

}

