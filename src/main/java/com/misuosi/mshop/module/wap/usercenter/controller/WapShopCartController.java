/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.wap.usercenter.controller;

import com.misuosi.mshop.common.context.SessionContextHolder;
import com.misuosi.mshop.entity.ShoppingCart;
import com.misuosi.mshop.module.wap.usercenter.service.WapShopCartService;
import com.misuosi.mshop.pojo.ShoppingCartItem;
import com.misuosi.mshop.service.ShoppingCartService;
import com.misuosi.mshop.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

/**
 * Description	 : 购物车controller
 * <br><br>Time	 : 2015/6/9 9:50
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/wap/usercenter/shopcart")
public class WapShopCartController {

    private static Logger log = LoggerFactory.getLogger(WapShopCartController.class);

    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private WapShopCartService wapShopCartService;

    /**
     * 加入购物车
     *
     * @param shoppingCart
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Boolean add(ShoppingCart shoppingCart) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Integer weinId = SessionContextHolder.getWechatInformationId();

        ShoppingCart oldShoppingCart = shoppingCartService
                .getShoppingCartByGpstIdAndWeinId(shoppingCart.getGpstId(), weinId);

        Boolean result = false;

        if (oldShoppingCart != null) { // 更新购数车中已有的商品
            int oldCount = StringUtils.intValue(oldShoppingCart.getShcaCount(), 0);
            int count = StringUtils.intValue(shoppingCart.getShcaCount(), 0);
            oldShoppingCart.setShcaCount(oldCount + count);
            oldShoppingCart.setShcaModifyTime(now);
            int updateCount = shoppingCartService.updateShoppingCart(oldShoppingCart);
            if (updateCount > 0) {
                result = true;
            }
        } else { // 新增商品到购物车
            shoppingCart.setWeinId(weinId);
            shoppingCart.setShcaModifyTime(now);
            shoppingCart.setShcaCreateTime(now);
            int addCount = shoppingCartService.addShoppingCart(shoppingCart);
            if (addCount > 0) {
                result = true;
            }
        }

        return result;
    }

    /**
     * 删除购物车内容
     *
     * @param shcaIds
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Boolean delete(Integer[] shcaIds) {
        try {
            shoppingCartService.batchDeleteShoppingCarts(shcaIds);
            return true;
        } catch (Exception e) {
            log.error("WXShopCartController.delete throws Exception", e);
            return false;
        }
    }

    /**
     * 跳转到购物车列表页
     *
     * @param model
     * @return
     */
    @RequestMapping
    public String shopcart(Model model) {
        Integer weinId = SessionContextHolder.getWechatInformationId();
        List<ShoppingCartItem> shoppingCartItemList = wapShopCartService.getShoppingCartItemsByWeinId(weinId);
        model.addAttribute("shoppingCartItemList", shoppingCartItemList);
        return "/wap/usercenter/shopcart/shop_cart";
    }

}
