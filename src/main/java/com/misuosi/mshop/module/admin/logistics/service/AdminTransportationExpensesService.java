/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.logistics.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.TreeDao;
import com.misuosi.mshop.entity.DistributionRegion;
import com.misuosi.mshop.entity.TransportationExpenses;
import com.misuosi.mshop.entity.TransportationExpensesTemplate;
import com.misuosi.mshop.module.common.manager.TransportationExpensesTemplateManager;
import com.misuosi.mshop.util.DateUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description		:
 * <p/>
 * <br><br>Time		: 2015年6月4日 下午4:30:32
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
@Service
public class AdminTransportationExpensesService {

    //运送方式常量
    /**
     * 快递
     */
    private static final Byte SHIPPING_METHOD_EXPRESS = 0;
    /**
     * ems
     */
    private static final Byte SHIPPING_METHOD_EMS = 1;
    /**
     * 平邮
     */
    private static final Byte SHIPPING_METHOD_POST = 2;

    @Resource(name = "transportationExpensesTemplateDao")
    private Dao<TransportationExpensesTemplate> transportationExpensesTemplateDao;
    @Resource(name = "transportationExpensesDao")
    private Dao<TransportationExpenses> transportationExpensesDao;
    @Resource(name = "distributionRegionDao")
    private Dao<DistributionRegion> distributionRegionDao;

    @Resource(name = "transportationExpensesDao")
    private TreeDao<TransportationExpenses> TransportationExpensesTreeDao;

    /**
     * @param transportationExpensesTemplate
     * @param express                        List<Map<String, Object>> 一个Map<String, Object>
     *                                       中有一个List<Integer> regiIds 和一个TransportationExpenses
     *                                       transportationExpenses
     * @param ems                            List<Map<String, Object>> 一个Map<String, Object>
     *                                       中有一个List<Integer> regiIds 和一个TransportationExpenses
     *                                       transportationExpenses
     * @param post                           List<Map<String, Object>> 一个Map<String, Object>
     *                                       中有一个List<Integer> regiIds 和一个TransportationExpenses
     *                                       transportationExpenses
     */
    public void addTransportationExpensesTemplate(
            TransportationExpensesTemplate transportationExpensesTemplate,
            List<Map<String, Object>> express, List<Map<String, Object>> ems,
            List<Map<String, Object>> post) {
        if (transportationExpensesTemplate.getTeteId() != null) {
            deleteTransportationExpensesByTeteId(transportationExpensesTemplate.getTeteId());
        } else {
            // TODO 需要完成插入
            transportationExpensesTemplate.setTeteCreateTime(DateUtils.getCurrentTime());
            transportationExpensesTemplateDao.save(transportationExpensesTemplate);
        }

        int teteId = transportationExpensesTemplate.getTeteId();

        List<DistributionRegion> distributionRegions = new ArrayList<DistributionRegion>();
        if (express != null) {
            for (Map<String, Object> exp : express) {
                TransportationExpenses transportationExpenses = (TransportationExpenses) exp.get("transportationExpenses");
                transportationExpenses.setTrexCreateTime(DateUtils.getCurrentTime());
                transportationExpenses.setTeteId(teteId);
                transportationExpenses.setTrexShippingMethod(SHIPPING_METHOD_EXPRESS);
                transportationExpensesDao.save(transportationExpenses);

                List<Integer> regiIds = (List<Integer>) exp.get("regiIds");
                if (regiIds != null) {
                    Integer trexId = transportationExpenses.getTrexId();
                    for (Integer regiId : regiIds) {
                        DistributionRegion distributionRegion = new DistributionRegion();
                        distributionRegion.setTrexId(trexId);
                        distributionRegion.setRegiId(regiId);
                        distributionRegion.setDireCreateTime(DateUtils.getCurrentTime());
                        distributionRegions.add(distributionRegion);
                    }
                }
            }
        }

        if (ems != null) {
            for (Map<String, Object> e : ems) {
                TransportationExpenses transportationExpenses = (TransportationExpenses) e.get("transportationExpenses");
                transportationExpenses.setTrexCreateTime(DateUtils.getCurrentTime());
                transportationExpenses.setTeteId(teteId);
                transportationExpenses.setTrexShippingMethod(SHIPPING_METHOD_EMS);
                transportationExpensesDao.save(transportationExpenses);

                List<Integer> regiIds = (List<Integer>) e.get("regiIds");
                if (regiIds != null) {
                    Integer trexId = transportationExpenses.getTrexId();
                    for (Integer regiId : regiIds) {
                        DistributionRegion distributionRegion = new DistributionRegion();
                        distributionRegion.setTrexId(trexId);
                        distributionRegion.setRegiId(regiId);
                        distributionRegion.setDireCreateTime(DateUtils.getCurrentTime());
                        distributionRegions.add(distributionRegion);
                    }
                }
            }
        }

        if (post != null) {
            for (Map<String, Object> po : post) {
                TransportationExpenses transportationExpenses = (TransportationExpenses) po.get("transportationExpenses");
                transportationExpenses.setTrexCreateTime(DateUtils.getCurrentTime());
                transportationExpenses.setTeteId(teteId);
                transportationExpenses.setTrexShippingMethod(SHIPPING_METHOD_POST);
                transportationExpensesDao.save(transportationExpenses);

                List<Integer> regiIds = (List<Integer>) po.get("regiIds");
                if (regiIds != null) {
                    Integer trexId = transportationExpenses.getTrexId();
                    for (Integer regiId : regiIds) {
                        DistributionRegion distributionRegion = new DistributionRegion();
                        distributionRegion.setTrexId(trexId);
                        distributionRegion.setRegiId(regiId);
                        distributionRegion.setDireCreateTime(DateUtils.getCurrentTime());
                        distributionRegions.add(distributionRegion);
                    }
                }
            }
        }

        if (distributionRegions != null) {
            distributionRegionDao.batchSave(distributionRegions);
        }

        TransportationExpensesTemplateManager.refresh();
    }

