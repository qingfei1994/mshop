/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.GoodsLabel;
import com.misuosi.mshop.entity.OrderSetting;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 OrderSettingService
 * <br><br>Time	 : 2015/06/01 10:55
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class OrderSettingService {

	@Resource(name = "orderSettingDao")
	private Dao<OrderSetting> orderSettingDao;

	public int addOrderSetting(OrderSetting orderSetting) {
		int rows = orderSettingDao.save(orderSetting);
		return rows;
	}

	public int updateOrderSetting(OrderSetting orderSetting) {
		int rows = orderSettingDao.update(orderSetting);
		return rows;
	}

	public int deleteOrderSetting(Integer id) {
		int rows = orderSettingDao.delete(id);
		return rows;
	}

	public int batchDeleteOrderSettings(Integer[] ids) {
		int rows = orderSettingDao.batchDelete(ids);
		return rows;
	}

	public OrderSetting getOrderSetting(Integer id) {
		OrderSetting orderSetting = orderSettingDao.get(id);
		return orderSetting;
	}

	public List<OrderSetting> getAllOrderSettings() {
		List<OrderSetting> list = orderSettingDao.findAll();
		return list;
	}

	/**
	 * 根据供货商id查找订单设置
	 * @return
	 */
	public OrderSetting findOrderSettings() {
		String sql = "SELECT * FROM order_setting";
		
		OrderSetting ordersetting = null;
		try{
			ordersetting = orderSettingDao.findUnique(sql);
		}catch(EmptyResultDataAccessException e){
			ordersetting = null;
		}
		return ordersetting;
	}

}

