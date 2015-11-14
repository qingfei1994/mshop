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
 * Description	 : 实体类 ShipmentsInformation
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class ShipmentsInformation extends Entity {

	@Id
	private Integer shinId;
	private Integer coadId;
	private Integer addrId;
	private Integer excoId;
	private Integer orinId;
	private Byte shinShipmentsStatus;
	private String shinExpressNo;
	private Byte shinSignStatus;
	private Timestamp shinModifyTime;
	private Timestamp shinShipmentsTime;
	private Timestamp shinSignTime;
	private Timestamp shinCreateTime;
	private String shinRemark;

	public Integer getShinId() {
		return shinId;
	}

	public void setShinId(Integer shinId) {
		this.shinId = shinId;
	}

	public Integer getCoadId() {
		return coadId;
	}

	public void setCoadId(Integer coadId) {
		this.coadId = coadId;
	}

	public Integer getAddrId() {
		return addrId;
	}

	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}

	public Integer getExcoId() {
		return excoId;
	}

	public void setExcoId(Integer excoId) {
		this.excoId = excoId;
	}

	public Integer getOrinId() {
		return orinId;
	}

	public void setOrinId(Integer orinId) {
		this.orinId = orinId;
	}

	public Byte getShinShipmentsStatus() {
		return shinShipmentsStatus;
	}

	public void setShinShipmentsStatus(Byte shinShipmentsStatus) {
		this.shinShipmentsStatus = shinShipmentsStatus;
	}

	public String getShinExpressNo() {
		return shinExpressNo;
	}

	public void setShinExpressNo(String shinExpressNo) {
		this.shinExpressNo = shinExpressNo;
	}

	public Byte getShinSignStatus() {
		return shinSignStatus;
	}

	public void setShinSignStatus(Byte shinSignStatus) {
		this.shinSignStatus = shinSignStatus;
	}

	public Timestamp getShinModifyTime() {
		return shinModifyTime;
	}

	public void setShinModifyTime(Timestamp shinModifyTime) {
		this.shinModifyTime = shinModifyTime;
	}

	public Timestamp getShinShipmentsTime() {
		return shinShipmentsTime;
	}

	public void setShinShipmentsTime(Timestamp shinShipmentsTime) {
		this.shinShipmentsTime = shinShipmentsTime;
	}

	public Timestamp getShinSignTime() {
		return shinSignTime;
	}

	public void setShinSignTime(Timestamp shinSignTime) {
		this.shinSignTime = shinSignTime;
	}

	public Timestamp getShinCreateTime() {
		return shinCreateTime;
	}

	public void setShinCreateTime(Timestamp shinCreateTime) {
		this.shinCreateTime = shinCreateTime;
	}

	public String getShinRemark() {
		return shinRemark;
	}

	public void setShinRemark(String shinRemark) {
		this.shinRemark = shinRemark;
	}

}

