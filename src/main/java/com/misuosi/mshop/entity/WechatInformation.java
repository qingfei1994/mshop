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
 * Description	 : 实体类 WechatInformation
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class WechatInformation extends Entity {

	@Id
	private Integer weinId;
	private String weinCountry;
	private String weinProvince;
	private String weinCity;
	private String weinHeadingimgurl;
	private String weinLanguage;
	private Byte weinSex;
	private String weinUnionid;
	private String weinOpenid;
	private String weinNickname;
	private Integer weinGrade;
	private Integer weinRecommendAmount;
	private Byte weinAttentionType;
	private Byte weinRelationType;
	private Timestamp weinModifyTime;
	private Timestamp weinCreateTime;
	private Integer weinParentId;

	public Integer getWeinId() {
		return weinId;
	}

	public void setWeinId(Integer weinId) {
		this.weinId = weinId;
	}

	public String getWeinCountry() {
		return weinCountry;
	}

	public void setWeinCountry(String weinCountry) {
		this.weinCountry = weinCountry;
	}

	public String getWeinProvince() {
		return weinProvince;
	}

	public void setWeinProvince(String weinProvince) {
		this.weinProvince = weinProvince;
	}

	public String getWeinCity() {
		return weinCity;
	}

	public void setWeinCity(String weinCity) {
		this.weinCity = weinCity;
	}

	public String getWeinHeadingimgurl() {
		return weinHeadingimgurl;
	}

	public void setWeinHeadingimgurl(String weinHeadingimgurl) {
		this.weinHeadingimgurl = weinHeadingimgurl;
	}

	public String getWeinLanguage() {
		return weinLanguage;
	}

	public void setWeinLanguage(String weinLanguage) {
		this.weinLanguage = weinLanguage;
	}

	public Byte getWeinSex() {
		return weinSex;
	}

	public void setWeinSex(Byte weinSex) {
		this.weinSex = weinSex;
	}

	public String getWeinUnionid() {
		return weinUnionid;
	}

	public void setWeinUnionid(String weinUnionid) {
		this.weinUnionid = weinUnionid;
	}

	public String getWeinOpenid() {
		return weinOpenid;
	}

	public void setWeinOpenid(String weinOpenid) {
		this.weinOpenid = weinOpenid;
	}

	public String getWeinNickname() {
		return weinNickname;
	}

	public void setWeinNickname(String weinNickname) {
		this.weinNickname = weinNickname;
	}

	public Integer getWeinGrade() {
		return weinGrade;
	}

	public void setWeinGrade(Integer weinGrade) {
		this.weinGrade = weinGrade;
	}

	public Integer getWeinRecommendAmount() {
		return weinRecommendAmount;
	}

	public void setWeinRecommendAmount(Integer weinRecommendAmount) {
		this.weinRecommendAmount = weinRecommendAmount;
	}

	public Byte getWeinAttentionType() {
		return weinAttentionType;
	}

	public void setWeinAttentionType(Byte weinAttentionType) {
		this.weinAttentionType = weinAttentionType;
	}

	public Byte getWeinRelationType() {
		return weinRelationType;
	}

	public void setWeinRelationType(Byte weinRelationType) {
		this.weinRelationType = weinRelationType;
	}

	public Timestamp getWeinModifyTime() {
		return weinModifyTime;
	}

	public void setWeinModifyTime(Timestamp weinModifyTime) {
		this.weinModifyTime = weinModifyTime;
	}

	public Timestamp getWeinCreateTime() {
		return weinCreateTime;
	}

	public void setWeinCreateTime(Timestamp weinCreateTime) {
		this.weinCreateTime = weinCreateTime;
	}

	public Integer getWeinParentId() {
		return weinParentId;
	}

	public void setWeinParentId(Integer weinParentId) {
		this.weinParentId = weinParentId;
	}

}

