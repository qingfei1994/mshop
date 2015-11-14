/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.wap.goods.controller;

import com.misuosi.mshop.entity.GoodsClassification;
import com.misuosi.mshop.module.common.manager.GoodsClassificationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Description	 :商品分类controller
 * <br><br>Time	 : 2015/6/9 9:40
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/wap/goods/classify")
public class WapClassifyController {

    /**
     * 分类汇总列表
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String classify(Model model) {
        List<GoodsClassification> goodsClassificationList = GoodsClassificationManager.getGoodsClassifications();
        model.addAttribute("goodsClassificationList", goodsClassificationList);

        return "/wap/goods/classify";
    }
}
