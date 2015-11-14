package com.misuosi.mshop.module.wap.shop.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.misuosi.mshop.entity.GoodsClassification;
import com.misuosi.mshop.entity.IndexBanner;
import com.misuosi.mshop.module.common.manager.GoodsClassificationManager;
import com.misuosi.mshop.module.wap.shop.service.WapShopCarouselService;
import com.misuosi.mshop.service.IndexBannerService;
import com.misuosi.mshop.util.DateUtils;

/**
 * Description : 前台首页轮播图片的controller
 * <p/>
 * <br>
 * <br>
 * Time : 2015-7-28 下午14:34
 * 
 * @author baymax
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/wap/shop/carousel")
public class WapShopCarouselController {
	@Autowired
	private WapShopCarouselService wapShopCarouselService;
	@Autowired
	private IndexBannerService indexBannerService;
	/**
	 * 跳转到轮播主页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		List<IndexBanner> list = indexBannerService.getAllIndexBanners();
		model.addAttribute("list", list);
		return "wap/shop/carousel_list";
	}


	
	/**
	 * 点击"x"按钮删除
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(Model model,Integer inbaId){
		indexBannerService.deleteIndexBanner(inbaId);
		return "redirect:/wap/shop/carousel/list";
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
	
	/**
	 * 点击保存按钮
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/save")
	public String save(){
		DateUtils.getCurrentTime();
		return null ;
	}
}
