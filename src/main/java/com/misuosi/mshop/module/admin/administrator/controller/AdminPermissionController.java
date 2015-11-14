/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.administrator.controller;

import com.misuosi.mshop.common.constants.UserConstants;
import com.misuosi.mshop.entity.Permission;
import com.misuosi.mshop.module.common.controller.ErrorController;
import com.misuosi.mshop.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description	 :管理管理员的权限操作Controller
 * <br><br>Time	 : 2015/5/11 8:58
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequiresRoles("admin")
@RequestMapping("/admin/permission")
public class AdminPermissionController {

    private static Logger log = LoggerFactory.getLogger(AdminPermissionController.class);

    @Resource(name = "permissionService")
    private PermissionService permissionService;

    @RequiresPermissions("admin:permission:view")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        List<Permission> permissions = permissionService.getAllPermissions(UserConstants.ADMINISTRATOR_TYPE);
        model.addAttribute("permissions", permissions);
        return "admin/administrator/permission_list";
    }

    @RequiresPermissions("admin:permission:save")
    @RequestMapping(value = "/{permParentId}/add", method = RequestMethod.GET)
    public String addPage(@PathVariable("permParentId")Integer permParentId, Model model) {
        Permission parentPermission = permissionService.getPermission(permParentId);
        model.addAttribute("parentPermission", parentPermission);
        return "admin/administrator/permission_edit";
    }

    @RequiresPermissions("admin:permission:save")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Permission permission, RedirectAttributes attr){
        try {
            permission.setPermType(UserConstants.ADMINISTRATOR_TYPE);
            permissionService.addPermission(permission);
        } catch (Exception e) {
            log.error("AdminPermissionController throws Exception", e);
            attr.addFlashAttribute(ErrorController.MESSAGE, "添加失败");
            return ErrorController.ERROR;
        }
        return "redirect:/admin/permission/list";
    }

    @RequiresPermissions("admin:permission:update")
    @RequestMapping(value = "/{permId}/edit",method = RequestMethod.GET)
    public String editPage(@PathVariable("permId")Integer permId, Model model) {
        Permission permission = permissionService.getPermission(permId);
        model.addAttribute("permission", permission);
        if(permission.getPermParentId() != null) {
            Permission parentPermission = permissionService.getPermission(permission.getPermParentId());
            model.addAttribute("parentPermission", parentPermission);
        }
        return "admin/administrator/permission_edit";
    }

    @RequiresPermissions("admin:permission:update")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Permission permission, RedirectAttributes attr){
        try {
            permissionService.updatePermission(permission);
        } catch (Exception e) {
            log.error("AdminPermissionController throws Exception", e);
            attr.addFlashAttribute(ErrorController.MESSAGE, "更新失败");
            return ErrorController.ERROR;
        }
        return "redirect:/admin/permission/list";
    }

    @RequiresPermissions("admin:permission:delete")
    @RequestMapping(value = "/{permId}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("permId")Integer permId, RedirectAttributes attr) {
        try {
            permissionService.deletePermission(permId);
        } catch (Exception e) {
            log.error("AdminPermissionController throws Exception", e);
            attr.addFlashAttribute(ErrorController.MESSAGE, "删除失败");
            return ErrorController.ERROR;
        }
        return "redirect:/admin/permission/list";
    }
}
