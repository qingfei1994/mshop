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
 * Description	 : 实体类 Request
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class Request extends Entity {

	@Id
	private Integer requId;
	private Integer requIp;
	private Timestamp requTime;

	public Integer getRequId() {
		return requId;
	}

	public void setRequId(Integer requId) {
		this.requId = requId;
	}

	public Integer getRequIp() {
		return requIp;
	}

	public void setRequIp(Integer requIp) {
		this.requIp = requIp;
	}

	public Timestamp getRequTime() {
		return requTime;
	}

	public void setRequTime(Timestamp requTime) {
		this.requTime = requTime;
	}

}

