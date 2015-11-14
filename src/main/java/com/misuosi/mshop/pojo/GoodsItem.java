/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.pojo;

import com.misuosi.mshop.db.Entity;

/**
 * Description		: 商品子项
 * <p/>
 * <br><br>Time		: 2015/6/16 16:14
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class GoodsItem extends Entity {

    private Integer teteId; // 运费模板ID
    private Integer gpstId; // 价格与库存ID
    private Double gpstWeight; // 商品重量
    private Double gpstVolume; // 商品体积
    private Byte goodAssumeExpenses; // 是否是卖家承担运费
    private Double guexExpressPrice; // 快递运费
    private Double guexEmsPrice; // EMS运费
    private Double guexPostPrice; // 平邮运费
    private Integer count; // 商品数量

    public Integer getTeteId() {
        return teteId;
    }

    public void setTeteId(Integer teteId) {
        this.teteId = teteId;
    }

    public Integer getGpstId() {
        return gpstId;
    }

    public void setGpstId(Integer gpstId) {
        this.gpstId = gpstId;
    }

    public Double getGpstWeight() {
        return gpstWeight;
    }

    public void setGpstWeight(Double gpstWeight) {
        this.gpstWeight = gpstWeight;
    }

    public Double getGpstVolume() {
        return gpstVolume;
    }

    public void setGpstVolume(Double gpstVolume) {
        this.gpstVolume = gpstVolume;
    }

    public Byte getGoodAssumeExpenses() {
        return goodAssumeExpenses;
    }

    public void setGoodAssumeExpenses(Byte goodAssumeExpenses) {
        this.goodAssumeExpenses = goodAssumeExpenses;
    }

    public Double getGuexExpressPrice() {
        return guexExpressPrice;
    }

    public void setGuexExpressPrice(Double guexExpressPrice) {
        this.guexExpressPrice = guexExpressPrice;
    }

    public Double getGuexEmsPrice() {
        return guexEmsPrice;
    }

    public void setGuexEmsPrice(Double guexEmsPrice) {
        this.guexEmsPrice = guexEmsPrice;
    }

    public Double getGuexPostPrice() {
        return guexPostPrice;
    }

    public void setGuexPostPrice(Double guexPostPrice) {
        this.guexPostPrice = guexPostPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
