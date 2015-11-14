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
 * Description	 : 实体类 ClientAppeal
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class ClientAppeal extends Entity {

	@Id
	private Integer clapId;
	private Integer goorId;
	private String clapReason;
	private Double clapMoney;
	private Timestamp clapApplicationTime;
	private Timestamp clapCheckTime;
	private Byte clapFlowStatus;
	private String clapReplyContent;
	private Byte clapType;
	private Byte clapPayWay;

	public Integer getClapId() {
		return clapId;
	}

	public void setClapId(Integer clapId) {
		this.clapId = clapId;
	}

	public Integer getGoorId() {
		return goorId;
	}

	public void setGoorId(Integer goorId) {
		this.goorId = goorId;
	}

	public String getClapReason() {
		return clapReason;
	}

	public void setClapReason(String clapReason) {
		this.clapReason = clapReason;
	}

	public Double getClapMoney() {
		return clapMoney;
	}

	public void setClapMoney(Double clapMoney) {
		this.clapMoney = clapMoney;
	}

	public Timestamp getClapApplicationTime() {
		return clapApplicationTime;
	}

	public void setClapApplicationTime(Timestamp clapApplicationTime) {
		this.clapApplicationTime = clapApplicationTime;
	}

	public Timestamp getClapCheckTime() {
		return clapCheckTime;
	}

	public void setClapCheckTime(Timestamp clapCheckTime) {
		this.clapCheckTime = clapCheckTime;
	}

	public Byte getClapFlowStatus() {
		return clapFlowStatus;
	}

	public void setClapFlowStatus(Byte clapFlowStatus) {
		this.clapFlowStatus = clapFlowStatus;
	}

	public String getClapReplyContent() {
		return clapReplyContent;
	}

	public void setClapReplyContent(String clapReplyContent) {
		this.clapReplyContent = clapReplyContent;
	}

	public Byte getClapType() {
		return clapType;
	}

	public void setClapType(Byte clapType) {
		this.clapType = clapType;
	}

	public Byte getClapPayWay() {
		return clapPayWay;
	}

	public void setClapPayWay(Byte clapPayWay) {
		this.clapPayWay = clapPayWay;
	}

}

