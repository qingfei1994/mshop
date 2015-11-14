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
 * Description	 : 实体类 Alipay
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class Alipay extends Entity {

	@Id
	private Integer alipId;
	private Integer abcaId;
	private String alipAccount;
	private String alipName;
	private Timestamp alipModifyTime;
	private Timestamp alipCreateTime;

	public Integer getAlipId() {
		return alipId;
	}

	public void setAlipId(Integer alipId) {
		this.alipId = alipId;
	}

	public Integer getAbcaId() {
		return abcaId;
	}

	public void setAbcaId(Integer abcaId) {
		this.abcaId = abcaId;
	}

	public String getAlipAccount() {
		return alipAccount;
	}

	public void setAlipAccount(String alipAccount) {
		this.alipAccount = alipAccount;
	}

	public String getAlipName() {
		return alipName;
	}

	public void setAlipName(String alipName) {
		this.alipName = alipName;
	}

	public Timestamp getAlipModifyTime() {
		return alipModifyTime;
	}

	public void setAlipModifyTime(Timestamp alipModifyTime) {
		this.alipModifyTime = alipModifyTime;
	}

	public Timestamp getAlipCreateTime() {
		return alipCreateTime;
	}

	public void setAlipCreateTime(Timestamp alipCreateTime) {
		this.alipCreateTime = alipCreateTime;
	}

}

