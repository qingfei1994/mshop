package com.misuosi.mshop.module.wap.shop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.misuosi.mshop.entity.GoodsClassification;
import com.misuosi.mshop.module.common.manager.GoodsClassificationManager;
import com.misuosi.mshop.module.wap.shop.service.WapShopCarouselService;

/**
 * Description		: 前台首页商品展示的controller
 * <p/>
 * <br><br>Time		: 2015-7-28 下午14:35
 *
 * @author baymax
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/wap/shop/show")
public class WapShopShowController {
	@Autowired
	private WapShopCarouselService wapShopCarouselService;
	/**
	 * 跳转到商品展示主页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model){
		return "/wap/shop/show_list";
	}
	
	/**
	 * 点击选择链接
	 * @return list
	 */
	@RequestMapping("/goodsInformation/list")
	public String getShopInformation(Model model){
		//查询商品内容，以及显示下拉列表框
		//取得所有商品
		List<GoodsClassification> goodsClassifications = GoodsClassificationManager.getGoodsClassifications();
		//取得所需商品的id,name,url,price
		List<Map<String,Object>> goods = wapShopCarouselService.getGoodsInformation();
		model.addAttribute("goodsClassifications", goodsClassifications);
		model.addAttribute("goods", goods);
		return "wap/shop/select_shop";
	}
}
