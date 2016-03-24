/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.wap.order.service;

import com.misuosi.mshop.common.constants.OrderConstants;
import com.misuosi.mshop.common.constants.TransportationExpensesConstants;
import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.TreeDao;
import com.misuosi.mshop.db.page.Pagination;
import com.misuosi.mshop.entity.*;
import com.misuosi.mshop.module.common.manager.TransportationExpensesManager;
import com.misuosi.mshop.module.wap.usercenter.service.WapShopCartService;
import com.misuosi.mshop.pojo.OrderItem;
import com.misuosi.mshop.pojo.ShoppingCartItem;
import com.misuosi.mshop.pojo.TransportationExpensesResult;
import com.misuosi.mshop.service.GoodsCommentService;
import com.misuosi.mshop.service.OrderInformationService;
import com.misuosi.mshop.service.OrderSettingService;
import com.misuosi.mshop.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

/**
 * Description		: 订单相关业务Service
 * <p/>
 * <br><br>Time		: 2015/6/12 20:19
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
@Service
public class WapOrderService {

    @Resource(name = "shoppingCartDao")
    private Dao<ShoppingCart> shoppingCartDao;
    @Resource(name = "goodsPriceStockDao")
    private Dao<GoodsPriceStock> goodsPriceStockDao;
    @Resource(name = "goodsDao")
    private Dao<Goods> goodsDao;
    @Resource(name = "goodsOrderDao")
    private Dao<GoodsOrder> goodsOrderDao;
    @Resource(name = "orderInformationDao")
    private Dao<OrderInformation> orderInformationDao;
    @Resource(name = "shipmentsInformationDao")
    private Dao<ShipmentsInformation> shipmentsInformationDao;

    @Autowired
    private WapShopCartService wapShopCartService;
    @Autowired
    private OrderInformationService orderInformationService;
    @Autowired
    private OrderSettingService orderSettingService;
    @Autowired
    private GoodsCommentService goodsCommentService;

    /**
     * 更新购物车商品数量
     *
     * @param shoppingCartItems
     */
    public void updateShoppingCart(ShoppingCartItem[] shoppingCartItems,Integer weinId) {
        if (shoppingCartItems == null || shoppingCartItems.length == 0) {
            return;
        }

        List<ShoppingCartItem> shoppingCartItemList = new ArrayList<ShoppingCartItem>();
        for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
            if (shoppingCartItem.getGpstId() != null) {
                shoppingCartItemList.add(shoppingCartItem);
            }
        }

        if (shoppingCartItemList.isEmpty()) {
            return;
        }

        Map<Integer, ShoppingCartItem> shoppingCartItemMap = new HashMap<Integer, ShoppingCartItem>();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM shopping_cart ");
        sql.append("WHERE gpst_id in (");
        for (ShoppingCartItem shoppingCartItem : shoppingCartItemList) {
            Integer gpstId = shoppingCartItem.getGpstId();
            sql.append(shoppingCartItem.getGpstId()).append(",");
            shoppingCartItemMap.put(gpstId, shoppingCartItem);
        }
        sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        sql.append(" AND wein_id="+weinId);

        List<ShoppingCart> shoppingCarts = shoppingCartDao.find(sql.toString());
        for (ShoppingCart shoppingCart : shoppingCarts) {
            ShoppingCartItem shoppingCartItem = shoppingCartItemMap.get(shoppingCart.getGpstId());
            if (shoppingCart.getShcaCount() == shoppingCartItem.getCount()) {
                shoppingCartItemMap.remove(shoppingCart.getGpstId());
            }
        }

        if (!shoppingCartItemMap.isEmpty()) {
            sql.delete(0, sql.length());
            for (Integer gpstId : shoppingCartItemMap.keySet()) {
                ShoppingCartItem shoppingCartItem = shoppingCartItemMap.get(gpstId);
                sql.append("UPDATE shopping_cart SET shca_count=").append(shoppingCartItem.getCount())
                        .append(" WHERE gpst_id=").append(gpstId).append(";");
            }
            shoppingCartDao.batchUpdate(sql.toString());
        }
    }

    /**
     * 订单统计
     *
     * @param weinId
     * @param shoppingCartItems
     * @return
     */
    public OrderItem statisticsOrder(Integer weinId, Integer regiId,
                                     ShoppingCartItem[] shoppingCartItems) {
        if (shoppingCartItems == null) {
            return null;
        }

        if (shoppingCartItems.length == 1 && shoppingCartItems[0].getShcaId() == null) {
            Integer gpstId = shoppingCartItems[0].getGpstId();
            Integer count = shoppingCartItems[0].getCount();
            ShoppingCartItem item = wapShopCartService.getShoppingCartItemByGpstId(gpstId);
            if (item != null) {
                item.setCount(count);
                shoppingCartItems[0] = item;
            }
        } else {
            // 从数据库重新拿一份商品信息
            List<ShoppingCartItem> realShoppingCartItems =
                    wapShopCartService.getShoppingCartItemsByWeinId(weinId);
            Map<Integer, ShoppingCartItem> shoppingCartItemMap = new HashMap<Integer, ShoppingCartItem>();
            for (ShoppingCartItem cartItem : realShoppingCartItems) {
                shoppingCartItemMap.put(cartItem.getGpstId(), cartItem);
            }
            for (ShoppingCartItem cartItem : shoppingCartItems) {
                ShoppingCartItem realCartItem = shoppingCartItemMap.get(cartItem.getGpstId());
                if (realCartItem != null) {
                    cartItem.setGpstId(realCartItem.getGpstId());
                    cartItem.setGoodId(realCartItem.getGoodId());
                    cartItem.setGoodName(realCartItem.getGoodName());
                    cartItem.setGoodPrice(realCartItem.getGoodPrice());
                    cartItem.setGoodPicture(realCartItem.getGoodPicture());
                }
            }
        }

        int totalCount = 0;
        double totalPrice = 0;
        for (ShoppingCartItem cartItem : shoppingCartItems) {
            Integer count = cartItem.getCount();
            Double goodPrice = cartItem.getGoodPrice();
            totalCount += StringUtils.intValue(count, 0);
            totalPrice += StringUtils.doubleValue(goodPrice, 0)
                    * StringUtils.intValue(count, 0);
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setTotalCount(totalCount);
        orderItem.setTotalPrice(totalPrice);
        orderItem.setShoppingCartItems(Arrays.asList(shoppingCartItems));
        orderItem.setTransportationExpensesResult(
                TransportationExpensesManager.calculateTransportationExpenses(
                        Arrays.asList(shoppingCartItems), regiId));

        return orderItem;
    }

    /**
     * 提交订单
     *
     * @param orderItem
     * @param coadId
     * @return 返回订单ID
     */
    public String submitOrder(Integer weinId, Integer coadId, OrderItem orderItem, String remark) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        byte refundStatus = 0;
        String orinSource = "微信";

        TransportationExpensesResult expensesResult = orderItem.getTransportationExpensesResult();
        double freight; // 运费
        String orinDeliveryMethod; // 订单配送方式
        Byte shippingMethod = orderItem.getShippingMethod();
        if (shippingMethod == TransportationExpensesConstants.SHIPPING_METHOD_EXPRESS) {
            freight = StringUtils.doubleValue(expensesResult.getTotalExpressExpenses(), 0);
            orinDeliveryMethod = "快递";
        } else if (shippingMethod == TransportationExpensesConstants.SHIPPING_METHOD_EMS) {
            freight = StringUtils.doubleValue(expensesResult.getTotalEmsExpenses(), 0);
            orinDeliveryMethod = "EMS";
        } else if (shippingMethod == TransportationExpensesConstants.SHIPPING_METHOD_POST) {
            freight = StringUtils.doubleValue(expensesResult.getTotalPostExpenses(), 0);
            orinDeliveryMethod = "平邮";
        } else {
            freight = StringUtils.doubleValue(expensesResult.getTotalMixExpenses(), 0);
            orinDeliveryMethod = "快递"; // 混有多种运送方式
        }

        OrderInformation orderInformation = new OrderInformation();
        orderInformation.setWeinId(weinId);
        orderInformation.setOrinTotal(calculateOrderTotal(orderItem) + freight);
        orderInformation.setOrinFreight(freight);
        orderInformation.setOrinOldFreight(freight);
        orderInformation.setOrinNo(orderInformationService.genOrderNo());
        orderInformation.setOrinPayStatus((byte) 0); // 支付状态：待支付
        orderInformation.setOrinStatus((byte) 1); // 订单状态：交易中
        orderInformation.setOrinDeliveryMethod(orinDeliveryMethod); // 订单配送方式
        orderInformation.setOrinSource(orinSource);
        orderInformation.setOrinModifyTime(now);
        orderInformation.setOrinCreateTime(now);
        orderInformation.setOrinOrderTime(now);
        orderInformationService.addOrderInformation(orderInformation);

        Integer orinId = orderInformation.getOrinId();
        List<ShoppingCartItem> shoppingCartItems = orderItem.getShoppingCartItems();
        for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
            Integer gpstId = shoppingCartItem.getGpstId();
            GoodsPriceStock goodsPriceStock = goodsPriceStockDao.get(gpstId);
            Integer goodId = shoppingCartItem.getGoodId();
            Goods goods = goodsDao.get(goodId);

            GoodsOrder goodsOrder = new GoodsOrder();
            goodsOrder.setGpstId(gpstId);
            goodsOrder.setOrinId(orinId);
            goodsOrder.setGoodId(goodId);
            goodsOrder.setGoorName(goods.getGoodName());
            goodsOrder.setGoorCode(goodsPriceStock.getGpstCode());
            goodsOrder.setGoorPrice(shoppingCartItem.getGoodPrice());
            goodsOrder.setGoorFirstPicture(goods.getGoodFirstPicture());
            goodsOrder.setGoorCount(shoppingCartItem.getCount());
            goodsOrder.setGoorRealityPay(StringUtils.doubleValue(shoppingCartItem.getGoodPrice(), 0)
                    * StringUtils.intValue(shoppingCartItem.getCount(), 0)); // 实收款
            goodsOrder.setGoorModifyTime(now);
            goodsOrder.setGoorCreateTime(now);
            goodsOrder.setGoorRefundStatus(refundStatus);
            goodsOrderDao.save(goodsOrder);
        }

        ShipmentsInformation shipmentsInformation = new ShipmentsInformation();
        shipmentsInformation.setCoadId(coadId);
        shipmentsInformation.setOrinId(orderInformation.getOrinId());
        shipmentsInformation.setShinShipmentsStatus(OrderConstants.SHIN_SHINMENT_STATUS_NOT);
        shipmentsInformation.setShinSignStatus(OrderConstants.SHIN_SIGN_STATUS_NOT);
        shipmentsInformation.setShinRemark(remark);
        shipmentsInformation.setShinModifyTime(now);
        shipmentsInformation.setShinCreateTime(now);
        shipmentsInformationDao.save(shipmentsInformation);

        return orderInformation.getOrinNo();
    }

    /**
     * 统计订单总价，不含运费
     *
     * @param orderItem
     * @return
     */
    private double calculateOrderTotal(OrderItem orderItem) {
        double total = 0;
        List<ShoppingCartItem> shoppingCartItems = orderItem.getShoppingCartItems();
        for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
            total += StringUtils.doubleValue(shoppingCartItem.getGoodPrice(), 0)
                    * StringUtils.intValue(shoppingCartItem.getCount(), 0);
        }
        return total;
    }

    /**
     * 删除订单
     *
     * @param orinId
     * @param weinId
     * @return
     */
    public int deleteOrder(Integer orinId, Integer weinId) {
        OrderInformation orderInformation = orderInformationService.getOrderInformation(orinId);

        if (orderInformation == null) {
            return 300; // 订单不存在
        }
        if (orderInformation.getWeinId() != weinId) {
            return 301; // 非本人操作
        }
        if (orderInformation.getOrinPayStatus() == 1) {
            return 302; // 已支付订单不可删除
        }

        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FORM goods_order WHERE orin_id=?;");
        sql.append("DELETE FORM shipments_information WHERE orin_id=?");
        orderInformationDao.batchDelete(sql.toString(), orinId, orinId);
        orderInformationDao.delete(orinId);

        return 200;
    }

    /**
     * 通过微信ID获得用户的订单列表
     *
     * @param weinId
     * @param type
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Pagination<OrderInformation> getOrderDataByWeinId(int weinId, int type, int pageNo, int pageSize) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT order_information.* FROM order_information")
                .append(" INNER JOIN shipments_information ON shipments_information.orin_id = order_information.orin_id")
                .append(" WHERE order_information.wein_id = ?");
        if (type == 0) {
            sql.append(" AND orin_status = 1 AND orin_pay_status = 0 AND shin_shipments_status = 0 AND shin_sign_status = 0");
        } else if (type == 1) {
            sql.append(" AND orin_status = 1 AND orin_pay_status = 1 AND shin_shipments_status = 0 AND shin_sign_status = 0");
        } else if (type == 2) {
            sql.append(" AND orin_status = 1 AND orin_pay_status = 1 AND shin_shipments_status = 1 AND shin_sign_status = 0");
        } else if (type == 3) {
            sql.append(" AND orin_status = 2 AND orin_pay_status = 1 AND shin_shipments_status = 1 AND shin_sign_status = 1");
        }
        sql.append(" ORDER BY order_information.orin_id DESC");

        return ((TreeDao<OrderInformation>) orderInformationDao).queryForPaginationTree(
                sql.toString(), pageNo, pageSize, weinId);
    }

    /**
     * 确认收货更新订单
     *
     * @param orinId
     */
    public int updateConfirmReception(int orinId) {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        OrderInformation orderInformation = orderInformationService.getTreeOrderInformation(orinId);
        orderInformation.setOrinStatus(OrderConstants.ORIN_STATUS_FINISH);
        orderInformation.setOrinModifyTime(now);

        ShipmentsInformation shipmentsInformation = orderInformation.getShipmentsInformation();
        shipmentsInformation.setShinSignStatus(OrderConstants.SHIN_SIGN_STATUS_YES);
        shipmentsInformation.setShinSignTime(now);

        int row = shipmentsInformationDao.dynamicUpdate(shipmentsInformation);
        row += orderInformationDao.dynamicUpdate(orderInformation);

        return row;
    }

    /**
     * 取消订单
     *
     * @param orinId
     * @return
     */
    public int cancelOrder(int orinId) {
        Timestamp now = new Timestamp(System.currentTimeMillis());

        OrderInformation orderInformation = orderInformationService.getOrderInformation(orinId);
        orderInformation.setOrinStatus(OrderConstants.ORIN_STATUS_CLOSE);
        orderInformation.setOrinModifyTime(now);

        int row = orderInformationDao.dynamicUpdate(orderInformation);

        return row;
    }

    /**
     * 评价商品
     *
     * @param data
     * @param stars
     * @param content
     * @return
     */
    public int evaluation(Integer[] data, Byte[] stars, String[] content) {
        if (data != null && stars != null && content != null
                && data.length == stars.length && stars.length == content.length) {
            int row = 0;
            for (int i = 0; i < data.length; i++) {
                int goorId = data[i];
                GoodsComment goodsComment = goodsCommentService.getGoodsCommentByGoorId(goorId);
                Timestamp now = new Timestamp(System.currentTimeMillis());
                if (goodsComment != null) {
                    goodsComment.setGocoSatisfaction(stars[i]);
                    goodsComment.setGocoCommentContent(content[i]);
                    goodsComment.setGocoCommentTime(now);
                    row += goodsCommentService.updateGoodsComment(goodsComment);
                } else {
                    goodsComment = new GoodsComment();
                    goodsComment.setGoorId(goorId);
                    goodsComment.setGocoSatisfaction(stars[i]);
                    goodsComment.setGocoCommentContent(content[i]);
                    goodsComment.setGocoCommentTime(now);
                    goodsComment.setGocoShowStatus(OrderConstants.GOCO_SHOW_YES);
                    row += goodsCommentService.addGoodsComment(goodsComment);
                }
            }
            return row;
        }
        return 0;
    }

}
