/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.client.controller;

import com.misuosi.mshop.entity.MemberInformation;
import com.misuosi.mshop.service.MemberInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Description		: 会员卡设置
 * <p/>
 * <br><br>Time		: 2015-6-15 下午5:11:46
 *
 * @author ZXL
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/clients/wechatmembers")
public class AdminWechatMembersController {
	@Autowired
	private MemberInformationService memberInformationService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<MemberInformation> list = memberInformationService.getMemberInformations();
		model.addAttribute("list", list);
		return "/admin/clients/wechatmembers";
	}

}
