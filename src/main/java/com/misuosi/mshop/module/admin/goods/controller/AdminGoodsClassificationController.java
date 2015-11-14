/**
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.goods.controller;

import com.misuosi.mshop.entity.GoodsClassification;
import com.misuosi.mshop.module.common.manager.GoodsClassificationManager;
import com.misuosi.mshop.service.GoodsClassificationService;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:商品分组列表的controller
 * 
 * <br>
 * <br>
 * Time:2015-5-6 下午2:50:44
 * 
 * @author qing
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/goods/classification")
public class AdminGoodsClassificationController {
	@Autowired
	private GoodsClassificationService goodsClassificationService;

	@RequestMapping("/list")
	public String group(Model model) {
		List<Map<String, Object>> groups = goodsClassificationService.getAllGoodsClassfications();
		model.addAttribute("groups", groups);
		return "/admin/goods/classification_list";
	}

	@RequestMapping(value = "/{parentGoclId}/add", method = RequestMethod.GET)
	public String addGroup(@PathVariable Integer parentGoclId, Model model) {
		GoodsClassification parent = goodsClassificationService
				.getGoodsClassification(parentGoclId);
		model.addAttribute("flag", "add");
		model.addAttribute("parent", parent);
		GoodsClassificationManager.refresh();
		return "/admin/goods/classification_edit";

	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addGroup(GoodsClassification goodsClassification,
			String[] image) {
		if(image!=null) {
			goodsClassification.setGoclIconUrl(image[0]);
		}
		
		goodsClassificationService.addGoodsClassification(goodsClassification);
		GoodsClassificationManager.refresh();
		return "redirect:/admin/goods/classification/list";
	}

	@RequestMapping(value = "/addparent", method = RequestMethod.GET)
	public String addParentGroup(Model model) {
		model.addAttribute("flag", "add");
		GoodsClassificationManager.refresh();
		return "/admin/goods/classification_edit";

	}

	@RequestMapping("/{goclId}/edit")
	public String editGroup(@PathVariable Integer goclId, Model model) {
		GoodsClassification cassification = goodsClassificationService
				.getGoodsClassification(goclId);
		model.addAttribute("flag", "edit");
		model.addAttribute("classification", cassification);
		GoodsClassificationManager.refresh();
		return "/admin/goods/classification_edit";
	}

	@RequestMapping("/edit")
	public String editGroup(GoodsClassification goodsClassification,
			String[] image) {
		if (image != null) {
			goodsClassification.setGoclIconUrl(image[0]);
		}
		goodsClassificationService
				.updateGoodsClassificationDynamically(goodsClassification);
		GoodsClassificationManager.refresh();
		return "redirect:/admin/goods/classification/list";
	}

	@RequestMapping("/{goclId}/delete")
	public String deleteGroup(@PathVariable Integer goclId) {
		goodsClassificationService.deleteGoodsClassificationDynamically(goclId);
		GoodsClassificationManager.refresh();
		return "redirect:/admin/goods/classification/list";
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, String> saveGroup(String data) {
		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> classifications;
		try {
			classifications = mapper.readValue(data, List.class);
			goodsClassificationService
					.updateSortedClassification(classifications);
			GoodsClassificationManager.refresh();
		} catch (Exception e) {
			e.printStackTrace();
			map.put("fail", "2");
		}

		map.put("success", "1");
		return map;
	}

}
