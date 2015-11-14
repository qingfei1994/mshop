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
 * Description	 : 实体类 WaybillTemplate
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class WaybillTemplate extends Entity {

	@Id
	private Integer wateId;
	private Integer excoId;
	private String wateName;
	private Double wateLength;
	private Double wateHeight;
	private String watePictureUrl;
	private Short wateOffsetX;
	private Short wateOffsetY;
	private Byte wateDefault;
	private Byte wateType;
	private String wateContent;
	private Timestamp wateModifyTime;
	private Timestamp wateCreateTime;

	public Integer getWateId() {
		return wateId;
	}

	public void setWateId(Integer wateId) {
		this.wateId = wateId;
	}

	public Integer getExcoId() {
		return excoId;
	}

	public void setExcoId(Integer excoId) {
		this.excoId = excoId;
	}

	public String getWateName() {
		return wateName;
	}

	public void setWateName(String wateName) {
		this.wateName = wateName;
	}

	public Double getWateLength() {
		return wateLength;
	}

	public void setWateLength(Double wateLength) {
		this.wateLength = wateLength;
	}

	public Double getWateHeight() {
		return wateHeight;
	}

	public void setWateHeight(Double wateHeight) {
		this.wateHeight = wateHeight;
	}

	public String getWatePictureUrl() {
		return watePictureUrl;
	}

	public void setWatePictureUrl(String watePictureUrl) {
		this.watePictureUrl = watePictureUrl;
	}

	public Short getWateOffsetX() {
		return wateOffsetX;
	}

	public void setWateOffsetX(Short wateOffsetX) {
		this.wateOffsetX = wateOffsetX;
	}

	public Short getWateOffsetY() {
		return wateOffsetY;
	}

	public void setWateOffsetY(Short wateOffsetY) {
		this.wateOffsetY = wateOffsetY;
	}

	public Byte getWateDefault() {
		return wateDefault;
	}

	public void setWateDefault(Byte wateDefault) {
		this.wateDefault = wateDefault;
	}

	public Byte getWateType() {
		return wateType;
	}

	public void setWateType(Byte wateType) {
		this.wateType = wateType;
	}

	public String getWateContent() {
		return wateContent;
	}

	public void setWateContent(String wateContent) {
		this.wateContent = wateContent;
	}

	public Timestamp getWateModifyTime() {
		return wateModifyTime;
	}

	public void setWateModifyTime(Timestamp wateModifyTime) {
		this.wateModifyTime = wateModifyTime;
	}

	public Timestamp getWateCreateTime() {
		return wateCreateTime;
	}

	public void setWateCreateTime(Timestamp wateCreateTime) {
		this.wateCreateTime = wateCreateTime;
	}

}

