/*
 * Copyright (c) 2015
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.entity;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.Id;
import com.misuosi.mshop.db.annotation.ManyToOne;

import java.sql.Timestamp;

/**
 * Description	 : 实体类 IndexGoods
 * <br><br>Time	 : 2015/07/28 11:28
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class IndexGoods extends Entity {

	@Id
	private Integer ingoId;
	private Integer goodId;
	private Integer ingrId;
	private Byte ingoSort;
	private Timestamp ingoModifyTime;
	private Timestamp ingoCreateTime;
	
	@ManyToOne(table = "Goods", referenceKey = "goodId")
	private Goods goods;

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Integer getIngoId() {
		return ingoId;
	}

	public void setIngoId(Integer ingoId) {
		this.ingoId = ingoId;
	}

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Integer getIngrId() {
		return ingrId;
	}

	public void setIngrId(Integer ingrId) {
		this.ingrId = ingrId;
	}

	public Byte getIngoSort() {
		return ingoSort;
	}

	public void setIngoSort(Byte ingoSort) {
		this.ingoSort = ingoSort;
	}

	public Timestamp getIngoModifyTime() {
		return ingoModifyTime;
	}

	public void setIngoModifyTime(Timestamp ingoModifyTime) {
		this.ingoModifyTime = ingoModifyTime;
	}

	public Timestamp getIngoCreateTime() {
		return ingoCreateTime;
	}

	public void setIngoCreateTime(Timestamp ingoCreateTime) {
		this.ingoCreateTime = ingoCreateTime;
	}

}

