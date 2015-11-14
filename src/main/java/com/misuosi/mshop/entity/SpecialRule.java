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
 * Description	 : 实体类 SpecialRule
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class SpecialRule extends Entity {

	@Id
	private Integer spruId;
	private Integer acmaId;
	private Integer spruConditionInputMoney;
	private Integer spruConditionInputPiece;
	private Double spruDiscountInput;
	private Double spruMoneyInput;
	private Byte spruMaxLimit;
	private Timestamp spruModifyTime;
	private Timestamp spruCreateTime;

	public Integer getSpruId() {
		return spruId;
	}

	public void setSpruId(Integer spruId) {
		this.spruId = spruId;
	}

	public Integer getAcmaId() {
		return acmaId;
	}

	public void setAcmaId(Integer acmaId) {
		this.acmaId = acmaId;
	}

	public Integer getSpruConditionInputMoney() {
		return spruConditionInputMoney;
	}

	public void setSpruConditionInputMoney(Integer spruConditionInputMoney) {
		this.spruConditionInputMoney = spruConditionInputMoney;
	}

	public Integer getSpruConditionInputPiece() {
		return spruConditionInputPiece;
	}

	public void setSpruConditionInputPiece(Integer spruConditionInputPiece) {
		this.spruConditionInputPiece = spruConditionInputPiece;
	}

	public Double getSpruDiscountInput() {
		return spruDiscountInput;
	}

	public void setSpruDiscountInput(Double spruDiscountInput) {
		this.spruDiscountInput = spruDiscountInput;
	}

	public Double getSpruMoneyInput() {
		return spruMoneyInput;
	}

	public void setSpruMoneyInput(Double spruMoneyInput) {
		this.spruMoneyInput = spruMoneyInput;
	}

	public Byte getSpruMaxLimit() {
		return spruMaxLimit;
	}

	public void setSpruMaxLimit(Byte spruMaxLimit) {
		this.spruMaxLimit = spruMaxLimit;
	}

	public Timestamp getSpruModifyTime() {
		return spruModifyTime;
	}

	public void setSpruModifyTime(Timestamp spruModifyTime) {
		this.spruModifyTime = spruModifyTime;
	}

	public Timestamp getSpruCreateTime() {
		return spruCreateTime;
	}

	public void setSpruCreateTime(Timestamp spruCreateTime) {
		this.spruCreateTime = spruCreateTime;
	}

}

