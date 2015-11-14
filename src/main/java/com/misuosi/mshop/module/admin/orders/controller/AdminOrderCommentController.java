/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.orders.controller;

import com.misuosi.mshop.common.constants.OrderConstants;
import com.misuosi.mshop.common.context.RequestContextHolder;
import com.misuosi.mshop.entity.GoodsComment;
import com.misuosi.mshop.module.admin.orders.service.AdminOrderCommentService;
import com.misuosi.mshop.service.GoodsCommentService;
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
 * Description : 订单评论管理Controller.
 * <p/>
 * <br>
 * <br>
 * Time : 2015年6月8日 下午6:07:35
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 * 
 */
@Controller
@RequestMapping(value = "/admin/order/comment")
public class AdminOrderCommentController {

	@Autowired
	private AdminOrderCommentService adminOrderCommentService;

	@Autowired
	private GoodsCommentService goodsCommentService;

	/**
	 * 评论列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		List<Map<String, Object>> entitys = adminOrderCommentService
				.getGoodsCommentMap();
		model.addAttribute("entitys", entitys);
		return "/admin/orders/comment_list";
	}

	/**
	 * 删除
	 * 
	 * @param gocoId
	 * @return
	 */
	@RequestMapping(value = "/delete/{gocoId}", method = RequestMethod.GET)
	public String delete(@PathVariable("gocoId") Integer gocoId) {
		goodsCommentService.deleteGoodsComment(gocoId);
		return RequestContextHolder.getPagingPath();
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/batchDelete", method = RequestMethod.GET)
	public String batchDelete(Integer[] ids) {
		goodsCommentService.batchDeleteGoodsComments(ids);
		return RequestContextHolder.getPagingPath();
	}

	/**
	 * 评论回复modal
	 * 
	 * @param gocoId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toReply/{gocoId}", method = RequestMethod.GET)
	public String toReply(@PathVariable("gocoId") Integer gocoId, Model model) {
		GoodsComment goodsComment = goodsCommentService.getGoodsComment(gocoId);
		model.addAttribute("gocoId", gocoId);
		model.addAttribute("goodsComment", goodsComment);
		return "/admin/orders/comment_reply";
	}

	/**
	 * 评论回复.
	 * 
	 * @param gocoId
	 * @param gocoReplyContent
	 * @return
	 */
	@RequestMapping(value = "/reply", method = RequestMethod.POST)
	public String reply(Integer gocoId, String gocoReplyContent) {
		GoodsComment goodsComment = new GoodsComment();
		goodsComment.setGocoId(gocoId);
		goodsComment.setGocoReplyContent(gocoReplyContent);
		goodsCommentService.updateGoodsComment(goodsComment);
		return "redirect:/admin/order/comment/list";
	}
	
	/**
	 * 改变测试状态
	 * 
	 * @param gocoId
	 * @return
	 */
	@RequestMapping(value = "/isShow/{gocoId}")
	@ResponseBody
	public Map<String, Object> isShow(@PathVariable("gocoId") Integer gocoId, Byte status){
		Map<String, Object> map = new HashMap<String, Object>();
		GoodsComment goodsComment = new GoodsComment();
		goodsComment.setGocoId(gocoId);
		if(status != null && status == OrderConstants.GOCO_SHOW_NOT){
			goodsComment.setGocoShowStatus(OrderConstants.GOCO_SHOW_YES);
			map.put("type", OrderConstants.GOCO_SHOW_YES);
		}else if(status != null && status == OrderConstants.GOCO_SHOW_YES){
			goodsComment.setGocoShowStatus(OrderConstants.GOCO_SHOW_NOT);
			map.put("type", OrderConstants.GOCO_SHOW_NOT);
		}
		
		int rows = goodsCommentService.updateGoodsComment(goodsComment);
		if(rows > 0){
			map.put("status", "1");
		}else{
			map.put("status", "-1");
		}
		
		return map;
	}

}
