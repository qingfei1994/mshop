/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.pojo;

import java.sql.Timestamp;
import java.util.List;

import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.Id;
import com.misuosi.mshop.db.annotation.OneToMany;
import com.misuosi.mshop.entity.GoodsOrder;

/**
 * Description		: 报表实体Item.
 * <p/>
 * <br><br>Time		: 2015年6月19日 下午5:28:33
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
public class ReportItem extends Entity {

	@Id
	private Integer orinId;
	private String orinNo;
	private Timestamp orinOrderTime;
	private Byte orinStatus;
	private Byte orinPayStatus;
	private String orinSource;
	private Double orinTotal;
	private Double orinFreight;
	private Byte orinPayWay;
	private Timestamp painPayTime;
	private String weinNickname;
	private String weinOpenid;
	private String orinDeliveryMethod;
	private String excoName;
	private String coadName;
	private String coadPhone;
	private String coadDetailedAddress;
	private String counRegiName;
	private String cityRegiName;
	private String provRegiName;
	private String shinRemark;
	private String shinExpressNo;
	private Byte orinMark;
	
	@OneToMany(classPath="com.misuosi.mshop.entity",table="GoodsOrder", foreignKey="orinId")
	private List<GoodsOrder> goodsOrders;

	/**
	 * @return the shinExpressNo
	 */
	public String getShinExpressNo() {
		return shinExpressNo;
	}

	/**
	 * @param shinExpressNo the shinExpressNo to set
	 */
	public void setShinExpressNo(String shinExpressNo) {
		this.shinExpressNo = shinExpressNo;
	}

	/**
	 * @return the orinId
	 */
	public Integer getOrinId() {
		return orinId;
	}

	/**
	 * @param orinId the orinId to set
	 */
	public void setOrinId(Integer orinId) {
		this.orinId = orinId;
	}

	/**
	 * @return the orinNo
	 */
	public String getOrinNo() {
		return orinNo;
	}

	/**
	 * @param orinNo the orinNo to set
	 */
	public void setOrinNo(String orinNo) {
		this.orinNo = orinNo;
	}

	/**
	 * @return the orinOrderTime
	 */
	public Timestamp getOrinOrderTime() {
		return orinOrderTime;
	}

	/**
	 * @param orinOrderTime the orinOrderTime to set
	 */
	public void setOrinOrderTime(Timestamp orinOrderTime) {
		this.orinOrderTime = orinOrderTime;
	}

	/**
	 * @return the orinStatus
	 */
	public Byte getOrinStatus() {
		return orinStatus;
	}

	/**
	 * @param orinStatus the orinStatus to set
	 */
	public void setOrinStatus(Byte orinStatus) {
		this.orinStatus = orinStatus;
	}

	/**
	 * @return the orinPayStatus
	 */
	public Byte getOrinPayStatus() {
		return orinPayStatus;
	}

	/**
	 * @param orinPayStatus the orinPayStatus to set
	 */
	public void setOrinPayStatus(Byte orinPayStatus) {
		this.orinPayStatus = orinPayStatus;
	}

	/**
	 * @return the orinSource
	 */
	public String getOrinSource() {
		return orinSource;
	}

	/**
	 * @param orinSource the orinSource to set
	 */
	public void setOrinSource(String orinSource) {
		this.orinSource = orinSource;
	}

	/**
	 * @return the orinTotal
	 */
	public Double getOrinTotal() {
		return orinTotal;
	}

	/**
	 * @param orinTotal the orinTotal to set
	 */
	public void setOrinTotal(Double orinTotal) {
		this.orinTotal = orinTotal;
	}

	/**
	 * @return the orinFreight
	 */
	public Double getOrinFreight() {
		return orinFreight;
	}

	/**
	 * @param orinFreight the orinFreight to set
	 */
	public void setOrinFreight(Double orinFreight) {
		this.orinFreight = orinFreight;
	}

	/**
	 * @return the orinPayWay
	 */
	public Byte getOrinPayWay() {
		return orinPayWay;
	}

	/**
	 * @param orinPayWay the orinPayWay to set
	 */
	public void setOrinPayWay(Byte orinPayWay) {
		this.orinPayWay = orinPayWay;
	}

	/**
	 * @return the painPayTime
	 */
	public Timestamp getPainPayTime() {
		return painPayTime;
	}

	/**
	 * @param painPayTime the painPayTime to set
	 */
	public void setPainPayTime(Timestamp painPayTime) {
		this.painPayTime = painPayTime;
	}

	/**
	 * @return the weinNickname
	 */
	public String getWeinNickname() {
		return weinNickname;
	}

	/**
	 * @param weinNickname the weinNickname to set
	 */
	public void setWeinNickname(String weinNickname) {
		this.weinNickname = weinNickname;
	}

	/**
	 * @return the weinOpenid
	 */
	public String getWeinOpenid() {
		return weinOpenid;
	}

	/**
	 * @param weinOpenid the weinOpenid to set
	 */
	public void setWeinOpenid(String weinOpenid) {
		this.weinOpenid = weinOpenid;
	}

	/**
	 * @return the orinDeliveryMethod
	 */
	public String getOrinDeliveryMethod() {
		return orinDeliveryMethod;
	}

	/**
	 * @param orinDeliveryMethod the orinDeliveryMethod to set
	 */
	public void setOrinDeliveryMethod(String orinDeliveryMethod) {
		this.orinDeliveryMethod = orinDeliveryMethod;
	}

	/**
	 * @return the excoName
	 */
	public String getExcoName() {
		return excoName;
	}

	/**
	 * @param excoName the excoName to set
	 */
	public void setExcoName(String excoName) {
		this.excoName = excoName;
	}

	/**
	 * @return the coadName
	 */
	public String getCoadName() {
		return coadName;
	}

	/**
	 * @param coadName the coadName to set
	 */
	public void setCoadName(String coadName) {
		this.coadName = coadName;
	}

	/**
	 * @return the coadPhone
	 */
	public String getCoadPhone() {
		return coadPhone;
	}

	/**
	 * @param coadPhone the coadPhone to set
	 */
	public void setCoadPhone(String coadPhone) {
		this.coadPhone = coadPhone;
	}

	/**
	 * @return the coadSetailedAddress
	 */
	public String getCoadDetailedAddress() {
		return coadDetailedAddress;
	}

	/**
	 * @param coadSetailedAddress the coadSetailedAddress to set
	 */
	public void setCoadDetailedAddress(String coadDetailedAddress) {
		this.coadDetailedAddress = coadDetailedAddress;
	}

	/**
	 * @return the counRegiName
	 */
	public String getCounRegiName() {
		return counRegiName;
	}

	/**
	 * @param counRegiName the counRegiName to set
	 */
	public void setCounRegiName(String counRegiName) {
		this.counRegiName = counRegiName;
	}

	/**
	 * @return the cityRegiName
	 */
	public String getCityRegiName() {
		return cityRegiName;
	}

	/**
	 * @param cityRegiName the cityRegiName to set
	 */
	public void setCityRegiName(String cityRegiName) {
		this.cityRegiName = cityRegiName;
	}

	/**
	 * @return the provRegiName
	 */
	public String getProvRegiName() {
		return provRegiName;
	}

	/**
	 * @param provRegiName the provRegiName to set
	 */
	public void setProvRegiName(String provRegiName) {
		this.provRegiName = provRegiName;
	}

	/**
	 * @return the shinRemark
	 */
	public String getShinRemark() {
		return shinRemark;
	}

	/**
	 * @param shinRemark the shinRemark to set
	 */
	public void setShinRemark(String shinRemark) {
		this.shinRemark = shinRemark;
	}

	/**
	 * @return the orinMark
	 */
	public Byte getOrinMark() {
		return orinMark;
	}

	/**
	 * @param orinMark the orinMark to set
	 */
	public void setOrinMark(Byte orinMark) {
		this.orinMark = orinMark;
	}

	/**
	 * @return the goodsOrders
	 */
	public List<GoodsOrder> getGoodsOrders() {
		return goodsOrders;
	}

	/**
	 * @param goodsOrders the goodsOrders to set
	 */
	public void setGoodsOrders(List<GoodsOrder> goodsOrders) {
		this.goodsOrders = goodsOrders;
	}

	
}
