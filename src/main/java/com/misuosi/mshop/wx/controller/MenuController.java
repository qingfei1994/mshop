/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.wx.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.sd4324530.fastweixin.api.MenuAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.Menu;
import com.github.sd4324530.fastweixin.api.entity.MenuButton;
import com.github.sd4324530.fastweixin.api.enums.MenuType;
import com.misuosi.mshop.wx.ApiConfigFactory;
import com.misuosi.mshop.wx.WXParamsManager;


/**
 * Description		: 
 * <p/>
 * <br><br>Time		: 2015-8-6 下午4:22:34
 *
 * @author ZXL
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/weixin/menu")
public class MenuController {
	
	@RequestMapping("/create")
	@ResponseBody
	public String create() {
		ApiConfig apiConfig = ApiConfigFactory.getApiConfig();
        MenuAPI menuAPI = new MenuAPI(apiConfig);
        Menu menu = new Menu();
        List<MenuButton> buttons = new ArrayList<MenuButton>();
        MenuButton btn = new MenuButton();
        btn.setType(MenuType.VIEW);
        btn.setName("微商城");
        btn.setUrl("http://zhanxiaoling1994.imwork.net/mshop/wap/home/index");
        //btn.setUrl(WXParamsManager.getWeixinQrCodeUrl());
        btn.setKey("111");
        buttons.add(btn);
        menu.setButton(buttons);
        menuAPI.createMenu(menu);
        
        return "success";
	}

}
