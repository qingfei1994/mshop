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
 * Description	 : 实体类 PayInformation
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class PayInformation extends Entity {

	@Id
	private Integer painId;
	private Timestamp painPayTime;
	private String painPayNo;
	private String painSerialNumber;
	private Double painPayMoney;
	private Byte painPayWay;

	public Integer getPainId() {
		return painId;
	}

	public void setPainId(Integer painId) {
		this.painId = painId;
	}

	public Timestamp getPainPayTime() {
		return painPayTime;
	}

	public void setPainPayTime(Timestamp painPayTime) {
		this.painPayTime = painPayTime;
	}

	public String getPainPayNo() {
		return painPayNo;
	}

	public void setPainPayNo(String painPayNo) {
		this.painPayNo = painPayNo;
	}

	public String getPainSerialNumber() {
		return painSerialNumber;
	}

	public void setPainSerialNumber(String painSerialNumber) {
		this.painSerialNumber = painSerialNumber;
	}

	public Double getPainPayMoney() {
		return painPayMoney;
	}

	public void setPainPayMoney(Double painPayMoney) {
		this.painPayMoney = painPayMoney;
	}

	public Byte getPainPayWay() {
		return painPayWay;
	}

	public void setPainPayWay(Byte painPayWay) {
		this.painPayWay = painPayWay;
	}

}

