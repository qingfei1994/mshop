/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.goods.controller;

import com.misuosi.mshop.common.context.RequestContextHolder;
import com.misuosi.mshop.entity.GoodsLabel;
import com.misuosi.mshop.module.common.controller.ErrorController;
import com.misuosi.mshop.service.GoodsLabelService;
import com.misuosi.mshop.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Description		: 商品标签页面的各种操作
 * <p/>
 * <br><br>Time		: 2015-5-26下午12:03:45
 *
 * @author ZXL
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/goods/label")
public class AdminGoodsLabelController {
	
	@Autowired
	private GoodsLabelService goodsLabelService;

	/**
	 * 展示商品标签
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model){
		List<GoodsLabel> list = null;
		
		list = goodsLabelService.getAllGoodsLabels();
		
		model.addAttribute("list", list);
		return "/admin/goods/label_list";
	}

	/**
	 * 批量删除商品标签
	 * @param attr
	 * @param ids
	 * @return
	 */
	@RequestMapping("/batchDelete")
    public String batchDelete(RedirectAttributes attr, Integer[] ids) {
        int rows = goodsLabelService.batchDeleteGoodsLabels(ids);
        if (rows < 1) {
            attr.addFlashAttribute(ErrorController.MESSAGE, "批量删除失败");
            return ErrorController.ERROR;
        }
        return RequestContextHolder.getPagingPath();
    }	

	/**
	 * 显示添加商品标签的模态框
	 * @return
	 */
	@RequestMapping("/addGoodsLabel")
	public String addGoodsLabel() {
		return "/admin/goods/label_edit";
	}
	
	/**
	 * 添加商品标签
	 * @param golaName
	 * @param golaSort
	 * @return
	 */
	@RequestMapping("/add")
	public String add(String golaName, String golaSort) {
		
		Byte golaSortByte = (byte) Integer.parseInt(golaSort);
		
		GoodsLabel goodsLabel = new GoodsLabel();
		goodsLabel.setGolaCreateTime(DateUtils.getCurrentTime());
		goodsLabel.setGolaName(golaName);
		goodsLabel.setGolaSort(golaSortByte);

		goodsLabelService.addGoodsLabel(goodsLabel);

		return "redirect:/admin/goods/label/list";
	}

	/**
	 * 展示修改的模态框
	 * @return
	 */
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(Integer golaId, Model model) {
		GoodsLabel goodsLabel = goodsLabelService.getGoodsLabel(golaId);
		String golaName = goodsLabel.getGolaName();
		Byte golaSort = goodsLabel.getGolaSort();
		model.addAttribute("golaId", golaId);
		model.addAttribute("golaName", golaName);
		model.addAttribute("golaSort", golaSort);
		return "/admin/goods/label_modify";
	}

	/**
	 * 根据商品id删除标签
	 * @param golaId
	 * @return
	 */
	@RequestMapping("/delete")
	public String delete(Integer golaId) {
		goodsLabelService.deleteGoodsLabel(golaId);
		return RequestContextHolder.getPagingPath();
	}
	
	/**
	 * 编辑商品标签
	 * @param golaName
	 * @param golaSort
	 * @param golaId
	 * @return
	 */
	@RequestMapping("/save")
	public String save(String golaName, Byte golaSort, Integer golaId) {
		
		GoodsLabel goodsLabel = new GoodsLabel();
		goodsLabel.setGolaId(golaId);
		goodsLabel.setGolaName(golaName);
		goodsLabel.setGolaSort(golaSort);
		
		goodsLabelService.updateGoodsLabel(goodsLabel);
		
		return "redirect:/admin/goods/label/list";
	}

}
