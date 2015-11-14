/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.common.manager;

import com.misuosi.mshop.common.constants.GoodsConstants;
import com.misuosi.mshop.common.constants.TransportationExpensesConstants;
import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.DaoFactory;
import com.misuosi.mshop.entity.Goods;
import com.misuosi.mshop.entity.ShoppingCart;
import com.misuosi.mshop.entity.TransportationExpenses;
import com.misuosi.mshop.module.common.manager.TransportationExpensesTemplateManager;
import com.misuosi.mshop.pojo.*;
import com.misuosi.mshop.util.NumberUtils;
import com.misuosi.mshop.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description		: 运费管理器
 * <p/>
 * <br><br>Time		: 2015/6/15 21:41
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class TransportationExpensesManager {

    /**
     * 统计单个商品的运费
     * @param goodId 商品Id
     * @param gpstId 商品库存规格Id
     * @param count 商品数量
     * @param regiId 地址Id
     * @return
     */
    public static TransportationExpensesResult calculateTransportationExpenses(
            Integer goodId, Integer gpstId, Integer count, Integer regiId) {
        ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
        shoppingCartItem.setGoodId(goodId);
        shoppingCartItem.setGpstId(gpstId);
        shoppingCartItem.setCount(count);
        List<ShoppingCartItem> shoppingCartItems = new ArrayList<ShoppingCartItem>();
        shoppingCartItems.add(shoppingCartItem);
        return calculateTransportationExpenses(shoppingCartItems, regiId);
    }

    /**
     * 统计运费
     *
     * @param shoppingCartItems
     * @return
     */
    public static TransportationExpensesResult calculateTransportationExpenses(
            List<ShoppingCartItem> shoppingCartItems, Integer regiId) {
        Dao<GoodsItem> goodsItemDao = DaoFactory.getDao(GoodsItem.class);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT good.tete_id, gpst.gpst_id, gpst.gpst_weight, gpst.gpst_volume, ");
        sql.append("good.good_assume_expenses, guex.guex_express_price, guex.guex_ems_price, guex.guex_post_price ");
        sql.append("FROM goods_price_stock gpst ");
        sql.append("INNER JOIN goods_goods_specification_value ggsv  ");
        sql.append("ON gpst.gpst_id=ggsv.gpst_id  ");
        sql.append("INNER JOIN goods good  ");
        sql.append("ON ggsv.good_id=good.good_id  ");
        sql.append("INNER JOIN goods_unification_expenses guex  ");
        sql.append("ON good.good_id=guex.good_id  ");
        sql.append("WHERE good.good_id in (");
        for (int i = 0; i < shoppingCartItems.size(); i++) {
            sql.append(shoppingCartItems.get(i).getGoodId());
            if (i != shoppingCartItems.size() - 1) {
                sql.append(",");
            }
        }
        sql.append(")");
        List<GoodsItem> goodsItems = goodsItemDao.find(sql.toString());

        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        for (ShoppingCartItem shoppingCartItem :shoppingCartItems) {
            countMap.put(shoppingCartItem.getGpstId(), shoppingCartItem.getCount());
        }

        for (GoodsItem goodsItem : goodsItems) {
            Integer gpstId = goodsItem.getGpstId();
            goodsItem.setCount(countMap.get(gpstId));
        }

        // 以运费模板为键，将商品分组
        Map<TransportationExpensesTemplateItem, List<GoodsItem>> goodsItemGroup = group(goodsItems, regiId);

        TransportationExpensesResult result = calculate(goodsItemGroup);

        return result;
    }

    /**
     * 按运费计算方式将商品分组
     *
     * @param goodsItems
     * @return
     */
    private static Map<TransportationExpensesTemplateItem, List<GoodsItem>> group(
            List<GoodsItem> goodsItems, Integer regiId) {
        Map<TransportationExpensesTemplateItem, List<GoodsItem>> map =
                new HashMap<TransportationExpensesTemplateItem, List<GoodsItem>>();

        // 以模板ID为键，存储运费模板
        Map<Integer, TransportationExpensesTemplateItem> templateItemMap =
                new HashMap<Integer, TransportationExpensesTemplateItem>();

        for (GoodsItem goodsItem : goodsItems) {
            byte goodAssumeExpenses = goodsItem.getGoodAssumeExpenses();
            if (goodAssumeExpenses == GoodsConstants.GOOD_ASSUME_EXPENSES_NO) {
                continue; // 卖家承担运费，该商品不参与运费计算
            }

            TransportationExpensesTemplateItem templateItem;
            Integer teteId = goodsItem.getTeteId();
            if (teteId == null) { // 使用自定义运费
                TransportationExpensesItem expressItem = new TransportationExpensesItem(); // 快递运费
                expressItem.setStartPrice(goodsItem.getGuexExpressPrice());
                TransportationExpensesItem emsItem = new TransportationExpensesItem(); // EMS运费
                emsItem.setStartPrice(goodsItem.getGuexEmsPrice());
                TransportationExpensesItem postItem = new TransportationExpensesItem(); // 平邮运费
                postItem.setStartPrice(goodsItem.getGuexPostPrice());

                templateItem = new TransportationExpensesTemplateItem();
                templateItem.setExpressItem(expressItem);
                templateItem.setEmsItem(emsItem);
                templateItem.setPostItem(postItem);
            } else { // 使用运费模板计算运费
                templateItem = templateItemMap.get(teteId);
                if (templateItem == null) {
                    templateItem = TransportationExpensesTemplateManager
                            .getTransportationExpensesTemplateItem(teteId, regiId);
                }
            }

            List<GoodsItem> goodsItemList = map.get(templateItem);
            if (goodsItemList == null) {
                goodsItemList = new ArrayList<GoodsItem>();
                map.put(templateItem, goodsItemList);
            }
            goodsItemList.add(goodsItem);
        }
        return map;
    }

    /**
     * 计算混合运费（每项最高运费之和）、快递运费、EMS运费、平邮运费
     *
     * @param goodsItemGroup
     * @return
     */
    private static TransportationExpensesResult calculate(
            Map<TransportationExpensesTemplateItem, List<GoodsItem>> goodsItemGroup) {
        TransportationExpensesResult expensesResult = new TransportationExpensesResult();

        Double totalExpressPrice = null;
        Double totalEmsPrice = null;
        Double totalPostPrice = null;
        Double totalMixPrice = 0D;
        for (TransportationExpensesTemplateItem templateItem : goodsItemGroup.keySet()) {
            List<GoodsItem> goodsItems = goodsItemGroup.get(templateItem);
            Double expressPrice = caculateItem(templateItem.getExpressItem(), goodsItems);
            Double emsPrice = caculateItem(templateItem.getEmsItem(), goodsItems);
            Double postPrice = caculateItem(templateItem.getPostItem(), goodsItems);
            if (totalExpressPrice != null && expressPrice != null) {
                totalExpressPrice += expressPrice;
            } else {
                totalExpressPrice = expressPrice;
            }
            if (totalEmsPrice != null && emsPrice != null) {
                totalEmsPrice += emsPrice;
            } else {
                totalEmsPrice = emsPrice;
            }
            if (totalPostPrice != null && postPrice != null) {
                totalPostPrice += postPrice;
            } else {
                totalPostPrice = postPrice;
            }

            double maxPrice = NumberUtils.getMaxNumber(new double[] {
                    StringUtils.doubleValue(expressPrice, 0),
                    StringUtils.doubleValue(emsPrice, 0),
                    StringUtils.doubleValue(postPrice, 0)
            });
            totalMixPrice += maxPrice;
        }

        expensesResult.setTotalExpressExpenses(totalExpressPrice);
        expensesResult.setTotalEmsExpenses(totalEmsPrice);
        expensesResult.setTotalPostExpenses(totalPostPrice);

        if (totalExpressPrice != null || totalEmsPrice != null || totalPostPrice != null) {
            totalMixPrice = null;
        }
        expensesResult.setTotalMixExpenses(totalMixPrice);

        return expensesResult;
    }

    /**
     * 按照运费计算方式（按数量、按重量、按体积、累加）计算运费
     *
     * @param expensesItem
     * @param goodsItems
     * @return
     */
    private static Double caculateItem(TransportationExpensesItem expensesItem,
                                   List<GoodsItem> goodsItems) {
        if (expensesItem == null) {
            return null;
        }

        Byte pricingManner = expensesItem.getPricingManner() == null ? -1 : expensesItem.getPricingManner();
        double price;
        double start = StringUtils.doubleValue(expensesItem.getStart(), 0);
        double startPrice = StringUtils.doubleValue(expensesItem.getStartPrice(), 0);
        double plus = StringUtils.doubleValue(expensesItem.getPlus(), 0);
        double plusPrice = StringUtils.doubleValue(expensesItem.getPlusPrice(), 0);

        if (pricingManner == TransportationExpensesConstants.PRICING_MANNER_NUMBER) { // 按数量计算运费
            int goodsSize = 0;
            for (GoodsItem goodsItem : goodsItems) {
                goodsSize += StringUtils.intValue(goodsItem.getCount(), 1);
            }
            int startNum = StringUtils.intValue(start, 1);
            int plusNum = StringUtils.intValue(plus, 1);
            if (goodsSize <= startNum) {
                price = startPrice;
            } else {
                int n = (goodsSize - startNum) / plusNum;
                int mod = (goodsSize - startNum) % plusNum;
                n += mod == 0 ? 0 : 1;
                price = startPrice + n * plusPrice;
            }
        } else if (pricingManner == TransportationExpensesConstants.PRICING_MANNER_WEIGHT) { // 按重量计算运费
            double weight = 0;
            for (GoodsItem goodsItem : goodsItems) {
                weight += StringUtils.doubleValue(goodsItem.getGpstWeight(), 0);
            }

            weight = weight - start;
            int n = 0;
            while (weight > 0) {
                n++;
                weight -= plus;
            }
            price = startPrice + n * plusPrice;
        } else if (pricingManner == TransportationExpensesConstants.PRICING_MANNER_VOLUME) { // 按体积计算运费
            double volume = 0;
            for (GoodsItem goodsItem : goodsItems) {
                volume += StringUtils.doubleValue(goodsItem.getGpstVolume(), 0);
            }

            volume = volume - start;
            int n = 0;
            while (volume > 0) {
                n++;
                volume -= plus;
            }
            price = startPrice + n * plusPrice;
        } else { // 通过累加计算运费
            price = startPrice * goodsItems.size();
        }
        return price == 0 ? null : price;
    }

}
