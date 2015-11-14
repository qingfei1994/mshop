/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.pojo;
/**
 * Description		: 
 * <p/>
 * <br><br>Time		: 2015年6月3日 下午8:57:44
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
public class TransportationAreaExpenses {

	private String area;
	private Double trexStart;
	private Double trexStartPrice;
	private Double trexPlus;
	private Double trexPlusPrice;

	/**
	 * @return the area
	 */
	public String getArea() {
		return area;
	}
	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}
	/**
	 * @return the trexStart
	 */
	public Double getTrexStart() {
		return trexStart;
	}
	/**
	 * @param trexStart the trexStart to set
	 */
	public void setTrexStart(Double trexStart) {
		this.trexStart = trexStart;
	}
	/**
	 * @return the trexStartPrice
	 */
	public Double getTrexStartPrice() {
		return trexStartPrice;
	}
	/**
	 * @param trexStartPrice the trexStartPrice to set
	 */
	public void setTrexStartPrice(Double trexStartPrice) {
		this.trexStartPrice = trexStartPrice;
	}
	/**
	 * @return the trexPlus
	 */
	public Double getTrexPlus() {
		return trexPlus;
	}
	/**
	 * @param trexPlus the trexPlus to set
	 */
	public void setTrexPlus(Double trexPlus) {
		this.trexPlus = trexPlus;
	}
	/**
	 * @return the trexPlusPrice
	 */
	public Double getTrexPlusPrice() {
		return trexPlusPrice;
	}
	/**
	 * @param trexPlusPrice the trexPlusPrice to set
	 */
	public void setTrexPlusPrice(Double trexPlusPrice) {
		this.trexPlusPrice = trexPlusPrice;
	}
}
