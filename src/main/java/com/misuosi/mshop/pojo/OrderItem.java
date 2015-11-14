/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.pojo;

import com.misuosi.mshop.pojo.ShoppingCartItem;
import com.misuosi.mshop.pojo.TransportationExpensesResult;

import java.util.List;

/**
 * Description		:
 * <p/>
 * <br><br>Time		: 2015/6/17 14:15
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class OrderItem {

    private Integer totalCount;
    private Double totalPrice;
    private List<ShoppingCartItem> shoppingCartItems;
    private TransportationExpensesResult transportationExpensesResult;
    private Byte shippingMethod; // 运送方式
    private String shipmentsRemark; // 发货备注

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<ShoppingCartItem> getShoppingCartItems() {
        return shoppingCartItems;
    }

    public void setShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
        this.shoppingCartItems = shoppingCartItems;
    }

    public TransportationExpensesResult getTransportationExpensesResult() {
        return transportationExpensesResult;
    }

    public void setTransportationExpensesResult(TransportationExpensesResult transportationExpensesResult) {
        this.transportationExpensesResult = transportationExpensesResult;
    }

    public Byte getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(Byte shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public String getShipmentsRemark() {
        return shipmentsRemark;
    }

    public void setShipmentsRemark(String shipmentsRemark) {
        this.shipmentsRemark = shipmentsRemark;
    }

}
