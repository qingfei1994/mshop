/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.pojo;

import com.misuosi.mshop.entity.GoodsOrder;

/**
 * Description		: 
 * <p/>
 * <br><br>Time		: 2015年6月12日 下午2:16:57
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
public class OrderPrice {

	private GoodsOrder[] lstPrice;

	/**
	 * @return the lstPrice
	 */
	public GoodsOrder[] getLstPrice() {
		return lstPrice;
	}

	/**
	 * @param lstPrice the lstPrice to set
	 */
	public void setLstPrice(GoodsOrder[] lstPrice) {
		this.lstPrice = lstPrice;
	}
}
