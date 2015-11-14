/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.common.controller;

import com.misuosi.mshop.entity.*;
import com.misuosi.mshop.module.admin.orders.service.AdminOrderShipmentsService;
import com.misuosi.mshop.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description		:
 * <p/>
 * <br><br>Time		: 2015年6月18日 下午2:39:36
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/common/waybill")
public class WaybillTemplateController {

    @Autowired
    AdminOrderShipmentsService adminOrderShipmentsService;
    @Autowired
    private GoodsOrderService goodsOrderService;
    @Autowired
    private ShopInformationService shopInformationService;
    @Autowired
    private ExpressCompanyService expressCompanyService;
    @Autowired
    private AddressesService addressesService;
    @Autowired
    private WaybillTemplateService waybillTemplateService;

    /**
     * 打印配送单
     *
     * @param ids
     * @param model
     * @return
     */
    @RequestMapping(value = "/printDistribution", method = RequestMethod.POST)
    public String printDistribution(Integer[] ids, Model model) {
        List<Integer> orinIds = new ArrayList<Integer>();
        for (Integer id : ids) {
            orinIds.add(id);
        }

        List<Map<String, Object>> entitys = adminOrderShipmentsService.getShipmentsInformationByOrinIds(orinIds);
        List<GoodsOrder> goodsOrders = goodsOrderService.getGoodsOrderByOrinIds(orinIds);

        ShopInformation shopInformation = shopInformationService.getShopInformation();

        model.addAttribute("entitys", entitys);
        model.addAttribute("goodsOrders", goodsOrders);
        model.addAttribute("shopInformation", shopInformation);
        return "/admin/orders/print_distribution";
    }

    /**
     * 打印快递单
     *
     * @param ids       订单id列表
     * @param expressId 快递id
     * @param address   发货地址Id
     * @param model
     * @return
     */
    @RequestMapping(value = "/printExpress")
    public String printExpress(Integer[] ids, Integer expressId, Integer address, Model model) {
        List<Integer> orinIds = new ArrayList<Integer>();
        for (Integer id : ids) {
            orinIds.add(id);
        }

        //获得快递列表
        List<ExpressCompany> expressCompanies = expressCompanyService.getAllExpressCompanys();
        //获得发货地址列表
        List<Map<String, Object>> addresses = addressesService.getAddresses();

        //获得订单信息列表
        List<Map<String, Object>> entitys = adminOrderShipmentsService.getShipmentsInformationByOrinIds(orinIds);

        WaybillTemplate waybillTemplate = null;
        if (expressId != null) {
            waybillTemplate = waybillTemplateService.getAllWaybillTemplatesByExcoId(expressId);

            model.addAttribute("expressId", expressId);
        }

        Map<String, Object> targetAddr = null;
        if (address != null) {
            targetAddr = addressesService.getAddressesMap(address);
            model.addAttribute("addressId", address);
        }

        List<WaybillTemplate> waybillTemplates = this.covertWaybillTemplate(waybillTemplate, entitys, targetAddr);

        model.addAttribute("orinIds", orinIds);
        model.addAttribute("expressCompanies", expressCompanies);
        model.addAttribute("addresses", addresses);
        model.addAttribute("waybillTemplates", waybillTemplates);
        return "/admin/orders/print_express";
    }

    private List<WaybillTemplate> covertWaybillTemplate(
            WaybillTemplate waybillTemplate, List<Map<String, Object>> shipInformations, Map<String, Object> address) {
        List<WaybillTemplate> waybillTemplates = new ArrayList<WaybillTemplate>();
        if (waybillTemplate == null || shipInformations == null || address == null) {
            return waybillTemplates;
        }

        for (Map<String, Object> map : shipInformations) {
            WaybillTemplate template = waybillTemplate;

            String content = template.getWateContent();
            content = content.replaceAll("订单编号", (String) map.get("orin_no"));
            content = content.replaceAll("发件人姓名", (String) address.get("addr_contacts"));
            content = content.replaceAll("发件人电话", (String) address.get("addr_phone"));
            content = content.replaceAll("发件人公司", (String) address.get("addr_company_name"));
            content = content.replaceAll("发件人地址", (String) address.get("province_regi_name") + (String) address.get("city_regi_name") +
                    (String) address.get("county_regi_name") + (String) address.get("addr_detailed_address"));
            content = content.replaceAll("发件人邮编", "");

            content = content.replaceAll("收件人姓名", (String) map.get("coad_name"));
            content = content.replaceAll("收件人电话", (String) map.get("coad_phone"));
            content = content.replaceAll("收件人地址", (String) map.get("prov_regi_name") + (String) map.get("city_regi_name") +
                    (String) map.get("coun_regi_name") + (String) map.get("coad_detailed_address"));
            content = content.replaceAll("收件人邮编", "");

            content = content.replaceAll("代收金额", "");
            content = content.replaceAll("货到付款物流编号", "");
            System.out.println(content);
            template.setWateContent(content);
            waybillTemplates.add(template);
        }

        return waybillTemplates;
    }

}
