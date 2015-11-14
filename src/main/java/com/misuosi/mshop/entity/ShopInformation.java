/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.entity;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.Id;

/**
 * Description	 : 实体类 ShopInformation
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class ShopInformation extends Entity {

	@Id
	private Integer shinId;
	private String shinName;
	private String shinLogo;
	private String shinCopyright;
	private Byte shinCurrencyType;
	private String shinPhone;
	private String shinAddress;
	private String shinDescription;

	public Integer getShinId() {
		return shinId;
	}

	public void setShinId(Integer shinId) {
		this.shinId = shinId;
	}

	public String getShinName() {
		return shinName;
	}

	public void setShinName(String shinName) {
		this.shinName = shinName;
	}

	public String getShinLogo() {
		return shinLogo;
	}

	public void setShinLogo(String shinLogo) {
		this.shinLogo = shinLogo;
	}

	public String getShinCopyright() {
		return shinCopyright;
	}

	public void setShinCopyright(String shinCopyright) {
		this.shinCopyright = shinCopyright;
	}

	public Byte getShinCurrencyType() {
		return shinCurrencyType;
	}

	public void setShinCurrencyType(Byte shinCurrencyType) {
		this.shinCurrencyType = shinCurrencyType;
	}

	public String getShinPhone() {
		return shinPhone;
	}

	public void setShinPhone(String shinPhone) {
		this.shinPhone = shinPhone;
	}

	public String getShinAddress() {
		return shinAddress;
	}

	public void setShinAddress(String shinAddress) {
		this.shinAddress = shinAddress;
	}

	public String getShinDescription() {
		return shinDescription;
	}

	public void setShinDescription(String shinDescription) {
		this.shinDescription = shinDescription;
	}

}

