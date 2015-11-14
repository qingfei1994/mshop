/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.pojo;

import java.util.List;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.Id;
import com.misuosi.mshop.db.annotation.OneToMany;
import com.misuosi.mshop.db.annotation.Transient;
import com.misuosi.mshop.entity.GoodsClassificationGoods;

/**
 * Description : 用于分销商代理商品管理的pojo对象
 * <p/>
 * <br>
 * <br>
 * Time : 2015-6-16 上午10:21:25
 * 
 * @author CHQ
 * @version 1.0
 * @since 1.0
 */
public class DistributionGoods extends Entity {

	@Id
	private Integer goodId;
	private Integer suppId;
	private String goodName;
	private String goodFirstPicture;
	private Double goodEvaluate;
	private Double goodPrice;
	private Integer goodStock;
	private Integer goodSales;
	private Integer digoId;
	@OneToMany(classPath = "com.misuosi.mshop.entity", table = "GoodsClassificationGoods", foreignKey = "goodId")
	private List<GoodsClassificationGoods> goodsClassificationGoodses;
	@Transient
	private List<String> goclNameSets;

	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public Integer getSuppId() {
		return suppId;
	}

	public void setSuppId(Integer suppId) {
		this.suppId = suppId;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
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

	public Integer getDigoId() {
		return digoId;
	}

	public void setDigoId(Integer digoId) {
		this.digoId = digoId;
	}

	public List<GoodsClassificationGoods> getGoodsClassificationGoodses() {
		return goodsClassificationGoodses;
	}

	public void setGoodsClassificationGoodses(
			List<GoodsClassificationGoods> goodsClassificationGoodses) {
		this.goodsClassificationGoodses = goodsClassificationGoodses;
	}

	public List<String> getGoclNameSets() {
		return goclNameSets;
	}

	public void setGoclNameSets(List<String> goclNameSets) {
		this.goclNameSets = goclNameSets;
	}

}
