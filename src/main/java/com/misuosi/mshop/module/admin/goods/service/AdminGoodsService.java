/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.goods.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.*;
import com.misuosi.mshop.service.GoodsPriceStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Description		: 商品管理
 * <p/>
 * <br><br>Time		: 2015/5/30 21:24
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
@Service
public class AdminGoodsService {

    @Resource(name = "goodsDao")
    private Dao<Goods> goodsDao;
    @Resource(name = "goodsPriceStockDao")
    private Dao<GoodsPriceStock> goodsPriceStockDao;
    @Resource(name = "goodsGoodsSpecificationValueDao")
    private Dao<GoodsGoodsSpecificationValue> goodsGoodsSpecificationValueDao;
    @Resource(name = "goodsPictureDao")
    private Dao<GoodsPicture> goodsPictureDao;
    @Resource(name = "goodsClassificationGoodsDao")
    private Dao<GoodsClassificationGoods> goodsClassificationGoodsDao;
    @Resource(name = "goodsLabelGoodsDao")
    private Dao<GoodsLabelGoods> goodsLabelGoodsDao;
    @Resource(name = "goodsUnificationExpensesDao")
    private Dao<GoodsUnificationExpenses> goodsUnificationExpensesDao;

    @Autowired
    private GoodsPriceStockService goodsPriceStockService;

    public void addGoods(Goods goods, GoodsPriceStock goodsPriceStock,
            String[] images, Integer[] goclIds, Integer[] golaIds,
            GoodsUnificationExpenses goodsUnificationExpenses) {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        // 保存商品信息
        goods.setGoodSort((byte) 0); // 商品排序
        goods.setGoodFirstPicture(images[0]); // 商品图片
        goods.setGoodPrice(goodsPriceStock.getGpstSalesPrice()); // 商品价格
        goods.setGoodStock(goodsPriceStock.getGpstStock()); // 商品总库存
        goods.setGoodSales(0); // 商品总销量
        goods.setGoodModifyTime(now);
        goods.setGoodCreateTime(now);
        goodsDao.save(goods);

        Integer goodId = goods.getGoodId();

        // 保存商品价格&库存
        goodsPriceStock.setGpstSales(0);
        goodsPriceStock.setGpstModifyTime(now);
        goodsPriceStock.setGpstCreateTime(now);
        goodsPriceStockDao.save(goodsPriceStock);

        // 保存商品规格值
        GoodsGoodsSpecificationValue goodsGoodsSpecificationValue = new GoodsGoodsSpecificationValue();
        goodsGoodsSpecificationValue.setGpstId(goodsPriceStock.getGpstId());
        goodsGoodsSpecificationValue.setGoodId(goodId);
        goodsGoodsSpecificationValue.setGgsvModifyTime(now);
        goodsGoodsSpecificationValue.setGgsvCreateTime(now);
        goodsGoodsSpecificationValueDao.save(goodsGoodsSpecificationValue);

        // 保存商品图片
        List<GoodsPicture> goodsPictureList = new ArrayList<GoodsPicture>();
        for (int i = 0; i < images.length; i++) {
            String imageUrl = images[i];
            GoodsPicture goodsPicture = new GoodsPicture();
            goodsPicture.setGoodId(goodId);
            goodsPicture.setGopiUrl(imageUrl);
            goodsPicture.setGopiSort((byte) i);
            goodsPicture.setGopiModifyTime(now);
            goodsPicture.setGopiCreateTime(now);
            goodsPictureList.add(goodsPicture);
        }
        goodsPictureDao.batchSave(goodsPictureList);

        // 保存商品分类
        if (goclIds != null && goclIds.length != 0) {
            List<GoodsClassificationGoods> goodsClassificationGoodsList
                    = new ArrayList<GoodsClassificationGoods>();
            for (Integer goclId : goclIds) {
                GoodsClassificationGoods goodsClassificationGoods = new GoodsClassificationGoods();
                goodsClassificationGoods.setGoclId(goclId);
                goodsClassificationGoods.setGoodId(goodId);
                goodsClassificationGoods.setGcgoModifyTime(now);
                goodsClassificationGoods.setGcgoCreateTime(now);
                goodsClassificationGoodsList.add(goodsClassificationGoods);
            }
            goodsClassificationGoodsDao.batchSave(goodsClassificationGoodsList);
        }

        // 保存商品标签
        if (golaIds != null && golaIds.length != 0) {
            List<GoodsLabelGoods> goodsLabelGoodsList = new ArrayList<GoodsLabelGoods>();
            for (Integer golaId : golaIds) {
                GoodsLabelGoods goodsLabelGoods = new GoodsLabelGoods();
                goodsLabelGoods.setGolaId(golaId);
                goodsLabelGoods.setGoodId(goodId);
                goodsLabelGoods.setGlgoModifyTime(now);
                goodsLabelGoods.setGlgoCreateTime(now);
                goodsLabelGoodsList.add(goodsLabelGoods);
            }
            goodsLabelGoodsDao.batchSave(goodsLabelGoodsList);
        }

        // 保存商品统一运费
        if (goodsUnificationExpenses != null) {
            goodsUnificationExpenses.setGoodId(goodId);
            goodsUnificationExpenses.setGuexModifyTime(now);
            goodsUnificationExpenses.setGuexCreateTime(now);
            goodsUnificationExpensesDao.save(goodsUnificationExpenses);
        }
    }

