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
 * Description	 : 实体类 Contacts
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class Contacts extends Entity {

	@Id
	private Integer contId;
	private String contName;
	private String contPhone;
	private String contMobilePhone;
	private String contEmail;
	private String contQq;
	private Timestamp contModifyTime;
	private Timestamp contCreateTime;

	public Integer getContId() {
		return contId;
	}

	public void setContId(Integer contId) {
		this.contId = contId;
	}

	public String getContName() {
		return contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public String getContPhone() {
		return contPhone;
	}

	public void setContPhone(String contPhone) {
		this.contPhone = contPhone;
	}

	public String getContMobilePhone() {
		return contMobilePhone;
	}

	public void setContMobilePhone(String contMobilePhone) {
		this.contMobilePhone = contMobilePhone;
	}

	public String getContEmail() {
		return contEmail;
	}

	public void setContEmail(String contEmail) {
		this.contEmail = contEmail;
	}

	public String getContQq() {
		return contQq;
	}

	public void setContQq(String contQq) {
		this.contQq = contQq;
	}

	public Timestamp getContModifyTime() {
		return contModifyTime;
	}

	public void setContModifyTime(Timestamp contModifyTime) {
		this.contModifyTime = contModifyTime;
	}

	public Timestamp getContCreateTime() {
		return contCreateTime;
	}

	public void setContCreateTime(Timestamp contCreateTime) {
		this.contCreateTime = contCreateTime;
	}

}

