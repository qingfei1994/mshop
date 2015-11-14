/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.pojo;

import com.misuosi.mshop.pojo.*;

/**
 * Description		: 运费子项
 * <p/>
 * <br><br>Time		: 2015/6/16 10:34
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class TransportationExpensesTemplateItem {

    private Integer teteId;
    private com.misuosi.mshop.pojo.TransportationExpensesItem expressItem; // 快递运费计算子项
    private com.misuosi.mshop.pojo.TransportationExpensesItem emsItem; // EMS运费计算子项
    private com.misuosi.mshop.pojo.TransportationExpensesItem postItem; // 平邮运费计算子项

    public Integer getTeteId() {
        return teteId;
    }

    public void setTeteId(Integer teteId) {
        this.teteId = teteId;
    }

    public com.misuosi.mshop.pojo.TransportationExpensesItem getExpressItem() {
        return expressItem;
    }

    public void setExpressItem(com.misuosi.mshop.pojo.TransportationExpensesItem expressItem) {
        this.expressItem = expressItem;
    }

    public com.misuosi.mshop.pojo.TransportationExpensesItem getEmsItem() {
        return emsItem;
    }

    public void setEmsItem(com.misuosi.mshop.pojo.TransportationExpensesItem emsItem) {
        this.emsItem = emsItem;
    }

    public com.misuosi.mshop.pojo.TransportationExpensesItem getPostItem() {
        return postItem;
    }

    public void setPostItem(com.misuosi.mshop.pojo.TransportationExpensesItem postItem) {
        this.postItem = postItem;
    }

}
