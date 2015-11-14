/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.wap.home.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.misuosi.mshop.entity.IndexBanner;
import com.misuosi.mshop.entity.IndexGroup;
import com.misuosi.mshop.module.wap.home.service.WapIndexService;
import com.misuosi.mshop.service.IndexBannerService;

/**
 * Description	 :首页controller
 * <br><br>Time	 : 2015/6/9 9:36
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/wap/home/index")
public class WapIndexController {
	@Autowired
	public WapIndexService wapIndexService;
	@Autowired
	public IndexBannerService indexBannerService;
    /**
     * 首页
     * @return
     */
    @RequestMapping()
    public String index(Model model) {
    	List<IndexGroup> groups = wapIndexService.queryForIndexGroup();
    	List<IndexBanner> banner = indexBannerService.getAllIndexBanners();
    	model.addAttribute("groups",groups);
    	model.addAttribute("banner", banner);
        return "wap/home/index";
    }

}
