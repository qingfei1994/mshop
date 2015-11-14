/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.module.admin.administrator.controller;

import com.misuosi.mshop.common.constants.UserConstants;
import com.misuosi.mshop.entity.Permission;
import com.misuosi.mshop.entity.Role;
import com.misuosi.mshop.module.common.controller.ErrorController;
import com.misuosi.mshop.service.PermissionService;
import com.misuosi.mshop.service.RoleService;
import com.misuosi.mshop.util.StringUtils;
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
 * Description	 :管理员角色管理
 * <br><br>Time	 : 2015/5/11 8:57
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
@Controller
@RequestMapping("/admin/role")
public class AdminRoleController {

    private static final Logger log = LoggerFactory.getLogger(AdminRoleController.class);

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    @RequiresPermissions("admin:role:view")
    @RequestMapping("/list")
    public String list(Model model) {
        List<Role> roles = roleService.getAllRoles(UserConstants.ADMINISTRATOR_TYPE);
        model.addAttribute("roles", roles);
        return "admin/administrator/role_list";
    }

    @RequiresPermissions("admin:role:save")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPage(Model model) {
        int root = UserConstants.ADMINISTRATOR_TYPE;
        Permission permission = permissionService.getPermission(root);
        model.addAttribute("permission", permission);
        return "admin/administrator/role_add";
    }

    @RequiresPermissions("admin:role:save")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(Integer[] permId, Role role, RedirectAttributes attr) {
        try {
            role.setRoleName(UserConstants.ADMINISTRATOR_STR + StringUtils.createNonceStr());
            role.setRoleType(UserConstants.ADMINISTRATOR_TYPE);
            roleService.addRole(role, permId);
        } catch (Exception e) {
            log.error("AdminRoleController throws Exception", e);
            attr.addFlashAttribute(ErrorController.MESSAGE, "添加失败！");
            return ErrorController.ERROR;
        }
        return "redirect:/admin/role/list";
    }

    @RequiresPermissions("admin:role:update")
    @RequestMapping(value = "/{roleId}/edit", method = RequestMethod.GET)
    public String editPage(@PathVariable("roleId")Integer roleId, Model model) {
        int root = UserConstants.ADMINISTRATOR_TYPE;
        Permission permission = permissionService.getPermission(root);
        model.addAttribute("permission", permission);

        List<Permission> rolePermissions = permissionService.getPermissions(roleId);
        model.addAttribute("rolePermissions", rolePermissions);

        Role role = roleService.getRole(roleId);
        model.addAttribute("role", role);
        return "admin/administrator/role_edit";
    }

    @RequiresPermissions("admin:role:update")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(Integer[] permId, Role role, RedirectAttributes attr) {
        try {
            roleService.updateRole(role, permId);
            return "redirect:/admin/role/list";
        } catch (Exception e) {
            log.error("AdminRoleController throws Exception", e);
            attr.addFlashAttribute(ErrorController.MESSAGE, "更新失败");
            return ErrorController.ERROR;
        }
    }

    @RequiresPermissions("admin:role:delete")
    @RequestMapping(value = "/{roleId}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("roleId")int roleId, RedirectAttributes attr) {
        try {
            roleService.deleteRole(roleId);
            return "redirect:/admin/role/list";
        } catch (Exception e) {
            log.error("AdminRoleController throws Exception", e);
            attr.addFlashAttribute(ErrorController.MESSAGE, "删除失败");
            return ErrorController.ERROR;
        }
    }

    @RequiresPermissions("admin:role:delete")
    @RequestMapping(value = "/batchDelete", method = RequestMethod.GET)
    public String batchDelete(Integer[] ids, RedirectAttributes attr) {
        try {
            roleService.batchDeleteRoles(ids);
            return "redirect:/admin/role/list";
        } catch (Exception e) {
            log.error("AdminRoleController throws Exception", e);
            attr.addFlashAttribute(ErrorController.MESSAGE, "删除失败");
            return ErrorController.ERROR;
        }
    }
}