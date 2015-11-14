/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.shiro.realm;

import com.misuosi.mshop.common.constants.UserConstants;
import com.misuosi.mshop.entity.Administrator;
import com.misuosi.mshop.service.AdministratorService;
import com.misuosi.mshop.shiro.principal.CustomPrincipal;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.Set;

/**
 * Description	 : shiro校对管理员用户名密码的Realm
 * <br><br>Time	 : 2015/5/22 14:24
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
public class AdministratorRealm extends AuthorizingRealm {

    private AdministratorService administratorService;

    public AdministratorService getAdministratorService() {
        return administratorService;
    }

    public void setAdministratorService(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取登录用户的信息
        CustomPrincipal customPrincipal = (CustomPrincipal) principals.getPrimaryPrincipal();
        String username = customPrincipal.getUsername();

        //给已经登录的用户分配角色
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        Set<String> roles = administratorService.getRoles(username);
        roles.add(UserConstants.ADMINISTRATOR_STR);
        authorizationInfo.setRoles(roles);

        //给已经登录的用户分配权限
        authorizationInfo.setStringPermissions(administratorService.getPermissions(username));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //获取登录用户的信息
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        //从数据库中查找用户信息
        String username = (String) usernamePasswordToken.getPrincipal();
        Administrator administrator = administratorService.getAdministrator(username);

        if (administrator == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        if (Boolean.FALSE.equals(administrator.getAdmiStatus())) {
            throw new LockedAccountException(); //帐号锁定
        }

        CustomPrincipal principal = new CustomPrincipal(administrator.getAdmiId(), administrator.getAdmiAccount());

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                principal, //用户名
                administrator.getAdmiPassword(), //密码
                ByteSource.Util.bytes(administrator.getCredentialsSalt()),//salt=username+salt
                getName()  //realm name
        );
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    /**
     * 清除缓存
     */
    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
