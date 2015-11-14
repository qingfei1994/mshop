/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.entity;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.Id;
import com.misuosi.mshop.db.annotation.OneToMany;
import com.misuosi.mshop.db.annotation.OneToOne;

import java.sql.Timestamp;
import java.util.List;

/**
 * Description	 : 实体类 OrderInformation
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class OrderInformation extends Entity {

	@Id
	private Integer orinId;
	private Integer weinId;
	private Integer painId;
	private Double orinTotal;
	private Double orinFreight;
	private Double orinOldFreight;
	private String orinNo;
	private Byte orinPayStatus;
	private Byte orinPayWay;
	private Byte orinStatus;
	private Byte orinMark;
	private String orinMarkContent;
	private String orinDeliveryMethod;
	private String orinSource;
	private Byte orinSourceType;
	private Timestamp orinModifyTime;
	private Timestamp orinCreateTime;
	private Timestamp orinOrderTime;

	@OneToMany(table = "GoodsOrder", foreignKey = "orinId")
	private List<GoodsOrder> goodsOrders;

	@OneToOne(table = "ShipmentsInformation", foreignKey = "orinId")
	private ShipmentsInformation shipmentsInformation;

	public Integer getOrinId() {
		return orinId;
	}

	public void setOrinId(Integer orinId) {
		this.orinId = orinId;
	}

	public Integer getWeinId() {
		return weinId;
	}

	public void setWeinId(Integer weinId) {
		this.weinId = weinId;
	}

	public Integer getPainId() {
		return painId;
	}

	public void setPainId(Integer painId) {
		this.painId = painId;
	}

	public Double getOrinTotal() {
		return orinTotal;
	}

	public void setOrinTotal(Double orinTotal) {
		this.orinTotal = orinTotal;
	}

	public Double getOrinFreight() {
		return orinFreight;
	}

	public void setOrinFreight(Double orinFreight) {
		this.orinFreight = orinFreight;
	}

	public Double getOrinOldFreight() {
		return orinOldFreight;
	}

	public void setOrinOldFreight(Double orinOldFreight) {
		this.orinOldFreight = orinOldFreight;
	}

	public String getOrinNo() {
		return orinNo;
	}

	public void setOrinNo(String orinNo) {
		this.orinNo = orinNo;
	}

	public Byte getOrinPayStatus() {
		return orinPayStatus;
	}

	public void setOrinPayStatus(Byte orinPayStatus) {
		this.orinPayStatus = orinPayStatus;
	}

	public Byte getOrinPayWay() {
		return orinPayWay;
	}

	public void setOrinPayWay(Byte orinPayWay) {
		this.orinPayWay = orinPayWay;
	}

	public Byte getOrinStatus() {
		return orinStatus;
	}

	public void setOrinStatus(Byte orinStatus) {
		this.orinStatus = orinStatus;
	}

	public Byte getOrinMark() {
		return orinMark;
	}

	public void setOrinMark(Byte orinMark) {
		this.orinMark = orinMark;
	}

	public String getOrinMarkContent() {
		return orinMarkContent;
	}

	public void setOrinMarkContent(String orinMarkContent) {
		this.orinMarkContent = orinMarkContent;
	}

	public String getOrinDeliveryMethod() {
		return orinDeliveryMethod;
	}

	public void setOrinDeliveryMethod(String orinDeliveryMethod) {
		this.orinDeliveryMethod = orinDeliveryMethod;
	}

	public String getOrinSource() {
		return orinSource;
	}

	public void setOrinSource(String orinSource) {
		this.orinSource = orinSource;
	}

	public Byte getOrinSourceType() {
		return orinSourceType;
	}

	public void setOrinSourceType(Byte orinSourceType) {
		this.orinSourceType = orinSourceType;
	}

	public Timestamp getOrinModifyTime() {
		return orinModifyTime;
	}

	public void setOrinModifyTime(Timestamp orinModifyTime) {
		this.orinModifyTime = orinModifyTime;
	}

	public Timestamp getOrinCreateTime() {
		return orinCreateTime;
	}

	public void setOrinCreateTime(Timestamp orinCreateTime) {
		this.orinCreateTime = orinCreateTime;
	}

	public Timestamp getOrinOrderTime() {
		return orinOrderTime;
	}

	public void setOrinOrderTime(Timestamp orinOrderTime) {
		this.orinOrderTime = orinOrderTime;
	}

	public List<GoodsOrder> getGoodsOrders() {
		return goodsOrders;
	}

	public void setGoodsOrders(List<GoodsOrder> goodsOrders) {
		this.goodsOrders = goodsOrders;
	}

	public ShipmentsInformation getShipmentsInformation() {
		return shipmentsInformation;
	}

	public void setShipmentsInformation(ShipmentsInformation shipmentsInformation) {
		this.shipmentsInformation = shipmentsInformation;
	}

}

