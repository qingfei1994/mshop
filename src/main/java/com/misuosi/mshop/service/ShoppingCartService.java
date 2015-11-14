/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.ShoppingCart;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 : service类 ShoppingCartService
 * <br><br>Time	 : 2015/05/06 02:39
 *
 * @author GenService
 * @version 1.0
 * @since 1.0
 */

@Service
public class ShoppingCartService {

	@Resource(name = "shoppingCartDao")
	private Dao<ShoppingCart> shoppingCartDao;

	public int addShoppingCart(ShoppingCart shoppingCart) {
		int rows = shoppingCartDao.save(shoppingCart);
		return rows;
	}

	public int updateShoppingCart(ShoppingCart shoppingCart) {
		int rows = shoppingCartDao.dynamicUpdate(shoppingCart);
		return rows;
	}

	public int deleteShoppingCart(Integer id) {
		int rows = shoppingCartDao.delete(id);
		return rows;
	}

	public int batchDeleteShoppingCarts(Integer[] ids) {
		int rows = shoppingCartDao.batchDelete(ids);
		return rows;
	}

	public ShoppingCart getShoppingCart(Integer id) {
		ShoppingCart shoppingCart = shoppingCartDao.get(id);
		return shoppingCart;
	}

	public List<ShoppingCart> getAllShoppingCarts() {
		List<ShoppingCart> list = shoppingCartDao.findAll();
		return list;
	}

	public ShoppingCart getShoppingCartByGpstIdAndWeinId(Integer gpstId, Integer weinId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM shopping_cart ");
		sql.append("WHERE gpst_id=? ");
		sql.append("AND wein_id=? ");
		ShoppingCart shoppingCart = shoppingCartDao.findUnique(sql.toString(), gpstId, weinId);
		return shoppingCart;
	}

	/**
	 * 获取用户存放在购物车中的所有商品列表
	 * @param weinId
	 * @return
	 */
	public List<ShoppingCart> getShoppingCartsByWeinId(Integer weinId) {
		List<ShoppingCart> shoppingCartList = shoppingCartDao.findByProperty("wein_id", weinId);
		return shoppingCartList;
	}

}

