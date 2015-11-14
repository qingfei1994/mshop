/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.entity;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.Id;
import com.misuosi.mshop.db.annotation.OneToMany;
import com.misuosi.mshop.db.annotation.Transient;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Description	 : 实体类 Goods
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class Goods extends Entity {

	@Id
	private Integer goodId;
	private Integer teteId;
	private String goodName;
	private String goodDetails;
	private Byte goodPurchaseLimit;
	private Integer goodPurchaseLimitAmount;
	private Byte goodMemberPrivilege;
	private Byte goodStockCalculateType;
	private Byte goodPutawayType;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date goodPutawayTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private Date goodEndPutawayTime;
	private Byte goodAssumeExpenses;
	private Byte goodSort;
	private String goodFirstPicture;
	private Double goodEvaluate;
	private Double goodPrice;
	private Integer goodStock;
	private Integer goodSales;
	private Timestamp goodModifyTime;
	private Timestamp goodCreateTime;

	@OneToMany(table = "GoodsClassificationGoods", foreignKey = "goodId")
	private List<GoodsClassificationGoods> goodsClassificationGoodses;

	@Transient
	private List<String> goclNameSets;

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Integer getTeteId() {
		return teteId;
	}

	public void setTeteId(Integer teteId) {
		this.teteId = teteId;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getGoodDetails() {
		return goodDetails;
	}

	public void setGoodDetails(String goodDetails) {
		this.goodDetails = goodDetails;
	}

	public Byte getGoodPurchaseLimit() {
		return goodPurchaseLimit;
	}

	public void setGoodPurchaseLimit(Byte goodPurchaseLimit) {
		this.goodPurchaseLimit = goodPurchaseLimit;
	}

	public Integer getGoodPurchaseLimitAmount() {
		return goodPurchaseLimitAmount;
	}

	public void setGoodPurchaseLimitAmount(Integer goodPurchaseLimitAmount) {
		this.goodPurchaseLimitAmount = goodPurchaseLimitAmount;
	}

	public Byte getGoodMemberPrivilege() {
		return goodMemberPrivilege;
	}

	public void setGoodMemberPrivilege(Byte goodMemberPrivilege) {
		this.goodMemberPrivilege = goodMemberPrivilege;
	}

	public Byte getGoodStockCalculateType() {
		return goodStockCalculateType;
	}

	public void setGoodStockCalculateType(Byte goodStockCalculateType) {
		this.goodStockCalculateType = goodStockCalculateType;
	}

	public Byte getGoodPutawayType() {
		return goodPutawayType;
	}

	public void setGoodPutawayType(Byte goodPutawayType) {
		this.goodPutawayType = goodPutawayType;
	}

	public Date getGoodPutawayTime() {
		return goodPutawayTime;
	}

	public void setGoodPutawayTime(Date goodPutawayTime) {
		this.goodPutawayTime = goodPutawayTime;
	}

	public Date getGoodEndPutawayTime() {
		return goodEndPutawayTime;
	}

	public void setGoodEndPutawayTime(Date goodEndPutawayTime) {
		this.goodEndPutawayTime = goodEndPutawayTime;
	}

	public Byte getGoodAssumeExpenses() {
		return goodAssumeExpenses;
	}

	public void setGoodAssumeExpenses(Byte goodAssumeExpenses) {
		this.goodAssumeExpenses = goodAssumeExpenses;
	}

	public Byte getGoodSort() {
		return goodSort;
	}

	public void setGoodSort(Byte goodSort) {
		this.goodSort = goodSort;
	}

	public String getGoodFirstPicture() {
		return goodFirstPicture;
	}

	public void setGoodFirstPicture(String goodFirstPicture) {
		this.goodFirstPicture = goodFirstPicture;
	}

	public Double getGoodEvaluate() {
		return goodEvaluate;
	}

	public void setGoodEvaluate(Double goodEvaluate) {
		this.goodEvaluate = goodEvaluate;
	}

	public Double getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(Double goodPrice) {
		this.goodPrice = goodPrice;
	}

	public Integer getGoodStock() {
		return goodStock;
	}

	public void setGoodStock(Integer goodStock) {
		this.goodStock = goodStock;
	}

	public Integer getGoodSales() {
		return goodSales;
	}

	public void setGoodSales(Integer goodSales) {
		this.goodSales = goodSales;
	}

	public Timestamp getGoodModifyTime() {
		return goodModifyTime;
	}

	public void setGoodModifyTime(Timestamp goodModifyTime) {
		this.goodModifyTime = goodModifyTime;
	}

	public Timestamp getGoodCreateTime() {
		return goodCreateTime;
	}

	public void setGoodCreateTime(Timestamp goodCreateTime) {
		this.goodCreateTime = goodCreateTime;
	}

	public List<GoodsClassificationGoods> getGoodsClassificationGoodses() {
		return goodsClassificationGoodses;
	}

	public void setGoodsClassificationGoodses(List<GoodsClassificationGoods> goodsClassificationGoodses) {
		this.goodsClassificationGoodses = goodsClassificationGoodses;
	}

	public List<String> getGoclNameSets() {
		return goclNameSets;
	}

	public void setGoclNameSets(List<String> goclNameSets) {
		this.goclNameSets = goclNameSets;
	}

}

