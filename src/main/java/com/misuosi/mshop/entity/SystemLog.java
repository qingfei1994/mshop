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
 * Description	 : 实体类 SystemLog
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class SystemLog extends Entity {

	@Id
	private Integer syloId;
	private Integer syloIp;
	private Timestamp syloCreateTime;
	private String syloOperator;
	private String syloOperation;

	public Integer getSyloId() {
		return syloId;
	}

	public void setSyloId(Integer syloId) {
		this.syloId = syloId;
	}

	public Integer getSyloIp() {
		return syloIp;
	}

	public void setSyloIp(Integer syloIp) {
		this.syloIp = syloIp;
	}

	public Timestamp getSyloCreateTime() {
		return syloCreateTime;
	}

	public void setSyloCreateTime(Timestamp syloCreateTime) {
		this.syloCreateTime = syloCreateTime;
	}

	public String getSyloOperator() {
		return syloOperator;
	}

	public void setSyloOperator(String syloOperator) {
		this.syloOperator = syloOperator;
	}

	public String getSyloOperation() {
		return syloOperation;
	}

	public void setSyloOperation(String syloOperation) {
		this.syloOperation = syloOperation;
	}

}

