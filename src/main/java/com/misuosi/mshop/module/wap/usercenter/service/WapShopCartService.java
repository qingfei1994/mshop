/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.wap.usercenter.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.DaoFactory;
import com.misuosi.mshop.pojo.ShoppingCartItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description		: 购物车 Service
 * <p/>
 * <br><br>Time		: 2015/6/11 21:53
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
@Service
public class WapShopCartService {

    private Dao<ShoppingCartItem> shoppingCartItemDao = DaoFactory.getDao(ShoppingCartItem.class);

    public List<ShoppingCartItem> getShoppingCartItemsByWeinId(Integer weinId) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT shca.shca_id, shca.gpst_id, good.good_id, good.good_first_picture AS good_picture, ");
        sql.append("good.good_name, good.good_price, shca.shca_count AS count ");
        sql.append("FROM goods AS good ");
        sql.append("INNER JOIN goods_goods_specification_value AS ggsv ");
        sql.append("ON good.good_id=ggsv.good_id ");
        sql.append("INNER JOIN goods_price_stock AS gpst ");
        sql.append("ON ggsv.gpst_id=gpst.gpst_id ");
        sql.append("INNER JOIN shopping_cart AS shca ");
        sql.append("ON gpst.gpst_id=shca.gpst_id ");
        sql.append("WHERE shca.wein_id=? ");
        sql.append("ORDER BY shca.shca_id DESC");
        List<ShoppingCartItem> list = shoppingCartItemDao.find(sql.toString(), weinId);
        return list;
    }

    public ShoppingCartItem getShoppingCartItemByGpstId(Integer gpstId) {
        ShoppingCartItem shoppingCartItem = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT gpst.gpst_id, good.good_id, good.good_first_picture AS good_picture, ");
        sql.append("good.good_name, good.good_price ");
        sql.append("FROM goods AS good ");
        sql.append("INNER JOIN goods_goods_specification_value AS ggsv ");
        sql.append("ON good.good_id=ggsv.good_id ");
        sql.append("INNER JOIN goods_price_stock AS gpst ");
        sql.append("ON ggsv.gpst_id=gpst.gpst_id ");
        sql.append("WHERE gpst.gpst_id=? ");
        List<ShoppingCartItem> list = shoppingCartItemDao.find(sql.toString(), gpstId);
        if (list != null && !list.isEmpty()) {
            shoppingCartItem = list.get(0);
        }
        return shoppingCartItem;
    }

}
