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
import com.misuosi.mshop.db.annotation.ManyToOne;
import com.misuosi.mshop.db.annotation.OneToMany;
import com.misuosi.mshop.db.annotation.OneToOne;

/**
 * Description	 : 实体类 DistributionRegion
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class DistributionRegion extends Entity {

	@Id
	private Integer direId;
	private Integer regiId;
	private Integer trexId;
	private Timestamp direModifyTime;
	private Timestamp direCreateTime;
	
	@ManyToOne(table="Regionalism",referenceKey="regiId")
	private Regionalism regionalism;
	public Integer getDireId() {
		return direId;
	}

	public void setDireId(Integer direId) {
		this.direId = direId;
	}

	public Integer getRegiId() {
		return regiId;
	}

	public void setRegiId(Integer regiId) {
		this.regiId = regiId;
	}

	public Integer getTrexId() {
		return trexId;
	}

	public void setTrexId(Integer trexId) {
		this.trexId = trexId;
	}

	public Timestamp getDireModifyTime() {
		return direModifyTime;
	}

	public void setDireModifyTime(Timestamp direModifyTime) {
		this.direModifyTime = direModifyTime;
	}

	public Timestamp getDireCreateTime() {
		return direCreateTime;
	}

	public void setDireCreateTime(Timestamp direCreateTime) {
		this.direCreateTime = direCreateTime;
	}

	public Regionalism getRegionalism() {
		return regionalism;
	}

	public void setRegionalism(Regionalism regionalism) {
		this.regionalism = regionalism;
	}

	

}

