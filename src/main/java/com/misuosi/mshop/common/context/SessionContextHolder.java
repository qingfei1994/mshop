/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.common.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.misuosi.mshop.common.constants.WXConstant;
import com.misuosi.mshop.entity.WechatInformation;
import com.misuosi.mshop.shiro.principal.CustomPrincipal;

/**
 * Description		: session上下文管理工具
 * <p/>
 * <br><br>Time		: 2015/4/15 20:02
 *
 * @author LXC
 * @version 1.0
 * @since 1.0
 */
public class SessionContextHolder {
	
    /**
     * 获取已登录管理员用户名
     * @return
     */
    public static String getAdminUsername() {
    	//从shiro中的SecurityUtils里面获取Subject,相当于从会话管理器中取，可通过currentUser来获取Session对象
        Subject currentUser = SecurityUtils.getSubject();
        CustomPrincipal customPrincipal = (CustomPrincipal) currentUser.getPrincipals().getPrimaryPrincipal();
        if (customPrincipal != null) {
            return customPrincipal.getUsername();
        }
        return null;
    }

    /**
     * 获取已登录管理员ID
     * @return
     */
    public static Integer getAdminId() {
        Subject currentUser = SecurityUtils.getSubject();
        CustomPrincipal customPrincipal = (CustomPrincipal) currentUser.getPrincipals().getPrimaryPrincipal();
        if (customPrincipal != null) {
            return customPrincipal.getId();
        }
        return null;
    }

    /**
     * 获取已登录的微信用户的微信信息ID
     * @return
     */
    public static Integer getWechatInformationId() {
    	int wechatInformationId = SessionContextHolder.getWechatInformation().getWeinId();
    	return  wechatInformationId;
    }
    
    public static void setSettionAttribute(String name, Object object) {
        if (object instanceof WechatInformation) {
        	WechatInformation wechatInformation = (WechatInformation) object;
        }

        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = req.getSession();
        session.setAttribute(name, object);
    }
    
    public static WechatInformation getWechatInformation() {
    	//先取得session里的实例，若无，则到COOKIE中取
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = req.getSession();
        Object wechatObject = session.getAttribute(WXConstant.MEMBER_INFO_KEY);
        if (wechatObject != null) {
        	WechatInformation wechatInformation = (WechatInformation) wechatObject;
            return wechatInformation;
        } /*else {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (WXConstant.MEMBER_OPEN_ID.equals(cookie.getName())) {
                        String memberOpenId = cookie.getValue(); // 得到cookie的用户名
                        MemberInfoService memberInfoService = ApplicationContextHolder.getBean("memberInfoService");
                        MemberInfo memberInfo = memberInfoService.getMemberInfoByOpenId(memberOpenId);
                        if (memberInfo != null) {
                            setSettionAttribute(WXConstant.MEMBER_INFO_KEY, memberInfo);
                        }
                    }
                }
            }
        }*/
        return null;
    }


}