    public void updateGoods(Goods goods, GoodsPriceStock goodsPriceStock,
                         String[] images, Integer[] goclIds, Integer[] golaIds,
                         GoodsUnificationExpenses goodsUnificationExpenses) {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        // 更新商品信息
        Goods oldGoods = goodsDao.get(goods.getGoodId());
        goods.setGoodSort(oldGoods.getGoodSort()); // 商品排序
        goods.setGoodFirstPicture(images[0]); // 商品图片
        goods.setGoodPrice(goodsPriceStock.getGpstSalesPrice()); // 商品价格
        goods.setGoodStock(goodsPriceStock.getGpstStock()); // 商品总库存
        goods.setGoodSales(oldGoods.getGoodSales()); // 商品总销量
        goods.setGoodModifyTime(now);
        goodsDao.update(goods);

        Integer goodId = goods.getGoodId();
        // 通过商品ID删除与商品相关的信息
        // 因为逻辑过于复杂，所以采取先删除，再添加的方式修改商品相关信息
        batchDeleteGoodsAppendant(new Integer[]{goodId}, false);

        // 更新商品价格&库存
        goodsPriceStock.setGpstModifyTime(now);
        goodsPriceStockDao.update(goodsPriceStock);

        // 保存商品规格值
        GoodsGoodsSpecificationValue goodsGoodsSpecificationValue = new GoodsGoodsSpecificationValue();
        goodsGoodsSpecificationValue.setGpstId(goodsPriceStock.getGpstId());
        goodsGoodsSpecificationValue.setGoodId(goodId);
        goodsGoodsSpecificationValue.setGgsvModifyTime(now);
        goodsGoodsSpecificationValue.setGgsvCreateTime(now);
        goodsGoodsSpecificationValueDao.save(goodsGoodsSpecificationValue);

        // 保存商品图片
        List<GoodsPicture> goodsPictureList = new ArrayList<GoodsPicture>();
        for (int i = 0; i < images.length; i++) {
            String imageUrl = images[i];
            GoodsPicture goodsPicture = new GoodsPicture();
            goodsPicture.setGoodId(goodId);
            goodsPicture.setGopiUrl(imageUrl);
            goodsPicture.setGopiSort((byte) i);
            goodsPicture.setGopiModifyTime(now);
            goodsPicture.setGopiCreateTime(now);
            goodsPictureList.add(goodsPicture);
        }
        goodsPictureDao.batchSave(goodsPictureList);

        // 保存商品分类
        if (goclIds != null && goclIds.length != 0) {
            List<GoodsClassificationGoods> goodsClassificationGoodsList
                    = new ArrayList<GoodsClassificationGoods>();
            for (Integer goclId : goclIds) {
                GoodsClassificationGoods goodsClassificationGoods = new GoodsClassificationGoods();
                goodsClassificationGoods.setGoclId(goclId);
                goodsClassificationGoods.setGoodId(goodId);
                goodsClassificationGoods.setGcgoModifyTime(now);
                goodsClassificationGoods.setGcgoCreateTime(now);
                goodsClassificationGoodsList.add(goodsClassificationGoods);
            }
            goodsClassificationGoodsDao.batchSave(goodsClassificationGoodsList);
        }

        // 保存商品标签
        if (golaIds != null && golaIds.length != 0) {
            List<GoodsLabelGoods> goodsLabelGoodsList = new ArrayList<GoodsLabelGoods>();
            for (Integer golaId : golaIds) {
                GoodsLabelGoods goodsLabelGoods = new GoodsLabelGoods();
                goodsLabelGoods.setGolaId(golaId);
                goodsLabelGoods.setGoodId(goodId);
                goodsLabelGoods.setGlgoModifyTime(now);
                goodsLabelGoods.setGlgoCreateTime(now);
                goodsLabelGoodsList.add(goodsLabelGoods);
            }
            goodsLabelGoodsDao.batchSave(goodsLabelGoodsList);
        }

        // 保存商品统一运费
        if (goodsUnificationExpenses != null) {
            goodsUnificationExpenses.setGoodId(goodId);
            goodsUnificationExpenses.setGuexModifyTime(now);
            goodsUnificationExpenses.setGuexCreateTime(now);
            goodsUnificationExpensesDao.save(goodsUnificationExpenses);
        }
    }

    public void deleteGoods(Integer goodId) {
        batchDeleteGoodsAppendant(new Integer[] { goodId }, true);
        goodsDao.delete(goodId);
    }

    public void batchDeleteGoods(Integer[] ids) {
        batchDeleteGoodsAppendant(ids, true);
        goodsDao.batchDelete(ids);
    }

    /**
     * 通过商品ID批量删除与商品相关的信息
     *
     * @param goodIds
     * @return
     */
    private void batchDeleteGoodsAppendant(Integer[] goodIds, boolean deleteStockAndPrice) {
        StringBuilder strBuf = new StringBuilder();
        for (Integer goodId : goodIds) {
            strBuf.append(goodId).append(",");
        }
        String str = strBuf.deleteCharAt(strBuf.length() - 1).toString();

        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM goods_goods_specification_value WHERE good_id in(").append(str).append(");");
        sql.append("DELETE FROM goods_picture WHERE good_id in(").append(str).append(");");
        sql.append("DELETE FROM goods_classification_goods WHERE good_id in(").append(str).append(");");
        sql.append("DELETE FROM goods_label_goods WHERE good_id in(").append(str).append(");");
        sql.append("DELETE FROM goods_unification_expenses WHERE good_id in(").append(str).append(");");
        goodsDao.batchDelete(sql.toString());

        if (deleteStockAndPrice) {
            List<Integer> gpstIds = goodsPriceStockService.getGpstIdsByGoodIds(goodIds);
            if (gpstIds != null && !gpstIds.isEmpty()) {
                goodsPriceStockService.batchDeleteGoodsPriceStocks((Integer[]) gpstIds.toArray());
            }
        }
    }

}
