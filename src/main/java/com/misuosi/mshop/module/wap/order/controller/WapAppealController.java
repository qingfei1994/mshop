/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.wap.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.misuosi.mshop.common.context.SessionContextHolder;
import com.misuosi.mshop.entity.ClientAppeal;
import com.misuosi.mshop.entity.GoodsOrder;
import com.misuosi.mshop.entity.RefundInformation;
import com.misuosi.mshop.module.wap.order.service.WapAppealService;
import com.misuosi.mshop.service.ClientAppealService;
import com.misuosi.mshop.service.GoodsOrderService;
import com.misuosi.mshop.service.RefundInformationService;

/**
 * Description : 微信端维权页面的controller
 * <p/>
 * <br>
 * <br>
 * Time : 2015-6-10 下午8:08:12
 * 
 * @author YLM
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/wap/order/appeal")
public class WapAppealController {

	@Autowired
	private GoodsOrderService goodsOrderService;
	@Autowired
	private ClientAppealService clientAppealService;
	@Autowired
	private WapAppealService wapAppealService;
	@Autowired
	private RefundInformationService refundInformationService;

	/**
	 * 跳转到维权订单页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		Integer weinId = SessionContextHolder.getWechatInformationId();
		List<GoodsOrder> goodsOrders = goodsOrderService
				.getRefundGoodsOrdersByWeinId(weinId);
		model.addAttribute("goodsOrders", goodsOrders);
		return "/wap/order/appeal/appeal_list";
	}

	/**
	 * 撤销维权申请
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/close")
	@ResponseBody
	public Map<String, Object> close(Integer clapId, Model model) {
		ClientAppeal clientAppeal = clientAppealService.getClientAppeal(clapId);
		clientAppeal.setClapFlowStatus(Byte.valueOf("3"));
		int rows = clientAppealService.updateClientAppeal(clientAppeal);
		Map<String, Object> msg = new HashMap<String, Object>();
		if (rows < 0) {
			msg.put("success", 0);
		} else if (rows > 0) {
			msg.put("success", 1);
		}
		return msg;
	}

	/**
	 * 跳转到订单详情页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "{goorId}/detail", method = RequestMethod.GET)
	public String detail(@PathVariable("goorId") Integer goorId, Model model) {

		ClientAppeal clientAppeal = clientAppealService
				.getClientAppealByGoorId(goorId);
		Byte status = clientAppeal.getClapFlowStatus();
		model.addAttribute("flowStatus", status);
		Map<String, Object> states = new HashMap<String, Object>();
		RefundInformation refundInfo = refundInformationService
				.getRefundInformationByGoorId(goorId);
		String reason = null;
		// 如果状态为正在审核
		if (status == 0) {
			// 当前状态为正在审核的状态
			states.put("current", clientAppeal.getClapApplicationTime());

		} else if (status == 1 || status == 2) {
			// 若当前状态为已审核状态，则设置当前时间和申请时间
			states.put("current", refundInfo.getReinModifyTime());
			states.put("previous", clientAppeal.getClapApplicationTime());
			//若当前状态为拒绝
			reason=clientAppeal.getClapReplyContent();
			if (status == 2) {
				model.addAttribute("refundInfo", refundInfo);
			}
		} else if (status == 3) {
			//若当前状态为撤销状态，则获取撤销时间和申请时间
			states.put("current", clientAppeal.getClapCheckTime());
			states.put("previous", clientAppeal.getClapApplicationTime());
		}
		model.addAttribute("states", states);
		model.addAttribute("reason", reason);
		model.addAttribute("clientAppeal", clientAppeal);
		return "/wap/order/appeal/appeal_detail";
	}

	/**
	 * 跳转到申请维权（退款）页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/{goorId}/apply", method = RequestMethod.GET)
	public String toApply(@PathVariable("goorId") Integer goorId, Model model) {
		GoodsOrder goodsOrder = goodsOrderService.getGoodsOrder(goorId);
		ClientAppeal clientAppeal = clientAppealService
				.getClientAppealByGoorId(goorId);
		model.addAttribute("goodsOrder", goodsOrder);
		model.addAttribute("clientAppeal", clientAppeal);
		return "/wap/order/appeal/appeal_apply";
	}

	/**
	 * 申请维权（退款）
	 * 
	 * @param goorId
	 * @param clapReason
	 * @return
	 */
	@RequestMapping(value = "/apply", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> apply(Integer goorId,String clapReason) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			wapAppealService.apply(goorId, clapReason);
			map.put("success", 1);
			map.put("message", "申请成功");
		} catch (Exception e) {
			map.put("error", 0);
			map.put("message", "申请失败");
			e.printStackTrace();
		}
		return map;
	}

}