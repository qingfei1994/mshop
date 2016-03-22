package com.misuosi.mshop.module.admin.shop.controller;

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
import com.misuosi.mshop.entity.IndexGoods;
import com.misuosi.mshop.entity.IndexGroup;
import com.misuosi.mshop.module.admin.shop.service.AdminShopShowService;
import com.misuosi.mshop.module.common.controller.ErrorController;
import com.misuosi.mshop.module.common.manager.GoodsClassificationManager;
import com.misuosi.mshop.service.GoodsService;
import com.misuosi.mshop.service.IndexGoodsService;
import com.misuosi.mshop.service.IndexGroupService;
import com.misuosi.mshop.util.DateUtils;

/**
 * Description : 前台首页商品展示的controller
 * <p/>
 * <br>
 * <br>
 * Time : 2015-7-28 下午14:35
 * 
 * @author baymax
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/shop/show")
public class AdminShopShowController {
	@Autowired
	private AdminShopShowService adminShopShowService;
	@Autowired
	private IndexGoodsService indexGoodsService;
	@Autowired
	private IndexGroupService indexGroupService;
	@Autowired
	private GoodsService goodsService;

	/**
	 * 跳转到商品展示主页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		List<IndexGroup> groups = adminShopShowService.getIndexGroups();
		model.addAttribute("groups", groups);
		return "admin/shop/show_list";
	}

	/**
	 * 点击选择链接
	 * 
	 * @param
	 * @return list
	 */
	@RequestMapping("/goodsInformation/list")
	public String getShopInformation(Model model, Integer groupId,
			Integer ingoId) {
		// 查询商品内容，以及显示下拉列表框
		// 取得所有商品
		List<GoodsClassification> goodsClassifications = GoodsClassificationManager
				.getGoodsClassifications();
		// 取得所需商品的id,name,url,price
		/*
		 * List<Map<String,Object>> goods =
		 * adminShopShowService.getGoodsInformation();
		 */
		model.addAttribute("goodsClassifications", goodsClassifications);
		List<Goods> goods = goodsService.getGoodsTree();
		model.addAttribute("goods", goods);
		if (ingoId != null) {
			IndexGoods indexGoods = indexGoodsService.getIndexGoods(ingoId);
			if (indexGoods != null) {
				model.addAttribute("selectedId", indexGoods.getGoodId());

				model.addAttribute("ingoId", ingoId);
			}
		}
		model.addAttribute("selectedGroupId", groupId);
		return "admin/shop/select_shop";
	}

	@RequestMapping("/addIndexGood")
	public String saveIndexGoods(IndexGoods indexGoods, RedirectAttributes attr) {
		Integer ingoId = indexGoods.getIngoId();
		int row = 0;
		if (ingoId == null) {
			row = indexGoodsService.addIndexGoods(indexGoods);

		} else {
			row = indexGoodsService.updateIndexGoods(indexGoods);
		}
		if (row > 0) {
			return "redirect:list";
		} else {
			attr.addAttribute(ErrorController.MESSAGE, "添加失败！");
			return ErrorController.ERROR;
		}
	}

	/**
	 * 删除商品展示图片
	 * 
	 * @param
	 * @return list
	 */
	@RequestMapping("/deleteChild")
	public String delete(Model model, Integer ingoId) {
		indexGoodsService.deleteIndexGoods(ingoId);
		return "redirect:/admin/shop/show/list";
	}

	/**
	 * 删除分组内的所有图片
	 * 
	 * @param
	 * @return list
	 */
	@RequestMapping("/deleteParent")
	public String deleteParent(Model model, Integer ingrId) {
		adminShopShowService.deleteParent(ingrId);
		indexGroupService.deleteIndexGroup(ingrId);
		return "redirect:/admin/shop/show/list";
	}

	/**
	 * 点击右上角"新增分组"按钮
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/addparent")
	public String addParentGroup(Model model) {
		model.addAttribute("flag", "add");
		return "/admin/shop/classification_edit";
	}

	/**
	 * 点击"铅笔"图标，编辑分组
	 * 
	 * @param goclId
	 * @param model
	 * @return
	 */
	@RequestMapping("/editParent")
	public String editGroup(Integer ingrId, Model model) {
		IndexGroup group = indexGroupService.getIndexGroup(ingrId);
		model.addAttribute("flag", "edit");
		model.addAttribute("group", group);
		return "/admin/shop/classification_edit";
	}

	/**
	 * 点击moadl确认按钮，完成添加
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model, IndexGroup indexGroup,String[] image) {
		indexGroup.setIngrCreateTime(DateUtils.getCurrentTime());
		indexGroup.setIngrModifyTime(DateUtils.getCurrentTime());
		if (image != null) {
			indexGroup.setIngrIconUrl(image[0]);
		}
		indexGroupService.addIndexGroup(indexGroup);
		return "redirect:/admin/shop/show/list";

	}

	/**
	 * 点击moadl确认按钮，完成编辑
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit(Model model, IndexGroup indexGroup,String[] image) {
		if (image != null) {
			indexGroup.setIngrIconUrl(image[0]);
		}
		indexGroup.setIngrModifyTime(DateUtils.getCurrentTime());
		indexGroupService.updateIndexGroup(indexGroup);
		return "redirect:/admin/shop/show/list";

	}

	/**
	 * 点击保存按钮
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping("/save")
	public String save(String data, RedirectAttributes attr) {
		ObjectMapper mapper = new ObjectMapper();
		List<Map<String, Object>> classification;
		try {
			classification = mapper.readValue(data, List.class);
			adminShopShowService.updateIndexGroupSort(classification);
		} catch (Exception e) {
			e.printStackTrace();
			attr.addAttribute(ErrorController.MESSAGE, "更新失败！");
			return ErrorController.ERROR;
		}
		return "redirect:list";
	}
}
