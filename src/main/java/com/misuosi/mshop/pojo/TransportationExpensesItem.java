/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.pojo;

/**
 * Description		: 运费子项
 * <p/>
 * <br><br>Time		: 2015/6/16 10:34
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class TransportationExpensesItem {

    private Double start; // 首件、首重、首体积
    private Double startPrice; // 首运费
    private Double plus; // 续件、续重、续体积
    private Double plusPrice; // 续运费
    private Byte pricingManner; // 计价方式

    public Double getStart() {
        return start;
    }

    public void setStart(Double start) {
        this.start = start;
    }

    public Double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Double startPrice) {
        this.startPrice = startPrice;
    }

    public Double getPlus() {
        return plus;
    }

    public void setPlus(Double plus) {
        this.plus = plus;
    }

    public Double getPlusPrice() {
        return plusPrice;
    }

    public void setPlusPrice(Double plusPrice) {
        this.plusPrice = plusPrice;
    }

    public Byte getPricingManner() {
        return pricingManner;
    }

    public void setPricingManner(Byte pricingManner) {
        this.pricingManner = pricingManner;
    }

}
