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
 * Description	 : 实体类 HelpDocument
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class HelpDocument extends Entity {

	@Id
	private Integer hedoId;
	private String hedoTitle;
	private String hedoContent;
	private Byte hedoType;
	private Timestamp hedoModifyDate;
	private Timestamp hedoCreateDate;

	public Integer getHedoId() {
		return hedoId;
	}

	public void setHedoId(Integer hedoId) {
		this.hedoId = hedoId;
	}

	public String getHedoTitle() {
		return hedoTitle;
	}

	public void setHedoTitle(String hedoTitle) {
		this.hedoTitle = hedoTitle;
	}

	public String getHedoContent() {
		return hedoContent;
	}

	public void setHedoContent(String hedoContent) {
		this.hedoContent = hedoContent;
	}

	public Byte getHedoType() {
		return hedoType;
	}

	public void setHedoType(Byte hedoType) {
		this.hedoType = hedoType;
	}

	public Timestamp getHedoModifyDate() {
		return hedoModifyDate;
	}

	public void setHedoModifyDate(Timestamp hedoModifyDate) {
		this.hedoModifyDate = hedoModifyDate;
	}

	public Timestamp getHedoCreateDate() {
		return hedoCreateDate;
	}

	public void setHedoCreateDate(Timestamp hedoCreateDate) {
		this.hedoCreateDate = hedoCreateDate;
	}

}

