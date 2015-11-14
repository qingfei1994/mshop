/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.goods.controller;

import com.misuosi.mshop.common.context.RequestContextHolder;
import com.misuosi.mshop.entity.*;
import com.misuosi.mshop.module.admin.goods.service.AdminGoodsService;
import com.misuosi.mshop.module.common.controller.ErrorController;
import com.misuosi.mshop.module.common.manager.GoodsClassificationManager;
import com.misuosi.mshop.module.common.manager.GoodsLabelManager;
import com.misuosi.mshop.service.*;
import com.misuosi.mshop.util.NumberUtils;
import com.misuosi.mshop.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

/**
 * Description		: 商品管理
 * <p/>
 * <br><br>Time		: 2015/5/30 17:26
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/goods/")
public class AdminGoodsController {

    private static Logger log = LoggerFactory.getLogger(AdminGoodsController.class);

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private AdminGoodsService adminGoodsService;
    @Autowired
    private GoodsPriceStockService goodsPriceStockService;
    @Autowired
    private GoodsPictureService goodsPictureService;
    @Autowired
    private GoodsClassificationService goodsClassificationService;
    @Autowired
    private GoodsLabelService goodsLabelService;
    @Autowired
    private TransportationExpensesTemplateService transportationExpensesTemplateService;
    @Autowired
    private GoodsUnificationExpensesService goodsUnificationExpensesService;

    @RequestMapping("/list")
    public String list(Model model) {
        List<Goods> list = goodsService.getGoodsTree();
        for (Goods goods : list) {
            List<GoodsClassificationGoods> goodsClassificationGoodses = goods.getGoodsClassificationGoodses();
            if (goodsClassificationGoodses == null) {
                continue;
            }
            List<String> goclNameSets = new ArrayList<String>();
            for (GoodsClassificationGoods goodsClassificationGoods : goodsClassificationGoodses) {
                goclNameSets.add(GoodsClassificationManager.getGoodsClassificationNameSet(
                        goodsClassificationGoods.getGoclId()));
            }
            goods.setGoclNameSets(goclNameSets);
        }

        model.addAttribute("goodsClassifications",
                GoodsClassificationManager.getGoodsClassifications());
        model.addAttribute("list", list);
        return "/admin/goods/goods_list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String toAdd(Model model) {
        model.addAttribute("goodsClassifications",
                GoodsClassificationManager.getGoodsClassifications());
        model.addAttribute("goodsLabels", GoodsLabelManager.getGoodsLabels());
        model.addAttribute("transportationExpensesTemplates",
                transportationExpensesTemplateService.getAllTransportationExpensesTemplates());
        RequestContextHolder.handlePagingPath();
        return "/admin/goods/goods_edit";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(RedirectAttributes attr, Goods goods, GoodsPriceStock goodsPriceStock,
            String[] images, String goclIds, String golaIds,
            GoodsUnificationExpenses goodsUnificationExpenses) {
        Integer[] goclIdArr = null;
        if (!StringUtils.isBlank(goclIds)) {
            String[] temp = goclIds.split(",");
            goclIdArr = new Integer[temp.length];
            for (int i = 0; i < temp.length; i++) {
                goclIdArr[i] = Integer.parseInt(temp[i]);
            }
        }
        Integer[] golaIdArr = null;
        if (!StringUtils.isBlank(golaIds)) {
            String[] temp = golaIds.split(",");
            golaIdArr = new Integer[temp.length];
            for (int i = 0; i < temp.length; i++) {
                golaIdArr[i] = Integer.parseInt(temp[i]);
            }
        }

        Double gpstWeight = goodsPriceStock.getGpstWeight();
        Double gpstVolume = goodsPriceStock.getGpstVolume();
        goodsPriceStock.setGpstWeight(NumberUtils.rangeDouble(gpstWeight, 0D, 9999D));
        goodsPriceStock.setGpstVolume(NumberUtils.rangeDouble(gpstVolume, 0D, 9999D));

        if (goodsUnificationExpenses != null) {
            Double guexExpressPrice = goodsUnificationExpenses.getGuexExpressPrice();
            Double guexEmsPrice = goodsUnificationExpenses.getGuexEmsPrice();
            Double guexPostPrice = goodsUnificationExpenses.getGuexPostPrice();
            goodsUnificationExpenses.setGuexExpressPrice(NumberUtils.rangeDouble(guexExpressPrice, 0D, 999999D));
            goodsUnificationExpenses.setGuexEmsPrice(NumberUtils.rangeDouble(guexEmsPrice, 0D, 999999D));
            goodsUnificationExpenses.setGuexPostPrice(NumberUtils.rangeDouble(guexPostPrice, 0D, 999999D));
        }

        try {
            adminGoodsService.addGoods(goods, goodsPriceStock,
                    images, goclIdArr, golaIdArr, goodsUnificationExpenses);
        } catch (Exception e) {
            log.error("SupplierGoodsController.add throws Exception", e);
            attr.addFlashAttribute(ErrorController.MESSAGE, "保存失败");
            return ErrorController.ERROR;
        }

        return RequestContextHolder.getPagingPath();
    }

    @RequestMapping(value = "/{goodId}/edit", method = RequestMethod.GET)
    public String toEdit(@PathVariable("goodId")Integer goodId, Model model) {
        model.addAttribute("isEdit", true);

        Goods goods = goodsService.getGoods(goodId);
        List<GoodsPriceStock> goodsPriceStocks = goodsPriceStockService.getGoodsPriceStocksByGoodId(goodId);
        GoodsPriceStock goodsPriceStock = null;
        if (goodsPriceStocks != null && !goodsPriceStocks.isEmpty()) {
            goodsPriceStock = goodsPriceStocks.get(0);
        }
        model.addAttribute("goods", goods);
        model.addAttribute("goodsPriceStock", goodsPriceStock);
        model.addAttribute("goodsClassificationList", goodsClassificationService.getGoodsClassificationsByGoodId(goodId));
        model.addAttribute("goodsLabelList", goodsLabelService.getGoodsLabelsByGoodId(goodId));
        model.addAttribute("goodsUnificationExpenses",
                goodsUnificationExpensesService.getGoodsUnificationExpensesByGoodId(goodId));

        model.addAttribute("goodsClassifications", GoodsClassificationManager.getGoodsClassifications());
        model.addAttribute("goodsLabels", GoodsLabelManager.getGoodsLabels());
        RequestContextHolder.handlePagingPath();
        model.addAttribute("transportationExpensesTemplates",
                transportationExpensesTemplateService.getAllTransportationExpensesTemplates());
        return "/admin/goods/goods_edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(RedirectAttributes attr, Goods goods, GoodsPriceStock goodsPriceStock,
                      String[] images, String goclIds, String golaIds,
                      GoodsUnificationExpenses goodsUnificationExpenses) {
        Integer[] goclIdArr = null;
        if (!StringUtils.isBlank(goclIds)) {
            String[] temp = goclIds.split(",");
            goclIdArr = new Integer[temp.length];
            for (int i = 0; i < temp.length; i++) {
                goclIdArr[i] = Integer.parseInt(temp[i]);
            }
        }
        Integer[] golaIdArr = null;
        if (!StringUtils.isBlank(golaIds)) {
            String[] temp = golaIds.split(",");
            golaIdArr = new Integer[temp.length];
            for (int i = 0; i < temp.length; i++) {
                golaIdArr[i] = Integer.parseInt(temp[i]);
            }
        }

        Double gpstWeight = goodsPriceStock.getGpstWeight();
        Double gpstVolume = goodsPriceStock.getGpstVolume();
        goodsPriceStock.setGpstWeight(NumberUtils.rangeDouble(gpstWeight, 0D, 9999D));
        goodsPriceStock.setGpstVolume(NumberUtils.rangeDouble(gpstVolume, 0D, 9999D));

        if (goodsUnificationExpenses != null) {
            Double guexExpressPrice = goodsUnificationExpenses.getGuexExpressPrice();
            Double guexEmsPrice = goodsUnificationExpenses.getGuexEmsPrice();
            Double guexPostPrice = goodsUnificationExpenses.getGuexPostPrice();
            goodsUnificationExpenses.setGuexExpressPrice(NumberUtils.rangeDouble(guexExpressPrice, 0D, 999999D));
            goodsUnificationExpenses.setGuexEmsPrice(NumberUtils.rangeDouble(guexEmsPrice, 0D, 999999D));
            goodsUnificationExpenses.setGuexPostPrice(NumberUtils.rangeDouble(guexPostPrice, 0D, 999999D));
        }

        try {
            adminGoodsService.updateGoods(goods, goodsPriceStock,
                    images, goclIdArr, golaIdArr, goodsUnificationExpenses);
        } catch (Exception e) {
            log.error("SupplierGoodsController.edit throws Exception", e);
            attr.addFlashAttribute(ErrorController.MESSAGE, "保存失败");
            return ErrorController.ERROR;
        }

        return RequestContextHolder.getPagingPath();
    }

    @RequestMapping(value = "/{goodId}/detail")
    public String detail(@PathVariable("goodId")Integer goodId, Model model) {
        Goods goods = goodsService.getGoods(goodId);
        model.addAttribute("goods", goods);
        return "";
    }

    @RequestMapping(value = "/{goodId}/updatePutawayType")
    public String updatePutawayType(@PathVariable("goodId")Integer goodId, RedirectAttributes attr,
                                    Byte goodPutawayType) {
        try {
            Goods goods = new Goods();
            goods.setGoodId(goodId);
            goods.setGoodPutawayType(goodPutawayType);
            goodsService.updateGoods(goods);
        } catch (Exception e) {
            log.error("SupplierGoodsController.updatePutawayTime throws Exception", e);
            attr.addFlashAttribute(ErrorController.MESSAGE, "状态更改失败");
            return ErrorController.ERROR;
        }
        return RequestContextHolder.getPagingPath();
    }

    @RequestMapping(value = "/{goodId}/delete")
    public String delete(@PathVariable("goodId")Integer goodId, RedirectAttributes attr) {
        try {
            adminGoodsService.deleteGoods(goodId);
        } catch (Exception e) {
            log.error("SupplierGoodsController.delete throws Exception", e);
            attr.addFlashAttribute(ErrorController.MESSAGE, "删除失败");
            return ErrorController.ERROR;
        }
        return RequestContextHolder.getPagingPath();
    }

    @RequestMapping("/batchUpdatePutawayType")
    public String batchUpdatePutawayType(RedirectAttributes attr, Integer[] ids, Byte goodPutawayType) {
        try {
            goodsService.batchUpdateGoodPutAwayType(ids, goodPutawayType);
        } catch (Exception e) {
            log.error("SupplierGoodsController.batchUpdatePutawayType throws Exception", e);
            attr.addFlashAttribute(ErrorController.MESSAGE, "批量更改状态失败");
            return ErrorController.ERROR;
        }
        return RequestContextHolder.getPagingPath();
    }

    @RequestMapping("/batchDelete")
    public String batchDelete(RedirectAttributes attr, Integer[] ids) {
        try {
            adminGoodsService.batchDeleteGoods(ids);
        } catch (Exception e) {
            log.error("SupplierGoodsController.batchDelete throws Exception", e);
            attr.addFlashAttribute(ErrorController.MESSAGE, "批量删除失败");
            return ErrorController.ERROR;
        }
        return RequestContextHolder.getPagingPath();
    }

    @RequestMapping("/goodsPictures")
    public String getGoodsPictures(Model model, Integer goodId) {
        List<GoodsPicture> goodsPictures = goodsPictureService.getGoodsPicturesByGoodId(goodId);

        Map<String, String> picPaths = new HashMap<String, String>();
        for(GoodsPicture goodsPicture : goodsPictures) {
            String picPath = goodsPicture.getGopiUrl();
            picPath = picPath.substring(picPath.lastIndexOf("/") + 1);
            picPath = picPath.replaceAll("_", "/");
            int index = picPath.lastIndexOf("/");
            picPath = picPath.substring(0, index).concat(".").concat(picPath.substring(index + 1));
            picPaths.put(goodsPicture.getGopiId().toString(), picPath);
        }

        model.addAttribute("goodsPictures", goodsPictures);
        model.addAttribute("picPaths", picPaths);
        return "/admin/goods/goods_picture";
    }

}
