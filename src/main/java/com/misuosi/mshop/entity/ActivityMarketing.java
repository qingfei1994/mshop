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
 * Description	 : 实体类 ActivityMarketing
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class ActivityMarketing extends Entity {

	@Id
	private Integer acmaId;
	private Integer regiId;
	private Integer prlaId;
	private Integer aclaId;
	private String acmaName;
	private Timestamp acmaStartTime;
	private Timestamp acmaEndTime;
	private Byte acmaStatus;
	private Byte acmaFreeExpress;
	private Timestamp acmaModifyTime;
	private Timestamp acmaCreateTime;

	public Integer getAcmaId() {
		return acmaId;
	}

	public void setAcmaId(Integer acmaId) {
		this.acmaId = acmaId;
	}

	public Integer getRegiId() {
		return regiId;
	}

	public void setRegiId(Integer regiId) {
		this.regiId = regiId;
	}

	public Integer getPrlaId() {
		return prlaId;
	}

	public void setPrlaId(Integer prlaId) {
		this.prlaId = prlaId;
	}

	public Integer getAclaId() {
		return aclaId;
	}

	public void setAclaId(Integer aclaId) {
		this.aclaId = aclaId;
	}

	public String getAcmaName() {
		return acmaName;
	}

	public void setAcmaName(String acmaName) {
		this.acmaName = acmaName;
	}

	public Timestamp getAcmaStartTime() {
		return acmaStartTime;
	}

	public void setAcmaStartTime(Timestamp acmaStartTime) {
		this.acmaStartTime = acmaStartTime;
	}

	public Timestamp getAcmaEndTime() {
		return acmaEndTime;
	}

	public void setAcmaEndTime(Timestamp acmaEndTime) {
		this.acmaEndTime = acmaEndTime;
	}

	public Byte getAcmaStatus() {
		return acmaStatus;
	}

	public void setAcmaStatus(Byte acmaStatus) {
		this.acmaStatus = acmaStatus;
	}

	public Byte getAcmaFreeExpress() {
		return acmaFreeExpress;
	}

	public void setAcmaFreeExpress(Byte acmaFreeExpress) {
		this.acmaFreeExpress = acmaFreeExpress;
	}

	public Timestamp getAcmaModifyTime() {
		return acmaModifyTime;
	}

	public void setAcmaModifyTime(Timestamp acmaModifyTime) {
		this.acmaModifyTime = acmaModifyTime;
	}

	public Timestamp getAcmaCreateTime() {
		return acmaCreateTime;
	}

	public void setAcmaCreateTime(Timestamp acmaCreateTime) {
		this.acmaCreateTime = acmaCreateTime;
	}

}

