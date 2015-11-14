/*
 * Copyright (c) 2015
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
 * Description	 : 实体类 IndexGroup
 * <br><br>Time	 : 2015/07/28 11:28
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class IndexGroup extends Entity {

	@Id
	private Integer ingrId;
	private String ingrName;
	private Byte ingrSort;
	private String ingrIconUrl;
	private Timestamp ingrModifyTime;
	private Timestamp ingrCreateTime;
	
	@OneToMany(table = "IndexGoods", foreignKey = "ingrId")
	private List<IndexGoods> indexGoodses;

	public Integer getIngrId() {
		return ingrId;
	}

	public void setIngrId(Integer ingrId) {
		this.ingrId = ingrId;
	}

	public String getIngrName() {
		return ingrName;
	}

	public void setIngrName(String ingrName) {
		this.ingrName = ingrName;
	}

	public Byte getIngrSort() {
		return ingrSort;
	}

	public void setIngrSort(Byte ingrSort) {
		this.ingrSort = ingrSort;
	}

	public String getIngrIconUrl() {
		return ingrIconUrl;
	}

	public void setIngrIconUrl(String ingrIconUrl) {
		this.ingrIconUrl = ingrIconUrl;
	}

	public Timestamp getIngrModifyTime() {
		return ingrModifyTime;
	}

	public void setIngrModifyTime(Timestamp ingrModifyTime) {
		this.ingrModifyTime = ingrModifyTime;
	}

	public Timestamp getIngrCreateTime() {
		return ingrCreateTime;
	}

	public void setIngrCreateTime(Timestamp ingrCreateTime) {
		this.ingrCreateTime = ingrCreateTime;
	}

	public List<IndexGoods> getIndexGoodses() {
		return indexGoodses;
	}

	public void setIndexGoodses(List<IndexGoods> indexGoodses) {
		this.indexGoodses = indexGoodses;
	}

}

