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
 * Description	 : 实体类 ClientAppealPicture
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class ClientAppealPicture extends Entity {

	@Id
	private Integer capiId;
	private Integer clapId;
	private String capiPictureUrl;
	private Timestamp capiModifyTime;
	private Timestamp capiCreateTime;

	public Integer getCapiId() {
		return capiId;
	}

	public void setCapiId(Integer capiId) {
		this.capiId = capiId;
	}

	public Integer getClapId() {
		return clapId;
	}

	public void setClapId(Integer clapId) {
		this.clapId = clapId;
	}

	public String getCapiPictureUrl() {
		return capiPictureUrl;
	}

	public void setCapiPictureUrl(String capiPictureUrl) {
		this.capiPictureUrl = capiPictureUrl;
	}

	public Timestamp getCapiModifyTime() {
		return capiModifyTime;
	}

	public void setCapiModifyTime(Timestamp capiModifyTime) {
		this.capiModifyTime = capiModifyTime;
	}

	public Timestamp getCapiCreateTime() {
		return capiCreateTime;
	}

	public void setCapiCreateTime(Timestamp capiCreateTime) {
		this.capiCreateTime = capiCreateTime;
	}

}

