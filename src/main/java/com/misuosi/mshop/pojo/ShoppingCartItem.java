/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.pojo;

import com.misuosi.mshop.db.Entity;

/**
 * Description		: 购物车子项
 * <p/>
 * <br><br>Time		: 2015/6/11 23:17
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class ShoppingCartItem extends Entity {

    private Integer shcaId;
    private Integer gpstId;
    private Integer goodId;
    private String goodName;
    private Double goodPrice;
    private String goodPicture;
    private Integer count;

    public Integer getShcaId() {
        return shcaId;
    }

    public void setShcaId(Integer shcaId) {
        this.shcaId = shcaId;
    }

    public Integer getGpstId() {
        return gpstId;
    }

    public void setGpstId(Integer gpstId) {
        this.gpstId = gpstId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(Double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getGoodPicture() {
        return goodPicture;
    }

    public void setGoodPicture(String goodPicture) {
        this.goodPicture = goodPicture;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
