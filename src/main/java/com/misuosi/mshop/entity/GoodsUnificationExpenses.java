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
 * Description	 : 实体类 GoodsUnificationExpenses
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class GoodsUnificationExpenses extends Entity {

	@Id
	private Integer guexId;
	private Integer goodId;
	private Double guexExpressPrice;
	private Double guexEmsPrice;
	private Double guexPostPrice;
	private Timestamp guexModifyTime;
	private Timestamp guexCreateTime;

	public Integer getGuexId() {
		return guexId;
	}

	public void setGuexId(Integer guexId) {
		this.guexId = guexId;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Double getGuexExpressPrice() {
		return guexExpressPrice;
	}

	public void setGuexExpressPrice(Double guexExpressPrice) {
		this.guexExpressPrice = guexExpressPrice;
	}

	public Double getGuexEmsPrice() {
		return guexEmsPrice;
	}

	public void setGuexEmsPrice(Double guexEmsPrice) {
		this.guexEmsPrice = guexEmsPrice;
	}

	public Double getGuexPostPrice() {
		return guexPostPrice;
	}

	public void setGuexPostPrice(Double guexPostPrice) {
		this.guexPostPrice = guexPostPrice;
	}

	public Timestamp getGuexModifyTime() {
		return guexModifyTime;
	}

	public void setGuexModifyTime(Timestamp guexModifyTime) {
		this.guexModifyTime = guexModifyTime;
	}

	public Timestamp getGuexCreateTime() {
		return guexCreateTime;
	}

	public void setGuexCreateTime(Timestamp guexCreateTime) {
		this.guexCreateTime = guexCreateTime;
	}

}

