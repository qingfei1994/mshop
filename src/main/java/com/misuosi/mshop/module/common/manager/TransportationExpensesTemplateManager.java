/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.common.manager;

import com.misuosi.mshop.common.constants.TransportationExpensesConstants;
import com.misuosi.mshop.db.builder.SimpleSqlBuilder;
import com.misuosi.mshop.db.dao.DaoFactory;
import com.misuosi.mshop.db.dao.TreeDao;
import com.misuosi.mshop.entity.DistributionRegion;
import com.misuosi.mshop.entity.TransportationExpenses;
import com.misuosi.mshop.entity.TransportationExpensesTemplate;
import com.misuosi.mshop.pojo.TransportationExpensesItem;
import com.misuosi.mshop.pojo.TransportationExpensesTemplateItem;
import com.misuosi.mshop.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description		: 运费模板管理器
 * <p/>
 * <br><br>Time		: 2015/6/16 8:52
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class TransportationExpensesTemplateManager {

    private static List<TransportationExpensesTemplate> transportationExpensesTemplates;
    // 以模板ID为键缓存运费模板
    private static Map<Integer, TransportationExpensesTemplate> transportationExpensesTemplateMap;
    // 以模板ID和地区ID为键缓存运费信息
    // 查找运费：teteId => regiId => List<TransportationExpenses>
    private static Map<Integer, Map<Integer, List<TransportationExpenses>>> teteRegiTransportationExpensesMap;

    static {
        init();
    }

    private static void init() {
        TreeDao<TransportationExpensesTemplate> transportationExpensesTemplateTreeDao =
                DaoFactory.getTreeDao(TransportationExpensesTemplate.class);
        String sql = SimpleSqlBuilder.getQuerySql(TransportationExpensesTemplate.class);
        transportationExpensesTemplates = transportationExpensesTemplateTreeDao.queryForTree(sql);
        transportationExpensesTemplateMap = new HashMap<Integer, TransportationExpensesTemplate>();
        teteRegiTransportationExpensesMap = new HashMap<Integer, Map<Integer, List<TransportationExpenses>>>();
        for (TransportationExpensesTemplate transportationExpensesTemplate : transportationExpensesTemplates) {
            initTransportationExpensesTemplateMap(transportationExpensesTemplate);
            initTeteRegiTransportationExpensesMap(transportationExpensesTemplate);
        }
    }

    private static void initTransportationExpensesTemplateMap(
            TransportationExpensesTemplate transportationExpensesTemplate) {
        transportationExpensesTemplateMap.put(transportationExpensesTemplate.getTeteId(),
                transportationExpensesTemplate);
    }

    private static final Integer ALL_REGION_ID = -1;

    private static void initTeteRegiTransportationExpensesMap(
            TransportationExpensesTemplate transportationExpensesTemplate) {
        Integer teteId = transportationExpensesTemplate.getTeteId();
        Map<Integer, List<TransportationExpenses>> expensesMap = teteRegiTransportationExpensesMap.get(teteId);
        if (expensesMap == null) {
            expensesMap = new HashMap<Integer, List<TransportationExpenses>>();
            teteRegiTransportationExpensesMap.put(teteId, expensesMap);
        }

        List<TransportationExpenses> transportationExpenseses =
                transportationExpensesTemplate.getTransportationExpenseses();
        for (TransportationExpenses expenses : transportationExpenseses) {
            if (StringUtils.intValue(expenses.getTrexAllRegion(), 0)
                    == TransportationExpensesConstants.ALL_REGION) {
                List<TransportationExpenses> expensesList = expensesMap.get(ALL_REGION_ID);
                if (expensesList == null) {
                    expensesList = new ArrayList<TransportationExpenses>();
                    expensesMap.put(ALL_REGION_ID, expensesList);
                }
                expensesList.add(expenses);
            }
        }
    }

    public static void refresh() {
        transportationExpensesTemplates = null;
        transportationExpensesTemplateMap = null;
        teteRegiTransportationExpensesMap = null;
        init();
    }

    /**
     * 获取所有运费模板
     *
     * @return
     */
    public static List<TransportationExpensesTemplate> getTransportationExpensesTemplates() {
        return transportationExpensesTemplates;
    }

    /**
     * 根据模板ID获取模板信息
     *
     * @param teteId
     * @return
     */
    public static TransportationExpensesTemplate getTransportationExpensesTemplateByTeteId(Integer teteId) {
        return transportationExpensesTemplateMap.get(teteId);
    }

    /**
     * 根据模板ID和地区ID获取运费模板信息
     *
     * @param teteId
     * @param regiId
     * @return
     */
    public static TransportationExpensesTemplateItem getTransportationExpensesTemplateItem(
            Integer teteId, Integer regiId) {
        TransportationExpensesTemplate template = transportationExpensesTemplateMap.get(teteId);
        Byte pricingManner = template.getTetePricingManner();
        List<TransportationExpenses> allRegionExpenseses =
                teteRegiTransportationExpensesMap.get(teteId).get(ALL_REGION_ID);
        List<TransportationExpenses> currentRegionExpenseses =
                teteRegiTransportationExpensesMap.get(teteId).get(regiId);

        TransportationExpensesTemplateItem templateItem = new TransportationExpensesTemplateItem();
        templateItem.setTeteId(teteId);

        if (currentRegionExpenseses != null && !currentRegionExpenseses.isEmpty()) {
            for (TransportationExpenses expenses : currentRegionExpenseses) {
                Byte shippingMethod = expenses.getTrexShippingMethod();
                if (shippingMethod == TransportationExpensesConstants.SHIPPING_METHOD_EXPRESS) {
                    // 快递运费
                    templateItem.setExpressItem(initTransportationExpensesItem(expenses, pricingManner));
                } else if (shippingMethod == TransportationExpensesConstants.SHIPPING_METHOD_EMS) {
                    // EMS运费
                    templateItem.setEmsItem(initTransportationExpensesItem(expenses, pricingManner));
                } else if (shippingMethod == TransportationExpensesConstants.SHIPPING_METHOD_POST) {
                    // 平邮运费
                    templateItem.setPostItem(initTransportationExpensesItem(expenses, pricingManner));
                }
            }
        }

        if (allRegionExpenseses != null && !allRegionExpenseses.isEmpty()) {
            for (TransportationExpenses expenses : allRegionExpenseses) {
                Byte shippingMethod = expenses.getTrexShippingMethod();
                if (shippingMethod == TransportationExpensesConstants.SHIPPING_METHOD_EXPRESS) {
                    // 快递运费
                    if (templateItem.getExpressItem() == null) {
                        templateItem.setExpressItem(initTransportationExpensesItem(expenses, pricingManner));
                    }
                } else if (shippingMethod == TransportationExpensesConstants.SHIPPING_METHOD_EMS) {
                    // EMS运费
                    if (templateItem.getEmsItem() == null) {
                        templateItem.setEmsItem(initTransportationExpensesItem(expenses, pricingManner));
                    }
                } else if (shippingMethod == TransportationExpensesConstants.SHIPPING_METHOD_POST) {
                    // 平邮运费
                    if (templateItem.getPostItem() == null) {
                        templateItem.setPostItem(initTransportationExpensesItem(expenses, pricingManner));
                    }
                }
            }
        }

        return templateItem;
    }

    /**
     * 初始化运费计算子项
     *
     * @param expenses
     * @param pricingManner
     * @return
     */
    private static TransportationExpensesItem initTransportationExpensesItem(
            TransportationExpenses expenses, byte pricingManner) {
        TransportationExpensesItem item = new TransportationExpensesItem();
        item.setStart(expenses.getTrexStart());
        item.setStartPrice(expenses.getTrexStartPrice());
        item.setPlus(expenses.getTrexPlus());
        item.setPlusPrice(expenses.getTrexPlusPrice());
        item.setPricingManner(pricingManner);
        return item;
    }

}
