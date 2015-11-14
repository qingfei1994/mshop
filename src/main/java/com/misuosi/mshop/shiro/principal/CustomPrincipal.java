/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.shiro.principal;

import java.io.Serializable;

/**
 * Description	 :
 *
 * 这个实体就是存储于session中的实体，主要存放用户信息。<br>
 * <b>如有需要添加用户信息到session中，则修改此类</b>
 * 页面使用方式<shiro:principal/><br>
 * 程序中获取：
 * <pre>
 * Subject currentUser = SecurityUtils.getSubject();
 * CustomPrincipal customPrincipal = (CustomPrincipal) currentUser.getPrincipals().getPrimaryPrincipal();
 *
 * <br><br>Time	 : 2015/5/27 17:28
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
public class CustomPrincipal implements Serializable {

    private Integer id;
    private String username;

    public CustomPrincipal(String username) {
        this.username = username;
    }

    public CustomPrincipal(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 本函数输出将作为默认的&lt;shiro:principal/&gt;输出.
     */
    @Override
    public String toString() {
        return username;
    }

}