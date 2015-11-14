/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.pojo;

import java.util.List;
import java.util.Map;

/**
 * Description		: 分销商
 * <p/>
 * <br><br>Time		: 2015/6/11 10:59
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class Distributor {

    private Integer diacId; // 分销商ID
    private Double discount; // 代理商品的折扣率
    // 以商品ID为键，缓存分销商代理的商品
    // 能找到表示有代理该商品，找不到表示没有代理该商品
    private Map<Integer, Boolean> distributingGoodses;
    private Distributor parentDistributor; // 父级分销商
    private List<Distributor> childDistributors; // 子级分销商

    public Integer getDiacId() {
        return diacId;
    }

    public void setDiacId(Integer diacId) {
        this.diacId = diacId;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Map<Integer, Boolean> getDistributingGoodses() {
        return distributingGoodses;
    }

    public void setDistributingGoodses(Map<Integer, Boolean> distributingGoodses) {
        this.distributingGoodses = distributingGoodses;
    }

    public Distributor getParentDistributor() {
        return parentDistributor;
    }

    public void setParentDistributor(Distributor parentDistributor) {
        this.parentDistributor = parentDistributor;
    }

    public List<Distributor> getChildDistributors() {
        return childDistributors;
    }

    public void setChildDistributors(List<Distributor> childDistributors) {
        this.childDistributors = childDistributors;
    }

}
