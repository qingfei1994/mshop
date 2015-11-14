/*
 * Copyright (c) 2014 
 * 广州米所思信息科技有限公司(Guangzhou Mythos Information technology co., LTD) 
 * All rights reserved.
 */
package com.misuosi.mshop.wx;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Properties;


/**
 * Description		: 微信参数配置管理工具
 * <br><br>Time		: 2015/4/26 16:44
 *
 * @author ICE
 * @version 1.0
 * @since 1.0
 */
public class WXParamsManager {
    private static Properties prop = null;

    static {
        prop = new Properties();
        InputStream in = ApiConfigFactory.class.getResourceAsStream("/wx-config.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getAppId() {
        return prop.getProperty("wx.appid");
    }

    public static String getAppSecrect() {
        return prop.getProperty("wx.appscrect");
    }

    public static String getToken() {
        return prop.getProperty("wx.token");
    }
    
    public static String getWeixinQrCodeUrl() {
    	
        return getUrlRoot() + "/getQrCode";
    }

    public static String getWeixinOAuthUrl(String param) {
        try {
            param = URLEncoder.encode(param, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String weixinLoginUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=" + getAppId() +
                "&redirect_uri=" + getUrlRoot() + "/oauth" +
                "&response_type=code" +
                "&scope=snsapi_base" +
                "&state=" + param +
                "#wechat_redirect";
        return weixinLoginUrl;
    }
    
    public static String getUrlRoot() {
    	return "http://zhanxiaoling1994.imwork.net/mshop";
    }

}
