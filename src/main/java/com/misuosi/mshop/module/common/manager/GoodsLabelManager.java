/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.common.manager;

import com.misuosi.mshop.db.dao.DaoFactory;
import com.misuosi.mshop.db.dao.TreeDao;
import com.misuosi.mshop.entity.GoodsLabel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description		: 商品标签管理器
 * <p/>
 * <br><br>Time		: 2015/6/4 13:55
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class GoodsLabelManager {

    private static List<GoodsLabel> goodsLabels;
    // 以供货商ID为键缓存商品标签
    private static Map<Integer, List<GoodsLabel>> supplierGoodsLabels;

    static {
        init();
    }

    private static void init() {
        TreeDao<GoodsLabel> goodsLabelTreeDao = DaoFactory.getTreeDao(GoodsLabel.class);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM goods_label ");
        sql.append("ORDER BY gola_sort ASC, gola_id ASC");
        goodsLabels = goodsLabelTreeDao.queryForTree(sql.toString());
        supplierGoodsLabels = new HashMap<Integer, List<GoodsLabel>>();
        for (GoodsLabel goodsLabel : goodsLabels) {
        }
    }

    public static void refresh() {
        goodsLabels = null;
        supplierGoodsLabels = null;
        init();
    }

    public static List<GoodsLabel> getGoodsLabels() {
        return goodsLabels;
    }

    /**
     * 通过供货商ID获取商品标签
     *
     * @param suppId
     * @return
     */
    public static List<GoodsLabel> getGoodsLabelsBySuppId(Integer suppId) {
        return supplierGoodsLabels.get(suppId);
    }
    
}
