/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.orders.service;

import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.db.dao.MultiTableDao;
import com.misuosi.mshop.entity.GoodsOrder;
import com.misuosi.mshop.entity.OrderInformation;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Description		:
 * <p/>
 * <br><br>Time		: 2015年6月8日 下午12:24:36
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
@Service
public class AdminOrderInformationService {

    @Resource(name = "orderInformationDao")
    private Dao<OrderInformation> orderInformationDao;
    @Resource(name = "goodsOrderDao")
    private Dao<GoodsOrder> goodsOrderDao;

    /**
     * 通过供货商Id，获得订单列表信息.
     *
     * @return
     */
    public List<Map<String, Object>> getOrderInformationMaps() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT order_information.*, shipments_information.shin_shipments_status, wechat_information.wein_nickname, ");
        sb.append("goods_order.goor_id, goods_order.goor_first_picture, goods_order.goor_code, goods_order.goor_count, ");
        sb.append("goods_order.goor_name, goods_order.goor_price ");
        sb.append("FROM order_information ");
        sb.append("INNER JOIN shipments_information ON shipments_information.orin_id = order_information.orin_id ");
        sb.append("INNER JOIN wechat_information ON wechat_information.wein_id = order_information.wein_id ");
        sb.append("INNER JOIN goods_order ON goods_order.orin_id = order_information.orin_id ");
        sb.append("ORDER BY order_information.orin_id DESC");

        String sql = sb.toString();
        List<Map<String, Object>> maps = ((MultiTableDao) orderInformationDao).queryForList(sql);
        return maps;
    }

    /**
     * @param orinId
     * @return
     */
    public Map<String, Object> getOrderInformationMapsByOrinId(
            Integer orinId) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT order_information.*, ");
        sb.append("shipments_information.shin_id,shipments_information.shin_shipments_status, shipments_information.shin_express_no, shipments_information.shin_remark, ");
        sb.append("consignees_address.coad_name, consignees_address.coad_phone, consignees_address.coad_detailed_address, ");
        sb.append("county.regi_name AS coun_regi_name, city.regi_name AS city_regi_name, province.regi_name AS prov_regi_name, ");
        sb.append("wechat_information.wein_nickname, wechat_information.wein_sex, wechat_information.wein_unionid, wechat_information.wein_country, ");
        sb.append("wechat_information.wein_province,wechat_information.wein_city, ");
        sb.append("member_information.mein_name,member_information.mein_phone,member_information.mein_integral, member_information.mein_balance, ");
        sb.append("pay_information.pain_pay_money, pay_information.pain_pay_no, pay_information.pain_pay_time, pay_information.pain_pay_way, pay_information.pain_serial_number ");
        sb.append("FROM order_information ");
        sb.append("INNER JOIN shipments_information ON shipments_information.orin_id = order_information.orin_id ");
        sb.append("LEFT JOIN consignees_address ON consignees_address.coad_id = shipments_information.coad_id ");
        sb.append("INNER JOIN regionalism AS county ON county.regi_id = consignees_address.regi_id ");
        sb.append("INNER JOIN regionalism AS city ON city.regi_id = county.regi_parent_id ");
        sb.append("INNER JOIN regionalism AS province ON province.regi_id = city.regi_parent_id ");
        sb.append("INNER JOIN wechat_information ON wechat_information.wein_id = order_information.wein_id ");
        sb.append("LEFT JOIN member_information ON member_information.wein_id = wechat_information.wein_id ");
        sb.append("LEFT JOIN pay_information ON pay_information.pain_id = order_information.pain_id ");
        sb.append("WHERE order_information.orin_id = ? ");

        String sql = sb.toString();
        Map<String, Object> maps = ((MultiTableDao) orderInformationDao).queryForMap(sql, orinId);
        return maps;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    public Integer batchDeleteOrderInformation(Integer[] ids) {
        if (ids == null || ids.length == 0) {
            return 0;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("DELETE order_information,shipments_information,goods_order,pay_information ");
        sb.append("FROM order_information ");
        sb.append("INNER JOIN shipments_information ON shipments_information.orin_id = order_information.orin_id ");
        sb.append("INNER JOIN goods_order ON goods_order.orin_id = order_information.orin_id ");
        sb.append("LEFT JOIN pay_information ON pay_information.pain_id = order_information.pain_id ");
        sb.append("WHERE order_information.orin_id = ? ");

        String sql = sb.toString();
        Integer sum = 0;
        for (Integer id : ids) {
            int rows = orderInformationDao.batchDelete(sql, id);
            sum += rows;
        }
        return sum;
    }

    /**
     * @param goodsOrders
     * @param orderInformation
     * @return
     */
    public Integer updateOrderInformationAndGoodsOrder(List<GoodsOrder> goodsOrders,
                                                       OrderInformation orderInformation) {
        int rows = goodsOrderDao.batchDynamicUpdate(goodsOrders);
        orderInformationDao.dynamicUpdate(orderInformation);
        return rows;
    }

    /**
     * @param orderInformations
     * @return
     */
    public Integer batchUpdateOrderInformation(
            List<OrderInformation> orderInformations) {
        int rows = orderInformationDao.batchDynamicUpdate(orderInformations);
        return rows;
    }

}
