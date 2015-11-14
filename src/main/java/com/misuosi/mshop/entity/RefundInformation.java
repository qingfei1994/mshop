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
 * Description	 : 实体类 RefundInformation
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class RefundInformation extends Entity {

	@Id
	private Integer reinId;
	private Integer goorId;
	private String reinNo;
	private String outReinNo;
	private Byte reinStatus;
	private Byte reinAuditStatus;
	private String reinReason;
	private String reinAuditReason;
	private Byte reinType;
	private Double reinMoney;
	private Timestamp reinModifyTime;
	private Timestamp reinCreateTime;

	public Integer getReinId() {
		return reinId;
	}

	public void setReinId(Integer reinId) {
		this.reinId = reinId;
	}

	public Integer getGoorId() {
		return goorId;
	}

	public void setGoorId(Integer goorId) {
		this.goorId = goorId;
	}

	public String getReinNo() {
		return reinNo;
	}

	public void setReinNo(String reinNo) {
		this.reinNo = reinNo;
	}

	public String getOutReinNo() {
		return outReinNo;
	}

	public void setOutReinNo(String outReinNo) {
		this.outReinNo = outReinNo;
	}

	public Byte getReinStatus() {
		return reinStatus;
	}

	public void setReinStatus(Byte reinStatus) {
		this.reinStatus = reinStatus;
	}

	public Byte getReinAuditStatus() {
		return reinAuditStatus;
	}

	public void setReinAuditStatus(Byte reinAuditStatus) {
		this.reinAuditStatus = reinAuditStatus;
	}

	public String getReinReason() {
		return reinReason;
	}

	public void setReinReason(String reinReason) {
		this.reinReason = reinReason;
	}

	public String getReinAuditReason() {
		return reinAuditReason;
	}

	public void setReinAuditReason(String reinAuditReason) {
		this.reinAuditReason = reinAuditReason;
	}

	public Byte getReinType() {
		return reinType;
	}

	public void setReinType(Byte reinType) {
		this.reinType = reinType;
	}

	public Double getReinMoney() {
		return reinMoney;
	}

	public void setReinMoney(Double reinMoney) {
		this.reinMoney = reinMoney;
	}

	public Timestamp getReinModifyTime() {
		return reinModifyTime;
	}

	public void setReinModifyTime(Timestamp reinModifyTime) {
		this.reinModifyTime = reinModifyTime;
	}

	public Timestamp getReinCreateTime() {
		return reinCreateTime;
	}

	public void setReinCreateTime(Timestamp reinCreateTime) {
		this.reinCreateTime = reinCreateTime;
	}

}

