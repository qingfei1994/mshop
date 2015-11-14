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
 * Description	 : 实体类 GuideFollow
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class GuideFollow extends Entity {

	@Id
	private Integer gufoId;
	private Byte gufoStatus;
	private String gufoPageUrl;
	private Timestamp gufoModifyTime;
	private Timestamp gufoCreateTime;

	public Integer getGufoId() {
		return gufoId;
	}

	public void setGufoId(Integer gufoId) {
		this.gufoId = gufoId;
	}

	public Byte getGufoStatus() {
		return gufoStatus;
	}

	public void setGufoStatus(Byte gufoStatus) {
		this.gufoStatus = gufoStatus;
	}

	public String getGufoPageUrl() {
		return gufoPageUrl;
	}

	public void setGufoPageUrl(String gufoPageUrl) {
		this.gufoPageUrl = gufoPageUrl;
	}

	public Timestamp getGufoModifyTime() {
		return gufoModifyTime;
	}

	public void setGufoModifyTime(Timestamp gufoModifyTime) {
		this.gufoModifyTime = gufoModifyTime;
	}

	public Timestamp getGufoCreateTime() {
		return gufoCreateTime;
	}

	public void setGufoCreateTime(Timestamp gufoCreateTime) {
		this.gufoCreateTime = gufoCreateTime;
	}

}

