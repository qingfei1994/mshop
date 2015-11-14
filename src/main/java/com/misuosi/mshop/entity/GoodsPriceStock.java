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
 * Description	 : 实体类 GoodsPriceStock
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class GoodsPriceStock extends Entity {

	@Id
	private Integer gpstId;
	private String gpstCode;
	private Double gpstSalesPrice;
	private Double gpstMarketPrice;
	private Integer gpstSales;
	private Integer gpstStock;
	private Double gpstWeight;
	private Double gpstVolume;
	private Timestamp gpstModifyTime;
	private Timestamp gpstCreateTime;

	public Integer getGpstId() {
		return gpstId;
	}

	public void setGpstId(Integer gpstId) {
		this.gpstId = gpstId;
	}

	public String getGpstCode() {
		return gpstCode;
	}

	public void setGpstCode(String gpstCode) {
		this.gpstCode = gpstCode;
	}

	public Double getGpstSalesPrice() {
		return gpstSalesPrice;
	}

	public void setGpstSalesPrice(Double gpstSalesPrice) {
		this.gpstSalesPrice = gpstSalesPrice;
	}

	public Double getGpstMarketPrice() {
		return gpstMarketPrice;
	}

	public void setGpstMarketPrice(Double gpstMarketPrice) {
		this.gpstMarketPrice = gpstMarketPrice;
	}

	public Integer getGpstSales() {
		return gpstSales;
	}

	public void setGpstSales(Integer gpstSales) {
		this.gpstSales = gpstSales;
	}

	public Integer getGpstStock() {
		return gpstStock;
	}

	public void setGpstStock(Integer gpstStock) {
		this.gpstStock = gpstStock;
	}

	public Double getGpstWeight() {
		return gpstWeight;
	}

	public void setGpstWeight(Double gpstWeight) {
		this.gpstWeight = gpstWeight;
	}

	public Double getGpstVolume() {
		return gpstVolume;
	}

	public void setGpstVolume(Double gpstVolume) {
		this.gpstVolume = gpstVolume;
	}

	public Timestamp getGpstModifyTime() {
		return gpstModifyTime;
	}

	public void setGpstModifyTime(Timestamp gpstModifyTime) {
		this.gpstModifyTime = gpstModifyTime;
	}

	public Timestamp getGpstCreateTime() {
		return gpstCreateTime;
	}

	public void setGpstCreateTime(Timestamp gpstCreateTime) {
		this.gpstCreateTime = gpstCreateTime;
	}

}

