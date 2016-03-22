/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.entity;

import java.sql.Timestamp;
import java.util.List;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.Id;
import com.misuosi.mshop.db.annotation.OneToMany;

/**
 * Description	 : 实体类 TransportationExpenses
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class TransportationExpenses extends Entity {

	@Id
	private Integer trexId;
	private Integer teteId;
	private Double trexStart;
	private Double trexStartPrice;
	private Double trexPlus;
	private Double trexPlusPrice;
	private Byte trexShippingMethod;
	private Timestamp trexModifyTime;
	private Timestamp trexCreateTime;
	private Byte trexAllRegion;
	
	@OneToMany(table = "DistributionRegion", foreignKey = "trexId")
	private List<DistributionRegion> distributionRegions;
	public Integer getTrexId() { 
		return trexId;
	}

	public void setTrexId(Integer trexId) {
		this.trexId = trexId;
	}

	public Integer getTeteId() {
		return teteId;
	}

	public void setTeteId(Integer teteId) {
		this.teteId = teteId;
	}

	public Double getTrexStart() {
		return trexStart;
	}

	public void setTrexStart(Double trexStart) {
		this.trexStart = trexStart;
	}

	public Double getTrexStartPrice() {
		return trexStartPrice;
	}

	public void setTrexStartPrice(Double trexStartPrice) {
		this.trexStartPrice = trexStartPrice;
	}

	public Double getTrexPlus() {
		return trexPlus;
	}

	public void setTrexPlus(Double trexPlus) {
		this.trexPlus = trexPlus;
	}

	public Double getTrexPlusPrice() {
		return trexPlusPrice;
	}

	public void setTrexPlusPrice(Double trexPlusPrice) {
		this.trexPlusPrice = trexPlusPrice;
	}

	public Byte getTrexShippingMethod() {
		return trexShippingMethod;
	}

	public void setTrexShippingMethod(Byte trexShippingMethod) {
		this.trexShippingMethod = trexShippingMethod;
	}

	public Timestamp getTrexModifyTime() {
		return trexModifyTime;
	}

	public void setTrexModifyTime(Timestamp trexModifyTime) {
		this.trexModifyTime = trexModifyTime;
	}

	public Timestamp getTrexCreateTime() {
		return trexCreateTime;
	}

	public void setTrexCreateTime(Timestamp trexCreateTime) {
		this.trexCreateTime = trexCreateTime;
	}

	public Byte getTrexAllRegion() {
		return trexAllRegion;
	}

	public void setTrexAllRegion(Byte trexAllRegion) {
		this.trexAllRegion = trexAllRegion;
	}

	public List<DistributionRegion> getDistributionRegions() {
		return distributionRegions;
	}

	public void setDistributionRegions(List<DistributionRegion> distributionRegions) {
		this.distributionRegions = distributionRegions;
	}

}

