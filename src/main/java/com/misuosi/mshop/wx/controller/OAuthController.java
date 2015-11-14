/*
 * Copyright (c) 2015 
 * 广州米所思信息科技有限公司(Guangzhou Misuosi Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.wx.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.sd4324530.fastweixin.api.OauthAPI;
import com.github.sd4324530.fastweixin.api.UserAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import com.github.sd4324530.fastweixin.api.response.OauthGetTokenResponse;
import com.misuosi.mshop.common.constants.WXConstant;
import com.misuosi.mshop.common.context.SessionContextHolder;
import com.misuosi.mshop.entity.WechatInformation;
import com.misuosi.mshop.service.WechatInformationService;
import com.misuosi.mshop.wx.ApiConfigFactory;

/**
 * Description		: 
 * <p/>
 * <br><br>Time		: 2015-8-8 上午10:40:04
 *
 * @author ZXL
 * @version 1.0
 * @since 1.0
 */
@Controller
public class OAuthController {

    @Autowired
    private WechatInformationService wechatInfoService;

    @RequestMapping("/oauth")
    public String oauth(String code, String state, HttpServletRequest request, HttpServletResponse response,HttpSession session) {
        //初始化apidConfig
        ApiConfig apiConfig = ApiConfigFactory.getApiConfig();

        OauthAPI oauthAPI = new OauthAPI(apiConfig);
        OauthGetTokenResponse oauthGetTokenResponse = oauthAPI.getToken(code);
        String open_id = oauthGetTokenResponse.getOpenid();
        
        session.setAttribute("openId", open_id);
        
        if (open_id == null) {
            return "/wap/408";
        }

        //如果数据库中没有数据，则把用户信息添加到数据库
        WechatInformation wechatInformation = wechatInfoService.getWechatInfoByOpenId(open_id);
        if (wechatInformation == null) {
            UserAPI userAPI = new UserAPI(apiConfig);
            GetUserInfoResponse getUserInfoResponse = userAPI.getUserInfo(open_id);

            if (getUserInfoResponse.getSubscribe() == 0) { // 未关注
                return "/wap/401";
            }

            wechatInfoService.addWechatInformation(getUserInfoResponse);
        }

        // 保存openId到Cookie
       /* String host = WXParamsManager.getDomain();
        Cookie cookie = new Cookie(WXConstant.MEMBER_OPEN_ID, memberInfo.getMeinWechatOpenid());
        cookie.setPath("/");
        cookie.setDomain(host);
        cookie.setMaxAge(99999999);
        response.addCookie(cookie);
*/
        WechatInformation wechatInfomation = wechatInfoService.getWechatInfoByOpenId(open_id);
        SessionContextHolder.setSettionAttribute(WXConstant.MEMBER_INFO_KEY, wechatInfomation);

        String redirectUrl = null;
        try {
            redirectUrl = URLDecoder.decode(state, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return redirectUrl;
    }

}
