/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.pojo;

import java.sql.Timestamp;

import com.misuosi.mshop.db.Entity;

/**
 * Description :
 * <p/>
 * <br>
 * <br>
 * Time : 2015年6月18日 下午8:44:04
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
public class StatementItem extends Entity {

	/** 订单编号 */
	private String orinNo;

	/** 订单状态 */
	private Byte orinStatus;

	/** 下单时间 */
	private Timestamp orinOrderTime;

	/** 支付单号 */
	private String painPayNo;

	/** 客户订单号 */
	private String painSerialNumber;

	/** 支付金额 */
	private Double painPayMoney;

	/** 支付方式 */
	private Byte painPayWay;

	/** 支付时间 */
	private Timestamp painPayTime;

	/**
	 * @return the orinNo
	 */
	public String getOrinNo() {
		return orinNo;
	}

	/**
	 * @param orinNo
	 *            the orinNo to set
	 */
	public void setOrinNo(String orinNo) {
		this.orinNo = orinNo;
	}

	/**
	 * @return the orinStatus
	 */
	public Byte getOrinStatus() {
		return orinStatus;
	}

	/**
	 * @param orinStatus
	 *            the orinStatus to set
	 */
	public void setOrinStatus(Byte orinStatus) {
//		if (orinStatus == 0) {
//			this.orinStatus = "订单关闭";
//		} else if (orinStatus == 1) {
//			this.orinStatus = "交易中";
//		} else if (orinStatus == 2) {
//			this.orinStatus = "订单完成";
//		}
		this.orinStatus = orinStatus;
	}

	/**
	 * @return the orinOrderTime
	 */
	public Timestamp getOrinOrderTime() {
		return orinOrderTime;
	}

	/**
	 * @param orinOrderTime
	 *            the orinOrderTime to set
	 */
	public void setOrinOrderTime(Timestamp orinOrderTime) {
		this.orinOrderTime = orinOrderTime;
	}

	/**
	 * @return the painPayNo
	 */
	public String getPainPayNo() {
		return painPayNo;
	}

	/**
	 * @param painPayNo
	 *            the painPayNo to set
	 */
	public void setPainPayNo(String painPayNo) {
		this.painPayNo = painPayNo;
	}

	/**
	 * @return the painSerialNumber
	 */
	public String getPainSerialNumber() {
		return painSerialNumber;
	}

	/**
	 * @param painSerialNumber
	 *            the painSerialNumber to set
	 */
	public void setPainSerialNumber(String painSerialNumber) {
		this.painSerialNumber = painSerialNumber;
	}

	/**
	 * @return the painPayMoney
	 */
	public Double getPainPayMoney() {
		return painPayMoney;
	}

	/**
	 * @param painPayMoney
	 *            the painPayMoney to set
	 */
	public void setPainPayMoney(Double painPayMoney) {
		this.painPayMoney = painPayMoney;
	}

	/**
	 * @return the painPayWay
	 */
	public Byte getPainPayWay() {
		return painPayWay;
	}

	/**
	 * @param painPayWay
	 *            the painPayWay to set
	 */
	public void setPainPayWay(Byte painPayWay) {
//		if (painPayWay == 0) {
//			this.painPayWay = "微信支付";
//		} else if (painPayWay == 1) {
//			this.painPayWay = "支付宝";
//		} else if (painPayWay == 2) {
//			this.painPayWay = "财付通";
//		} else if (painPayWay == 3) {
//			this.painPayWay = "银行卡支付";
//		} else if (painPayWay == 4) {
//			this.painPayWay = "货到付款";
//		} else if (painPayWay == 5) {
//			this.painPayWay = "会员余额";
//		} else {
//			this.painPayWay = "支付方式未知";
//		}
		this.painPayWay = painPayWay;
	}

	/**
	 * @return the painPayTime
	 */
	public Timestamp getPainPayTime() {
		return painPayTime;
	}

	/**
	 * @param painPayTime
	 *            the painPayTime to set
	 */
	public void setPainPayTime(Timestamp painPayTime) {
		this.painPayTime = painPayTime;
	}

}
