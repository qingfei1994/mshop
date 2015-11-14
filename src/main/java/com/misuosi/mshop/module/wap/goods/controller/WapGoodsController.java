/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.wap.goods.controller;

import com.misuosi.mshop.common.context.SessionContextHolder;
import com.misuosi.mshop.db.page.Pagination;
import com.misuosi.mshop.entity.GoodsClassification;
import com.misuosi.mshop.entity.GoodsPicture;
import com.misuosi.mshop.entity.ShoppingCart;
import com.misuosi.mshop.entity.WechatInformation;
import com.misuosi.mshop.module.common.manager.GoodsClassificationManager;
import com.misuosi.mshop.module.common.manager.TransportationExpensesManager;
import com.misuosi.mshop.module.wap.goods.service.WapGoodsService;
import com.misuosi.mshop.pojo.TransportationExpensesResult;
import com.misuosi.mshop.service.GoodsPictureService;
import com.misuosi.mshop.service.ShoppingCartService;
import com.misuosi.mshop.service.WechatInformationService;
import com.misuosi.mshop.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Description	 :商品controller
 * <br><br>Time	 : 2015/6/9 9:39
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/wap/goods")
public class WapGoodsController {

    @Autowired
    private WapGoodsService wapGoodsService;
    @Autowired
    private WechatInformationService wechatInformationService;
    @Autowired
    private GoodsPictureService goodsPictureService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 商品列表页
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<GoodsClassification> goodsClassificationList =
                GoodsClassificationManager.getGoodsClassifications();
        model.addAttribute("goodsClassificationList", goodsClassificationList);

        return "/wap/goods/goods_list";
    }

    /**
     * 商品详情
     * @return
     */
    @RequestMapping(value = "/{goodId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("goodId")Integer goodId, Model model) {
        // 微信Id
        Integer wechatInformationId = SessionContextHolder.getWechatInformationId();
        WechatInformation wechatInformation = wechatInformationService
                .getWechatInformation(wechatInformationId);
        model.addAttribute("weinId", wechatInformationId);

        //获取
        Map<String, Object> goodsDetail = wapGoodsService.getGoodsDetail(goodId, wechatInformationId);
        Double goodPrice = Double.parseDouble(String.valueOf(goodsDetail.get("good_price")));
        goodsDetail.put("good_real_price", goodPrice);
        model.addAttribute("goodsDetail", goodsDetail);

        //计算快递费用
        TransportationExpensesResult transportationExpensesResult = TransportationExpensesManager
                .calculateTransportationExpenses(goodId, (Integer) goodsDetail.get("gpst_id"), 1, null);
        model.addAttribute("transportationExpensesResult", transportationExpensesResult);

        List<GoodsPicture> goodsPictureList = goodsPictureService.getGoodsPicturesByGoodId(goodId);
        model.addAttribute("goodsPictureList", goodsPictureList);

        List<ShoppingCart> shoppingCartList = shoppingCartService.getShoppingCartsByWeinId(wechatInformationId);
        int shoppingCartCount = 0;
        for (ShoppingCart shoppingCart : shoppingCartList) {
            shoppingCartCount += shoppingCart.getShcaCount();
        }
        model.addAttribute("shoppingCartCount", shoppingCartCount);

        return "/wap/goods/goods_detail";
    }

    /**
     * 获取商品列表数据（分页）
     * @param goclId 商品分类（为空则为所有商品）
     * @param orderStr 排序字段（可为空）
     * @param orderType 排序类型（可为空， “ASC”， “DESC”）
     * @return
     */
    @RequestMapping("/list/data")
    @ResponseBody
    public Pagination<Map<String, Object>> getData(
            Integer goclId, String orderStr, String orderType,
            Integer pageNo, Integer pageSize) {
        if (StringUtils.isBlank(orderStr)) {
            orderStr = null;
        }
        if (StringUtils.isBlank(orderType)) {
            orderType = null;
        }

        Integer wechatInformationId = SessionContextHolder.getWechatInformationId();
        WechatInformation wechatInformation = wechatInformationService
                .getWechatInformation(wechatInformationId);

        Pagination<Map<String, Object>> goodsPaginationList = wapGoodsService.getGoodsList(
                wechatInformationId, goclId, orderStr, orderType, pageNo, pageSize);

        return goodsPaginationList;
    }

}
