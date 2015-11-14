/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.wap.usercenter.controller;

import com.misuosi.mshop.common.context.SessionContextHolder;
import com.misuosi.mshop.db.page.Pagination;
import com.misuosi.mshop.entity.CollectGoods;
import com.misuosi.mshop.entity.ShoppingCart;
import com.misuosi.mshop.entity.WechatInformation;
import com.misuosi.mshop.module.wap.usercenter.service.WapCollectService;
import com.misuosi.mshop.service.CollectGoodsService;
import com.misuosi.mshop.service.ShoppingCartService;
import com.misuosi.mshop.service.WechatInformationService;
import com.misuosi.mshop.util.DateUtils;
import com.misuosi.mshop.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

/**
 * Description		: 微信端我的收藏页面的controller
 * <p/>
 * <br><br>Time		: 2015-6-8 下午4:08:12
 *
 * @author YLM
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/wap/usercenter/collect")
public class WapCollectController {

	@Autowired
	private CollectGoodsService collectGoodsService;
	
	@Autowired
	private WapCollectService wapCollectService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private WechatInformationService wechatInformationService;

	/**
	 * 跳转到我的收藏页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String collect(Model model) {
		return "/wap/usercenter/collect/collect";
	}
	
	/**
	 * 获取收藏列表数据
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/collectData", method = RequestMethod.GET)
	@ResponseBody
	public Pagination<Map<String, Object>> collectData(Integer pageNo, Integer pageSize) {
		return wapCollectService.getCollectList(pageNo, pageSize);
	}
	
	/**
	 * 删除收藏
	 * 
	 * @param cogoId
	 * @return
	 */
	@RequestMapping(value="/{cogoId}/delete", method = RequestMethod.GET)
	public String deleteCollect(@PathVariable Integer cogoId) {
		collectGoodsService.deleteCollectGoods(cogoId);
		return "redirect:/wap/usercenter/collect";
	}
	
	/**
	 * 显示加入购物车的对话框
	 * 
	 * @param goodId
	 * @return
	 */
	@RequestMapping(value="/{goodId}/add2shopcart", method = RequestMethod.GET)
	public String add2shopcart(Model model, @PathVariable Integer goodId) {
		WechatInformation wechatInformation = wechatInformationService.getWechatInformation(SessionContextHolder.getWechatInformationId());
		Map<String, Object> goods = wapCollectService.getGoodsInfoBygoodId(goodId);
		BigDecimal goodPrice = (BigDecimal)goods.get("good_price");
		model.addAttribute("price", goodPrice);
		model.addAttribute("stock", goods.get("gpst_stock"));
		model.addAttribute("gpstId", goods.get("gpst_id"));
		return "wap/usercenter/collect/add2shopcart_dialog";
	}
	
	/**
	 * 加入购物车
	 * 
	 * @param shoppingCart
	 * @return
	 */
	@RequestMapping(value="/add2shopcart", method = RequestMethod.POST)
	@ResponseBody
	public String add2shopcart(ShoppingCart shoppingCart) {
		Timestamp now = DateUtils.getCurrentTime();
        Integer weinId = SessionContextHolder.getWechatInformationId();
        
        ShoppingCart oldShoppingCart = shoppingCartService.getShoppingCartByGpstIdAndWeinId(shoppingCart.getGpstId(), weinId);
        if (oldShoppingCart != null) {
            int oldCount = StringUtils.intValue(oldShoppingCart.getShcaCount(), 0);
            int count = StringUtils.intValue(shoppingCart.getShcaCount(), 0);
            oldShoppingCart.setShcaCount(oldCount + count);
            shoppingCartService.updateShoppingCart(oldShoppingCart);
        } else {
            shoppingCart.setWeinId(weinId);
            shoppingCart.setShcaCreateTime(now);
            shoppingCartService.addShoppingCart(shoppingCart);
        }
		return "success";
	}

	/**
	 * 收藏或取消收藏
	 * @param goodId
	 * @return
	 */
	@RequestMapping(value = "/{goodId}", method = RequestMethod.GET)
	@ResponseBody
	public boolean collectOrCancel(@PathVariable("goodId") Integer goodId) {
		if(goodId == null) {
			return false;
		}

		int weinId = SessionContextHolder.getWechatInformationId();
		CollectGoods collectGoods = new CollectGoods();
		collectGoods.setGoodId(goodId);
		collectGoods.setWeinId(weinId);
		try {
			collectGoodsService.addCollectGoodsOrCancel(collectGoods);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}