/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.entity;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.Id;

import java.util.Date;
import java.sql.Timestamp;

/**
 * Description	 : 实体类 MemberInformation
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class MemberInformation extends Entity {

	@Id
	private Integer meinId;
	private Integer weinId;
	private Integer regiId;
	private String meinName;
	private String meinPhone;
	private Byte meinSex;
	private Date meinBirthdayDate;
	private String meinDetailedAddress;
	private Integer meinIntegral;
	private Double meinBalance;
	private Timestamp meinModifyTime;
	private Timestamp meinCreateTime;

	public Integer getMeinId() {
		return meinId;
	}

	public void setMeinId(Integer meinId) {
		this.meinId = meinId;
	}

	public Integer getWeinId() {
		return weinId;
	}

	public void setWeinId(Integer weinId) {
		this.weinId = weinId;
	}

	public Integer getRegiId() {
		return regiId;
	}

	public void setRegiId(Integer regiId) {
		this.regiId = regiId;
	}

	public String getMeinName() {
		return meinName;
	}

	public void setMeinName(String meinName) {
		this.meinName = meinName;
	}

	public String getMeinPhone() {
		return meinPhone;
	}

	public void setMeinPhone(String meinPhone) {
		this.meinPhone = meinPhone;
	}

	public Byte getMeinSex() {
		return meinSex;
	}

	public void setMeinSex(Byte meinSex) {
		this.meinSex = meinSex;
	}

	public Date getMeinBirthdayDate() {
		return meinBirthdayDate;
	}

	public void setMeinBirthdayDate(Date meinBirthdayDate) {
		this.meinBirthdayDate = meinBirthdayDate;
	}

	public String getMeinDetailedAddress() {
		return meinDetailedAddress;
	}

	public void setMeinDetailedAddress(String meinDetailedAddress) {
		this.meinDetailedAddress = meinDetailedAddress;
	}

	public Integer getMeinIntegral() {
		return meinIntegral;
	}

	public void setMeinIntegral(Integer meinIntegral) {
		this.meinIntegral = meinIntegral;
	}

	public Double getMeinBalance() {
		return meinBalance;
	}

	public void setMeinBalance(Double meinBalance) {
		this.meinBalance = meinBalance;
	}

	public Timestamp getMeinModifyTime() {
		return meinModifyTime;
	}

	public void setMeinModifyTime(Timestamp meinModifyTime) {
		this.meinModifyTime = meinModifyTime;
	}

	public Timestamp getMeinCreateTime() {
		return meinCreateTime;
	}

	public void setMeinCreateTime(Timestamp meinCreateTime) {
		this.meinCreateTime = meinCreateTime;
	}

}

