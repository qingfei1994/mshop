/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.logistics.controller;

import com.misuosi.mshop.common.constants.RegionalismConstant;
import com.misuosi.mshop.common.context.SessionContextHolder;
import com.misuosi.mshop.entity.Regionalism;
import com.misuosi.mshop.entity.TransportationExpenses;
import com.misuosi.mshop.entity.TransportationExpensesTemplate;
import com.misuosi.mshop.module.admin.logistics.service.AdminTransportationExpensesService;
import com.misuosi.mshop.pojo.TransportationAreaExpenses;
import com.misuosi.mshop.pojo.TransportationAreaExpensesArray;
import com.misuosi.mshop.service.AddressesService;
import com.misuosi.mshop.service.RegionalismService;
import com.misuosi.mshop.service.TransportationExpensesService;
import com.misuosi.mshop.service.TransportationExpensesTemplateService;
import com.misuosi.mshop.util.DateUtils;
import com.misuosi.mshop.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description :运费模板Controller.
 * <p/>
 * <br>
 * <br>
 * Time : 2015年6月1日 下午8:22:00
 *
 * @author HONG
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping(value = "/admin/logistics/transportation")
public class AdminTransportationExpensesController {

    @Autowired
    private TransportationExpensesTemplateService transportationExpensesTemplateService;
    @Autowired
    private TransportationExpensesService transportationExpensesService;
    @Autowired
    private AddressesService addressesService;
    @Autowired
    private RegionalismService regionalismService;

    @Autowired
    private AdminTransportationExpensesService adminTransportationExpensesService;

    /**
     * 查看运费列表.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Map<String, Object>> transportationExpensesTemplates = transportationExpensesTemplateService
                .getTransportationExpensesTemplates();
//		List<Map<String, Object>> transportationExpensess = transportationExpensesService
//				.getTransportationExpensesBySuppId(suppId);
        List<TransportationExpenses> trexs = new ArrayList<TransportationExpenses>();
        for (Map<String, Object> map : transportationExpensesTemplates) {
            Integer teteId = (Integer) map.get("tete_id");
            List<TransportationExpenses> transportationExpensess =
                    adminTransportationExpensesService.getTransportationExpensesByTeteId(teteId);
            trexs.addAll(transportationExpensess);
        }

        model.addAttribute("transportationExpensesTemplates",
                transportationExpensesTemplates);
        model.addAttribute("transportationExpensess", trexs);
        return "/admin/logistics/transportation_list";
    }

    /**
     * 跳转到添加运费模板的页面.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/toAddTransExpeTemplate", method = RequestMethod.GET)
    public String toAddTransExpeTemplate(Model model) {
        List<Map<String, Object>> addressess = addressesService.getAddresses();

        model.addAttribute("addressess", addressess);
        return "/admin/logistics/transportation_edit";
    }

    /**
     * 编辑运费模板
     *
     * @param teteId
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "/edit/{teteId}", method = RequestMethod.GET)
    public String edit(@PathVariable("teteId") Integer teteId, RedirectAttributes redirectAttributes) {
        TransportationExpensesTemplate transportationExpensesTemplate = transportationExpensesTemplateService
                .getTransportationExpensesTemplate(teteId);
        List<TransportationExpenses> transportationExpensess = adminTransportationExpensesService.getTransportationExpensesByTeteId(teteId);

        redirectAttributes.addFlashAttribute("transportationExpensesTemplate", transportationExpensesTemplate);
        redirectAttributes.addFlashAttribute("transportationExpensess", transportationExpensess);
        return "redirect:/admin/logistics/transportation/toAddTransExpeTemplate";
    }

    /**
     * 运费模板保存
     *
     * @param transportationExpensesTemplate
     * @param shippingMethod
     * @param transportationAreaExpensesArray
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(TransportationExpensesTemplate transportationExpensesTemplate,
                       Integer[] shippingMethod, TransportationAreaExpensesArray transportationAreaExpensesArray) {
    	Integer teteId=transportationExpensesTemplate.getTeteId();
    	if(teteId!=null) {
    		adminTransportationExpensesService.deleteTransportationExpensesByTeteId(teteId);
    	}
        List<Map<String, Object>> express = this
                .convertTransportationExpenses(transportationAreaExpensesArray.getExpress());
        List<Map<String, Object>> ems = this
                .convertTransportationExpenses(transportationAreaExpensesArray.getEms());
        List<Map<String, Object>> post = this
                .convertTransportationExpenses(transportationAreaExpensesArray.getPost());

        adminTransportationExpensesService.addTransportationExpensesTemplate(transportationExpensesTemplate, express, ems, post);

        return "redirect:/admin/logistics/transportation/list";
    }

    @RequestMapping(value = "/copy/{teteId}", method = RequestMethod.GET)
    public String copy(@PathVariable("teteId") Integer teteId) {
        TransportationExpensesTemplate transportationExpensesTemplate = transportationExpensesTemplateService
                .getTransportationExpensesTemplate(teteId);
        List<TransportationExpenses> transportationExpenses = transportationExpensesService.getTransportationExpensesByTeteId(teteId);

        int rows = adminTransportationExpensesService.addTransportationExpensesTemplate(transportationExpensesTemplate, transportationExpenses);
        return "redirect:/admin/logistics/transportation/list";
    }

    /**
     * 删除运费模板.
     *
     * @param teteId
     * @return
     */
    @RequestMapping(value = "/delete/{teteId}", method = RequestMethod.GET)
    public String delete(@PathVariable("teteId") Integer teteId) {
        adminTransportationExpensesService.deleteTransportationExpensesTemplateByTeteId(teteId);
        return "redirect:/admin/logistics/transportation/list";
    }

