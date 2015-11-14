/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.common.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.misuosi.mshop.common.constants.RegionalismConstant;
import com.misuosi.mshop.entity.Regionalism;
import com.misuosi.mshop.service.RegionalismService;

/**
 * Description		: 公共的controller
 * 
 * 
 * <br><br>Time		: 2015年5月20日 下午8:28:09
 * 
 * @version 1.0
 * 
 * @since 1.0
 * 
 * @author HONG
 */
@RequestMapping("/common/regionalism")
@Controller
public class RegionalismController {

	@Autowired
	private RegionalismService regionalismService;
	
	@RequestMapping("/toRegionalism")
	public String toRegionalism(Model model){
		List<Regionalism> regionalisms = regionalismService.getRegionalismByRegiGrade(RegionalismConstant.RELI_GRADE_FIRST);
		model.addAttribute("regionalisms", regionalisms);
		return "/common/_template/regionalism_select";
	}
	
	@RequestMapping("/initRegionalismList")
	@ResponseBody
	public List<Regionalism> initRegionalismList(){
		List<Regionalism> regionalisms = regionalismService.getRegionalismByRegiGrade(RegionalismConstant.RELI_GRADE_FIRST);
		return regionalisms;
	}

	@RequestMapping("/getRegionalismList")
	@ResponseBody
	public List<Regionalism> getRegionalismList(Integer regiId){
		List<Regionalism> regionalisms = regionalismService.getRegionalismByRegiParentId(regiId);
		
		return regionalisms;
	}
}
