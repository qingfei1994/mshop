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
 * Description	 : 实体类 OrderSetting
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class OrderSetting extends Entity {

	@Id
	private Integer orseId;
	private Byte orseRefund;
	private Byte orseRefundDeadline;
	private Byte orseAutomateAffirm;
	private Byte orseAutomateAffirmDeadline;
	private Byte orseAutomateClose;
	private Short orsePayDeadline;
	private Byte orseDefaultPraises;
	private Byte orseAutomatePraisesDeadline;
	private String orsePraisesContent;
	private Timestamp orseModifyTime;
	private Timestamp orseCreateTime;

	public Integer getOrseId() {
		return orseId;
	}

	public void setOrseId(Integer orseId) {
		this.orseId = orseId;
	}

	public Byte getOrseRefund() {
		return orseRefund;
	}

	public void setOrseRefund(Byte orseRefund) {
		this.orseRefund = orseRefund;
	}

	public Byte getOrseRefundDeadline() {
		return orseRefundDeadline;
	}

	public void setOrseRefundDeadline(Byte orseRefundDeadline) {
		this.orseRefundDeadline = orseRefundDeadline;
	}

	public Byte getOrseAutomateAffirm() {
		return orseAutomateAffirm;
	}

	public void setOrseAutomateAffirm(Byte orseAutomateAffirm) {
		this.orseAutomateAffirm = orseAutomateAffirm;
	}

	public Byte getOrseAutomateAffirmDeadline() {
		return orseAutomateAffirmDeadline;
	}

	public void setOrseAutomateAffirmDeadline(Byte orseAutomateAffirmDeadline) {
		this.orseAutomateAffirmDeadline = orseAutomateAffirmDeadline;
	}

	public Byte getOrseAutomateClose() {
		return orseAutomateClose;
	}

	public void setOrseAutomateClose(Byte orseAutomateClose) {
		this.orseAutomateClose = orseAutomateClose;
	}

	public Short getOrsePayDeadline() {
		return orsePayDeadline;
	}

	public void setOrsePayDeadline(Short orsePayDeadline) {
		this.orsePayDeadline = orsePayDeadline;
	}

	public Byte getOrseDefaultPraises() {
		return orseDefaultPraises;
	}

	public void setOrseDefaultPraises(Byte orseDefaultPraises) {
		this.orseDefaultPraises = orseDefaultPraises;
	}

	public Byte getOrseAutomatePraisesDeadline() {
		return orseAutomatePraisesDeadline;
	}

	public void setOrseAutomatePraisesDeadline(Byte orseAutomatePraisesDeadline) {
		this.orseAutomatePraisesDeadline = orseAutomatePraisesDeadline;
	}

	public String getOrsePraisesContent() {
		return orsePraisesContent;
	}

	public void setOrsePraisesContent(String orsePraisesContent) {
		this.orsePraisesContent = orsePraisesContent;
	}

	public Timestamp getOrseModifyTime() {
		return orseModifyTime;
	}

	public void setOrseModifyTime(Timestamp orseModifyTime) {
		this.orseModifyTime = orseModifyTime;
	}

	public Timestamp getOrseCreateTime() {
		return orseCreateTime;
	}

	public void setOrseCreateTime(Timestamp orseCreateTime) {
		this.orseCreateTime = orseCreateTime;
	}

}

