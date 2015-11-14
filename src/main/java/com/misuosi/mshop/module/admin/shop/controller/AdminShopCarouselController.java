package com.misuosi.mshop.module.admin.shop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.misuosi.mshop.entity.Goods;
import com.misuosi.mshop.entity.GoodsClassification;
import com.misuosi.mshop.entity.GoodsClassificationGoods;
import com.misuosi.mshop.entity.IndexBanner;
import com.misuosi.mshop.module.admin.shop.service.AdminShopCarouselService;
import com.misuosi.mshop.module.common.controller.ErrorController;
import com.misuosi.mshop.module.common.manager.GoodsClassificationManager;
import com.misuosi.mshop.service.GoodsService;
import com.misuosi.mshop.service.IndexBannerService;

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
@RequestMapping("/admin/shop/carousel")
public class AdminShopCarouselController {
	@Autowired
	private AdminShopCarouselService adminShopCarouselService;
	@Autowired
	private IndexBannerService indexBannerService;
	@Autowired
	private GoodsService goodsService;
	
	/**
	 * 跳转到轮播主页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		//List<IndexBanner> list = indexBannerService.getAllIndexBanners();
		List<Map<String,Object>> list=adminShopCarouselService.getIndexBannerInformation();
		model.addAttribute("list", list);
		return "/admin/shop/carousel_list";
	}
	
	@RequestMapping("/addpic")
	public String addPic(Integer count,RedirectAttributes attr) {
			if(count>=10) {
				  attr.addFlashAttribute(ErrorController.MESSAGE, "幻灯片不能超过10张");
				  return ErrorController.ERROR;
			} else {
				IndexBanner indexBanner =new IndexBanner();
				indexBanner.setInbaImageUrl("http://dummyimage.com/640x320/ddd/555");
				int row=indexBannerService.addIndexBanner(indexBanner);
				if(row>0) {
					return "redirect:list";
				} else {
					  attr.addFlashAttribute(ErrorController.MESSAGE, "新增图片失败");
					  return ErrorController.ERROR;
				}
			}
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
		return "redirect:/admin/shop/carousel/list";
	}
	
	/**
	 * 点击选择链接
	 * @return list
	 */
	@RequestMapping("/goodsInformation/list")
	public String getShopInformation(Model model,Integer inbaId,Integer goodId){
		//查询商品内容，以及显示下拉列表框
		//取得所有商品
		List<GoodsClassification> goodsClassifications = GoodsClassificationManager.getGoodsClassifications();
        List<Goods> list = goodsService.getGoodsTree();
      /*  for (Goods goods : list) {
            List<GoodsClassificationGoods> goodsClassificationGoodses = goods.getGoodsClassificationGoodses();
            if (goodsClassificationGoodses == null) {
                continue;
            }
            List<String> goclNameSets = new ArrayList<String>();
            for (GoodsClassificationGoods goodsClassificationGoods : goodsClassificationGoodses) {
                goclNameSets.add(GoodsClassificationManager.getGoodsClassificationNameSet(
                        goodsClassificationGoods.getGoclId()));
            }
            goods.setGoclNameSets(goclNameSets);
        }
*/
        model.addAttribute("list", list);
		model.addAttribute("goodsClassifications", goodsClassifications);
		model.addAttribute("inbaId", inbaId);
		model.addAttribute("goodId",goodId);
		
		return "/admin/shop/carousel_edit";
	}
	
	/**
	 * 点击保存按钮
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/save")
	public String save(String data,RedirectAttributes attr){
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> banners;
		try {
			banners = mapper.readValue(data, List.class);
			adminShopCarouselService.updateIndexBanner(banners);
		} catch (Exception e) {
			e.printStackTrace();
			  attr.addFlashAttribute(ErrorController.MESSAGE, "保存图片失败");
			  return ErrorController.ERROR;
		
			
		}
		return "redirect:list";
	}
}
