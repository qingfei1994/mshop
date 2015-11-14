/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.entity;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.Id;

/**
 * Description	 : 实体类 WeixinReplyTemplate
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class WeixinReplyTemplate extends Entity {

	@Id
	private Integer wrteId;
	private Integer wpseId;
	private String wrteTemplateJoin;
	private Byte wrteType;

	public Integer getWrteId() {
		return wrteId;
	}

	public void setWrteId(Integer wrteId) {
		this.wrteId = wrteId;
	}

	public Integer getWpseId() {
		return wpseId;
	}

	public void setWpseId(Integer wpseId) {
		this.wpseId = wpseId;
	}

	public String getWrteTemplateJoin() {
		return wrteTemplateJoin;
	}

	public void setWrteTemplateJoin(String wrteTemplateJoin) {
		this.wrteTemplateJoin = wrteTemplateJoin;
	}

	public Byte getWrteType() {
		return wrteType;
	}

	public void setWrteType(Byte wrteType) {
		this.wrteType = wrteType;
	}

}

