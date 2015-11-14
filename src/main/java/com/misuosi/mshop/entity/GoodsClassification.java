/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.entity;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Description	 : 实体类 GoodsClassification
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class GoodsClassification extends Entity {

	@Id
	private Integer goclId;
	private String goclName;
	private Byte goclSort;
	private String goclIconUrl;
	private Timestamp goclModifyTime;
	private Timestamp goclCreateTime;
	private Integer goclParentId;

	@OneToMany(table = "GoodsClassification", foreignKey = "goclParentId")
	@Orders({@Order(key = "goclSort", order = Order.ASC),
			@Order(key = "goclId", order = Order.ASC)})
	private List<GoodsClassification> goodsClassifications;

	public Integer getGoclId() {
		return goclId;
	}

	public void setGoclId(Integer goclId) {
		this.goclId = goclId;
	}

	public String getGoclName() {
		return goclName;
	}

	public void setGoclName(String goclName) {
		this.goclName = goclName;
	}

	public Byte getGoclSort() {
		return goclSort;
	}

	public void setGoclSort(Byte goclSort) {
		this.goclSort = goclSort;
	}

	public String getGoclIconUrl() {
		return goclIconUrl;
	}

	public void setGoclIconUrl(String goclIconUrl) {
		this.goclIconUrl = goclIconUrl;
	}

	public Timestamp getGoclModifyTime() {
		return goclModifyTime;
	}

	public void setGoclModifyTime(Timestamp goclModifyTime) {
		this.goclModifyTime = goclModifyTime;
	}

	public Timestamp getGoclCreateTime() {
		return goclCreateTime;
	}

	public void setGoclCreateTime(Timestamp goclCreateTime) {
		this.goclCreateTime = goclCreateTime;
	}

	public Integer getGoclParentId() {
		return goclParentId;
	}

	public void setGoclParentId(Integer goclParentId) {
		this.goclParentId = goclParentId;
	}

	public List<GoodsClassification> getGoodsClassifications() {
		return goodsClassifications;
	}

	public void setGoodsClassifications(List<GoodsClassification> goodsClassifications) {
		this.goodsClassifications = goodsClassifications;
	}

}