    /**
     * 获得省份
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/regionalism", method = RequestMethod.GET)
    public String regionalism(Model model, Integer[] regiIds) {
    	
    	List<Regionalism> selectedRegionalism=regionalismService.getRegionalismsByRegiIds(regiIds);
    	List<Regionalism> regionalisms = regionalismService.
                getRegionalismByRegiGrade(RegionalismConstant.RELI_GRADE_FIRST);
        List<Regionalism> secondRegionlisms = null;
        if (regionalisms != null && regionalisms.size() > 0) {
            secondRegionlisms = regionalismService
                    .getRegionalismByRegiParentId(regionalisms.get(0)
                            .getRegiId());
        }
        model.addAttribute("selectedRegionalism",selectedRegionalism);
        model.addAttribute("regionalisms", regionalisms);
        model.addAttribute("secondRegionlisms", secondRegionlisms);
        return "/admin/logistics/transportation_edit_model";
    }

    /**
     * 获得下级地区
     *
     * @param regiId 地区id
     * @return
     */
    @RequestMapping(value = "/childRegionalism", method = RequestMethod.GET)
    @ResponseBody
    public List<Regionalism> childRegionalism(Integer regiId) {
        List<Regionalism> regionalisms = regionalismService
                .getRegionalismByRegiParentId(regiId);
        return regionalisms;
    }

    private List<Map<String, Object>> convertTransportationExpenses(
            TransportationAreaExpenses[] trans) {
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();

        if (trans == null) {
            return null;
        }

        for (TransportationAreaExpenses tran : trans) {
            if (tran == null) {
                break;
            }
            Map<String, Object> map = new HashMap<String, Object>();
            List<Integer> regiIds = new ArrayList<Integer>();
            TransportationExpenses transportationExpenses = new TransportationExpenses();

            transportationExpenses
                    .setTrexCreateTime(DateUtils.getCurrentTime());
            transportationExpenses.setTrexStart(tran.getTrexStart());
            transportationExpenses.setTrexStartPrice(tran.getTrexStartPrice());
            transportationExpenses.setTrexPlus(tran.getTrexPlus());
            transportationExpenses.setTrexPlusPrice(tran.getTrexPlusPrice());
            if (StringUtils.equals("all", tran.getArea())) {
                transportationExpenses.setTrexAllRegion((byte) 1);
            } else {
                if (tran.getArea() != null) {
                    String[] ids = tran.getArea().split(",");
                    for (String id : ids) {
                        if (StringUtils.isNotEmpty(id) && StringUtils.isNumeric(id)) {
                            regiIds.add(Integer.parseInt(id));
                        }
                    }
                }
            }

            map.put("regiIds", regiIds);
            map.put("transportationExpenses", transportationExpenses);
            maps.add(map);
        }
        return maps;
    }
}
