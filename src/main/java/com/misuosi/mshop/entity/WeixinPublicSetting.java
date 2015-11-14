/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.entity;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.Id;

/**
 * Description	 : 实体类 WeixinPublicSetting
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class WeixinPublicSetting extends Entity {

	@Id
	private Integer wpseId;
	private String wpseAppid;
	private String wpseAppscrect;
	private String wpseToken;

	public Integer getWpseId() {
		return wpseId;
	}

	public void setWpseId(Integer wpseId) {
		this.wpseId = wpseId;
	}

	public String getWpseAppid() {
		return wpseAppid;
	}

	public void setWpseAppid(String wpseAppid) {
		this.wpseAppid = wpseAppid;
	}

	public String getWpseAppscrect() {
		return wpseAppscrect;
	}

	public void setWpseAppscrect(String wpseAppscrect) {
		this.wpseAppscrect = wpseAppscrect;
	}

	public String getWpseToken() {
		return wpseToken;
	}

	public void setWpseToken(String wpseToken) {
		this.wpseToken = wpseToken;
	}

}

