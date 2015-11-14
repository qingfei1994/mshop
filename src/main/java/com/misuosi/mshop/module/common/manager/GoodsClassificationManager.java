/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.common.manager;

import com.misuosi.mshop.db.dao.DaoFactory;
import com.misuosi.mshop.db.dao.TreeDao;
import com.misuosi.mshop.entity.GoodsClassification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description		: 商品分类管理器
 * <p/>
 * <br><br>Time		: 2015/6/2 10:51
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class GoodsClassificationManager {

    private static final String SEPARATOR = " > ";

    private static List<GoodsClassification> goodsClassifications;
    // 以供货商ID为键缓存商品分类
    private static Map<Integer, List<GoodsClassification>> supplierGoodsClassifications;
    // 以商品子分类ID为键缓存商品分类名字组合
    private static Map<Integer, String> goodsClassificationNameSets;

    static {
        init();
    }

    private static void init() {
        TreeDao<GoodsClassification> goodsClassificationTreeDao =
                DaoFactory.getTreeDao(GoodsClassification.class);
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT * FROM goods_classification ");
        sql.append("WHERE gocl_parent_id IS NULL ");
        sql.append("ORDER BY gocl_sort ASC, gocl_id ASC");
        goodsClassifications = goodsClassificationTreeDao.queryForTree(sql.toString());
        supplierGoodsClassifications = new HashMap<Integer, List<GoodsClassification>>();
        goodsClassificationNameSets = new HashMap<Integer, String>();
        for (GoodsClassification goodsClassification : goodsClassifications) {
            initGoodsClassificationNameSet(goodsClassification, null);
        }
    }

    private static void initGoodsClassificationNameSet(GoodsClassification goodsClassification, 
                String parentNameSet) {
        String nameSet = goodsClassification.getGoclName();
        if (parentNameSet != null) {
            nameSet = parentNameSet.concat(SEPARATOR).concat(nameSet);
        } 
        List<GoodsClassification> childGoodsClassifications = goodsClassification.getGoodsClassifications();
        if (childGoodsClassifications == null) {
            goodsClassificationNameSets.put(goodsClassification.getGoclId(), nameSet);
            return;
        } else {
            for (GoodsClassification childGoodsClassification : childGoodsClassifications) {
                initGoodsClassificationNameSet(childGoodsClassification, nameSet);
            }
        }
    }

    public static void refresh() {
        goodsClassifications = null;
        supplierGoodsClassifications = null;
        goodsClassificationNameSets = null;
        init();
    }

    public static List<GoodsClassification> getGoodsClassifications() {
        return goodsClassifications;
    }

    /**
     * 通过供货商ID获取商品分类
     *
     * @param suppId
     * @return
     */
    public static List<GoodsClassification> getGoodsClassificationsBySuppId(Integer suppId) {
        return supplierGoodsClassifications.get(suppId);
    }
    
    public static String getGoodsClassificationNameSet(Integer goclId) {
        return goodsClassificationNameSets.get(goclId);
    }

}
