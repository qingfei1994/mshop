/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.wap.usercenter.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.misuosi.mshop.common.context.SessionContextHolder;
import com.misuosi.mshop.entity.MemberInformation;
import com.misuosi.mshop.entity.WechatInformation;
import com.misuosi.mshop.module.wap.usercenter.service.WapUserCenterService;
import com.misuosi.mshop.service.ConsigneesAddressService;
import com.misuosi.mshop.service.MemberInformationService;
import com.misuosi.mshop.service.WechatInformationService;

/**
 * Description		: 微信端用户中心页面的controller
 * <p/>
 * <br><br>Time		: 2015-6-1 下午6:08:12
 *
 * @author YLM
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/wap/usercenter")
public class WapUserCenterController {

	@Autowired
	WechatInformationService wechatInformationService;
	@Autowired
	ConsigneesAddressService consigneesAddressService;
	@Autowired
	MemberInformationService memberInformationService;
	@Autowired
	WapUserCenterService wapUserCenterService;
	
	/**
	 * 跳转到用户中心页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String userCenter(Model model,HttpSession session) {
		int weinId = SessionContextHolder.getWechatInformationId();
		WechatInformation wechatInformation = wechatInformationService.getWechatInformation(weinId);
		MemberInformation memberInformation = memberInformationService.getMemberInformationByWeinId(weinId);
		Map<String, Object> orderStatistic = wapUserCenterService.getOrderStatisticByWeinId(weinId);
		model.addAttribute("wechat", wechatInformation);
		model.addAttribute("member", memberInformation);
		model.addAttribute("orderStatistic", orderStatistic);
		return "/wap/usercenter/user_center";
	}
	
	/**
	 * 跳转到个人信息页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/personalDetail", method = RequestMethod.GET)
	public String personalDetail(Model model,HttpSession session) {
		WechatInformation wechatInformation = wechatInformationService.getWechatInformation(SessionContextHolder.getWechatInformationId());
		int consigneesAddressCount = consigneesAddressService.getAllConsigneesAddresssCountByWeinId(SessionContextHolder.getWechatInformationId());
		model.addAttribute("wechat", wechatInformation);
		model.addAttribute("addressCount", consigneesAddressCount);
		return "/wap/usercenter/user/personal_detail";
	}
	
	/**
	 * 保存修改的昵称
	 * @param nickname
	 * @return
	 */
	@RequestMapping(value = "/nickname/save", method = RequestMethod.POST)
	@ResponseBody
	public String saveNickname(String nickname,HttpSession session) {
		WechatInformation wechatInformation = new WechatInformation();
		wechatInformation.setWeinId(SessionContextHolder.getWechatInformationId());
		wechatInformation.setWeinNickname(nickname);
		if(wechatInformationService.updateWechatInformation(wechatInformation) > 0) {
			return "success";
		} else {
			return "fail";
		}
	}
	
}