    /**
     * @param exp
     * @param transportationExpenses
     */
    private List<DistributionRegion> struDistributionRegion(Map<String, Object> exp, TransportationExpenses transportationExpenses) {
        List<DistributionRegion> distributionRegions = new ArrayList<DistributionRegion>();

        Integer trexId = transportationExpenses.getTrexId();
        @SuppressWarnings("unchecked")
        List<Integer> regiIds = (ArrayList<Integer>) exp.get("regiIds");
        if (regiIds == null || regiIds.size() == 0) {
            return null;
        }
        for (Integer regiId : regiIds) {
            DistributionRegion distributionRegion = new DistributionRegion();
            distributionRegion.setDireCreateTime(DateUtils.getCurrentTime());
            distributionRegion.setRegiId(regiId);
            distributionRegion.setTrexId(trexId);
            distributionRegions.add(distributionRegion);
        }

        return distributionRegions;
    }


    /**
     * 删除运费模板.
     *
     * @param teteId
     * @return
     */
    public int deleteTransportationExpensesTemplateByTeteId(Integer teteId) {
        int rows = transportationExpensesTemplateDao.delete(teteId);

        rows += deleteTransportationExpensesByTeteId(teteId);

        TransportationExpensesTemplateManager.refresh();

        return rows;
    }

    private int deleteTransportationExpensesByTeteId(Integer teteId) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE transportation_expenses, distribution_region ");
        sb.append("FROM transportation_expenses ");
        sb.append("LEFT JOIN distribution_region ON distribution_region.trex_id = transportation_expenses.trex_id ");
        sb.append("WHERE transportation_expenses.trex_id = ?");
        String sql = sb.toString();
        int rows = transportationExpensesDao.batchDelete(sql, teteId);
        return rows;
    }

    /**
     * @param transportationExpensesTemplate
     * @param transportationExpenses
     * @return
     */
    public int addTransportationExpensesTemplate(TransportationExpensesTemplate transportationExpensesTemplate,
                                                 List<TransportationExpenses> transportationExpenses) {
        transportationExpensesTemplate.setTeteCreateTime(DateUtils.getCurrentTime());
        int rows = transportationExpensesTemplateDao.save(transportationExpensesTemplate);

        List<DistributionRegion> targets = new ArrayList<DistributionRegion>();
        Integer teteId = transportationExpensesTemplate.getTeteId();
        for (TransportationExpenses tran : transportationExpenses) {
            tran.setTeteId(teteId);
            tran.setTrexCreateTime(DateUtils.getCurrentTime());

            Integer trexId = tran.getTrexId();
            List<DistributionRegion> distributionRegions = distributionRegionDao.findByProperty("trexId", trexId);

            transportationExpensesDao.save(tran);
            trexId = tran.getTrexId();

            if (distributionRegions == null) {
                break;
            }
            for (DistributionRegion dist : distributionRegions) {
                dist.setTrexId(trexId);
                dist.setDireCreateTime(DateUtils.getCurrentTime());
                targets.add(dist);
            }
        }

        if (targets != null && targets.size() != 0) {
            distributionRegionDao.batchSave(targets);
        }

        TransportationExpensesTemplateManager.refresh();

        return rows;
    }

    /**
     * @param teteId
     * @return
     */
    public List<TransportationExpenses> getTransportationExpensesByTeteId(
            Integer teteId) {
        String sql = "SELECT transportation_expenses.* FROM transportation_expenses WHERE transportation_expenses.tete_id = ?";
        List<TransportationExpenses> trexs = TransportationExpensesTreeDao.queryForTree(sql, teteId);
        return trexs;
    }


}
