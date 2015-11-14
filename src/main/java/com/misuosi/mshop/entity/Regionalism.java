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
 * Description	 : 实体类 Regionalism
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class Regionalism extends Entity {

	@Id
	private Integer regiId;
	private String regiName;
	private String regiPostalCode;
	private Byte regiGrade;
	private Timestamp regiModifyTime;
	private Timestamp regiCreateTime;
	private Integer regiParentId;

	public Integer getRegiId() {
		return regiId;
	}

	public void setRegiId(Integer regiId) {
		this.regiId = regiId;
	}

	public String getRegiName() {
		return regiName;
	}

	public void setRegiName(String regiName) {
		this.regiName = regiName;
	}

	public String getRegiPostalCode() {
		return regiPostalCode;
	}

	public void setRegiPostalCode(String regiPostalCode) {
		this.regiPostalCode = regiPostalCode;
	}

	public Byte getRegiGrade() {
		return regiGrade;
	}

	public void setRegiGrade(Byte regiGrade) {
		this.regiGrade = regiGrade;
	}

	public Timestamp getRegiModifyTime() {
		return regiModifyTime;
	}

	public void setRegiModifyTime(Timestamp regiModifyTime) {
		this.regiModifyTime = regiModifyTime;
	}

	public Timestamp getRegiCreateTime() {
		return regiCreateTime;
	}

	public void setRegiCreateTime(Timestamp regiCreateTime) {
		this.regiCreateTime = regiCreateTime;
	}

	public Integer getRegiParentId() {
		return regiParentId;
	}

	public void setRegiParentId(Integer regiParentId) {
		this.regiParentId = regiParentId;
	}

}

