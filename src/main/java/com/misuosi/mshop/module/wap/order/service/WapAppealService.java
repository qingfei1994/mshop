/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.wap.order.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.misuosi.mshop.common.constants.OrderConstants;
import com.misuosi.mshop.entity.ClientAppeal;
import com.misuosi.mshop.entity.GoodsOrder;
import com.misuosi.mshop.entity.OrderInformation;
import com.misuosi.mshop.entity.PayInformation;
import com.misuosi.mshop.service.ClientAppealService;
import com.misuosi.mshop.service.GoodsOrderService;
import com.misuosi.mshop.service.OrderInformationService;
import com.misuosi.mshop.service.PayInformationService;

/**
 * Description		:
 * <p/>
 * <br><br>Time		: 2015/7/25 0025 10:06
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
@Service
public class WapAppealService {

    @Autowired
    private GoodsOrderService goodsOrderService;
    @Autowired
    private ClientAppealService clientAppealService;
    @Autowired
    private OrderInformationService orderInformationService;
    @Autowired
    private PayInformationService payInformationService;
    /**
     * 申请维权
     *
     * @param goorId
     * @param clapReason
     */
    public void apply(Integer goorId, String clapReason) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        GoodsOrder goodsOrder = goodsOrderService.getGoodsOrder(goorId);
        ClientAppeal clientAppeal = clientAppealService.getClientAppealByGoorId(goorId);
        OrderInformation orderInfo=orderInformationService.getOrderInformation(goodsOrder.getOrinId());
        PayInformation payInfo=payInformationService.getPayInformation(orderInfo.getPainId());
        if (clientAppeal == null) {
            clientAppeal = new ClientAppeal();
            clientAppeal.setGoorId(goorId);
            clientAppeal.setClapReason(clapReason);
            clientAppeal.setClapMoney(goodsOrder.getGoorRealityPay());
            clientAppeal.setClapApplicationTime(now);
            clientAppeal.setClapFlowStatus(OrderConstants.CLAP_FLOW_STATUS_PENDING);
            clientAppeal.setClapType(OrderConstants.CLAP_TYPE_RETURNED_PURCHASE);
            clientAppeal.setClapPayWay(payInfo.getPainPayWay());
            clientAppealService.addClientAppeal(clientAppeal);
        } else {
            clientAppeal.setClapReason(clapReason);
            clientAppealService.updateClientAppeal(clientAppeal);
        }

        goodsOrder.setGoorRefundStatus(OrderConstants.GOOR_REFUND_STATUS_YES);
        goodsOrderService.updateGoodsOrder(goodsOrder);
    }
    


}
