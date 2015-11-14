/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.wap.payment.service;

import com.github.cuter44.wxpay.WxpayFactory;
import com.github.cuter44.wxpay.WxpayNotifyListener;
import com.github.cuter44.wxpay.constants.TradeType;
import com.github.cuter44.wxpay.reqs.GetBrandWCPayRequest;
import com.github.cuter44.wxpay.reqs.UnifiedOrder;
import com.github.cuter44.wxpay.resps.Notify;
import com.github.cuter44.wxpay.resps.UnifiedOrderResponse;
import com.github.cuter44.wxpay.servlet.WxpayNotifyGatewayServlet;
import com.misuosi.mshop.common.constants.OrderConstants;
import com.misuosi.mshop.common.context.SessionContextHolder;
import com.misuosi.mshop.db.dao.Dao;
import com.misuosi.mshop.entity.GoodsOrder;
import com.misuosi.mshop.entity.OrderInformation;
import com.misuosi.mshop.entity.PayInformation;
import com.misuosi.mshop.entity.WechatInformation;
import com.misuosi.mshop.service.OrderInformationService;
import com.misuosi.mshop.util.DateUtils;
import com.misuosi.mshop.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Description		:
 * <p/>
 * <br><br>Time		: 2015/7/20 0020 17:50
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
@Service
public class WapPaymentService {

    @Resource(name = "wechatInformationDao")
    private Dao<WechatInformation> wechatInformationDao;
    @Resource(name = "payInformationDao")
    private Dao<PayInformation> payInformationDao;
    @Resource(name = "orderInformationDao")
    private Dao<OrderInformation> orderInformationDao;

    @Autowired
    private OrderInformationService orderInformationService;

    /**
     * 获取jsApi支付请求参数
     *
     * @param orderInformation
     * @param ip
     * @return
     */
    public String getPayParameter(OrderInformation orderInformation, String ip) {
        String[] temp = ip.split(":");
        if (temp.length > 4) { // 如果是IPv6，直接写死成IPv4的127.0.0.1
            ip = "127.0.0.1";
        } else {
            temp = ip.split(".");
            if (temp.length > 4) { // 如果IP不只一个，写死成127.0.0.1
                ip = "127.0.0.1";
            }
        }

        String jsonGbwxpr = null;
        Integer weinId = SessionContextHolder.getWechatInformationId();
        WechatInformation wechatInformation = wechatInformationDao.get(weinId);

        List<GoodsOrder> goodsOrders = orderInformation.getGoodsOrders();
        String subject = "";
        if (goodsOrders != null) {
            for (GoodsOrder goodsOrder : goodsOrders) {
                subject += goodsOrder.getGoorName() + "|";
            }
        }
        if (subject.length() > 127) {
            subject = subject.substring(0, 124).concat("...");
        }

        WxpayFactory wxpayFactory = WxpayFactory.getDefaultInstance();
        try {
            UnifiedOrder order = wxpayFactory.newUnifiedOrder()
                    .setBody(subject)
                    .setTotalFee(orderInformation.getOrinTotal())
                    .setOpenid(wechatInformation.getWeinOpenid())
                    .setOutTradeNo(orderInformation.getOrinNo())
                    .setSpbillCreateIp(ip)
                    .setTradeType(TradeType.JSAPI)
                    .build()
                    .sign();

            UnifiedOrderResponse orderResp = order.execute();

            GetBrandWCPayRequest gbwxpr = wxpayFactory.newGetBrandWCPayRequest(orderResp.getProperties())
                    .build()
                    .sign();
            jsonGbwxpr = gbwxpr.toJSON();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsonGbwxpr;
    }

    /**
     * 添加支付回调监听器
     *
     * @param notifyGatewayServlet
     */
    public void addNotifyListener(WxpayNotifyGatewayServlet notifyGatewayServlet) {
        notifyGatewayServlet.addListener(new WxpayNotifyListener() {
            public boolean handle(Notify n) {
                try {
                    if (n.isResultCodeSuccess()) {
                        Timestamp now = new Timestamp(System.currentTimeMillis());

                        String orinNo = n.getOutTradeNo();
                        String orinTotal = n.getProperty("total_fee");
                        String painPayNo = n.getProperty("transaction_id");
                        double payMoney = StringUtils.doubleValue(orinTotal, 0) / 100;

                        OrderInformation orderInformation = orderInformationService.getOrderInformationByOrinNo(orinNo);
                        if (orderInformation != null) {
                            if (orderInformation.getOrinPayStatus() == OrderConstants.ORIN_PAY_STATUS_PAID) {
                                return true; // 重复通知
                            }

                            if (payMoney < orderInformation.getOrinTotal()) {
                                throw new RuntimeException("Did not pay enough money! ["
                                        + payMoney + " < " + orderInformation.getOrinTotal() + "]");
                            }

                            PayInformation payInformation = new PayInformation();
                            String timeEnd = n.getProperty("time_end");
                            payInformation.setPainPayTime(DateUtils.toTime("yyyyMMddHHmmss", timeEnd));
                            payInformation.setPainPayNo(painPayNo);
                            payInformation.setPainSerialNumber(orinNo);
                            payInformation.setPainPayMoney(payMoney);
                            payInformation.setPainPayWay(OrderConstants.ORIN_PAY_WAY_WEIXIN);
                            payInformationDao.save(payInformation);

                            orderInformation.setOrinPayStatus(OrderConstants.ORIN_PAY_STATUS_PAID);
                            orderInformation.setOrinPayWay(OrderConstants.ORIN_PAY_WAY_WEIXIN);
                            orderInformation.setPainId(payInformation.getPainId());
                            orderInformation.setOrinModifyTime(now);
                            orderInformationDao.update(orderInformation);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        });
    }

}
