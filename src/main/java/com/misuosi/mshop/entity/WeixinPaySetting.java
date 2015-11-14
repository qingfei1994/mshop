/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.entity;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.Id;

/**
 * Description	 : 实体类 WeixinPaySetting
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class WeixinPaySetting extends Entity {

	@Id
	private Integer wpseId;
	private String wpseMchId;
	private String wpseApiKey;

	public Integer getWpseId() {
		return wpseId;
	}

	public void setWpseId(Integer wpseId) {
		this.wpseId = wpseId;
	}

	public String getWpseMchId() {
		return wpseMchId;
	}

	public void setWpseMchId(String wpseMchId) {
		this.wpseMchId = wpseMchId;
	}

	public String getWpseApiKey() {
		return wpseApiKey;
	}

	public void setWpseApiKey(String wpseApiKey) {
		this.wpseApiKey = wpseApiKey;
	}

}

