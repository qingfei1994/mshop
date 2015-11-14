/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.wx.interceptor;

import com.misuosi.mshop.common.context.SessionContextHolder;
import com.misuosi.mshop.entity.WechatInformation;
import com.misuosi.mshop.wx.WXParamsManager;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Description :
 * <p/>
 * <br>
 * <br>
 * Time : 2015-8-8 上午9:14:55
 * 
 * @author ZXL
 * @version 1.0
 * @since 1.0
 */
/*
 * Copyright (c) 2014 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co.,
 * LTD) All rights reserved.
 */

public class WeixinAuthorityIntercepter extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// 拿到微信用户的id
//		Integer wechatInformationId = SessionContextHolder.getWechatInformationId();
		Integer wechatInformationId = null;

		if (wechatInformationId == null) {
			String redirectUri = request.getRequestURI();
			int index = redirectUri.indexOf("/", 1);
			redirectUri = redirectUri.substring(index + 1);
			response.sendRedirect(WXParamsManager
					.getWeixinOAuthUrl(redirectUri));
			return false;
		}

		return true;
	}

}
