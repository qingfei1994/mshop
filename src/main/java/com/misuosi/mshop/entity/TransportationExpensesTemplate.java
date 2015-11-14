/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.entity;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.Id;
import com.misuosi.mshop.db.annotation.OneToMany;

import java.sql.Timestamp;
import java.util.List;

/**
 * Description	 : 实体类 TransportationExpensesTemplate
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class TransportationExpensesTemplate extends Entity {

	@Id
	private Integer teteId;
	private Integer addrId;
	private String teteName;
	private Byte tetePricingManner;
	private Timestamp teteModifyTime;
	private Timestamp teteCreateTime;

	@OneToMany(table = "TransportationExpenses", foreignKey = "teteId")
	private List<TransportationExpenses> transportationExpenseses;

	public Integer getTeteId() {
		return teteId;
	}

	public void setTeteId(Integer teteId) {
		this.teteId = teteId;
	}

	public Integer getAddrId() {
		return addrId;
	}

	public void setAddrId(Integer addrId) {
		this.addrId = addrId;
	}

	public String getTeteName() {
		return teteName;
	}

	public void setTeteName(String teteName) {
		this.teteName = teteName;
	}

	public Byte getTetePricingManner() {
		return tetePricingManner;
	}

	public void setTetePricingManner(Byte tetePricingManner) {
		this.tetePricingManner = tetePricingManner;
	}

	public Timestamp getTeteModifyTime() {
		return teteModifyTime;
	}

	public void setTeteModifyTime(Timestamp teteModifyTime) {
		this.teteModifyTime = teteModifyTime;
	}

	public Timestamp getTeteCreateTime() {
		return teteCreateTime;
	}

	public void setTeteCreateTime(Timestamp teteCreateTime) {
		this.teteCreateTime = teteCreateTime;
	}

	public List<TransportationExpenses> getTransportationExpenseses() {
		return transportationExpenseses;
	}

	public void setTransportationExpenseses(List<TransportationExpenses> transportationExpenseses) {
		this.transportationExpenseses = transportationExpenseses;
	}

}

