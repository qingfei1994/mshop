/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.administrator.controller;

import com.misuosi.mshop.entity.Administrator;
import com.misuosi.mshop.entity.AdministratorRole;
import com.misuosi.mshop.entity.Role;
import com.misuosi.mshop.module.common.controller.ErrorController;
import com.misuosi.mshop.service.AdministratorRoleService;
import com.misuosi.mshop.service.AdministratorService;
import com.misuosi.mshop.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Description	 :管理员用户管理
 * <br><br>Time	 : 2015/5/24 21:21
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/administrator")
public class AdminAdministratorController {

    private static final Logger log = LoggerFactory.getLogger(AdminAdministratorController.class);

    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AdministratorRoleService administratorRoleService;

    @RequiresPermissions("admin:administrator:view")
    @RequestMapping("/list")
    public String list(Model model) {
        List<Administrator> administratorList = administratorService.getAllAdministrators();
        model.addAttribute("administratorList", administratorList);
        return "/admin/administrator/administrator_list";
    }

    @RequiresPermissions("admin:administrator:save")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(Model model) {
        List<Role> roles = roleService.getAdminRoles();
        model.addAttribute("roles", roles);
        return "/admin/administrator/administrator_add";
    }

    @RequiresPermissions("admin:administrator:save")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Administrator administrator, Integer[] roleId, RedirectAttributes attr) {
        String admiAccount = administrator.getAdmiAccount();
        Administrator checkAdministrator = administratorService.getAdministrator(admiAccount);
        if (checkAdministrator != null) {
            attr.addFlashAttribute(ErrorController.MESSAGE, "用户名已存在");
            return ErrorController.ERROR;
        }

        try {
            administratorService.addAdministrator(administrator, roleId);
            return "redirect:/admin/administrator/list";
        } catch (Exception e) {
            log.error("AdminAdministratorController throws Exception", e);
            attr.addFlashAttribute(ErrorController.MESSAGE, "添加失败");
            return ErrorController.ERROR;
        }
    }

    @RequiresPermissions("admin:administrator:update")
    @RequestMapping(value = "/{admiId}/edit", method = RequestMethod.GET)
    public String editPage(@PathVariable("admiId")Integer admiId, Model model) {
        Administrator administrator = administratorService.getAdministrator(admiId);
        model.addAttribute("administrator", administrator);

        List<Role> roles = roleService.getAdminRoles();
        model.addAttribute("roles", roles);

        List<AdministratorRole> administratorRoles = administratorRoleService.getAdministratorRoles(admiId);
        model.addAttribute("administratorRoles", administratorRoles);

        return "/admin/administrator/administrator_edit";
    }

    @RequiresPermissions("admin:administrator:update")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Administrator administrator, Integer[] roleId, RedirectAttributes attr) {
        try {
            administratorService.updateAdministrator(administrator, roleId);
            return "redirect:/admin/administrator/list";
        } catch (Exception e) {
            log.error("AdminAdministratorController throws Exception", e);
            attr.addFlashAttribute(ErrorController.MESSAGE, "修改失败");
            return ErrorController.ERROR;
        }
    }

    @RequiresPermissions("admin:administrator:delete")
    @RequestMapping(value = "/{admiId}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("admiId")Integer admiId, RedirectAttributes attr) {
        try {
            administratorService.deleteAdministrator(admiId);
            return "redirect:/admin/administrator/list";
        } catch (Exception e) {
            log.error("AdminAdministratorController throws Exception", e);
            attr.addFlashAttribute(ErrorController.MESSAGE, "删除失败");
            return ErrorController.ERROR;
        }
    }

    @RequiresPermissions("admin:administrator:delete")
    @RequestMapping(value = "/batchDelete", method = RequestMethod.GET)
    public String batchDelete(Integer[] ids, RedirectAttributes attr) {
        try {
            administratorService.batchDeleteAdministrators(ids);
            return "redirect:/admin/administrator/list";
        } catch (Exception e) {
            log.error("AdminAdministratorController throws Exception", e);
            attr.addFlashAttribute(ErrorController.MESSAGE, "删除失败");
            return ErrorController.ERROR;
        }
    }

}
