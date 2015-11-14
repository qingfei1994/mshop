/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.pojo;

/**
 * Description : 三级区域用来接收前台的参数
 * <p/>
 * <br>
 * <br>
 * Time : 2015年5月29日 上午11:39:37
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
public class ThreeStageArea {

	/**
	 * 省份
	 */
	private Integer province;
	/**
	 * 城市
	 */
	private Integer city;
	/**
	 * 区或县
	 */
	private Integer county;

	/**
	 * @return the province
	 */
	public Integer getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(Integer province) {
		this.province = province;
	}

	/**
	 * @return the city
	 */
	public Integer getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(Integer city) {
		this.city = city;
	}

	/**
	 * @return the county
	 */
	public Integer getCounty() {
		return county;
	}

	/**
	 * @param county the county to set
	 */
	public void setCounty(Integer county) {
		this.county = county;
	}
	
	
}
