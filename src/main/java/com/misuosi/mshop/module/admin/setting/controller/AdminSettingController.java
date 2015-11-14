package com.misuosi.mshop.module.admin.setting.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.misuosi.mshop.common.context.SessionContextHolder;
import com.misuosi.mshop.entity.ShopInformation;
import com.misuosi.mshop.module.common.controller.ErrorController;
import com.misuosi.mshop.service.ShopInformationService;

/**
 * Description		: 基本信息
 * <p/>
 * <br><br>Time		: 2015/5/27 8:49
 *
 * @author baymax
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/setting")
public class AdminSettingController {
	@Autowired
	private ShopInformationService shopInformationService;
	
	@RequestMapping("/test")
    public String mytest(Model model){
	    String s1="中国建设银行";
	    String s2="中国人民银行";
	    String s3="中国农业银行";
	    String s4="中国工商银行";
	    String s5="招商银行";
	    String s6="民生银行";
	    String s7="汇丰银行";
	    String s8="浦发银行";
    	List list = new ArrayList();
    	List list1 = new ArrayList();
    	list.clear();
		list1.clear();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		list.add(s4);
		list.add(s5);
		list.add(s6);
		list.add(s7);
		list.add(s8);
		list1.add("人民币");
		list1.add("美元");
		list1.add("英镑");
		model.addAttribute("selectdetails", list);
		model.addAttribute("currency", list1);
		System.out.println("DDDDDDDDDDDDDDDDDDDDD");
		return "admin/details";
    }
	
	@RequestMapping("/save")
    public String save(RedirectAttributes attr, Model model, ShopInformation shopInformation){
		//shopInformation.setSuppId(8888);
		shopInformation.setShinLogo("http://hs-net-shop-img.oss-cn-hangzhou.aliyuncs.com/378685/Group/1501021639077751.jpg");
		int rows = shopInformationService.addShopInformation(shopInformation);
		if(rows > 0){
			return "redirect:/admin/setting/success";
		}else{
			return "redirect:/admin/setting/error";
		}
    }
	
	@RequestMapping("/success")
	public String saveSuccess(){
		return "admin/success";
	}
	@RequestMapping("/error")
	public String saveError(){
		return "admin/error";
	}
}
