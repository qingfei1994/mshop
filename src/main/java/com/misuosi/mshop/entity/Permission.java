/*
 * Copyright (c) 2014
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD)
 * All rights reserved.
 */
package com.misuosi.mshop.entity;

import com.misuosi.mshop.common.context.ApplicationContextHolder;
import com.misuosi.mshop.common.context.SessionContextHolder;
import com.misuosi.mshop.db.Entity;
import com.misuosi.mshop.db.annotation.*;
import com.misuosi.mshop.service.PermissionService;

import java.sql.Timestamp;
import java.util.List;

/**
 * Description	 : 实体类 Permission
 * <br><br>Time	 : 2015/07/14 08:46
 *
 * @author GenEntity
 * @version 1.0
 * @since 1.0
 */

public class Permission extends Entity {

	@Id
	private Integer permId;
	private String permName;
	private String permPermission;
	private String permDescription;
	private String permUrl;
	private String permIcon;
	private Boolean permStatus;
	private Byte permSort;
	private Byte permScope;
	private String permParentTree;
	private Byte permType;
	private Timestamp permModifyTime;
	private Timestamp permCreateTime;
	private Integer permParentId;

	@OneToMany(table = "Permission", foreignKey = "permParentId")
	@Orders({@Order(key = "permSort", order = Order.ASC),
			@Order(key = "permId", order = Order.DESC)})
	private List<Permission> permissions;

	public Integer getPermId() {
		return permId;
	}

	public void setPermId(Integer permId) {
		this.permId = permId;
	}

	public String getPermName() {
		return permName;
	}

	public void setPermName(String permName) {
		this.permName = permName;
	}

	public String getPermPermission() {
		return permPermission;
	}

	public void setPermPermission(String permPermission) {
		this.permPermission = permPermission;
	}

	public String getPermDescription() {
		return permDescription;
	}

	public void setPermDescription(String permDescription) {
		this.permDescription = permDescription;
	}

	public String getPermUrl() {
		return permUrl;
	}

	public void setPermUrl(String permUrl) {
		this.permUrl = permUrl;
	}

	public String getPermIcon() {
		return permIcon;
	}

	public void setPermIcon(String permIcon) {
		this.permIcon = permIcon;
	}

	public Boolean getPermStatus() {
		return permStatus;
	}

	public void setPermStatus(Boolean permStatus) {
		this.permStatus = permStatus;
	}

	public Byte getPermSort() {
		return permSort;
	}

	public void setPermSort(Byte permSort) {
		this.permSort = permSort;
	}

	public Byte getPermScope() {
		return permScope;
	}

	public void setPermScope(Byte permScope) {
		this.permScope = permScope;
	}

	public String getPermParentTree() {
		return permParentTree;
	}

	public void setPermParentTree(String permParentTree) {
		this.permParentTree = permParentTree;
	}

	public Byte getPermType() {
		return permType;
	}

	public void setPermType(Byte permType) {
		this.permType = permType;
	}

	public Timestamp getPermModifyTime() {
		return permModifyTime;
	}

	public void setPermModifyTime(Timestamp permModifyTime) {
		this.permModifyTime = permModifyTime;
	}

	public Timestamp getPermCreateTime() {
		return permCreateTime;
	}

	public void setPermCreateTime(Timestamp permCreateTime) {
		this.permCreateTime = permCreateTime;
	}

	public Integer getPermParentId() {
		return permParentId;
	}

	public void setPermParentId(Integer permParentId) {
		this.permParentId = permParentId;
	}

	public String makeSelfAsParentTree() {
		return getPermParentTree() + getPermId() + "/";
	}

	/**
	 * 获取所有子权限
	 * @return
	 */
	public List<Permission> getSubPermissions() {
		PermissionService permissionService = ApplicationContextHolder.getBean("permissionService");
		List<Permission> subPermissions = permissionService.getSubPermissions(getPermId());
		return subPermissions;
	}

	/**
	 * 获取所有启用的子权限
	 * @return
	 */
	public List<Permission> getAvailableSubPermissions() {
		PermissionService permissionService = ApplicationContextHolder.getBean("permissionService");
		List<Permission> subPermissions = permissionService.getAvailableSubPermissions(getPermId());
		return subPermissions;
	}

	public List<Permission> getAdminAvailableSubPermissions() {
		int adminId = SessionContextHolder.getAdminId();
		PermissionService permissionService = ApplicationContextHolder.getBean("permissionService");
		List<Permission> subPermissions =  permissionService.getAdminAvailableSubPermissions(adminId, getPermId());
		return subPermissions;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}

