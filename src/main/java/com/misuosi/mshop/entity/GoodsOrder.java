/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.entity;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.Id;
import com.misuosi.mshop.db.annotation.OneToOne;

import java.sql.Timestamp;

/**
 * Description	 : 实体类 GoodsOrder
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class GoodsOrder extends Entity {

	@Id
	private Integer goorId;
	private Integer gpstId;
	private Integer orinId;
	private Integer goodId;
	private String goorName;
	private String goorCode;
	private Double goorPrice;
	private String goorFirstPicture;
	private Integer goorCount;
	private String goorSpecialOffers;
	private Double goorFavourablePrice;
	private Double goorRealityPay;
	private Timestamp goorModifyTime;
	private Timestamp goorCreateTime;
	private Timestamp goorRefundCloseTime;
	private Byte goorRefundStatus;

	@OneToOne(table = "GoodsComment", foreignKey = "goorId")
	private GoodsComment goodsComment;

	@OneToOne(table = "ClientAppeal", foreignKey = "goorId")
	private ClientAppeal clientAppeal;

	public Integer getGoorId() {
		return goorId;
	}

	public void setGoorId(Integer goorId) {
		this.goorId = goorId;
	}

	public Integer getGpstId() {
		return gpstId;
	}

	public void setGpstId(Integer gpstId) {
		this.gpstId = gpstId;
	}

	public Integer getOrinId() {
		return orinId;
	}

	public void setOrinId(Integer orinId) {
		this.orinId = orinId;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public String getGoorName() {
		return goorName;
	}

	public void setGoorName(String goorName) {
		this.goorName = goorName;
	}

	public String getGoorCode() {
		return goorCode;
	}

	public void setGoorCode(String goorCode) {
		this.goorCode = goorCode;
	}

	public Double getGoorPrice() {
		return goorPrice;
	}

	public void setGoorPrice(Double goorPrice) {
		this.goorPrice = goorPrice;
	}

	public String getGoorFirstPicture() {
		return goorFirstPicture;
	}

	public void setGoorFirstPicture(String goorFirstPicture) {
		this.goorFirstPicture = goorFirstPicture;
	}

	public Integer getGoorCount() {
		return goorCount;
	}

	public void setGoorCount(Integer goorCount) {
		this.goorCount = goorCount;
	}

	public String getGoorSpecialOffers() {
		return goorSpecialOffers;
	}

	public void setGoorSpecialOffers(String goorSpecialOffers) {
		this.goorSpecialOffers = goorSpecialOffers;
	}

	public Double getGoorFavourablePrice() {
		return goorFavourablePrice;
	}

	public void setGoorFavourablePrice(Double goorFavourablePrice) {
		this.goorFavourablePrice = goorFavourablePrice;
	}

	public Double getGoorRealityPay() {
		return goorRealityPay;
	}

	public void setGoorRealityPay(Double goorRealityPay) {
		this.goorRealityPay = goorRealityPay;
	}

	public Timestamp getGoorModifyTime() {
		return goorModifyTime;
	}

	public void setGoorModifyTime(Timestamp goorModifyTime) {
		this.goorModifyTime = goorModifyTime;
	}

	public Timestamp getGoorCreateTime() {
		return goorCreateTime;
	}

	public void setGoorCreateTime(Timestamp goorCreateTime) {
		this.goorCreateTime = goorCreateTime;
	}

	public Timestamp getGoorRefundCloseTime() {
		return goorRefundCloseTime;
	}

	public void setGoorRefundCloseTime(Timestamp goorRefundCloseTime) {
		this.goorRefundCloseTime = goorRefundCloseTime;
	}

	public Byte getGoorRefundStatus() {
		return goorRefundStatus;
	}

	public void setGoorRefundStatus(Byte goorRefundStatus) {
		this.goorRefundStatus = goorRefundStatus;
	}

	public GoodsComment getGoodsComment() {
		return goodsComment;
	}

	public void setGoodsComment(GoodsComment goodsComment) {
		this.goodsComment = goodsComment;
	}

	public ClientAppeal getClientAppeal() {
		return clientAppeal;
	}

	public void setClientAppeal(ClientAppeal clientAppeal) {
		this.clientAppeal = clientAppeal;
	}

}

