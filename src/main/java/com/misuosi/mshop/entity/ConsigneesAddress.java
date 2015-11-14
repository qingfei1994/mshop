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
 * Description	 : 实体类 ConsigneesAddress
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class ConsigneesAddress extends Entity {

	@Id
	private Integer coadId;
	private Integer weinId;
	private Integer regiId;
	private String coadName;
	private String coadPhone;
	private String coadDetailedAddress;
	private Byte coadDefaultAddress;
	private Timestamp coadModifyTime;
	private Timestamp coadCreateTime;

	public Integer getCoadId() {
		return coadId;
	}

	public void setCoadId(Integer coadId) {
		this.coadId = coadId;
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

	public String getCoadName() {
		return coadName;
	}

	public void setCoadName(String coadName) {
		this.coadName = coadName;
	}

	public String getCoadPhone() {
		return coadPhone;
	}

	public void setCoadPhone(String coadPhone) {
		this.coadPhone = coadPhone;
	}

	public String getCoadDetailedAddress() {
		return coadDetailedAddress;
	}

	public void setCoadDetailedAddress(String coadDetailedAddress) {
		this.coadDetailedAddress = coadDetailedAddress;
	}

	public Byte getCoadDefaultAddress() {
		return coadDefaultAddress;
	}

	public void setCoadDefaultAddress(Byte coadDefaultAddress) {
		this.coadDefaultAddress = coadDefaultAddress;
	}

	public Timestamp getCoadModifyTime() {
		return coadModifyTime;
	}

	public void setCoadModifyTime(Timestamp coadModifyTime) {
		this.coadModifyTime = coadModifyTime;
	}

	public Timestamp getCoadCreateTime() {
		return coadCreateTime;
	}

	public void setCoadCreateTime(Timestamp coadCreateTime) {
		this.coadCreateTime = coadCreateTime;
	}

}

