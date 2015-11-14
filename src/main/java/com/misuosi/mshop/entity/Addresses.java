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
 * Description	 : 实体类 Addresses
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class Addresses extends Entity {

	@Id
	private Integer addrId;
	private Integer regiId;
	private String addrDetailedAddress;
	private String addrAreaCode;
	private String addrPhone;
	private String addrMobilePhone;
	private String addrCompanyName;
	private String addrContacts;
	private Byte addrDefaultSendAddress;
	private Byte addrDefaultReturnAddress;
	private Timestamp addrModifyTime;
	private Timestamp addrCreateTime;

	public Integer getAddrId() {
		return addrId;
	}

	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}

	public Integer getRegiId() {
		return regiId;
	}

	public void setRegiId(Integer regiId) {
		this.regiId = regiId;
	}

	public String getAddrDetailedAddress() {
		return addrDetailedAddress;
	}

	public void setAddrDetailedAddress(String addrDetailedAddress) {
		this.addrDetailedAddress = addrDetailedAddress;
	}

	public String getAddrAreaCode() {
		return addrAreaCode;
	}

	public void setAddrAreaCode(String addrAreaCode) {
		this.addrAreaCode = addrAreaCode;
	}

	public String getAddrPhone() {
		return addrPhone;
	}

	public void setAddrPhone(String addrPhone) {
		this.addrPhone = addrPhone;
	}

	public String getAddrMobilePhone() {
		return addrMobilePhone;
	}

	public void setAddrMobilePhone(String addrMobilePhone) {
		this.addrMobilePhone = addrMobilePhone;
	}

	public String getAddrCompanyName() {
		return addrCompanyName;
	}

	public void setAddrCompanyName(String addrCompanyName) {
		this.addrCompanyName = addrCompanyName;
	}

	public String getAddrContacts() {
		return addrContacts;
	}

	public void setAddrContacts(String addrContacts) {
		this.addrContacts = addrContacts;
	}

	public Byte getAddrDefaultSendAddress() {
		return addrDefaultSendAddress;
	}

	public void setAddrDefaultSendAddress(Byte addrDefaultSendAddress) {
		this.addrDefaultSendAddress = addrDefaultSendAddress;
	}

	public Byte getAddrDefaultReturnAddress() {
		return addrDefaultReturnAddress;
	}

	public void setAddrDefaultReturnAddress(Byte addrDefaultReturnAddress) {
		this.addrDefaultReturnAddress = addrDefaultReturnAddress;
	}

	public Timestamp getAddrModifyTime() {
		return addrModifyTime;
	}

	public void setAddrModifyTime(Timestamp addrModifyTime) {
		this.addrModifyTime = addrModifyTime;
	}

	public Timestamp getAddrCreateTime() {
		return addrCreateTime;
	}

	public void setAddrCreateTime(Timestamp addrCreateTime) {
		this.addrCreateTime = addrCreateTime;
	}

}

